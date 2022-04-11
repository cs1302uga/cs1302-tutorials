package cs1302.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Example 2: Get license data using the GitHub REST API.
 * <pre>
 * $ ./compile cs1302.web/cs1302.web.Example2
 * </pre>
 */
public class Example2 {

    /**
     * Represents GitHub license data. This is used by Gson to create an object
     * from the JSON response body.
     */
    private static class GitHubLicense {
        String key;
        String name;
        String spdxId;
        URL url;
        String nodeId;
        URL htmlUrl;
        String description;
        String implementation;
        String[] permissions;
        String[] conditions;
        String[] limitations;
        String body;
        boolean featured;
    } // GitHubLicense

    private static HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns an HttpClient

    private static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    private static final String GITHUB_API = "https://api.github.com";
    private static final String GITHUB_ACCEPT = "application/vnd.github.v3.text-match+json";

    public static void main(String[] args) {
        try {
            String mitLicenseData = Example2.fetchLicense("MIT");
            Example2.parseLicense(mitLicenseData);
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
     * @param licenseData license data in JSON format
     */
    private static void parseLicense(String licenseData) {
        GitHubLicense license = GSON.fromJson(licenseData, Example2.GitHubLicense.class);
        System.out.println("********** RAW JSON STRING: **********");
        System.out.println(licenseData);
        System.out.println();
        System.out.println("********** PRETTY JSON STRING: **********");
        System.out.println(GSON.toJson(license));
        System.out.println();
        System.out.println("********** LICENSE INFORMATION: **********");
        System.out.println(license.name);
        System.out.println(license.url);
        System.out.println(license.description);
    } // parseLicense

    /**
     * Returns license data using the GitHub REST API.
     * @param license desired license
     * @return license data
     * @throws IOException if an I/O error occurs when sending, receiving, or parsing
     * @throws InterruptedException if the HTTP client's {@code send} method is
     *    interrupted
     */
    private static String fetchLicense(String license) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(GITHUB_API + "/licenses/" + license))
            .header("Accept", GITHUB_ACCEPT)
            .build();
        System.out.printf("request = %s; headers = %s\n", request, request.headers());
        HttpResponse<String> response = HTTP_CLIENT
            .send(request, BodyHandlers.ofString());
        System.out.printf("response = %s; headers = %s\n", response, response.headers());
        Example2.ensureGoodResponse(response);
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

} // Example2
