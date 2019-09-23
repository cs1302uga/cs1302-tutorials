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
   called `cs1302-jdb`:

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
   
1. To write and run JUnit 5 tests without a tool like Maven (we'll cover that later), you 
   will usually need to use `junit-platform-console-standalone`, a program provided by 
   JUnit that includes all of the JUnit dependencies. If you inspect the `lib` directory, then
   you'll notice that we've included this file for you. 

   ```
   $ find lib
   ```

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
