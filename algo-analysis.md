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

First, let's focus on **time complexity analysis**. Supose you have a set of
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

### Example Problems

1. **Example 1**

   Here is an algorithm that solves the problem of printing all elements of an array.

   ```java    
   void printA(int[] a) {
       for(int i = 0; i < a.length; i++) {
           System.out.println(a[i]);
       } // for
   } // printA
   ```
  
   **Questions:**

   * What is the problem size?

   * What is `T(n)` if the set of key processing steps only includes print-like statements?

   *Think about the answers to the previous two questions before reading ahead*

   **Towards a Sample Solution:**
   
   * What is the problem size? 
     In this example, the problem size is the array length. 
     Therefore, let `n = a.length`.
     
   * What is `T(n)` if the set of key processing steps only includes print-like statements?
     To answer this question, let's first identify the most nested operation:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { 
             System.out.println(a[i]); // -------> 1
         } // for 
     } // printA
     ```
     
     Next, we identify the enclosing loop:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // -----\
             System.out.println(a[i]); // -------> 1 | n
         } // for // --------------------------------/
     } // printA
     ```
     
     Finally, we identify how many iterations the loop will have with respect to `n`:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // -----\
             System.out.println(a[i]); // -------> 1 | n
         } // for // --------------------------------/
     } // printA
     ```
     
     If you label the operations and iterations as we did in the diagram above, then 
     you can simply multiply across in a simple scenario like this:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // -----\
             System.out.println(a[i]); // -------> 1 | n = 1 * n
         } // for // --------------------------------/
     } // printA
     ```
     
     Therefore, `T(n) = n` for this `printA` method. 

