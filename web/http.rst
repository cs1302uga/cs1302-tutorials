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

Java's ``java.net.http`` Library
********************************

If an HTTP response message is received, then the program must decide what to
do based on the information contained in the response message. Typically,
the following information is: ...
