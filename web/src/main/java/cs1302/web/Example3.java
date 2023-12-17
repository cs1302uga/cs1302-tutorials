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
     *
     * <pre>
     * {
     *   "resultCount": 3,
     *   "results": [
     *     ItunesResult object,
     *     ItunesResult object,
     *     ItunesResult object
     *   ]
     * }
     * </pre>
     */
    private static class ItunesResponse {
        int resultCount;         // package private visibility is intentional
        ItunesResult[] results;  // if you make these private, then add getters
    } // ItunesResponse

    /**
     * Represents a result in a response from the iTunes Search API. This is
     * used by Gson to create an object from the JSON response body.
     *
     * <pre>
     * {
     *   "wrapperType": "track",
     *   "kind": "song",
     *   ...,
     *   "artworkUrl100": "https://.../source/100x100bb.jpg",
     *   ...
     * }
     * </pre>
     */
    private static class ItunesResult {
        String wrapperType;   // package private visibility is intentional
        String kind;          // if you make these, private, then add getters
        String artworkUrl100; // we omit variables for data we're not interested in
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
            // form URI
            String term = URLEncoder.encode("daft punk", StandardCharsets.UTF_8);
            String media = URLEncoder.encode("music", StandardCharsets.UTF_8);
            String limit = URLEncoder.encode("5", StandardCharsets.UTF_8);
            String query = String.format("?term=%s&media=%s&limit=%s", term, media, limit);
            String uri = ITUNES_API + query;
            // build request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
            // send request / receive response in the form of a String
            HttpResponse<String> response = HTTP_CLIENT
                .send(request, BodyHandlers.ofString());
            // ensure the request is okay
            if (response.statusCode() != 200) {
                throw new IOException(response.toString());
            } // if
            // get request body (the content we requested)
            String jsonString = response.body();
            System.out.println("********** RAW JSON STRING: **********");
            System.out.println(jsonString.trim());
            // parse the JSON-formatted string using GSON
            ItunesResponse itunesResponse = GSON
                .fromJson(jsonString, Example3.ItunesResponse.class);
            // print info about the response
            Example3.printItunesResponse(itunesResponse);
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
     * Print a response from the iTunes Search API.
     * @param itunesResponse the response object
     */
    private static void printItunesResponse(ItunesResponse itunesResponse) {
        System.out.println();
        System.out.println("********** PRETTY JSON STRING: **********");
        System.out.println(GSON.toJson(itunesResponse));
        System.out.println();
        System.out.println("********** PARSED RESULTS: **********");
        System.out.printf("resultCount = %s\n", itunesResponse.resultCount);
        for (int i = 0; i < itunesResponse.results.length; i++) {
            System.out.printf("itunesResponse.results[%d]:\n", i);
            ItunesResult result = itunesResponse.results[i];
            System.out.printf(" - wrapperType = %s\n", result.wrapperType);
            System.out.printf(" - kind = %s\n", result.kind);
            System.out.printf(" - artworkUrl100 = %s\n", result.artworkUrl100);
        } // for
    } // parseItunesResponse

} // Example3
