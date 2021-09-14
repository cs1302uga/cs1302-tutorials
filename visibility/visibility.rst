.. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Fall%202021-blue
   :alt: Approved for: Spring 2021

.. external links
.. |uml_tutorial| replace:: UML Class Diagrams
.. _uml_tutorial: https://github.com/cs1302uga/cs1302-tutorials/blob/master/uml/uml.md

.. internal links
.. |reading_private| replace:: Private Visibility
.. _reading_private: private.rst
.. |reading_package| replace:: Package Private Visibility
.. _reading_package: package-private.rst
.. |reading_protected| replace:: Protected Visibility
.. _reading_protected: protected.rst
.. |reading_public| replace:: Public Visibility
.. _reading_public: public.rst
.. 
  image:: img/in-progress.svg

Visibility Reading
##################

|approval_notice|

Introduction
************

Java has four different **visibility** options that can be used to facilitate
**access control**, i.e., to control access to the things that we declare [0]_
in our code. When you declare that something has a particular visibility in your code,
you communicate to the compiler the set of locations that are allowed to access
that thing. The term "access" simply means to "use from elsewhere in the code."
With that in mind, throughout this tutorial we will often say that various things
are "visible from" some location; this wording just means that the thing
"can be accessed from" or "can be used from" that location based on its
visibility.

.. [0] In Java, a **declaration** introduces an entity (e.g., a variable, method,
   class, etc.) into a program and includes an identifier (name) that can be used
   to represent that entity.
   
   Example declarations:
      .. code-block:: java   
      
         int x;                     // Variable Declaration
         public class Foo           // Class Declaration
         public static void bar()   // Method Declaration

The table below shows all four visibility options that are available in Java,
three of which have an associated **visibility modifier** that we can
include in various declarations throughout our code. The set of potential
visibility options for a declaration may also depend on its level and
other factors. We encourage you to review [4]_, which contains declarations
at each level.

===============  ================  ==========  ==============  =================  ================
Visibility Name  Modifier Keyword  UML Symbol  Top-Level [1]_  Member-Level [2]_  Local-Level [3]_
===============  ================  ==========  ==============  =================  ================
private          ``private``       ``-``       |N|             |Y|                |N|
package private  ..                ``~``       |Y|             |Y|                |N|
protected        ``protected``     ``#``       |N|             |Y|                |N|
public           ``public``        ``+``       |Y|             |Y|                |N|
===============  ================  ==========  ==============  =================  ================

.. [1] A **top-level declaration** is the outermost declaration in a ``.java`` file.
       Some things that can be declared at the top-level include classes and
       interfaces.

.. [2] A **member-level declaration** is any declaration of a class or interface member.
       Members can include, where applicable, the constructors, methods, variables, constants,
       etc. (both static or non-static/instance) of the class or interface; however, they
       never includes Local-level declarations.

.. [3] A **local-level declaration** is any variable declaration that is local, in
       scope, to a particular method. The local variables of a method include
       its parameter and any variables declared within the body of the
       method.

.. [4] Here are some examples to help you identify the different declaration levels:

       .. code-block:: java

          public class Person { // <------------- `Person` is top-level
              private String name; // <---------- `name` is member-level
              public Person(String name) { // <-- `Person` is member-level; `name` is local-level
                  int x = 42; // <--------------- `x` is local-level
                  this.name = name;
              } // Person
          } // Person

In this tutorial, we cover each available visibility option with a few examples,
often illustrated using a combination of `UML diagrams <uml_tutorial>`__ and code
snippets.

We will take some liberties when discussing examples involving multiple
labelled lines (e.g., ``LINE1``) of code; for example, whenever we consider
whether or not a line will work, we will make a good faith assumption that
all other labelled lines of code will also work, even if we find out later
that they don't. We mention this because, technically, if one does not work,
then the whole thing might not work; however, at the same time, it's usually
less constructive to break such an example into multiple examples as that
impacts readability.

While you are likely already familiar private and public visibility, please do not
assume that you already understand how it works. Over the years, we have
found that many students have a somewhat flawed conceptual model for how
some visibility options work that is actually more complicated than what it actually is.
Regardless of your experience, you should work through each visibility example
in this tutorial until you are able to:

1. correctly determine the visibility outcome and justification; and
2. write your own code that illustrates a similar visibility scenario.

We encourage you to make Piazza posts about your examples, ask questions,
and help others to understand the important details of visibility.

Individual Readings
*******************

* |reading_private|_
* |reading_package|_
* |reading_protected|_
* |reading_public|_

Summary of Visibilities
***********************

In the table below, we summarize the locations that declarations
with a particular visibility are visible from.

===============  ==========  ============  ===========  =========
Entity           Visible From
---------------  ------------------------------------------------
Declared As      Same Class  Same Package  Child Class  Elsewhere
===============  ==========  ============  ===========  =========
public           |Y|         |Y|           |Y|          |Y|
protected        |Y|         |Y|           |Y|          |N|
package private  |Y|         |Y|           |N|          |N|
private          |Y|         |N|           |N|          |N|
===============  ==========  ============  ===========  =========

.. #############################################################################

.. util
.. |Y| unicode:: U+2713
.. |N| unicode:: U+2717

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
