# CSCI 1302 Algorithm Analysis Tutorial

## Introduction

In computer science it is important to understand how efficient a particular algorithm
is to determine if it is appropriate for the application. This tutorial is designed to
help you understand the basics of algorithm analysis.

We will focus on:

    * What it means for an algorithm to be efficient
    * The concept of algorithm analysis
    * Comparing algorithmic growth functions
    * Asymptotic complexity
    * Big-O notation

## Algorithm Efficiency

An algorithm's efficiency is a major factor in determining how fast a program executes.

Algorithm analysis can be performed relative to the amount of memory a program uses
(**space complexity**) or the amount of CPU time required to complete the work
(**time complexity**). The amount of CPU time is usually a more interesting issue and
will be the focus of this reading.

## Defining Problem Size

For every algorithm we want to analyze, we need to define the **size of the problem**.

    * When downloading a file, the size of the problem is the size of the file.
    * When searching for a target value, the size of the problem is the size of the
    search pool (e.g. if finding a value in an array, the array size is the problem
    size).
    * For a sorting algorithm, the size of the problem is the number of elements to
    be sorted.
    * When downloading images from iTunes, the problem size is the number of images
    to be downloaded.

## Processing Steps

Algorithms (or more generally functions or methods) have many operations/steps that
occur if you look closely.

For the purposes of analysis, instead of focusing on everything, we usually only focus
on **key processing steps**. These are the operations that we're intested in.

    * If downloading images from iTunes, downloading a single image might be the
    key processing step.
    * In searching and sorting, the key processing step is usually the number of
    comparisons done.

Sometimes, we might focus on other operations. It depends on the problem.

## Time Complexity Analysis

Given:
    
    * Problem size = n
    * Set of key processing steps

Goal:
    
    * Derive a **timing function**, T(n), that reflects the number of key processing
    steps in terms of the problem size.
    * Classify T(n) into a **complexity class** based on the formula for the function.


## Examples

**Example 1**:

```java    
void printN(int n) {
    for(int i = 0; i < n; i++) {
        System.out.println(n);
    } // for
} // printN
```
    
What is the problem size?

What is T(n) if the key processing step is `System.out.println`?

**Think about the answers to the previous two questions before reading ahead**
To derive the timing function, you might consider putting in a few values for `n`. For example,
if `n` is 5, how many times does the key processing step execute? If `n` is 10? 100? 1000000?
After thinking about these, can you define the timing function in terms of `n`?

<table>
   <tr>
      <td>
         <pre>    void printN(int n) {
        for(int i = 0; i < n; i++) {
            System.out.println(n);
        } // for
    } // printN </pre>
      </td>
      <td>   
         <pre>
                                +---+
                                    |
<-------- 1 println per iteration   | n iterations
                                    |
                                +---+
         </pre>
      </td>
   </tr>
</table>
   
What is the problem size?
In this example, the problem size is the parameter, `n`.
    
What is T(n) if the key processing step is `System.out.println`?
T(n) = 1 * n = n
    
**Example 2**:

```java
void printN(int n) {
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            System.out.println(i + j);
        } // for
    } // for
} // printN
```

What is the problem size?

What is T(n) if the key processing step is `System.out.println`?

**Think about the answers to the previous two questions before reading ahead**


```
void printN(int n) {
    for(int i = 0; i < n; i++) {                                             +--+
        for(int j = 0; j < n; j++) {                               +--+         |
            System.out.println(i + j); <----- 1 println per iteration | n iters | n iters
        } // for                                                   +--+         |
    } // for                                                                 +--+
} // printN
```

What is the problem size?
In this example, the problem size is the parameter `n`.

What is T(n) if the key processing step is `System.out.println`?
T(n) = 1 * n * n = n^2

**Example 3 [Tricky]**:

```java
void printN(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 10; j++) {
            System.out.println(i+j);
        } // for
    } // for
} // printN
```

What is the problem size?

What is T(n) if the key processing step is `System.out.println`?

**Think about the answers to the previous two questions before reading ahead**


```
void printN(int n) {
    for(int i = 0; i < n; i++) {                                              +--+
        for(int j = 0; j < 10; j++) {                              +--+          |
            System.out.println(i + j); <----- 1 println per iteration | 10 iters | n iters
        } // for                                                   +--+          |
    } // for                                                                  +--+
} // printN
```

What is the problem size?
In this example, the problem size is the parameter `n`.

What is T(n) if the key processing step is `System.out.println`?

This is the tricky part. **Note**: just because we have a nested loop, that doesn't mean the
order of the polynomial is increased. Since the inner loop always executes 10 times, our
timing function would be:

T(n) = 1 * 10 * n = 10n

**Example 4 [Trickier]**:

```java
void printN(int n) {
    for (int i = 0; i < n; i++) {
        System.out.println(i);
        for (int j = 0; j < n; j++) {
            System.out.println(i+j);
        } // for
        System.out.println(i);
    } // for
} // printN
```

What is the problem size?

What is T(n) if the key processing step is `System.out.println`?

**Think about the answers to the previous two questions before reading ahead**


```
void printN(int n) {
    for (int i = 0; i < n; i++) {                             +------+
        System.out.println(i);               <----------+ 1 per iter |
        for (int j = 0; j < n; j++) {                +--+            |
            System.out.println(i+j); <-----+ 1 per iter | n iters    | n iters
        } // for                                     +--+            |
        System.out.println(i);               <----------+ 1 per iter |
    } // for                                                  +------+
} // printN

```

What is the problem size?
In this example, the problem size is the parameter `n`.

What is T(n) if the key processing step is `System.out.println`?

This is the `trickier` part. To the get timing function, we can multiply the values above
going across then add going down. The steps would look something like this:
    1. The first `println` statement happens once per iteration of the outer loop and this outer 
       loop executes `n` times.  The total number of times this line executes is `1 * n = `**n**.
       (an example of multiplying across).
    2. The second `println` statement (nested within two loops) executes once per execution
       of the inner loop which executes `n` times. Since the inner loop is wrapped in a loop
       that also executes `n` times, the total number of times this `println` statement executes
       is `1 * n * n = `**n^2**. (Again, multiplying across)
    3. Similar to the first `println` statement, the last one executes `n` times.
    4. Now, we add the three values we just calculated (add going down) to get: 
       `T(n) = n + n + n^2 =` **n^2 + 2n**.

T(n) = n^2 + 2n


**Example 5 [Even Trickier]**:

```java
void printN(int n) {
    for (int i = 0; i < n; i++) {
        System.out.println(i);
        for (int j = i; j < n; j++) {
            System.out.println(i+j);
        } // for
    System.out.println(i);
    } // for
} // printN
```

What is the problem size?
In this example, the problem size is the parameter `n`.

What is T(n) if the key processing step is `System.out.println`?

In this example, an exact formula for T(n) is tough to compute.  We can, however, give a worst case
analysis of:

T(n) <= (1 * `n`) + (1 * `n` * `n`) + (1 * `n`) = `n`^2 + 2n

Congratulations! You now have a basic understanding of algorithm analysis!

<hr/>
[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
