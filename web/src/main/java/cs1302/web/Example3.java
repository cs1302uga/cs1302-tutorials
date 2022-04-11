package cs1302.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Example 3: Get result from iTunes Search API.
 * <pre>
 * $ ./compile cs1302.web/cs1302.web.Example3
 * </pre>
 */
public class Example3 {

    /**
     * Represents a response from the iTunes Search API. This is used by Gson to
     * create an object from the JSON response body.
     */
    private static class ItunesResponse {
        int resultCount;
        ItunesResult[] results;
    } // ItunesResponse

    /**
     * Represents a result in a response from the iTunes Search API. This is
     * used by Gson to create an object from the JSON response body.
     */
    private static class ItunesResult {
        String wrapperType;
        String kind;
        String artworkUrl100;
    } // ItunesResult

    private static HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns an HttpClient

    private static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    private static final String ITUNES_API = "https://itunes.apple.com/search";

    public static void main(String[] args) {
        try {
            String term = URLEncoder.encode("daft punk", StandardCharsets.UTF_8);
            String limit = URLEncoder.encode("5", StandardCharsets.UTF_8);
            String query = String.format("?term=%s&limit=%s", term, limit);
            String url = ITUNES_API + query;
            String responseBody = Example3.fetchString(url);
            Example3.parseItunesResponse(responseBody);
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
     * Parse the JSON string using Gson, then print some information.
     * @param responseBody the JSON response body to parse
     */
    private static void parseItunesResponse(String responseBody) {
        ItunesResponse response = GSON.fromJson(responseBody, Example3.ItunesResponse.class);
        System.out.println("********** RAW JSON STRING: **********");
        System.out.println(responseBody);
        System.out.println();
        System.out.println("********** PRETTY JSON STRING: **********");
        System.out.println(GSON.toJson(response));
        System.out.println();
        System.out.println("********** PARSED RESULTS: **********");
        System.out.printf("resultCount = %s\n", response.resultCount);
        for (int i = 0; i < response.results.length; i++) {
            System.out.printf("response.results[%d]:\n", i);
            ItunesResult result = response.results[i];
            System.out.printf(" - wrapperType = %s\n", result.wrapperType);
            System.out.printf(" - kind = %s\n", result.kind);
            System.out.printf(" - artworkUrl100 = %s\n", result.artworkUrl100);
        } // for
    } // parseItunesResponse

    /**
     * Returns string from a URL.
     * @param url content location
     * @return content as string
     * @throws IOException if an I/O error occurs when sending, receiving, or parsing
     * @throws InterruptedException if the HTTP client's {@code send} method is
     *    interrupted
     */
    private static String fetchString(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        System.out.printf("request = %s; headers = %s\n", request, request.headers());
        HttpResponse<String> response = HTTP_CLIENT
            .send(request, BodyHandlers.ofString());
        System.out.printf("response = %s; headers = %s\n", response, response.headers());
        Example3.ensureGoodResponse(response);
        return response.body();
    } // fetchLicense

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

} // Example3