1. **Example 2**:

   Here is an algorithm that solves the problem of printing all elements of an array
   in a square fashion:

   ```java    
   void printA(int[] a) {
       for(int i = 0; i < a.length; i++) {
           for(int j = 0; j < a.length; j++) {
               System.out.print(a[i] + " ");
           } // for
           System.out.println();
       } // for
   } // printA
   ```
   
   **Questions:**

   * What is the problem size?

   * What is `T(n)` if the set of key processing steps only includes print-like statements?

   *Think about the answers to the previous two questions before reading ahead*

   **Towards a Sample Solution:**
   
   * What is the problem size? 
     In this example, the problem size is the array length. 
     Therefore, let `n = a.length`.
     
   * What is `T(n)` if the set of key processing steps only includes print-like statements?
     To answer this question, let's first identify the most nested operation:   

     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) {
             for(int j = 0; j < a.length; j++) {
                 System.out.print(a[i] + " ");  // -------> 1
             } // for
             System.out.println();
         } // for
     } // printA
     ```
     
     Next, we identify the enclosing loop and its number of iterations:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) {
             for(int j = 0; j < a.length; j++) { // ----------\
                 System.out.print(a[i] + " ");  // -------> 1 | n
             } // for  // ------------------------------------/
             System.out.println();
         } // for
     } // printA
     ```
     
     It appears that there is another print-like statement at the same level
     as the loop we just identified: 
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) {
             for(int j = 0; j < a.length; j++) { // ----------\
                 System.out.print(a[i] + " ");  // -------> 1 | n
             } // for  // ------------------------------------/
             System.out.println(); // ------------------------> 1
         } // for
     } // printA
     ```
     
     Now, we identify the next enclosing loop and its number of iterations:

     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // ------------------\
             for(int j = 0; j < a.length; j++) { // ----------\   |
                 System.out.print(a[i] + " ");  // -------> 1 | n | n
             } // for  // ------------------------------------/   |
             System.out.println(); // ------------------------> 1 |
         } // for ------------------------------------------------/
     } // printA
     ```
     
     If you label the operations and iterations as we did in the diagram above, then 
     you can simply multiply across and add up the results in a scenario like this:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // ------------------\
             for(int j = 0; j < a.length; j++) { // ----------\   |
                 System.out.print(a[i] + " ");  // -------> 1 | n | n = 1 * n * n
             } // for  // ------------------------------------/   |   
             System.out.println(); // ------------------------> 1 |   +     1 * n
         } // for ------------------------------------------------/
     } // printA
     ```
     
     Therefore, `T(n) = n^2 + n` for this particular `printA` method.
 
 1. **Example 3 [Tricky]**:

   Here is a modified version of the previous example:

   ```java    
   void printA(int[] a) {
       for(int i = 0; i < a.length; i++) {
           for(int j = 0; j < 10; j++) {
               System.out.print(a[i] + " ");
           } // for
           System.out.println();
       } // for
   } // printA
   ```
   
   **Questions:**

   * What is the problem size?

   * What is `T(n)` if the set of key processing steps only includes print-like statements?

   *Think about the answers to the previous two questions before reading ahead*

   **Towards a Sample Solution:**
   
   * What is the problem size? 
     In this example, the problem size is the array length. 
     Therefore, let `n = a.length`.
     
   * What is `T(n)` if the set of key processing steps only includes print-like statements?
     To answer this question, let's diagram the code the way we did in the previous
     examples:
   
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // -------------------\
             for(int j = 0; j < 10; j++) { // ----------------\    |
                 System.out.print(a[i] + " ");  // -------> 1 | 10 | n
             } // for  // ------------------------------------/    |
             System.out.println(); // ------------------------> 1  |
         } // for -------------------------------------------------/
     } // printA
     ```
     
     This was the tricky part. The inner for-loop only iterates `10` times. 
     This changes the overall timing function. Just as before, you can simply 
     multiply across and add up the results:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // -------------------\
             for(int j = 0; j < 10; j++) { // ----------------\    |
                 System.out.print(a[i] + " ");  // -------> 1 | 10 | n = 1 * 10 * n
             } // for  // ------------------------------------/    |
             System.out.println(); // ------------------------> 1  |   +      1 * n
         } // for -------------------------------------------------/
     } // printA
     ```
     
     Therefore, `T(n) = 11n` for this particular `printA` method!
   
     **Note:** One brutally common mistake is to assume that the number of the
     resulting polynomial always increases with the number of loop nestings. 
     This is not the case, as is illustrated in the last example. Even though
     there are two loops that are nested, the overall degree of the polynomial
     is `1` and not `2`. 

1. **Example 4 [Trickier]**:

   Here is a slightly modified version of the previous example (pay close attention to
   spot the subtle differences):

   ```java    
   void printA(int[] a) {
       for(int i = 0; i < a.length; i++) {
           for(int j = 0; j < i; j++) {
               System.out.print(a[i] + " ");
           } // for
           System.out.println();
       } // for
   } // printA
   ```
   
   **Questions:**

   * What is the problem size?

   * What is `T(n)` if the set of key processing steps only includes print-like statements?

   *Think about the answers to the previous two questions before reading ahead*

   **Towards a Sample Solution:**
   
   * What is the problem size? 
     In this example, the problem size is the array length. 
     Therefore, let `n = a.length`.
     
   * What is `T(n)` if the set of key processing steps only includes print-like statements?
     To answer this question, let's diagram the code the way we did in the previous
     examples:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // -------------------\
             for(int j = i; j < n; j++) { // -----------------\    |
                 System.out.print(a[i] + " ");  // -------> 1 | ≤n | n
             } // for  // ------------------------------------/    |
             System.out.println(); // ------------------------> 1  |
         } // for -------------------------------------------------/
     } // printA
     ```
     
     This was the tricky part. The inner for-loop may have as many as
     `n` iterations, however, this number changes based on what iteration
     of the outer-most loop the code is in. We don't want to mark the loop
     as having the minumum number since that would undercount, however, it's
     okay in this scenarion to mark it with the maximum number (technically
     an overcount) so long as we indicate it's `≤` that number. Just as before, 
     you can simply multiply across and add up the results, this time accounting
     for the presence of `≤` instead of an `=`:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // -------------------\
             for(int j = i; j < n; j++) { // -----------------\    |
                 System.out.print(a[i] + " ");  // -------> 1 | ≤n | n  1 * 10 * n
             } // for  // ------------------------------------/    |
             System.out.println(); // ------------------------> 1  |   +     1 * n
         } // for -------------------------------------------------/
     } // printA
     ```
     
     Therefore, `T(n) ≤ 11n` for this particular `printA` method!
   
     **Note:** You could pick either the min or max in scenarios similar to
     the one encountered above, so long as you consistently make the same
     choice in all such scenarios for a particular analysis. That is, either
     always choose the min or always choose the max. For our purposes, we want 
     to find an uppper-bound for `T(n)`, so choosing the max is perfectly fine.

Congratulations! You now have a basic understanding of algorithm analysis!

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
