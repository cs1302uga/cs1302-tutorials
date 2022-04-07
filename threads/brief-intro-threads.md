# Brief Introduction to Java Threads

![Approved for: Spring 2022](https://img.shields.io/badge/Approved%20for-Spring%202022-purple) 

## Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working with a command-line text editor (e.g. emacs, vi, etc.). Readers should also be familiar with compiling and running Java programs from the command-line. 
To get the most out of this tutorial, you should follow along and take notes.

## Course-Specific Learning Outcomes

* **LO7.a:** Design and implement a graphical user interface in a software project.

## Introduction Video

In this video, we introduce the concept of a thread and provide some code examples to illustrate how threads 
can be used to make your program execute two pieces of code at the same time. Many of the definitions presented 
in this video are simplified in order to make them more approachable for beginners.

https://youtu.be/zCeo15G3nvI

<a href="https://www.youtube.com/watch?v=zCeo15G3nvI">
<img src="https://img.youtube.com/vi/zCeo15G3nvI/0.jpg?20190726" alt="IMAGE ALT TEXT">
</a>

### Basic Terms

<dl>
    <dt>thread</dt>
    <dd>a sequence of instructions emitted by executing a method</dd>
    <dt>main thread</dt>
    <dd>the thread for instructions emitted by the main method</dd>
</dl> 

### Daemon Threads

In Java, every `Thread` object has a boolean property named `daemon` that 
impacts when a program can exit. Under normal conditions, a Java program
will only exit under two conditions:

1. explicit exit: the `exit(int)` method in the `Runtime` class is called
   (e.g., as is done by `System.exit(int)`[^1]); or
   
2. implicit exit: all the threads currently running are *daemon* threads 
   (i.e., they have their `daemon` property set to `true`) [^2].

| Thread Type      | `isDaemon()` | Prevent implicit exit? |
|------------------|--------------|------------------------|
| *non-daemon*[^3] | `false`      | `true`                 |
| *daemon*         | `true`       | `false`                |

A thread's `daemon` property cannot be changed after the thread has started executing
(i.e., after it's `start()` method has been called). When a new thread is created, its
`daemon` property is set to the same as the thread that created it. 

The *main thread* is a *non-daemon thread*, so any new threads that are 
created in the main thread will also be non-daemon threads, i.e., unless their 
`daemon` value is changed to `true` using using `setDaemon(true)` before they 
are started.

[^1]: [System.exit(int)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#exit(int))

[^2]: Per the API documentation for the 
[`Thread` class](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Thread.html),
the implicit exit condition can also be phrased as follows: all started non-daemon threads
have finished executing.

[^3]: In offocial Java documentation, a non-daemon thread is often referred to as a
*user thread*; however, we chose not to use that term here since its 
[usual definition](https://en.wikipedia.org/wiki/Thread_(computing)#User_threads)
would include both both daemon and non-daemon threads in Java.

## JavaFX Application Thread

In this video, we briefly discuss the JavaFX Application Cycle and the JavaFX Application Thread 
and how their design might impact your code for event handlers and programmatic changes to 
the scene graph.

https://youtu.be/9qCUqzYGGpo

<a href="https://www.youtube.com/watch?v=9qCUqzYGGpo">
<img src="https://img.youtube.com/vi/9qCUqzYGGpo/0.jpg?20190726" alt="IMAGE ALT TEXT">
</a>

## Example Starter Code

Here is the example code for the first video:

```java
package cs1302.threads;

/**
 * Driver for thread example.
 */
public class ThreadDriver {

    public static void main(String[] args) {
        loop("###");
        loop("---");
        System.err.println("main thread is done.");
    } // main

    /**
     * Executes an infinite loop.
     * @param name loop name
     */
    public static void loop(String loopName) {
        int x = 0;
        while (true) {
            System.err.printf("%s-%d\n", loopName, x);
            x += 1;
        } // while
    } // loop

    /**
     * Creates and immediately starts a new daemon thread that executes
     * {@code target.run()}. This method, which may be called from any thread,
     * will return immediately its the caller.
     * @param target the object whose {@code run} method is invoked when this
     *               thread is started
     */
    public static void runNow(Runnable target) {
        Thread t = new Thread(target);
        t.setDaemon(true);
        t.start();
    } // runNow

} // ThreadDriver
```

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
