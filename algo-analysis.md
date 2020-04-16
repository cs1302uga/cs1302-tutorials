# CSCI 1302 Algorithm Analysis Tutorial

![Approved for: Spring 2020](https://img.shields.io/badge/Approved%20for-Spring%202020-blue)

## Introduction

In computer science, there often exist multiple algorithms that a programmer can choose to
use or implement to solve a problem. For example, to sort an array of directly comparable 
values such as integers, there are some famous algorithms such as bubble sort and merge 
sort (see [President Obama on Bubble Sort](https://youtu.be/k4RRi_ntQc8?t=34)).
In scenarios such as these where multiple algorithms are available, it's important
to understand the trade-offs between the different choices. One way to help us do this
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
will be the focus of this reading. There is usually a trade-off between these two
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
  to be downloaded (or the total number of bytes).

## Processing Steps

Algorithms (or more generally functions or methods) have many operations/steps that
occur if you look closely. For the purpose of analysis, instead of focusing on 
everything, we usually only focus on a set of **key processing steps**. 
These are the operations that we're intested in.

* If downloading images from iTunes, downloading a single image might be the
  key processing step.
  
* In searching and sorting, the key processing step is usually the number of
  comparisons done.

Sometimes, we might focus on other operations. The key processing step ultimately
depends on the problem in question. In general, this set usually comprises of operations 
that are expensive (i.e., take a long time or require a lot of memory) as they will 
dominate less expensive operations in their impact towards the overall complexity of 
the algorithm. 

## Time Complexity Analysis

First, let's focus on **time complexity analysis**. Suppose you have a set of
algorithms that all solve the same problem. In order to analyze each of them, 
we first need to do the following:
    
1. Define the problem size (`n`); then

2. Define the set of key processing steps.

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
     **Note:** We choose print statements as our key processing step because they take significantly longer
     to execute when compared to the other operations in this method (addition, assignment, array access, etc.).

   *Think about the answers to the previous two questions before reading ahead*

   **Towards a Sample Solution:**
   
   * What is the problem size? 
     In this example, the problem size is the array length. We know this because the number of operations
     that will execute is determined by the length of the array. If we increase the size of `a`, that directly
     impacts the number of print statements that will execute.
     Therefore, let `n = a.length`.
     
   * What is `T(n)` if the set of key processing steps only includes print-like statements?
     
     You could try to find a pattern by performing multiple executions of the program
     with different array lengths and recording the number of print-like statements:
     
     | `n = a.length` | `# print-like statements` |
     |----------------|---------------------------|
     |            `0` |                       `0` |
     |            `1` |                       `1` |
     |            `2` |                       `2` |
     |          `...` |                     `...` |
     |            `4` |                       `4` |
     |          `...` |                     `...` |
     |            `8` |                       `8` |
     |          `...` |                     `...` |
     |           `16` |                      `16` |
     |          `...` |                     `...` |
     
     After multiple executions, we think we see a pattern: `T(n) = n`. Indeed, if we
     check this formula for each row in the table we made, then we'll see that this
     formula works! However, this strategy of finding a pattern is often tedious and
     error prone as the algorithms get more complicated. Instead, we prefer to use
     a more systematic approach that leads to less errors and gives us insight into
     the structure of the algorithm with respect to the key processing steps.
     
     **NOTE:** That being said, creating the table is a good way to verify your 
     formula for `T(n)` after deriving it systematically.
   
     Now, to derive `T(n)` for this algorithm in a more systematic way, let's first 
     identify the most nested operation -- here, operation refers to any instruction
     in the set of key processing steps:
     
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
             System.out.println(a[i]); // -------> 1 | n ⇒ T(n) = 1 * n
         } // for // --------------------------------/
     } // printA
     ```
     
     Therefore, `T(n) = n` for this `printA` method. You might read this as "The number of
     print statements this method performs is equal to the length of the array".

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
                 System.out.print(a[i] + " ");  // -------> 1 | n | n ⇒ T(n) = 1 * n * n
             } // for  // ------------------------------------/   |   
             System.out.println(); // ------------------------> 1 |           +     1 * n
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
                 System.out.print(a[i] + " ");  // -------> 1 | 10 | n ⇒ T(n) = 1 * 10 * n
             } // for  // ------------------------------------/    |
             System.out.println(); // ------------------------> 1  |           +      1 * n
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
         for(int i = 0; i < a.length; i++) { // --------------------\
             for(int j = 0; j < i; j++) { // -----------------\     |
                 System.out.print(a[i] + " ");  // -------> 1 | < n | n
             } // for  // ------------------------------------/     |
             System.out.println(); // ------------------------> 1   |
         } // for --------------------------------------------------/
     } // printA
     ```
     
     This was the tricky part. The inner for-loop may have as many as
     `n - 1` iterations, however, this number changes based on what iteration
     of the outer-most loop the code is in. We don't want to mark the loop
     as having the minumum number since that would undercount, however, it's
     okay in this scenarion to mark it with the maximum number (technically
     an overcount) so long as we indicate it's `<` that number. Just as before, 
     you can simply multiply across and add up the results, this time accounting
     for the presence of `<` instead of an `=`:
     
     ```java    
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // --------------------\
             for(int j = 0; j < i; j++) { // -----------------\     |
                 System.out.print(a[i] + " ");  // -------> 1 | < n | n ⇒ T(n) < 1 * n * n
             } // for  // ------------------------------------/     |
             System.out.println(); // ------------------------> 1   |           +     1 * n
         } // for --------------------------------------------------/
     } // printA
     ```
     
     Therefore, `T(n) < n^2 + n` for this particular `printA` method!

1. **<a id="composition"/>Example 5 [Even Trickier]**:

   Below is a slightly modified version of the previous example, now decomposed
   into two separate methods.

   ```java 
   void printUntil(int[] a, int i) {
       for(int j = 0; j < i; j++) {
           System.out.print(a[i] + " ");
       } // for
   } // printUntil
   ```
   
   ```java
   void printA(int[] a) {
       for(int i = 0; i < a.length; i++) {
           printUntil(a, i);
           System.out.println();
       } // for
   } // printA
   ```
   
   Here, we will focus our analysis on the algorithm described by `printA`.
   Since this example has the same code as the previous example, albeit split
   between two methods, our analysis should result in the same timing function.  
   
   **Questions:**

   * What is the problem size?

   * What is `T(n)` if the set of key processing steps only includes print-like statements?

   *Think about the answers to the previous two questions before reading ahead*

   **Towards a Sample Solution:**
   
   * What is the problem size? 
     In this example, **we actually have two problem sizes!**
     
     * The problem size for `printA` is the array length because
       increasing or decreasing that length impacts how long the
       method takes to execute. Therefore, let `n = a.length` when
       discussing this method.
       
     * The problem size for `printUntil` is its parameter `i`. Although
       we are passing the array into the method, the array's length
       does not impact how long this method takes to execute; however,
       it's parameter `i` does. Therefore, let `n = i` when discussing
       this method.
   
   * Before we continue, please remember that `n` (the problem size) means
     something different depending on the method that we're analyzing.
   
   * First, let's analyze `printUntil` to derive its timing function. So that
     we don't confuse the function we derive for this method with the one we'll
     derive for `printA`, let `U(n)` denote for this method where `n = i`.
     What is `U(n)` if the set of key processing steps only includes print-like statements?
     To answer this question, let's diagram the code the way we did in the previous
     examples:
   
     ```java 
     void printUntil(int[] a, int i) {
         for(int j = 0; j < i; j++) { // -------------------\
             System.out.print(a[i] + " "); // ----------> 1 | = i ⇒ U(n) = 1 * i 
         } // for // ---------------------------------------/             = 1 * n [since n = i]
     } // printUntil                                                      = n
     ```
   
   * As we can see, `U(n) = n` print-like statements for `printUntil` where `n = i`.
     Remember that the problem size `n` for `printUntil` is different than the problem
     size for `printA`. 
   
   * Now that we have derived the timing function for our helper method, let's analyze
     the `printA` method. What is `T(n)` if the set of key processing steps only includes 
     print-like statements?
     Remember that the problem size `n` for `printA` is different than the problem
     size for `printUntil`.
     To answer this question, let's diagram the code the way we did in the previous
     examples:
     
     ```java
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // ----------------\
             printUntil(a, i);     // -------------------> U(i) | n [where n = a.length]
             System.out.println(); // -------------------> 1    |
         } // for // -------------------------------------------/
     } // printA
     ```
     
     Before we continue, we note that `U(i)` here is the `U(n)` function that we previously derived for
     `printUntil` called to explicitly supply `i` as its `n` value. This is called **function composition**.  
     Since `printA` utilizes `printUntil`, it stands to reason that `T(n)` needs to utilize `U(n)`:
     
   * To complete this tricky derivation, we now use the result of the function composition
     to replace `U(n)` with its result:
     
     ```java
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // ---------------\
             printUntil(a, i);     // -------------------> i   | n [since U(i) = i]
             System.out.println(); // -------------------> 1   |
         } // for // ------------------------------------------/
     } // printA
     ```
     
     We want out final derivation to be in terms of `n`. Within the observed loop, we see
     that `printUntil(a, i)` will execute `1 * i` print-like statements. We further
     observe that the largest value that `i` can be inside its enclosed loop is `n`.
     Therefore, we simplify the expression in order to provide a reasonable upper bound with
     respect to `n`, then complete the derivation:
     
     ```java
     void printA(int[] a) {
         for(int i = 0; i < a.length; i++) { // ---------------\
             printUntil(a, i);     // -------------------> < n | n ⇒ T(n) < n * n
             System.out.println(); // -------------------> 1   |           + 1 * n
         } // for // ------------------------------------------/
     } // printA
     ```
     
     Therefore, `T(n) < n^2 + n` for this particular `printA` method! This should not be
     surprising as this is the same algorithm from **Example 4** decomposed into two
     separate methods.
     
1. **Example 6 [Even Trickier 2]**:

   Here is an algorithm that solves the problem of printing characters of a string, in
   reverse, cutting the index value by `3` each time.

   ```java    
   void printS(String s) {
       for(int i = s.length() - 1; i > 0; i = i / 3) {
           System.out.println(s.charAt(i));
       } // for
   } // printS
   ```
  
   **Questions:**

   * What is the problem size?

   * What is `T(n)` if the set of key processing steps only includes print-like statements?

   *Think about the answers to the previous two questions before reading ahead*

   **Towards a Sample Solution:**
   
   * What is the problem size? 
     In this example, the problem size is the string length. 
     Therefore, let `n = s.length()`.
     
   * What is `T(n)` if the set of key processing steps only includes print-like statements?
     If we follow our established strategy for a derivation, we might end up with the
     following diagram:
     
     ```java    
     void printS(String s) {
         for(int i = s.length() - 1; i > 0; i = i / 3) { // --\
             System.out.println(s.charAt(i)); // ---------> 1 | < n ⇒ T(n) < 1 * n
         } // for // -----------------------------------------/
     } // printS
     ```
     
     While this **is correct**, it is **not the best estimate for an upper bound** that
     we can derive for the number of iterations of the observed loop. To get a better
     estimate, we first observe that this loop behaves a little differently than the
     loops we've observed in the previous examples -- instead of incrementing or decrementing
     by some fixed value, the loop variable instead decreases by multiplying or dividing
     by some fixed value. In this case, the loop variable `i` is divided by `3` after each
     iteration. We can use this to our advantage to derive a better estimate.
     
     Without loss of generality, assume that `n = s.length()` is a power of `3`. To
     determine how many iterations of the loop occur, we might ask the following
     question:
     
     > If you start with `n - 1`, how many times can you integer divide by `3` before you 
     > get to `0`?
     
     This can be slightly reworded to:
     
     > If you start with `n`, how many times can you integer divide by `3` until 
     > the value is `1`?
     
     If we let `k` denote the number of iterations of the loop, then we have the following:
     
     ```java    
     void printS(String s) {
         for(int i = s.length() - 1; i > 0; i = i / 3) { // --\
             System.out.println(s.charAt(i)); // ---------> 1 | k
         } // for // -----------------------------------------/
     } // printS
     ```
     
     This can be modeled as a math problem:
     
     ```
     n / 3^k = 1
     ```
     
     To answer the question "how many times can you integer divide", we need only solve 
     for `k` using some precalculus magic (i.e., logarithms):
     
     1. Original model:
        
        ```
        n / 3^k = 1
        ```
     
     1. Multiply both sides by `3^k`:
     
        ```
        3^k = n
        ```
     
     1. Take the logarithm of both sides:
     
        ```
        log(3^k) = log(n)
        ```
     
     1. Move the exponent out of the logarithm:
     
        ```
        k * log(3) = log(n)
        ```
     
     1. Divide both sides by `log(3)`:
     
        ```
        k = log(n) / log(3)
        ```
     
     1. Assuming both logarithms are the same base (they are), dividing the first
        logarithm by `log(3)` changes the base of the logarithm to `3`:
     
        ```
        k = log3(n)
        ```
     
        where `log3(n)` denotes the base-3 logarithm of `n`. 
        
     We did make a pretty big assumption that `n` is a power of `3`. 
     If `n` is not a power of `3`, then the base-3 logarithm will
     not be in an integer -- this poses a problem as the count for the number of iterations
     must be an integer. We could get fancy as use the `ceil` (ceiling function) to
     get an exact answer:
     
     ```
     k = ceil(log3(n))
     ```
     
     However, this will make formally establishing a complexity class (covered later) a little
     trickier. Instead, we'll let `k` denote an upper bound instead of an equality -- that is,
     we'll express it as some value (assumed to be an integer) less than or equal to 
     the logarithm plus one:
     
     ```
     k ≤ log3(n) + 1
     ```
     
     This is okay since `ceil(log3(n)) ≤ log3(n) + 1` for all reasonable `n` values. In a
     Discrete Mathematics course, you would need to be more rigorous about this. For now,
     we'll take it as a reasonable estimate. Now that we have a value for `k`, we
     can subsitute it into our derivation: 
     
     ```java    
     void printS(String s) {
         for(int i = s.length() - 1; i > 0; i = i / 3) { // --\
             System.out.println(s.charAt(i)); // ---------> 1 | ≤ log3(n) + 1 ⇒ T(n) ≤ 1 * log3(n) + 1
         } // for // -----------------------------------------/
     } // printS
     ```
     
     Therefore, `T(n) ≤ log3(n) + 1` for this particular `printS` method! Taking
     advantage of the way the loop iterates differently (i.e., multiply/dividing
     vs. adding/subtracting) leads us to a much better estimate for the number
     of key processing steps with respect to the problem size.
     
## Space Complexity Analysis

Now, let's briefly focus on **space complexity analysis**. Suppose you have a set of
algorithms that all solve the same problem. In order to analyze each of them, 
we first need to do the following:
    
1. Define the problem size = `n`; then

2. Define the unit of measurement.

We need to define the problem size and the unit the same way for all of the 
algorithms we wish to compare based on their spave complexities so that it's a 
fair comparison. 

In order to actually determine these space complexities, we need to do the
following: 
    
3. Derive a **spacing function**, `S(n)`, that reflects the number of units required
   to solve the problem in terms of the problem size.
  
4. Classify `S(n)` into a **complexity class** based on the formula for the function.

That's it! Those four steps are what you need to do in order to perform a space complexity
analysis for an algorithm. The trickiest part is usually the third step, which is what
most of this tutorial will focus on. We will have a separate tutorial that focuses 
exclusively on the fourth step.

### Example Problems

1. **Iterative Example**

   Here is an algorithm that solves the problem of iteratively printing all characters 
   of a string:

   ```java    
   void printS(String s) {
       for(int i = init; i < s.length(); i++) {
           System.out.println(s.charAt(i));
       } // for
   } // printS
   ```
  
   **Considerations:**

   * As usual, let's define the problem size as the input length. 
     Therefore, let `n = s.length()`.

   * Let's also define the unit of measurement as bytes.

   **Towards a Sample Solution:**
   
   * What is `S(n)` if the units are bytes? Without loss of generality, we'll 
     assume that `init` is initially supplied a value of `0`. 
     To answer this question, let's first consider how much memory the string
     itself takes and use that as a starting amount for `S(n)`:
     
     ```
     S(n) = (n * 1) + ?
     ```
     
     ```
     S(n) = n + ?
     ```
     
     This starting value is derived from the fact that a `char` in Java is
     one byte. Additionally, each of the local variables for the method take
     up a spot in the method's call stack frame:
     
     | Variable | Description                       |
     |----------|-----------------------------------|
     | `s`      | `8`-byte local reference variable |
     | `i`      | `4`-byte local `int` variable     |

     Therefore, we get the following spacing function:
     
     ```
     S(n) = n + 12
     ```
     
1. **Recursive Example**

   Here is an algorithm that solves the problem of recursively printing all characters 
   of a string:

   ```java    
   void printS(String s) {
       if (!s.isEmpty()) {
           System.out.println(s.charAt(0));
           printS(s.substring(1));
       } // if
   } // printS
   ```
  
   **Considerations:**

   * As usual, let's define the problem size as the array length. 
     Therefore, let `n = s.length()`.

   * Let's also define the unit of measurement as bytes.

   **Towards a Sample Solution:**
   
   * What is `S(n)` if the units are bytes? Without loss of generality, we'll 
     assume that `init` is initially supplied a value of `0`. 
     To answer this question, let's first consider how much memory the string
     itself takes and use that as a starting amount for `S(n)`:
     
     ```
     S(n) = n + ?
     ```
     
     This starting value is derived from the fact that a `char` in Java is
     1 byte. Additionally, each of the local variables for the method take
     up a spot in the method's call stack frame:
     
     | Variable | Description                       |
     |----------|-----------------------------------|
     | `s`      | `8`-byte local reference variable |

     This provides us with something like this:
     
     ```
     S(n) = n + 8 + ?
     ```     
     
     However, we're not done! In order to complete it's work, the recursive
     `printR` method calls itself on a slightly smaller `printS` method:
     
     ```
     S(n) = n + 8 + S(n - 1)
     S(0) = 8
     ```
     
     This is known as a recurrence relation, and it does not have an obvious,
     intuitive solution. You should learn how to solve relations such as this
     in a Discrete Mathematics course. For now, we'll provide you with the
     solution:
     
     ```
     S(n) = 0.5 * (n + 1)(n + 16)
     ```
     
     This can simplified to the following if we're concerned with identifying
     an upper bound:
     
     ```
     S(n) ≤ n^2 + 17n + 16
     ```
    
 ## Closing Remarks

Congratulations! You now have a basic understanding of the first three steps involved in
an algorithm analysis! We will have a separate tutorial that focuses exclusively on the 
fourth step.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
