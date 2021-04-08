# Java Streams and Chaining

![Approved for: Spring 2021](https://img.shields.io/badge/Approved%20for-Spring%202021-success)

## Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience 
working with a command-line text editor (e.g. emacs, vi, etc.). Readers should also be familiar
with compiling and running Java programs from the command-line. 
To get the most out of this tutorial, you should follow along and take notes.

## Course-Specific Learning Outcomes

## Java Streams and Chaining Video

In this video, we demonstrate how to use Java Streams and method chaining to
rapidly produce solutions without writing any loop code ourselves.

https://youtu.be/CWYQ-OqDe74

<a href="https://youtu.be/CWYQ-OqDe74">
<img src="https://img.youtube.com/vi/CWYQ-OqDe74/0.jpg?20190727" alt="IMAGE ALT TEXT">
</a>

## Example Starter Code

Here is the example code for the first video:

```java
package cs1302.timestream;

import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream Examples using {@code java.time}.
 */
public class TimeStreamDriver {

    public static void main(String[] args) {

        LocalDate date = Dates.random();
        System.out.println(date);

    } // main


    /*
    public static LocalDate[] randomDates(long n) {
        return ?
    } // randomDates
    */


    public static void banner(String str) {
        System.out.printf("\n**** %s\n", str);
    } // banner

} // TimeStreamDriver
```

```java
package cs1302.timestream;

import java.time.LocalDate;
import java.time.Year;
import java.util.Random;

/**
 * Utility class for {@code java.time.LocalDate}
 * generation.
 */
public class Dates {

    /** Random Number Generator. */
    private static final Random RNG = new Random();

    /** 1970-01-01T00:00, the beginning of time. */
    public static final LocalDate EPOCH = LocalDate.ofEpochDay(0);

    /**
     * Returns a randomly generated {@code LocalDate}
     * within 100 years of the {@code EPOCH}.
     * @return a randomly generated {@code LocalDate}
     */
    public static LocalDate random() {
        long maxDays = EPOCH.lengthOfYear() * 100L;
        long rngDays = RNG.nextLong() % maxDays;
        return EPOCH.plusDays(rngDays);
    } // random

} // Dates
```

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
