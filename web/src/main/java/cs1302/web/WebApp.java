package cs1302.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Driver class for the {@code cs1302-web} tutorial.
 */
public class WebApp extends Application {

    private static final String DEFAULT_IMAGE_URL =
        "file:resources/default.png";

    private static final String DEFAULT_PROMPT_TEXT =
        "https://deelay.me/5000/https://picsum.photos/200/300";

    private static final HttpClient HTTP_CLIENT = HttpClient
        .newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    private Scene scene;
    private Stage stage;
    private BorderPane pane;
    private UrlToolBar urlToolBar;
    private ImageView imageView;

    /**
     * Construct a {@code WebApp} object.
     */
    public WebApp() {
        this.scene = null;
        this.stage = null;
        this.pane = new BorderPane();
        this.urlToolBar = new UrlToolBar(WebApp.DEFAULT_PROMPT_TEXT, "Load");
        this.imageView = new ImageView(WebApp.DEFAULT_IMAGE_URL);
    } // WebApp

    /** {@inheritDoc} */
    @Override
    public void init() {
        this.pane.setTop(this.urlToolBar);
        this.pane.setCenter(this.imageView);
        this.urlToolBar.getButton().setOnAction(event -> {
            Runnable loadImageTask = () -> loadImage();
            runOnNewThread(loadImageTask);
        });
    } // init

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(this.pane);
        this.stage.setOnCloseRequest(event -> Platform.exit());
        this.stage.setTitle("cs1302-web");
        this.stage.setScene(this.scene);
        this.stage.sizeToScene();
        this.stage.show();
    } // start

    /**
     * Build and return an HTTP request for the given URI.
     * @param requestUri the request URI
     * @return HTTP request
     */
    private static HttpRequest buildHttpRequest(URI requestUri) {
        return HttpRequest
            .newBuilder()
            .uri(requestUri)
            .build();
    } // buildHttpRequest

    /**
     * Send an HTTP request and return its HTTP response.
     * @param <T> response body type
     * @param request HTTP request
     * @param handler a body handler
     * @return HTTP response
     */
    private static <T> HttpResponse<T> sendHttpRequest(HttpRequest request, BodyHandler<T> handler)
        throws IOException, InterruptedException {
        return HTTP_CLIENT.send(request, handler);
    } // sendHttpRequest

    /**
     * Load an image based on user input.
     */
    private void loadImage() {
        try {
            WebApp.ensureFxThread(false);
            this.urlToolBar.disableButton();
            URI imageUri = this.urlToolBar.getUri();
            HttpRequest request = WebApp.buildHttpRequest(imageUri);
            BodyHandler<InputStream> bodyHandler = BodyHandlers.ofInputStream();
            HttpResponse<InputStream> response = WebApp.sendHttpRequest(request, bodyHandler);
            WebApp.ensureGoodResponse(response);
            InputStream imageStream = response.body();
            Image newImage = new Image(imageStream);
            WebApp.ensureGoodImage(newImage);
            this.updateImage(newImage);
        } catch (IOException | InterruptedException e) {
            // either:
            // 1. an I/O error occurred when sending or receiving;
            // 2. the operation was interrupted; or
            // 3. the Image class could not load the image.
            System.err.println(e);
            e.printStackTrace();
        } finally {
            this.urlToolBar.enableButton();
        } // try
    } // loadImage

    /**
     * Update the image that is displayed in the main content area.
     * @param image image to display
     */
    private void updateImage(Image image) {
        // need to run on the JFXAT since this modifies the scene graph
        WebApp.runOnFxThread(() -> this.imageView.setImage(image));
    } // updateImage

    /**
     * Throw an {@link java.io.IOException} if the HTTP status code of the
     * {@link java.net.http.HttpResponse} supplied by {@code response} is
     * not {@code 200 OK}.
     * @param <T> response body type
     * @param response response to check
     * @see <a href="https://httpwg.org/specs/rfc7231.html#status.200">[RFC7232] 200 OK</a>
     */
    private static <T> void ensureGoodResponse(HttpResponse<T> response) throws IOException {
        if (response.statusCode() != 200) {
            throw new IOException(response.toString());
        } // if
    } // ensureGoodResponse

    /**
     * Throw an {@link java.io.IOException} if an error was detected while loading
     * the supplied {@code image}.
     * @param image image to check
     */
    private static void ensureGoodImage(Image image) throws IOException {
        if (image.isError()) {
            Throwable cause = image.getException();
            throw new IOException(cause);
        } // if
    } // ensureGoodImage

    /**
     * Throw a {@link java.lang.InterruptedException} if not called on the
     * JavaFX Application Thread (JFXAT).
     * @param isFxThread indicates whether the caller requires itself to be
     *    executing on the JFXAT.
     */
    private static void ensureFxThread(boolean isFxThread) throws InterruptedException {
        boolean isFxApplicationThread = Platform.isFxApplicationThread();
        if (isFxApplicationThread != isFxThread) {
            String message = String.format(
                "ensureFxThread(isFxThread = %s), but Platform.isFxApplicationThread() = %s",
                isFxThread,
                isFxApplicationThread);
            throw new InterruptedException(message);
        } // if
    } // ensureFxThread

    /**
     * Run the supplied <em>scene task</em> (i.e., {@code sceneTask.run()}) on
     * the JavaFX Application Thread (JFXAT). If this method is called from the
     * JFXAT, then the scene task is executed immediately; otherwise the
     * {@link javafx.application.Platform#runLater Platform.runLater} method is
     * used to ensure the scene task executes on the JFXAT at some unspecified
     * time in the future
     * @param sceneTask task that manipulates the scene graph
     */
    private static void runOnFxThread(Runnable sceneTask) {
        if (Platform.isFxApplicationThread()) {
            sceneTask.run();
        } else {
            Platform.runLater(sceneTask);
        } // if
    } // runOnFxThread

    /**
     * Run the supplied <em>task</em> (i.e., {@code task.run()}) on a new daemon
     * thread. This method always returns immediately to its caller.
     * @param task task to perform
     */
    private static void runOnNewThread(Runnable task) {
        Thread taskThread = new Thread(task);
        taskThread.setDaemon(true);
        taskThread.start();
    } // runOnNewThread

} // WebApp
