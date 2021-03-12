**IN PROGRESS!**

.. |api_queue| replace:: ``Queue<E>``
.. _api_queue: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html

.. |api_queue_offer| replace:: ``offer``
.. _api_queue_offer: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html#offer(E)

***********************
Unit Testing with JUnit
***********************

Introduction
============

In programming, a **unit** of code is a method or small collection of methods
(e.g., from a class or interface) that can be logically isolated in such way 
that their expectated behavior is predictable. Consider the |api_queue_offer|_
method in Java's |api_queue|_ interface, which inserts a specified element into 
a queue:

.. code-block:: java

   boolean offer (E e)

In most programming languages
 that together with any associated data, usage procedures, and operating procedures


**unit testing** is a software testing method by which individual units of source code sets of one or more computer program modules together with associated control data, usage procedures, and operating procedures—are tested to determine whether they are fit for use.
