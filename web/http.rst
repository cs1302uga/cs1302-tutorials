HTTP Tutorial
=============

.. contents::

Quick Introduction to HTTP
**************************

.. |WWW| replace:: World Wide Web
.. _WWW: https://en.wikipedia.org/wiki/World_Wide_Web

.. |URL| replace:: URL
.. _URL: https://en.wikipedia.org/wiki/URL

.. |web_server| replace:: web server

HTTP stands for "HyperText Transfer Protocol", the protocol that defines
the standard data exchange procedure used by the |WWW|_ (i.e., the "web").
Whenever a program (e.g., a web browser) tries to access web content (i.e.,
content located at a ``http://``-prefixed or ``https://``-prefixed |URL|_), it
uses an **HTTP client** (web client) to communicate with the **HTTP server**
(web server) that hosts the content. Sometimes the program itself is an
HTTP client (i.e., it knows all the protocol details); however, it's more common
these days to use an HTTP client provided by a library. From the program's
perspective, the following steps are taken to access :

1. construct an HTTP request message;
2. use an HTTP client to send the HTTP request message to the HTTP server;
3. (hopefully) receive an HTTP response message; then
4. decide how to update its program state.

Here is diagram that illustrates the entire high-level data exchange
that might occur when a program attempts to access content hosted
by an HTTP server:

.. image:: img/http.png

.. |http_spec| replace:: HTTP/1.1 specification
.. _http_spec: https://httpwg.org/specs/rfc7231.html

If you're interested in the low-level details of HTTP, then we
encourage you to take a Computer Networks class, read the
|http_spec|_, or both!


Java's ``java.net.http`` Library
********************************

.. |java_net_http| replace:: ``java.net.http``
.. _java_net_http: https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/package-summary.html

Java (versions >=11) ships with |java_net_http|_, a package that provides
classes and interfaces that understand HTTP. It enables Java programs to
access web content without worrying about the low-level details of the
data exchange we described earlier.

``HttpClient``
++++++++++++++


``HttpRequest``
++++++++++++++

``HttpResponse<T>``
+++++++++++++++++++

If an HTTP response message is received, then the program must decide what to
do based on the information contained in the response message. Typically,
the following information is: ...
