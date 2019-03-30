# CSCI 1302 Recursion Tutorial

## Introduction

This tutorial is designed to help you learn about and understand recursion, a powerful
programming technique in which a method can call itself in orer to fulfill its purpose.

A **recursive definition** is one which uses the word or concept being defined in the
definition itself.

   `GNU stands for "GNU's not Unix"`

In some situations, a recursive definition can be an appropriate and concise 
way to express a concept. Before applying recursion to programming, it is best to 
practice thinking recursively.

## Inductively Defined Recursive Data Definition

An inductively defined recursive data definition is one that specifies how to construct
instances of the data. We often call these **recursive definitions**.

**Example**: An inductively defined recursive _list_ definition

Consider the following list of numbers:

   `88, 42, 37`

Such a list can be defined recursively. 

A `LIST` is a:
   * number `#`
   * or a number comma LIST `#, LIST`

That is, a `LIST` can be a `#` or a `#, LIST`. 
Here, the concept of a `LIST` is used to define itself!

We can demonstrate that `88, 42, 37` is a `LIST` using the inductively defined recursive data definition by
following the definition one step at a time until we reach the base case.

1. `88, 42, 37` does not correspond to the base case. So, we must verify that it matches the recursive case.
We see that it starts with a number and a comma. However, in order to show that this is a `LIST`, we need to know
if `42, 37` is a `LIST`. Don't jump ahead! We are limited to the rules given in our recursive definition.
1. `42, 37` does not correspond to the base case. It is a number, `42`, followed by a comma followed by `37`. 
In order to verify `42, 37` is a `LIST`, we need to verify that `37` is a `LIST`.
1. `37` corresponds to our base case and, therefore, fits the recursive definition of a `LIST`.
1. Since `37` is a `LIST`, `42, 37` is a `LIST` and so is `88, 42, 37`

We might also represent this with a **recursion tree** as follows:

   ```
            [88, 42, 37]
              /   |    \
             88   ,  [42, 37]
                      /  |  \
                     42  ,  [37]
                              |
                             37
   ```
In general, to create a recursive definition of some concept, we need to establish two things:
	
   * **Base Case**: create a non-recursive definition as a "base".
   * **Recursive Case**: create a definition in terms of itself, changing it somehow 
   (usually towards the base case).

## Infinite Recursion

If a recursive definition doesn't have a base case or the recursive case doesn't move
towards and eventually reach a base case, then there is no way to terminate the recursive
path.

   * This is called **infinite recursion**.
   * This problem is similar to an infinite loop -- with the definition itself causing 
     the infinite "looping".
   * The biggest difference is that an infinite recursion is guaranteed to cause a
     stack overflow error.

Let's try some code!  Compile and run the following on `nike`:

   ```java
   public class InfRecursion {
      public static void main(String[] args) {
         recurse();
      } // main

      public static void recurse() {
         recurse();
      }
   } // InfRecursion
   ```

Eventually, the program will throw a `RuntimeException` because it runs out of memory (stack space). You should
see an error that looks something like the following:

   ```
   Exception in thread "main" java.lang.StackOverflowError
   at InfRecursion.recurse(InfRecursion.java:8)
   at InfRecursion.recurse(InfRecursion.java:8)
   at InfRecursion.recurse(InfRecursion.java:8)
   ...
   ```

Each call to `recurse` (called infinitely) sets up a new execution environment (*stack frame*), 
with new parameters and local variables. Each stack frame takes up memory in a special region called the
stack. When the stack fills up due to too many method calls, you get a `StackOverflowError`.

## Problems and Sub-problems

As you can probably imagine, we generally want to avoid infinite recursion.  That's why we have to 
make sure our recursive algorithms make progress toward the base case. In order to do this, we typically 
want our recursive call to work on a smaller version of the original problem -- eventually reaching the
base case. A few general definitions:
	
   * **Problem**: what you're trying to solve.
   * **Sub-problem**: a smaller version or part of the problem that's easier to solve.

