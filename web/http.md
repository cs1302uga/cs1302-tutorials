# HTTP

## Quick Introduction to HTTP

HTTP stands for "HyperText Transfer Protocol", the protocol that defines
the standard data exchange procedure used by the World Wide Web\_ (i.e.,
the "web"). Whenever a program (e.g., a web browser) tries to access web
content (i.e., content located at a `http://`-prefixed or
`https://`-prefixed URL\_ or URI\_), it uses an **HTTP client** (web
client) to communicate with the **HTTP server** (web server) that hosts
the content. Sometimes the program itself is an HTTP client (i.e., it
knows all the protocol details); however, it's more common these days to
use an HTTP client provided by a library.

From a program's perspective, the following steps are taken to access
web content:

1.  construct an HTTP request message;
2.  use an HTTP client to send the HTTP request message to the HTTP
    server;
3.  (hopefully) receive an HTTP response message; then
4.  decide how to update its program state.

Here is diagram that illustrates the entire high-level data exchange
that might occur when a program attempts to access content hosted by an
HTTP server:

![image](img/http.png)

If you're interested in the low-level details of HTTP, then we encourage
you to take a Computer Networks class, read the HTTP/1.1
specification\_, or both!

## Java's `java.net.http` Library

Java (versions &gt;=11) ships with `java.net.http`\_, a package that
provides classes and interfaces that understand HTTP. It enables Java
programs to access web content without worrying about the low-level
details of the data exchange we described earlier. In this reading, we
are going to focus on these three types:

| Class                        | Description                                                  |
|------------------------------|--------------------------------------------------------------|
| `java.net.http.HttpRequest`  | Represents an HTTP request.                                  |
| `java.net.http.HttpClient`   | Used to send HTTP requests and receive their HTTP responses. |
| `java.net.http.HttpResponse` | Represents an HTTP response.                                 |

Some complete code examples that involve all three types are included
near the end of the reading.

### `HttpRequest`

The `HttpRequest`\_ class provided by `java.net.http`\_ allows Java
programs to "build" HTTP request messages that can be sent using an HTTP
client. Instead of providing a public constructor, the authors of
`HttpRequest` decided to use the builder pattern — `HttpRequest` objects
are constructed by *building* them using an `HttpRequest.Builder`\_
object returned from `HttpRequest.newBuilder()`\_. The authors' use of
the builder pattern prevents the construction of incomplete request
objects and provides a nice alternative to potentially complex looking
constructor calls.

Most of the methods provided by `HttpRequest.Builder` merely update the
request information stored in the builder object, then just return a
reference to builder object itself so that you can update it further via
additional method calls. Once all the request information is specified,
the `build()` method is called to construct the actual `HttpRequest`
object. The following two code snippets build a request object using the
exact same statement — the second snippet distributes the statement
across several lines to make it easier to read:

```java
HttpRequest request = HttpRequest.newBuilder().uri(location).build();
```

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(location) // sets the HttpRequest's URI
    .build();      // builds and returns an HttpRequest
```

The HTTP specification requires that requests indicate their purpose and
expectations regarding a successful result by setting a request *method*
value. By default, `HttpRequest.Builder` builds "GET" requests that ask
an HTTP server to include the requested content in the body of the
response message that is sent back to the client. The following two code
snippets build the same "GET" request:

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(location) // sets the HttpRequest's URI
    .GET();        // sets the HttpRequest's request method to GET
    .build();      // builds and returns an HttpRequest
```

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(location) // sets the HttpRequest's URI
    .build();      // builds and returns an HttpRequest
```

Other request *method* values are outside the scope of this reading;
however, readers who are interested should note that
`HttpRequest.Builder` does include Java methods to specify a different
request *method* value if "GET" is not what you need.

Here is an example that builds an `HttpRequest` for an image:

```java
URI location = URI.create("http://csweb.cs.uga.edu/~mec/cs1302/gui/pikachu.png");
HttpRequest request = HttpRequest.newBuilder()
    .uri(location) // sets this HttpRequest's request URI
    .build();      // builds and returns an HttpRequest.
```

**NOTE:** The classes and interfaces in `java.net.http` use the `URI`
class to represent location / address information for web content. You
are likely familiar with the concept of a URL; all URLs are also
[URIs](URI).

**NOTE:** `cs1302.web/cs1302.web.Example1`\_ demonstrates how to build a
request for an image and create a JavaFX `Image` object using the data
included in the body of the associated response.

Some HTTP servers host Application Programming Interfaces (APIs) that we
can interact with using HTTP requests — instead of a URI referring to a
"page" or "file", it refers to structured "data" that our program might
leverage to accomplish some goal. For example, the GitHub REST API
provides URIs for accessing information stored by GitHub. Since GitHub
supports many open source projects, their API provides a URI for
structured data about open source software licenses. The example below
builds an `HttpRequest` to get a license\_ (in this case, the MIT
license) using the GitHub REST API. According to GitHub's API
documentation, they recommend setting the "Accept" header when building
a request — headers are one way to provide an HTTP server with more
information about a request. Here is the code:

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.github.com/licenses/MIT"))
    .header("Accept", "application/vnd.github.v3.text-match+json")
    .build();
```

**NOTE:** `cs1302.web/cs1302.web.Example2`\_ demonstrates how to build a
request for license data and use the Google Gson\_ library to parse the
JSON-formatted string included in the body of the associated response.

Some HTTP servers also let you specify request metadata using a special
query string\_ included near the end of the request URI. Special care
must be taken when including a query string in a URI so that the
metadata values are encoded properly using a combination of
`URLEncoder.encode`\_ and `StandardCharsets.UTF_8`\_. The example below
builds an `HttpRequest` that queries the iTunes Search API for up to 5
records related to "Daft Punk".

