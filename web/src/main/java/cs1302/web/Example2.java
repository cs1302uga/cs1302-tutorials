package cs1302.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Example 2: Get license data using the GitHub REST API.
 * <pre>
 * $ ./compile cs1302.web/cs1302.web.Example2
 * </pre>
 */
public class Example2 {

    private static HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns an HttpClient

    private static Gson GSON = new GsonBuilder()
        .enableComplexMapKeySerialization()
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
        JsonElement root = JsonParser.parseString(licenseData);
        System.out.println("\nJSON RESPONSE:");
        System.out.println(GSON.toJson(root));
        String name = Example2.jsonPath(root, "name").getAsString();
        String description = Example2.jsonPath(root, "description").getAsString();
        JsonArray permissions = Example2.jsonPath(root, "permissions").getAsJsonArray();
        System.out.println("\nLICENSE INFO:");
        System.out.println(name);
        System.out.println(description);
        for (int i = 0; i < permissions.size(); i++) {
            String permission = Example2.jsonPath(permissions, i).getAsString();
            System.out.println(" - " + permission);
        } // for
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
        String licenseData = response.body();
        return licenseData;
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

    /**
     * Return the {@link JsonElement} described by the {@code keys}.
     * Given a {@code JsonElement} that denotes a portion
     * of valid JSON, hereafter referred to as the {@code root} element, this
     * method attempts to return the element reached by starting at the root and
     * following the path described by the sequence of provided {@code keys}.
     * Here are some various examples, assuming {@code root} refers to the
     * outer-most element in the JSON response that is presented:
     *
     * <pre>
     * {                          // get(root).getAsJsonObject()
     *   numFound: 494,           // get(root, "numFound").getAsInt()
     *   docs: [                  // get(root, "docs").getAsJsonArray()
     *     {                      // get(root, "docs", 0).getAsJsonObject()
     *       title: "Some Title", // get(root, "docs", 0, "title").getAsString()
     *       person: [            // get(root, "docs", 0, "person").getAsJsonArray()
     *         "Person 1",        // get(root, "docs", 0, "person", 0).getAsString()
     *         "Person 2",        // get(root, "docs", 0, "person", 1).getAsString()
     *       ],
     *     },
     *     {                      // get(root, "docs", 1).getAsJsonObject()
     *        title: "Title 2",   // get(root, "docs", 1, "title").getAsString()
     *        ...
     *     },
     *     ...
     *   ]
     * }
     * </pre>
     *
     * @param root starting element
     * @param keys list of keys describing the path from the root to the
     *        desired element
     * @return desired element
     * @throws NullPointerException if a key is encountered that does not exist
     *         at the corresponding location in the path
     * @throws IllegalArgumentException if a key is encountered that is neither
     *         an {@code int} nor a {@code String}.
     */
    public static JsonElement jsonPath(JsonElement root, Object... keys) {
        for (Object key : keys) {
            try {
                if (key instanceof String) {
                    root = root.getAsJsonObject().get((String) key);
                } else if (key instanceof Integer) {
                    root = root.getAsJsonArray().get((int) key);
                } else {
                    String message = String
                        .format("argument type not supported (%s)", key.getClass());
                    throw new IllegalArgumentException(message);
                } // if
            } catch (NullPointerException npe) {
                String message = String.format("key does not exist (%s)", key);
                throw new NullPointerException(message);
            } // try
        } // for
        return root;
    } // jsonPath

} // Example2
