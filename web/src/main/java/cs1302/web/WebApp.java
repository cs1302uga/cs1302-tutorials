package cs1302.web;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Driver class for the {@code cs1302-web} tutorial.
 */
public class WebApp extends Application {

    private HttpClient httpClient;
    private Scene scene;
    private Stage stage;
    private BorderPane pane;

    /**
     * Construct a {@code WebApp} object.
     */
    public WebApp() {
        this.httpClient = HttpClient.newHttpClient();
        this.scene = null;
        this.stage = null;
        this.pane = new BorderPane();
    } // WebApp

    /** {@inheritDoc} */
    @Override
    public void init() {
    } // init

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(this.pane);
        this.stage.setOnCloseRequest(event -> Platform.exit());
        this.stage.setTitle("cs1302-web");
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        this.stage.show();
    } // start

} // WebApp
