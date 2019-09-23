# JUnit Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

[JUnit](https://junit.org/junit5/) is programmer-friendly _unit testing framework_ for Java.
A **unit test** is some code that attempts to test another unit of code. In Java, the word 
unit generally means method. The JUnit framework provides the following:

* A set of static methods and annotations that make writing unit tests easier. Instead of
  rewriting conditionals for passing or failing a test, implementing a counter for passed
  versus failed test cases, or setting up complicated control flow scenarios to test for
  exceptions, the JUnit framework already provides these things.
  
* A driver program to discover and run the unit tests. This helps ensure that tests
  are run in a consistent manner with consistent output.

In this tutorial, you'll learn some of the basics of setting up and running JUnit-based
unit tests.

## Getting Started

1. Login to your Nike account.

1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-junit`:

   ```
   $ curl -s -L https://git.io/Jes9m | bash
   ```
   
1. Change into the `cs1302-junit` directory that was just created and look around. You should
   see the following:
   
   ```
   .
   ├── bin
   ├── ConsoleLauncher
   ├── doc
   ├── lib
   │   └── junit-platform-console-standalone-1.5.2.jar
   ├── src
   └── test
   ```
   
1. To write and run JUnit 5 tests from the command-line you will need `ConsoleLauncher` and
   `junit-platform-console-standalone-1.5.2.jar` (version may differ). Try to use the
   `ConsoleLauncher` program:
   
   ```
   $ ./ConsoleLauncher --help
   ```
   
1. Now, let's create a class that we can test. Create a class called `Counter` in
   `src/cs1302/junit/Counter.java` with a fully-qualified name of `cs1302.junit.Counter`.
   Use the following code:
   
   ```java
   package cs1302.junit;
   
   /**
    * A simple counter.
    */
   public class Counter {
   
       private long value;
       
       /**
        * Constructs a new {@code Counter} object with an initial value of {@code 0}.
        */
       public Counter() {
           value = 0;
       } // Counter
       
       /**
        * Constructs a new {@code Counter} object with an initial value
        * specified by {@code initValue}.
        * @param initValue  initial value
        * @throws IllegalArgumentException  when {@code initValue < 0}
        */
       public Counter(long initValue) {
           if (initValue < 0) {
               throw new IllegalArgumentException("initValue cannot be negative");
           } // if
           value = initValue;
       } // Counter
       
       /**
        * Increment the counter value by {@code 1}.
        * @throws IllegalStateException  when {@code getValue == Long.MAX_VALUE}
        */
       public void increment() {
           if (value == Long.MAX_VALUE) {
               throw new IllegalStateException("cannot increment maxed counter");
           } // if
           value += 1;
       } // increment
       
       /**
        * Returns the current value of the counter.
        * @return current value of the counter
        */
       public long getValue() {
           return value;
       } // getValue
   
   } // Counter
   ```
   
1. Compile the `Counter` class, specifying `bin` as the default package for compiled
   code:
   
   ```
   $ javac -d bin src/cs1302/junit/Counter.java
   ```
   
1. Now, let's create a class with some JUnit-based unit tests. Create a class called 
   `CounterTest` in `test/cs1302/junit/CounterTest.java` with a fully-qualified name of 
   `cs1302.junit.CounterTEst`. While not always the case, **this test class is in the
   _same package_ as `Counter` under a _different default package_**. Please use the 
   following code:
   
   ```
   package cs1302.junit;
   
   import static org.junit.jupiter.api.Assertions.assertEquals;
   import org.junit.jupiter.api.Test;

   class CounterTest {
   
       @Test
       void getValue1() {
           final Counter counter = new Counter();
           assertEquals(0L, counter.getValue(), "new counter should have getValue() return 0");
       } // getValue1
       
   } // CounterTest 
   ```

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
