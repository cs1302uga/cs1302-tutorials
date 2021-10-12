.. rst setup
.. sectnum::
.. |approval_notice| image:: https://img.shields.io/badge/Status-Not%20Ready-red.svg
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

==================
Lambda Expressions
==================

|approval_notice|

.. contents:: **Table of Contents**
   :depth: 3

----

This tutorial assumes that the reader is already familiar with
interface-based polymorphism in Java. Later sections also assume
familiarity with generics.

Introduction
============

.. |functional_interface| replace:: functional interface
.. _functional_interface: https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html#jls-9.8

.. |java_lang_object| replace:: ``Object``
.. _java_lang_object: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Object.html

.. |java_lang_runnable| replace:: ``Runnable``
.. _java_lang_runnable: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Runnable.html

In Java, a **lambda expression** is a special syntax for creating an object that implements
an interface that only has one abstract method that doesn't match a method in the |java_lang_object|_
class. Not all interfaces meet that requirement, but any interface that does is called a
|functional_interface|_. One of the simplest functional interfaces that comes with Java
is |java_lang_runnable|_, which we display below without its associated documentation (it's
not relevant to the discussion):

.. code:: java

   public interface Runnable {

       public void run();

   } // Runnable

The ``Runnable`` interface is considered a *functional interface* since it has
one abstract method, ``run``, that doesn't match a method in the ``Object``
class. To implement this interface, you might define a class, create an object
of that class elsewhere, then call the object's ``run`` method:

.. rubric:: **MyRunnable.java:**

.. code:: java

   public class MyRunnable implements Runnable {

       @Override
       public void run() {
           System.out.println("hello");
           System.out.println("world");
       } // run

   } // Runnable

.. rubric:: **Driver.java:**

.. code:: java

   public class Driver {

       public static void runTwice(Runnable r) {
           r.run();
           r.run();
       } // runTwice

       public static void main(String[] args) {
           Runnable r = new MyRunnable();  // create object
           runTwice(r);                    // calls r.run() twice
       } // main

   } // Driver

.. rubric:: **Output:**

.. code:: text

   hello
   world
   hello
   world

The example above illustrates the typical way to implement almost
any interface. Since ``Runnable`` meets the definition for a
functional interface, it is also possible to implement ``Runnable``
using lambda expression syntax. Before we show you the syntax,
it's important to note different aspects of the abstract method in
``Runnable`` that made it qualify as a functional interface:

==============  ===========  ==============  ================  =================
Parameter List  Name         Return Type     Type Layout [1]_  Scaffold [2]_
==============  ===========  ==============  ================  =================
``()``          ``run``      ``void``        ``() -> void``    ``() -> { ... }``
==============  ===========  ==============  ================  =================

.. [1] To construct the "Type Layout," write the parameter list
   with the variable names omitted, an arrow (``->``), then
   the return type.

.. [2] To construct the "Scaffold," write the parameter list
   with the variable types omitted, an arrow (``->``), then
   ``{ ... }`` if the return type is ``void`` and ``{ ... return ?; }``
   if the return type is anything else.

Now that the functional interface's method has been described,
including its type layout, we discuss how to define a nameless
class that implements that interface using a lambda expression.
The goal of the next few steps is to have ``r.run()`` do the
same thing that it did in the previous code example, but with
an object created using a lambda expression instead of using
``new``.

.. rubric:: **Constructing a Lambda Expression**

1. Since a lambda expression creates a nameless class, we cannot
   create a ``.java`` file for it; instead, we will define the class
   where the object is created. Using the previous code example
   as a starting point, replace the object instantiation expression
   (``new MyRunnable()``) with the ``run`` method's scaffold
   we identified earlier (``() -> { ... }``):

   .. rubric:: **Driver.java:**

   .. code:: java

      public class Driver {

          public static void runTwice(Runnable r) {
             r.run();
             r.run();
          } // runTwice

          public static void main(String[] args) {
              Runnable r = () -> { ... };
              runTwice(r);
          } // main

      } // Driver

   **IMPORTANT:** Take care to note that the semicolon remained!

2. Once the ``run`` method's scaffold is in place, replace `...`
   with the desired method body, epanding the scaffold's block
   (i.e., the curly braces) as needed:

   .. rubric:: **Driver.java:**

   .. code:: java

      public class Driver {

          public static void main(String[] args) {
              Runnable r = () -> {
                  System.out.println("hello");
                  System.out.println("world");
              };
              r.run();
          } // main

      } // Driver

3.  **That's it!**