With respect to recursion:
	
   * **Recursive Cases**: sub-problems that cannot be solved directly.
   * **Base Cases**: sub-problems that can be solved directly.

## Recursive Countdown

If asked to write a static method to countdown from a specified value to zero, you would
likely write a for-loop and create a method that looks something like:

   ```java
   public static void countFrom(int value) {
      for(int i = value; i >= 0; i++) {
         System.out.println(i);
      } // for
    
   } // countFrom
   ```
    
You might also see a recursive solution to this problem! Can you identify the recursive cases (subproblems)
and base cases? **Take a second to think about it before reading ahead**

You might have come up with something like this:

   ```
   countFrom(5) as: print(5) then call countFrom(4)
   countFrom(4) as: print(4) then call countFrom(3)
   countFrom(3) as: print(3) then call countFrom(2)
   countFrom(2) as: print(2) then call countFrom(1)
   countFrom(1) as: print(1) then call countFrom(0)
   countFrom(0) as: simply return
   ```

If `countFrom` is passed any value other than `0`, we are in the recursive case where the method prints its
current value and then calls itself with the value minus one. This will eventually lead to the base case and
each call to `countFrom` works on a smaller version of the original problem (a smaller sequence to print).


**Take a few minutes to think about what the code for this might look like**


**One Possible Solution:**

   ```java
   public static void countFrom(int num) {
      //Base Case
      if(num < 0) {
          return;
      } // if

      //Recursive Case
      countFrom(num-1);
      System.out.println(num);

   } // countFrom
   ```

Try out the above method. Call it from your `main` method using various input values to
make sure it's working properly.
    
## Recursive CountUp

How could you modify the previous code to make it count up instead of down? **Hint**: You can
do this simply by swapping two lines of code.


   **Don't read beyond this point until you've attempted to change the above code to count up**


If you haven't realized the solution, try swapping the last two lines in the `countFrom` method.
Now, the method will print the current value before calling itself recursively.  It results in
the following structure:

   ```
   countFrom(5): countFrom(4) then print(5)
   countFrom(4): countFrom(3) then print(4)
   countFrom(3): countFrom(2) then print(3)
   countFrom(2): countFrom(1) then print(2)
   countFrom(1): countFrom(0) then print(1)
   countFrom(0): return
   ```
   
To better understand how this works, consider the recursion tree below:

   ```
                         countFrom(5)
                         /          \
                      countFrom(4)  print(5)
                      /          \
                  countFrom(3)   print(4)
                  /         \
             countFrom(2)  print(3)
             /          \
        countFrom(1)   print(2)
        /          \
    countFrom(0)  print(1)
         |
        return
   ```
   
**NOTE**: The recursion tree traverses down the left-hand side of the tree first. For example,
after our call to `countFrom(5)`, `countFrom(4)` has to completely finish its execution before
`print(5)` executes. This explains why all other numbers are printed *before* 5 in the output.

## Recursive Factorial

** Problem **: What is the factorial of the non-negative integer `n`?

   * By definition, the first number in the factorial sequence is 1.
   * Example:

      ```
      0! = 1 (base case)
      1! = 0! * 1 = 1 * 1
      2! = 1! * 2 = 1 * 1 * 2
      3! = 2! * 3 = 1 * 1 * 2 * 3
      4! = 3! * 4 = 1 * 1 * 2 * 3 * 4
      ```
	
Given the above problem description, identify the base cases and the recursive cases. Now, try to write a
method called `factorial` that takes a single integer argument, `n`, and returns `n!`.


   **Don't read beyond this point until you've attempted to write the recursive factorial method.**

**Solution**

   ```java
   int factorial(int n) {
      if(n == 0) return 1;               //base case
         return n * factorial(n - 1);    //recursive case
   } // factorial
   ```
   
## Using Recursion with Loops

Sometimes, it may be appropriate to combine loops with recursion.

    Do some things 
  
## References

* [[1] What is Maven?](https://maven.apache.org/what-is-maven.html)
* [[2] Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
