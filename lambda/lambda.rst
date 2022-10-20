.. rst setup
.. sectnum::
.. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Fall%202022-darkgreen
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
.. _java_lang_object: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html

.. |java_lang_runnable| replace:: ``Runnable``
.. _java_lang_runnable: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Runnable.html

In Java, a **lambda expression** is a special syntax for creating an object that implements
an interface that only has one abstract method that doesn't match a method in the |java_lang_object|_
class. Not all interfaces meet that requirement, but any interface that does is called a
|functional_interface|_.

Quick Review: Interfaces
========================

In Java, any interface can be implemented and utilized via the following steps.

1. **Sign the contract:** Create a class in its own `.java` file, and include the
   appropriate `implements` clause in the class declaration.

2. **Meet the minimum requirements:** Override the abstract methods from the
   interface so that code compiles.

3. **Meet the full requirements:** Ensure that each overridden method aligns
   with the expectations outlined in the interface documentation.

4. **Instantiate:** Create one or more objects of the class.

5. **Interact using the interface:** When interacting with the objects,
   use a variable with the interface as its type so that your code works
   with any object (of a class) that implements the interface and not
   just objects of the new class you created.

The code described in step 5 may have been written before you step 1
is started, especially in cases where other classes that implement the
interface already exist. The overall process outlined above has real,
tangible benefites. For example, the new class can:

1. be reused (by utlizing its constructor);

2. have instance variables that allow its objects to manage their state;

3. include helper methods; and

4. include documentation that differs from or adds on to the interface
   documentation in some way.

All of that sounds great, but what if you don't need one or more
of those benefits? What if you don't need any of them? Suddenly,
the five-step process described earlier for implementing the
interface sounds tedious, especially in cases where the interface
only has one abstract method.

Functional Interfaces
=====================

.. |java_util_function_consumer| replace:: ``Consumer<T>``
.. _java_util_function_consumer: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Consumer.html

As mentioned in the introduction, any interface that only has one abstract
method that doesn't match a method in the |java_lang_object|_ class is a
**functional interface**, and while they can be implemented and used by
following the five-step process outlined earlier, Java allows us to
reduce that down to a two-step process by using something
**lambda expression** syntax:

1. **Instantiate:** Use a lambda expression to create an object of an
   unnamed class that implements the interface by defining what the
   override behavior should be for the one abstract method in the
   interface (all in one place).

2. **Interact using the interface:** When interacting with the objects,
   use a variable with the interface as its type so that your code works
   with any object (of a class) that implements the interface and not
   just objects of the new class you created.

Example: Named Class vs. Lambda Expression
******************************************

Below is an example that illustrates the difference between using
a named class and a lambda expression when implementing
|java_util_function_consumer|_, a functional interface that comes
with Java (its one abstract method is ``void accept(T t)``).

.. rubric:: **NAMED CLASS:**

.. code:: java

   // Shouter.java (assume proper package and import statements)
   public class Shouter implements Consumer<String>() {

       @Override
       public void accept(String t) {
           System.out.println(t.toUpperCase());
       } // accept

   } // Shouter

.. code:: java

   // Driver.java (assume proper package and import statements)
   public class Driver {

       public static void forEach(String[] strings, Consumer<String> consumer) {
           for (int i = 0; i < strings.length; i++) {
               String str = strings[i];
               consumer.accept(str);
           } // for
       } // forEach

       public static void main(String[] args) {
           Consumer<String> shout = new Shouter();
           Driver.forEach(args, shout);
       } // main

   } // Driver

.. code:: text

   # compile two files, then run:

   $ java Driver hello world how are you?
   HELLO
   WORLD
   HOW
   ARE
   YOU?

.. rubric:: **USING A LAMBDA EXPRESSION:**

.. code:: java

   // Driver.java (assume proper package and import statements)
   public class Driver {

       public static void forEach(String[] strings, Consumer<String> consumer) {
           for (int i = 0; i < strings.length; i++) {
               String str = strings[i];
               consumer.accept(str);
           } // for
       } // forEach

       public static void main(String[] args) {
           Consumer<String> shout = (String t) -> System.out.println(t.toUpperCase());
           Driver.forEach(args, shout);
       } // main

   } // Driver

.. code:: text

   # compile one file, then run:

   $ java Driver hello world how are you?
   HELLO
   WORLD
   HOW
   ARE
   YOU?

