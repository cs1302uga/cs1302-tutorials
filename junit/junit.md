# Command-Line JUnit Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

[JUnit](https://junit.org/junit5/) is programmer-friendly _unit testing framework_ and
_integration testing framework_ for Java. A **unit test** is some code that attempts to 
test another, single unit of code. An **integration test** is some code that attempts to
test that different units work together. In Java, the word unit generally means method. 
The JUnit framework provides the following:

* A set of static methods and annotations that make writing tests easier. Instead of
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
   
   **NOTE:** If you look inside the `ConsoleLauncher` file (e.g., using `cat` or `emacs`),
   you will notice that it's just a Bash script that executes the 
   `junit-platform-console-standalone-1.5.2.jar` file.
   
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
        * @throws IllegalStateException  when {@code getValue() == Long.MAX_VALUE}
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
   
   ```java
   package cs1302.junit;
   
   import static org.junit.jupiter.api.Assertions.assertEquals;
   import org.junit.jupiter.api.Test;

   class CounterTest {
   
       @Test
       void getValue1() {
           final Counter counter = new Counter();
           assertEquals(0L, counter.getValue(), "new counter should have value 0");
       } // getValue1
       
   } // CounterTest 
   ```
   
   Here are some important notes:
   
   * The `@Test` annotation 
     from [`org.junit.jupiter.api.Test`](https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/Test.html)
     is used to denote that a method is a test method. JUnit looks for these methods.
     Other annotations are included in the 
     [`org.junit.jupiter.api`](https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/package-summary.html) 
     package for different testing use cases.
     
   * The `assertEquals` method
     from [`org.junit.jupiter.api.Assertions`](https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/Assertions.html)
     is used to test that an equality should hold. The `Assertions` class contains many 
     conventient static methods that you might use when testing your code.
     We used a `static import` so that we could write `assertEquals` instead of
     `Assertions.assertEquals`. For more information on static imports, 
     please see [this page](https://docs.oracle.com/javase/8/docs/technotes/guides/language/static-import.html).
     
   * The convention used by the official JUnit documentation is to make
     the test methods have package private visibility.
     
1. Compile the `CounterTest` class, specifying `bin` as the default package for compiled
   code. Since this class relies on your already-compiled `Counter` class and some
   JUnit dependencies, you need to include both `bin` and 
   `junit-platform-console-standalone-1.5.2.jar` on the classpath. 
   Try the following (see the note below if you have trouble):
   
   ```
   $ javac -cp bin:lib/junit-platform-console-standalone-1.5.2.jar \
     -d bin \
     test/cs1302/junit/CounterTest.java
   ```
   
   **NOTE:** The `\` at the end of each line in the previous command allows us to write
   a single command in the shell prompt using multiple lines. This can be convenient when
   typing out long commands. When typing the command, you should end a line with a single
   space followed by`\`, then immediately press the `RET` key. 
   
1. Now let's run the test class. When using `ConsoleLauncher`, you do not need to
   manually include `junit-platform-console-standalone-1.5.2.jar` on the classpath
   (it's included automatically). You specify the class using `-c`:

   ```
   $ ./ConsoleLauncher -cp bin -c cs1302.junit.CounterTest
   ```
   
   Here is the output you should expect:
   
   ```
   + java -jar lib/junit-platform-console-standalone-1.5.2.jar -cp bin -c cs1302.junit.CounterTest

   Thanks for using JUnit! Support its development at https://junit.org/sponsoring
   
   ╷
   ├─ JUnit Jupiter ✔
   │  └─ CounterTest ✔
   │     └─ getValue1() ✔
   └─ JUnit Vintage ✔

   Test run finished after 102 ms
   [         3 containers found      ]
   [         0 containers skipped    ]
   [         3 containers started    ]
   [         0 containers aborted    ]
   [         3 containers successful ]
   [         0 containers failed     ]
   [         1 tests found           ]
   [         0 tests skipped         ]
   [         1 tests started         ]
   [         0 tests aborted         ]
   [         1 tests successful      ]
   [         0 tests failed          ]
   ```
   
1. You can also tell `ConsoleLauncher` to run all classes that it can find on the
   classpath containing test method by specifying the `--scan-classpath` option:

   ```
   $ ./ConsoleLauncher -cp bin --scan-classpath
   ```
   
1. Since we'll frequently want to recompile and test our code, let's create a script
   so that we don't have to manually type out the commands. Create a regular text
   file called `test.sh` with the following contents:
   
   ```bash
   #!/bin/bash -ex
   
   JUNIT_JAR=lib/junit-platform-console-standalone-1.5.2.jar
   rm -rf bin/*
   javac -d bin src/cs1302/junit/Counter.java
   javac -d bin -cp bin:$JUNIT_JAR test/cs1302/junit/CounterTest.java
   ./ConsoleLauncher -cp bin --scan-classpath
   ```
   
   After saving the `test.sh` file, you'll need to give it user execute permission.
   
   ```
   $ chmod u+x test.sh
   ```
   
   Now test the script:
   
   ```
   $ ./test.sh
   ```

## Jupiter API

JUnit 5's API is known as Jupiter. When you write a JUnit test, you'll be using annotations
and methods from the Jupiter API.

1. Look at the documentation for the
   [`org.junit.jupiter.api`](https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/package-summary.html)
   package. **Save a bookmark to that link!** You may need to refer to it when writing tests.

1. Look at the documentation for the 
   [`org.junit.jupiter.api.Assertions`](https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/Assertions.html)
   class. **It has a lot of methods!** Nobody remembers them all (that's not a challenge; it's a matter
   of practicality). You should consult the documentation for an `assert` method in order to test that
   some condition holds. Here are some of the popular ones:
   
   | Name           | Example |
   |----------------|---------|
   | `assertTrue`   | `assertTrue(array.length > 0, "array length should be > 0");`       |
   | `assertFalse`  | `assertFalse(array.length == 0, "array length should not be 0");`   |
   | `assertEquals` | `assertEquals("Emily", person.getName());`                          |
   | `assertThrows` | `assertThrows(IllegalArgumentException.class, () -> list.add(""));` |
   
   **This is not an exhaustive list!** Remember to consult the documentation as needed. One thing
   that you should notice is that each `assert` method includes an overload with a `String` parameter
   that can be used to denote an associated message.

## Adding More Unit Tests

### Testing a Method

1. Let's test the `increment` method. Add the following test method to your `CounterTest` class:

   ```java
   @Test
   void increment1() {
       final Counter counter = new Counter();
       counter.increment();
       assertEquals(1L, counter.getValue());
   } // increment1
   ```
   
1. Compile and run the unit tests using the `./test.sh` script you created in the
   first part of this tutorial. As written, the method should pass the test. 
   
1. Now, let's write a test to make sure that the `IllegalStateException` is
   correctly thrown when the counter's internal value equals `Long.MAX_VALUE`.
   Add the following test method to your `CounterTest` class:
   
   ```java
   @Test
   void increment2() {
       final Counter counter = new Counter(Long.MAX_VALUE);
       assertThrows(IllegalStateException, () -> counter.increment());
   } // increment2
   ```
   
1. Compile and run the unit tests using the `./test.sh` script you created in the
   first part of this tutorial. As written, the method should pass the test. 

### Failing Tests

1. Take any of the tests we've written so far and change them so that they should
   fail.
   
1. Compile and run the unit tests using the `./test.sh` script you created in the
   first part of this tutorial. If you did the previous step correctly, then you
   should see one or more `✘` in the output, indicating that a test failed.  

### Display Names for Tests

1. By default, JUnit will use the name of your test method as the actual test name
   in the reported output. To have JUnit use a more descriptive name, you can use
   the `@DisplayName` annotation 
   from [`org.junit.jupiter.api.DisplayName`](https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/DisplayName.html).
   To try this, add an import statement for `org.junit.jupiter.api.DisplayName`, then 
   update the `increment2` method that we added earlier in this tutorial
   to the following:
   
   ```java
   @DisplayName("Incrementing a maxed out counter throws an IllegalStateException.")
   @Test
   void increment2() {
       final Counter counter = new Counter(Long.MAX_VALUE);
       assertThrows(IllegalStateException, () -> counter.increment());
   } // increment2
   ```
   
1. Compile and run the unit tests using the `./test.sh` script you created in the
   first part of this tutorial. You should see the following in your output
   instead of the method name:
   
   ```
   Incrementing a maxed out counter throws an IllegalStateException. ✔
   ```

### Parameterized Test

1. Let's write a unit test for the overloaded constructor. According the Javadoc
   comment, the overloaded constructor is expected to throw an
   `IllegalArgumentException` when `initValue < 0`. Add the following `import`
   statements to `CounterTest,java` if they are not already there:
   
   ```java
   import static org.junit.jupiter.api.Assertions.assertThrows;
   import org.junit.jupiter.api.DisplayName;
   import org.junit.jupiter.params.ParameterizedTest;
   import org.junit.jupiter.params.provider.ValueSource;
   ```
   
   Now add the following test method to your `CounterTest` class:
   
   ```java
   @DisplayName("A negative initValue value is not supported when calling the the constructor.")
   @ParameterizedTest(name = "For example, letting initValue = {0} is not supported.")
   @ValueSource(longs = { -1, -4 })
   void constructor1(long initValue) {
       assertThrows(IllegalArgumentException.class, () -> new Counter(initValue));
   } // constructor1
   ```
   
1. Compile and run the unit tests using the `./test.sh` script you created in the
   first part of this tutorial. You should see the following in your output:
   
   ```
   ╷
   ├─ JUnit Jupiter ✔
   │  └─ CounterTest ✔
   │     ├─ A negative initValue value is not supported. ✔
   │     │  ├─ For example, letting initValue = -1 is not supported. ✔
   │     │  └─ For example, letting initValue = -4 is not supported. ✔
   ...
   ```

## Test Class Inheritance

1. TBD

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