```java
String term = URLEncoder.encode("daft punk", StandardCharsets.UTF_8); // "daft+punk"
String limit = URLEncoder.encode("5", StandardCharsets.UTF_8);        // "5"
String query = String.format("?term=%s&limit=%s", term, limit);       // "?term=daft+punk&limit=5"

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://itunes.apple.com/search" + query))
    .build();
```

**NOTE:** `cs1302.web/cs1302.web.Example3`\_ demonstrates how to build a
request for the iTunes Search API and use the Google Gson\_ library to
parse the JSON-formatted string included in the body of the associated
response.

### `HttpClient`

The `HttpClient`\_ class provided by `java.net.http`\_ includes a `send`
method to send an HTTP request message (described by an `HttpRequest`\_
object) and return the corresponding HTTP response message (described as
an `HttpResponse<T>`\_ object). The `HttpClient` class also uses the
builder pattern\_ for object creation.

Here is a quick example that builds an `HttpClient` with preferred,
modern settings:

```java
HttpClient httpClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
    .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
    .build();                                     // builds and returns an HttpClient
```

Since a single `HttpClient` object can be used to send multiple
requests, you are encouraged to only create one `HttpClient` object for
your program, unless a specific need to do otherwise arises — you might
do this by defining a static constant:

```java
public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
    .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
    .build();                                     // builds and returns an HttpClient
```

Once built, an `HttpClient` object's `send`\_ method can be called to
send an `HttpRequest`\_; when doing so, an
`HttpResponse.BodyHandler<T>`\_ must also be supplied so that the
`HttpClient` object knows how to construct the `HttpResponse<T>`\_
object is creates for the response message. The
`HttpResponse.BodyHandlers`\_ class provides some static methods to
create create commonly used `HttpResponse.BodyHandler<T>` objects:

| Method                         | Response Type           | Response Body Type |
|--------------------------------|-------------------------|--------------------|
| `BodyHandlers.ofString()`      | `Response<String>`      | `String`           |
| `BodyHandlers.ofInputStream()` | `Response<InputStream>` | `InputStream`      |

In the example below, we access a copy of *The Adventures of Sherlock
Holmes* by Arthur Conan Doyle that is hosted by Project Gutenberg — when
we send the request, we use `BodyHandlers.ofString()` to inform the
client that we want it to interpret the body of the response (i.e., the
response content) as a string.

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://www.gutenberg.org/files/1661/1661-0.txt"))
    .build();
HttpResponse<String> response = HTTP_CLIENT.send(response. BodyHandlers.ofString());
String body = response.body();
```

**NOTE:** A rewritten version of the code above is provided in
`cs1302.web/cs1302.web.Example0`\_ so that you can see it alongside the
required exception handling.

### `HttpResponse<T>`

If an HTTP response message is received, then the program must decide
what to do based on the information contained in that response message.
This information can be accessed by calling methods on the associated
`HttpResponse<T>`\_ object. Here are some typical examples:

<table>
<thead>
<tr class="header">
<th><code>HttpResponse&lt;T&gt;</code>_</th>
<th>Details</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Information Method</td>
<td>Description Note</td>
</tr>
<tr class="even">
<td>=========== ================</td>
<td>============================ ==================================</td>
</tr>
<tr class="odd">
<td><p>body <code>body()</code></p></td>
<td><dl>
<dt>The content of the response. The return type of <code>body()</code></dt>
<dd><p>is determined by the the <code>HttpResponse.BodyHandler&lt;T&gt;</code>_ that was used to <code>send</code>_ the request.</p>
</dd>
</dl></td>
</tr>
<tr class="even">
<td><p>status code <code>statusCode()</code></p></td>
<td><p>The "status code" integer Usually <code>200</code> is what you want. that indicates whether the A list of more status code can be request was successful. found here_.</p></td>
</tr>
</tbody>
</table>

Here is a generic method that you can use to throw an exception if the
status code of a supplied response is not `200` (OK) — you can see it
used in several of the code examples provided in the [Complete
Examples](#complete-examples) section:

```java
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
```

## Complete Examples

Several complete and working code examples accompany this reading so
that readers can see the `java.net.http` package in some real-world
situations. To download these examples, use the following command:

```
$ curl -s -L https://github.com/cs1302uga/cs1302-tutorials/raw/master/web/setup.sh | bash
```

Here is a list of the examples:

| Name                               | Description                                                                                                                       |
|------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| `cs1302.web/cs1302.web.Example0`\_ | Get text content hosted by the Project Gutenberg website.                                                                         |
| `cs1302.web/cs1302.web.Example1`\_ | Get image data to construct a JavaFX `Image` object.                                                                              |
| `cs1302.web/cs1302.web.Example2`\_ | Get license data using the GitHub API, then parse the JSON-formatted string in the response body using Google's Gson library.[1]  |
| `cs1302.web/cs1302.web.Example3`\_ | Get results from the iTunes Search API, then parse the JSON-formatted string in the response body using Google's Gson library.[2] |

To run an example, use the provided `compile.sh` script along with the
name of the example:

```
$ ./compile.sh cs1302.web/cs1302.web.Example0
```

<div class="footer">

[![license\_image](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

Copyright © Michael E. Cotterell, Bradley J. Barnes, and the University
of Georgia. This work is licensed under a CC BY-NC-ND 4.0\_ license to
students and the public. The content and opinions expressed on this Web
page do not necessarily reflect the views of nor are they endorsed by
the University of Georgia or the University System of Georgia.

</div>

[1] A quick introduction to JSON-formatted strings and Google's Gson
library is provided [here](json.rst).

[2] A quick introduction to JSON-formatted strings and Google's Gson
library is provided [here](json.rst).