In the second example that utilizes the lambda expression syntax, we didn't
create an additional file for a class that implements the interface, but
we did define a class that implements the interface and make an object
out of that class. **It all happened on one line:**

.. code:: java

   Consumer<String> shout = (String t) -> System.out.println(t.toUpperCase());

Explaining the Example
**********************

Let's break it down:

.. code:: java

   Consumer<String> shout = (String t) -> System.out.println(t.toUpperCase());
   // -------------------|-|-------------------------------------------------|
   //         1          |3|                       2                         |
   //                                    the lambda expression

1. First, a reference variable named ``shout`` is declared with type
   ``Consumer<String>``;

2. A lambda expression is used to **create an object** that has one method
   by defining what that method should do. In this case, we want the
   method's type layout to match the abstract method ``accept`` in
   ``Consumer<String>``, and it does.

3. Assign the object's reference to the variable.

Since we didn't define the object's method in some named class, it is
considered an object of an unnamed class. That's okay, so long we don't
need multiple objects of that class.

How to Create a Lambda Expression
*********************************

The easiest way to create a **lambda expression** is by pretending
to assign your intended method override to a variable of the
interface type. Here's an example:

1. Assign your intended method override to a variable of the
   interface type:

   .. code:: java

      // this won't compile, but it's a good way to get started
      Consumer<String> shout = @Override public void accept(String t) {
          return System.out.println(t.toUpperCase());
      };

2. Remove the annotation (``@Override``), the visibility modifer (``public``),
   the return type (``void``) and the method name (``accept``):

   .. code:: java

      // this still won't compile, but it's only two characters away from working
      Consumer<String> shout = (String t) {
          System.out.println(t.toUpperCase());
      };

3. Add an arrow (``->``) between the parameter list and the opening curly
   brace:

   .. code:: java

      // this WILL compile; we provided a valid lambda expression!
      Consumer<String> shout = (String t) -> {
          System.out.println(t.toUpperCase());
      };

The three-step process above leads to a valid lambda expression (try it);
however, there's still room for improvement.

1. If the method body only contains a single statement, then we
   can omit the curly braces all together and write the lambda
   expression on a single line:

   .. code:: java

      Consumer<String> shout = (String t) -> System.out.println(t.toUpperCase());

   If the only statement is a `return` statement, then the keyword `return`
   is omitted when writing a lambda expression without curly braces.

2. Specifying the parameter types is optional.

   .. code:: java

      Consumer<String> shout = (t) -> System.out.println(t.toUpperCase());

   If we don't include the parameter types in our lambda expression, then
   Java will try to determine what they are based on context. For example,
   if we're assigning the created object to a ``Consumer<String>`` variable,
   then Java knows that the parameter list for ``accept`` is ``(String t)``
   and will automatically convert ``(t)`` to ``(String t)``.

3. If there is exactly one method parameter, then the parentheses for the
   parameter list are optional.

   .. code:: java

      Consumer<String> shout = t -> System.out.println(t.toUpperCase());

   If the method doesn't have any parameters, then parentheses are still
   required, and ``() ->`` is used.

More Examples
*************

In the code presented below, we create three more objects using lambda
expressions (four total). Using the usual named class approach to
implementing the interface would have required four ``.java`` files,
one for each named class. Using the lambda expression approach, all
four (unnamed) classes are created and instantiated using a single
``.java`` file, and in this case, all in one method!

.. code:: java

   // Driver.java (assume proper package and import statements)
   public class Driver {

       public static void forEach(String[] strings, Consumer<String> consumer) {
           for (int i = 0; i < strings.length; i++) {
               String str = strings[i];
               consumer.accept(str);
           } // for
       } // forEach

       public static void main(String[] args) {
           Consumer<String> println = t -> System.out.println(t);
           Driver.forEach(args, println);
           System.out.println();

           Consumer<String> shout = t -> System.out.println(t.toUpperCase());
           Driver.forEach(args, shout);
           System.out.println();

           Consumer<String> whisper = t -> System.out.println(t.toLowerCase());
           Driver.forEach(args, whisper);
           System.out.println();

           Consumer<String> repeat2 = t -> System.out.println((t + " ").repeat(2));
           Driver.forEach(args, repeat2);
           System.out.println();
       } // main

   } // Driver

.. code:: text

   # compile ONE file, then run:

   $ java Driver hello WORLD
   hello
   WORLD

   HELLO
   WORLD

   hello
   world

   hello hello
   WORLD WORLD

Video Examples
==============

**Coming Soon** Attend class for live examples. 
