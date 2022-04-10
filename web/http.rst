HTTP Tutorial
=============

.. |WWW| replace:: World Wide Web
.. _WWW: https://en.wikipedia.org/wiki/World_Wide_Web

.. |URL| replace:: URL
.. _URL: https://en.wikipedia.org/wiki/URL

.. |web_server| replace:: web server

HTTP stands for the HyperText Transfer Protocol, the protocol that defines
the standard data exchange procedure used by the |WWW|_ (i.e., the "web").
Whenever a program (e.g., a web browser) tries to access content at some
location that is addressed using a ``http://``-prefixed or ``https://``-prefixed
|URL|_, it must use an **HTTP client** (web client) to communicate with the
**HTTP server** (web server) that hosts that location. Sometimes the program
itself is an HTTP client (i.e., it knows all the protocol details); however,
it's more common these days to use library code that provides an HTTP client.
From the program's perspective, the following steps are taken:

1. construct an HTTP request message;
2. use an HTTP client to send the HTTP request message to the HTTP server;
3. (hopefully) receive an HTTP response message; then
4. decide how to update its program state.

If an HTTP response message is received, then the program decides what to
do based on the information contained in the response message. Here is a
diagram that illustrates the entire communication:

.. image:: img/http.png
