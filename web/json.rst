JSON
====

.. contents::

Quick Introduction to JSON
**************************

.. |GSON| replace:: Gson
.. _GSON: https://github.com/google/gson

.. |JSON| replace:: JSON
.. _JSON: https://en.wikipedia.org/wiki/JSON

JSON stands for "JavaScript Object Notation", a text format for storing data in
strings based on the JavaScript programming language. You do not need to know
JavaScript to use JSON -- the two are completely independent, and many libraries
exist in various programming languages to let us work with the format instead
of writing it by hand. Here is a small example of a JSON-formatted string
constructed by hand using Java code:

.. code-block:: java

   String nl = "\n";
   String jsonString = "{     " + nl
       + "  \"name\": \"Jay\"," + nl
       + "  \"age\": \"19\",  " + nl
       + "  \"classes\": [    " + nl
       + "    \"CSCI 1302\",  " + nl
       + "    \"CSCI 1730\"   " + nl
       + "  ]                 " + nl
       + "}                   ";
   System.out.println(jsonString);

Here is the output for that example:

.. code-block:: json

   {
     "name": "Jay",
     "age": "19",
     "classes": [
       "CSCI 1302",
       "CSCI 1730",
       "CSCI 2610"
     ]
   }

.. copyright and license information
.. |copy| unicode:: U+000A9 .. COPYRIGHT SIGN
.. |copyright| replace:: Copyright |copy| Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
.. |license| replace:: CC BY-NC-ND 4.0
.. _license: http://creativecommons.org/licenses/by-nc-nd/4.0/
.. |license_image| image:: https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg
                   :target: http://creativecommons.org/licenses/by-nc-nd/4.0/
.. standard footer
.. footer:: |license_image|

   |copyright| This work is licensed under a |license|_ license to students
   and the public. The content and opinions expressed on this Web page do not necessarily
   reflect the views of nor are they endorsed by the University of Georgia or the University
   System of Georgia.
