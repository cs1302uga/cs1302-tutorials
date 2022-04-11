package cs1302.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.util.Scanner;

import javafx.scene.image.Image;

/**
 * Example 0: Request text content hosted on a website.
 * <pre>
 * $ ./compile cs1302.web/cs1302.web.Example0
 * </pre>
 */
public class Example0 {

    private static HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns an HttpClient

    public static void main(String[] args) {
        // The Adventures of Sherlock Holmes by Arthur Conan Doyle (Plain Text UTF-8)
        // See: https://www.gutenberg.org/ebooks/1661
        String url = "https://www.gutenberg.org/files/1661/1661-0.txt";
        try {
            HttpRequest request = HttpRequest.newBuilder()  // build request
                .uri(URI.create(url))
                .build();
            HttpResponse<String> response = HTTP_CLIENT     // send request
                .send(request, BodyHandlers.ofString());
            if (response.statusCode() != 200) {             // check response
                throw new IOException(response.toString());
            } // if
            String responseBody = response.body().trim();   // get response body
            System.out.println(responseBody);               // print response body
        } catch (IOException | InterruptedException e) {
            // either:
            // 1. an I/O error occurred when sending or receiving;
            // 2. the operation was interrupted; or
            // 3. the Image class could not load the image.
            System.err.println(e);
            e.printStackTrace();
        } // try
    } // main


} // Example1
