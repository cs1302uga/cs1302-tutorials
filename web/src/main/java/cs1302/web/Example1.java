package cs1302.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;

import javafx.scene.image.Image;

/**
 * Example 1: Request data for a JavaFX {@link javafx.scene.image.Image} object.
 * <pre>
 * $ ./compile cs1302.web/cs1302.web.Example1
 * </pre>
 */
public class Example1 {

    private static HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns an HttpClient

    public static void main(String[] args) {
        try {
            String url = "http://csweb.cs.uga.edu/~mec/cs1302/gui/pikachu.png";
            Image image = Example1.fetchImage(url);
            System.out.printf("image width = %s\n", image.getWidth());
            System.out.printf("image height = %s\n", image.getHeight());
        } catch (IOException | InterruptedException e) {
            // either:
            // 1. an I/O error occurred when sending or receiving;
            // 2. the operation was interrupted; or
            // 3. the Image class could not load the image.
            System.err.println(e);
            e.printStackTrace();
        } // try
    } // main

    /**
     * Returns a {@link javafx.image.Image} using data located at the specified
     * {@code url}. Instead of letting the {@code Image} class handle the
     * download, this method uses the {@link java.net.http/java.net.http}
     * package instead.
     * @param url image location
     * @return an image
     * @throws IOException if an I/O error occurs when sending, receiving, or parsing
     * @throws InterruptedException if the HTTP client's {@code send} method is
     *    interrupted
     */
    private static Image fetchImage(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        System.out.printf("request = %s\n", request);
        HttpResponse<InputStream> response = HTTP_CLIENT
            .send(request, BodyHandlers.ofInputStream());
        System.out.printf("response = %s\n", response);
        Example1.ensureGoodResponse(response);
        InputStream imageStream = response.body();
        Image newImage = new Image(imageStream);
        Example1.ensureGoodImage(newImage);
        return newImage;
    } // fetchImage

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

} // Example1
