**IN PROGRESS!**

.. |api_queue| replace:: ``Queue<E>``
.. _api_queue: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html

.. |api_queue_offer| replace:: ``offer``
.. _api_queue_offer: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html#offer(E)

.. |api_assertion_error| replace:: ``AssertionError``
.. _api_assertion_error: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/AssertionError.html

***********************
Unit Testing with JUnit
***********************

Introduction to Unit Testing
============================

In programming, a **unit** of code is a method or small collection of methods
(e.g., from a class or interface) that can be logically isolated in such way 
that their expectated behavior is predictable. Consider the |api_queue_offer|_
method in Java's |api_queue|_ interface, which inserts a specified element into 
a queue (i.e, a collection with first-in, first-out (FIFO) semantics):

.. code-block:: java

   boolean offer (E e)

This method constitutes a unit because it's isolated from the code in other
methods and its impact on the queue object is predictable. This quality makes
it possible to write and execute **unit tests**. In programming, **unit testing** 
is a software testing method by which individual units of code are tested to 
determine whether they are fit for use.

Testing without Special Libraries
=================================

Technically, no special software or libraries are required to perform unit
testing; it can be done by writing code. For example, the |api_queue_offer|
method should return ``true`` if the element supplied was added to the queue, 
else ``false``; here is some code to that tests a call to |api_queue_offer| on
an empty queue:

.. code-block:: java

   Queue<String> queue = new LinkedList<String>();
   result = queue.offer("hello");
   
   if (!result) {
       System.out.println("FAIL! offer should return true");
   } // if

It's common to use the words **pass** and **fail** to inducate that a unit
being tested is fit or not fit, respectively, for a particular use. The same 
unit test we just looked at can also be written using an ``assert`` 
statement, a statement that asserts that some condition must be 
``true``, else an |api_assertion_error|_ with, if provided, a failure
message is thrown during runtime:

.. code-block:: java

   Queue<String> queue = new LinkedList<String>();
   result = queue.offer("hello");
   
   assert result : "FAIL! offer should return true";
   
**Normally, Java ignores assertions**; to enable them you need to
include the ``-ea`` command-line option when running the ``java`` command
to run the unit test.

Testing with JUnit
==================


