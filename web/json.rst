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

Here is the output for that example -- once printed, the JSON-formatted
string looks really nice:

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

In a JSON-formatted string,

* ``""`` is used to denote a *key* or literal *value*;
* ``{}`` is used to denote an *object*; and
* ``[]`` is used to denote an array.

With that in mind, let's consider a Java class that might
organize its instance data in a similar to way to the
object described by the JSON-formatted string from earlier:

.. code-block:: java

   public class Student {
       String name;
       int age;
       String[] classes;
   } // Student

.. |Gson_fromJson| replace:: fromJson
.. _Gson_fromJson: https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/Gson.html

Now that we have a Java class to represent the same kind
of information, we can use Google's |GSON|_ library to
parse the JSON-formatted string directly into a ``Student``
object using the |Gson_fromJson|_ method. The example below
assumes that Gson is added as a project dependency and that a
``Gson`` object is available via ``GSON`` -- instructions
describing how to add Gson to a Maven project are included
later in this reading:

.. code-block:: java

   // parse JSON-formatted string into a Student object
   Student jay = GSON.fromJson(jsonString, Example4.Student.class);

   // inspect the result
   System.out.println(jay.name);
   System.out.println(jay.age);
   System.out.println("Classes:");
   for (int i = 0; i < jay.classes.length; i++) {
       String className = jay.classes[i];
       System.out.println(" - " + className);
   } // for

Here is the expected output:

.. code-block::

   Jay
   19
   Classes:
    - CSCI 1302
    - CSCI 1730
    - CSCI 2610

**NOTE:** An extended version of this example can be seen in
|ex4|_.

Complete Examples
*****************

Several complete and working code examples accompany this reading so
that readers can see JSON and Gson used in some real-world
situations. To download these examples, follow the instructions
`here <http.rst#complete-examples>`__.

In addition to the examples listed in the HTTP reading, the following
examples are specific to this JSON reading:

.. |ex4| replace:: ``cs1302.web/cs1302.web.Example4``
.. _ex4: src/main/java/cs1302/web/Example4.java

=======  ================================================================================
Name     Description
=======  ================================================================================
|ex4|_   Create a JSON-formatted string by hand, then parse it into a Java object using
         using Google's Gson library.
=======  ================================================================================

To run an example, use the provided ``compile.sh`` script along with the
name of the example:

.. code-block::

   $ ./compile.sh cs1302.web/cs1302.web.Example4

Adding Gson to a Maven Project
******************************

.. |maven_add_deps| replace:: Importing Dependencies
.. _maven_add_deps: https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Importing_Dependencies

To use Gson in a Maven project, Gson must be added as a dependency
in the project's ``pom.xml`` file -- refer to the |maven_add_deps|_
page in Maven's documentation for more information:

.. code-block:: xml

   <dependency>
     <groupId>com.google.code.gson</groupId>
     <artifactId>gson</artifactId>
     <version>2.9.0</version>
   </dependency>

Once added as a dependency, Gson will automatically be available
on the classpath. For example, you could include the following
near the top a class to make a ``Gson`` object available within
that class -- the code will compile using ``mvn compile`` so
long as the Gson dependency is aded to the ``pom.xml`` file correctly:

.. code-block:: java

   private static Gson GSON = new GsonBuilder()
       .setPrettyPrinting()
       .create();

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
