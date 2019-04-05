# CSCI 1302 Algorithm Analysis Tutorial

## Introduction

In computer science, there often exist multiple algorithms that a programmer can choose to
use or implement to solve a problem. For example, to sort an array of directly comparable 
values such as integers, there are some famous algorithms such as bubble sort and merge 
sort (see [President Obama on Bubble Sort](https://youtu.be/k4RRi_ntQc8?t=34)).
In scenarios such as these where multiple algorithms are available, it's important
to understand the trade-offs between the different choice. One way to help us do this
is to characterize an algorithm's performance with respect to some criteria. If you're
able to characterize the performance of bubble sort and merge sort, for example, then
you might discover that one is better than the other for your particular use case. 
This is the goal of algorithm analysis. 

We will focus on:

* What it means for an algorithm to be efficient
* The concept of algorithm analysis
* Comparing algorithmic growth functions
* Asymptotic complexity
* Big-O notation

## Algorithm Efficiency

Algorithm analysis can be performed relative to the amount of memory a program uses
(**space complexity**) or the amount of CPU time required to complete the work
(**time complexity**). The amount of CPU time is usually a more interesting issue and
will be the focus of this reading. There is usually trade-off between these two
complexities. 

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
occur if you look closely. For the purposes of analysis, instead of focusing on 
everything, we usually only focus on a set of **key processing steps**. 
These are the operations that we're intested in.

* If downloading images from iTunes, downloading a single image might be the
  key processing step.
* In searching and sorting, the key processing step is usually the number of
  comparisons done.

Sometimes, we might focus on other operations. It depends on the problem. In general,
this set usually comprises of operations that expensive (i.e., take a long time or
require a lot of memory) as they will dominate less expensive operations in their 
impact towards the overall complexity of the algorithm. 

## Time Complexity Analysis

First, let's focus on ((time complexity analysis**. Supose you have a set of
algorithms that all solve the same problem. In order to analyze each of them, 
we first need to do the following:
    
1. Define the problem size = `n`; then

2. Degine the set of key processing steps.

Think of this as identifying the units. We need to define the problem size and
set of key processing steps the same way for all of the algorithms we wish to
compare based on their time complexities so that it's a fair comparison. 

In order to actually determine these time complexities, we need to do the
following: 
    
3. Derive a **timing function**, `T(n)`, that reflects the number of key processing
   steps in terms of the problem size.
  
4. Classify `T(n)` into a **complexity class** based on the formula for the function.

That's it! Those four steps are what you need to do in order to perform a time complexity
analysis for an algorithm. The trickiest part is usually the third step, which is what
most of this tutorial will focus on. We will have a separate tutorial that focuses 
exclusively on the fourth step.

## Example Problems

1. **Example 1**

   Here is an algorithm that solves the problem of printing the numbers `0` through `n` 
   (exclusive).

   ```java    
   void printN(int n) {
       for(int i = 0; i < n; i++) {
           System.out.println(n);
       } // for
   } // printN
   ```
  
   Questions:

   * What is the problem size?

   * What is `T(n)` if the set of key processing steps only includes `System.out.println`?

   **Think about the answers to the previous two questions before reading ahead**

   Towards a Sample Solution:

   * To derive the timing function, you might consider putting in a few values for `n`. For example,
     if `n` is 5, how many times does the key processing step execute? For example:

     |    `n`   |   `T(n)` |
     |----------|----------|
     | 5        |     ?    |
     | 100      |     ?    |
     | 1000     |     ?    |
     | 100000   |     ?    |

   * After thinking about these, can you define the timing function as a function of `n`?

   Sample Solution Derivation:
   
   ```
   void printN(int n) {
       for(int i = 0; i < n; i++) {                              +--+
           System.out.println(n);  <------- 1 println per iteration | n iterations
       } // for                                                  +--+
   } // printN
   ```
   
   * What is the problem size? In this example, the problem size is the parameter, `n`.
    
   * What is `T(n)`? In this example, `T(n) = 1 * n = n`.
    
1. **Example 2**:

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

What is `T(n)` if the key processing step is `System.out.println`?

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
`T(n) = 1 * n * n = n^2`

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

What is `T(n)` if the key processing step is `System.out.println`?

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

What is `T(n)` if the key processing step is `System.out.println`?

This is the tricky part. **Note**: just because we have a nested loop, that doesn't mean the
order of the polynomial is increased. Since the inner loop always executes 10 times, our
timing function would be:

`T(n) = 1 * 10 * n = 10n`

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

What is `T(n)` if the key processing step is `System.out.println`?

**Think about the answers to the previous two questions before reading ahead**


```
void printN(int n) {
    for (int i = 0; i < n; i++) {                             +------+
        System.out.println(i);               <----------+ 1 per iter |
        for (int j = 0; j < n; j++) {                +--+            |
            System.out.println(i+j); <-----  1 per iter | n iters    | n iters
        } // for                                     +--+            |
        System.out.println(i);               <----------+ 1 per iter |
    } // for                                                  +------+
} // printN

```

What is the problem size?
In this example, the problem size is the parameter `n`.

What is `T(n)` if the key processing step is `System.out.println`?

This is the `trickier` part. To the get timing function, we can multiply the values above
going across then add going down. The steps would look something like this:

1. The first `println` statement happens once per iteration of the outer loop and this outer 
   loop executes `n` times.  The total number of times this line executes is `1 * n = n`.
   (an example of multiplying across).
2. The second `println` statement (nested within two loops) executes once per execution
   of the inner loop which executes `n` times. Since the inner loop is wrapped in a loop
   that also executes `n` times, the total number of times this `println` statement executes
   is `1 * n * n = n^2`. (Again, multiplying across)
3. Similar to the first `println` statement, the last one executes `n` times.
4. Now, we add the three values we just calculated (add going down) to get: 
   `T(n) = n + n + n^2 = n^2 + 2n`.

    `T(n) = n^2 + 2n`


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

`T(n) <= (1 * n) + (1 * n * n) + (1 * n)` = `n^2 + 2n`

Congratulations! You now have a basic understanding of algorithm analysis!

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
