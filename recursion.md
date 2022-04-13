# CSCI 1302 Recursion Tutorial

![Approved for: Spring 2022](https://img.shields.io/badge/Approved%20for-Spring%202022-purple) 

## Introduction

This tutorial is designed to help you learn about and understand recursion, a powerful
programming technique in which a method can call itself in order to fulfill its purpose.

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

That is, a `LIST` can be a `#` or a `#, LIST`. Here, the concept of a `LIST` is used to define itself!
In this example, `#` is the base case as it is the non-recursive part of the definition. In other words, it
is the part of the definition that does not use `LIST`.

We can demonstrate that `88, 42, 37` is a `LIST` using the inductively defined recursive data definition by
following the definition one step at a time until we reach the base case.

1. If we look at `88, 42, 37`, we see that it does not correspond to the base case. 
   Therefore, we must verify that it matches the recursive case.
   We see that it starts with a number and a comma. 
   However, in order to show that this is a `LIST`, we need to know if `42, 37` is a `LIST`. 
   Don't jump ahead! We are limited to the rules given in our recursive definition.
   
1. If we look at`42, 37`, we see that is also does not correspond to the base case. 
   It is a number, `42`, followed by a comma followed by `37`.
   In order to verify `42, 37` is a `LIST`, we need to verify that `37` is a `LIST`.
   
1. If we look at `37`, we see that is corresponds to our base!
   Therefore, fits the recursive definition of a `LIST`.
   
1. Since `37` is a `LIST`, we can also say that `42, 37` is a `LIST`. 
   Finally, since `42, 37` is a `LIST`, we can confidently say that `88, 42, 37` is also a list.
   
As you can see, in order to answer the original problem of whether or not `88, 42, 37`
is a list, we used the recursive definition to break it up into smaller sub-problems
that get easier to answer. We do not know the overall answer to the original problem
until we have answered all of the sub-problems. We'll explore the idea of problems and
sub-problems again in a later section.

We might also represent this with a **recursion tree** as follows:

   ```
             88, 42, 37
              /   |    \
             88   ,   42, 37
                      /  |  \
                     42  ,   37
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

Let's try some code!  Compile and run the following on Odin:

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

Eventually, the program will throw a `StackOverflowError` because it runs out of memory (stack space). You should
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
stack. When a method returns, the memory taken up in the stack is freed up for other stack frames to use.
In the situation above, `recurse` never returns. In fact, it keeps calling itself and each call adds
another stack frame causing the stack to eventually fill up (overflow). When the stack fills up due to 
too many (unreturned) method calls, you get a `StackOverflowError`.

**ASIDE:** You may have noticed that `StackOverflowError` is reported as an exception but it does not have
`Exception` in its name. Indeed, it is reported the same as exceptions because it is a sub-class
of `java.lang.Throwable`, however, it is NOT a sub-class of `java.lang.Exception`. Instead it is
a sub-class of `java.lang.Error`, a class whose children comprise what the Java Virtual Machine
considers to be errors that are non-recoverable during runtime.

## Problems and Sub-problems

As you can probably imagine, we generally want to avoid infinite recursion.  That's why 
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
      for(int i = value; i > 0; i--) {
         System.out.println(i);
      } // for
   } // countFrom
   ```

Here is a trace of the code above:

   ```
   countFrom(5):
   i = 5: print(5) then i--
   i = 4: print(4) then i--
   i = 3: print(3) then i--
   i = 2: print(2) then i--
   i = 1: print(1) then i--
   i = 0: loop condition no longer met
   ```

You might also see a recursive solution to this problem! Can you identify the recursive cases (sub-problems)
and base cases? **Take a second to think about it before reading ahead**

You may have come up with a _problem decomposition_ similar to the following:

   ```
   countFrom(5): print(5) then call countFrom(4)
   countFrom(4): print(4) then call countFrom(3)
   countFrom(3): print(3) then call countFrom(2)
   countFrom(2): print(2) then call countFrom(1)
   countFrom(1): print(1) then call countFrom(0)
   countFrom(0): simply return
   ```

If `countFrom` is passed any value other than `0`, we are in the recursive case where the method prints its
current value and then calls itself with the value minus one. This will eventually lead to the base case and
each call to `countFrom` works on a smaller version of the original problem (a smaller sequence to print).

**Take a few minutes to think about what the code for this might look like**

**One Possible Solution:**

   ```java
   public static void countFrom(int num) {
   
       // Base Case
       if (num == 0) {
           return;
       } // if

       // Recursive Case
       System.out.println(num);
       countFrom(num - 1);     
       
   } // countFrom
   ```

Try out the above method. Call it from your `main` method using various input values to
make sure it's working properly.
    
## Recursive Count Up

How could you modify the previous code to make it count up instead of down? **Hint**: You can
do this simply by swapping two lines of code.

**Don't read beyond this point until you've attempted to change the above code to count up.**

Did you try swapping the last two lines in the `countFrom` method? If not, then try it!
Now, the method will print the current value before calling itself recursively.  It results in
the following structure:

   ```
   countFrom(5): countFrom(4) then print(5)
   countFrom(4): countFrom(3) then print(4)
   countFrom(3): countFrom(2) then print(3)
   countFrom(2): countFrom(1) then print(2)
   countFrom(1): countFrom(0) then print(1)
   countFrom(0): simply return
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

Here is a similar recursion tree for `countFrom(3)`:

   ```
                  countFrom(3)
                  /         \
             countFrom(2)  print(3)
             /          \
        countFrom(1)   print(2)
        /          \
    countFrom(0)  print(1)
         |
       return
   ```

The recursion tree may give you the impression that all sub-problems are being evaluated
concurrently. Without special code to facilitate this (e.g., using threads), these method
calls occur only one at a time. To better illustrate this, here is a depiction how
the _call stack_ changes as the recursive method calls approach and reach the base case
(for an explanation of the call stack, please see the [On the Call Stack](#aside-on-the-call-stack) aside):

```
 immediately             immediately             immediately             immediately
 after calling           after calling           after calling           after calling 
 countFrom(3)            countFrom(2)            countFrom(1)            countFrom(0)
|------------------|    |------------------|    |------------------|    |------------------|
| [calling method] | => | [calling method] | => | [calling method] | => | [calling method] |
|------------------|    |------------------|    |------------------|    |------------------|
| [countFrom(3)]   |    | [countFrom(3)]   |    | [countFrom(3)]   |    | [countFrom(3)]   |
| num = 3          |    | num = 3          |    | num = 3          |    | num = 3          |
|------------------|    |------------------|    |------------------|    |------------------|
                        | [countFrom(2)]   |    | [countFrom(2)]   |    | [countFrom(2)]   | 
 NO OUTPUT YET          | num = 2          |    | num = 2          |    | num = 2          |
                        |------------------|    |------------------|    |------------------|
                                                | [countFrom(1)]   |    | [countFrom(1)]   | 
                          NO OUTPUT YET         | num = 1          |    | num = 1          |
                                                |------------------|    |------------------|
                                                                        | [countFrom(0)]   |
                                                  NO OUTPUT YET         | num = 0          |
                                                                        |------------------|  
                                                                        
                                                                          NO OUTPUT YET
```

Since the print statements are written after the recursive calls, we do not see any output
as the program approaches the base case. However, as the recusive method calls return
back to their calling methods, we begin to see the program produce the expected output.
as depicted below:

```
 immediately             immediately             immediately             immediately
 after returning         after returning         after returning         after returning
 countFrom(0)            countFrom(1)            countFrom(2)            countFrom(3)
|------------------|    |------------------|    |------------------|    |------------------|
| [calling method] | => | [calling method] | => | [calling method] | => | [calling method] |
|------------------|    |------------------|    |------------------|    |------------------|
| [countFrom(3)]   |    | [countFrom(3)]   |    | [countFrom(3)]   |
| num = 3          |    | num = 3          |    | num = 3          |      OUTPUT:
|------------------|    |------------------|    |------------------|      1
| [countFrom(2)]   |    | [countFrom(2)]   |                              2
| num = 2          |    | num = 2          |     OUTPUT:                  3
|------------------|    |------------------|     1
| [countFrom(1)]   |                             2
| num = 1          |     OUTPUT:
|------------------|     1

 STILL
 NO OUTPUT YET
```

### Aside: On the Call Stack

You've seen a call stack before! When your Java programs crash from an exception
or error, the output produces a _stack trace_. Each line in the stack trace represents a each method
call that was made (exluding methods that have already returned), starting from `main` up to the 
method that whose execution caused the crash. 

In our depiction of the call stack, each boxed area called a _stack frame_ 
and represents a specific method call, including its local variables. You should think of this
as the scratch space for that specific invocation of the method. The frames are usually
depicted in a downward-moving stack as that is how they are generally stored in 
computer memory. If a method calls another method, then the frame for that other method
is pushed / added to the bottom end of this stack. When the other method returns, its frame 
is popped / removed from the stack. Therefore, the frame at the end always represents the
specific method where code is being executed at any given moment in time. 
Of course, this assumes single-threaded execution. In a multi-threaded application, there is usually
one call stack per thread. In our recursion examples, we do not explicitly create new threads
so we assume that only one method is ever executing at any given time.

What is presented above is good enough to establish a proper conceptual model
for how method calls are handled in many programming languages. Students who are 
interested in taking a deep dive into how all of this works specifically in Java
are encouraged to read [Chapter 2: The Structure of the Java Virtual Machine](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-2.html) in
*The Java® Virtual Machine Specification* (Java SE 11 Edition).

## Recursive Factorial

**Problem**: What is the factorial of the non-negative integer `n`?

   * By definition, the first number in the factorial sequence is 1.
   * Example:

      ```
      0! = 1 (base case)
      1! = 0! * 1 = 1 * 1
      2! = 1! * 2 = 1 * 1 * 2
      3! = 2! * 3 = 1 * 1 * 2 * 3
      4! = 3! * 4 = 1 * 1 * 2 * 3 * 4
      ```
	
Given the above problem description, identify the base cases and the recursive cases. 
Now, try to write a method called `factorial` that takes a single `int` argument, 
`n`, and returns `n!` as a `long`.

**Don't read beyond this point until you've attempted to write the recursive factorial method.**

**Sample Solution**

   ```java
   long factorial(int n) {
       if (n == 0) return 1;           // base case
       return n * factorial(n - 1);    // recursive case
   } // factorial
   ```
   
**Sample Recursion Tree**

Here is a sample recursion tree for `factorial(3)`:

   ```
   factorial(3)
        \
   3 * factorial(2)
            \
        2 * factorial(1)
                 \
             1 * factorial(0)
                      \
                       1
   ```

**Sample Call Stack**

The recursion tree above only shows the method calls. Notice how the multiplications
are shown to the side. It is interesting to note that these multiplications, although
written in a return statement on the same line as the recursive call, are actually 
evaluated after the associated recursive call returns (just as with the print statements
in the `countFrom` example). To help us better understand what is going on, step-by-step,
here is a depiction of how the _call stack_ changes as the recursive method calls approach 
and reach the base case for `factorial(3)`:

```
 immediately             immediately             immediately             immediately
 after calling           after calling           after calling           after calling 
 factorial(3)            factorial(2)            factorial(1)            factorial(0)
|------------------|    |------------------|    |------------------|    |------------------|
| [calling method] | => | [calling method] | => | [calling method] | => | [calling method] |
|------------------|    |------------------|    |------------------|    |------------------|
| [factorial(3)]   |    | [factorial(3)]   |    | [factorial(3)]   |    | [factorial(3)]   |
| n = 3            |    | n = 3            |    | n = 3            |    | n = 3            |
| return 3 * ?     |    | return 3 * ?     |    | return 3 * ?     |    | return 3 * ?     |
|------------------|    |------------------|    |------------------|    |------------------|
                        | [factorial(2)]   |    | [factorial(2)]   |    | [factorial(2)]   | 
                        | n = 2            |    | n = 2            |    | n = 2            |
			| return 2 * ?     |    | return 2 * ?     |    | return 2 * ?     |
                        |------------------|    |------------------|    |------------------|
                                                | [factorial(1)]   |    | [factorial(1)]   | 
                                                | n = 1            |    | n = 1            |
						| return 1 * ?     |    | return 1 * ?     |
                                                |------------------|    |------------------|
                                                                        | [factorial(0)]   |
                                                                        | n = 0            |
									| return 1         |
                                                                        |------------------|
```

As the recusive method calls return back to their calling methods, we begin to see the program 
perform the multiplications in order to produce the return values:

```
 immediately             immediately             immediately             immediately
 after returning         after returning         after returning         after returning
 factorial(0)            factorial(1)            factorial(2)            factorial(3)
|------------------|    |------------------|    |------------------|    |------------------|
| [calling method] | => | [calling method] | => | [calling method] | => | [calling method] |
|------------------|    |------------------|    |------------------|    |------------------|
| [factorial(3)]   |    | [factorial(3)]   |    | [factorial(3)]   |
| n = 3            |    | n = 3            |    | n = 3            |     Now has value 6
| return 3 * ?     |    | return 3 * ?     |    | return 3 * 2 = 6 |
|------------------|    |------------------|    |------------------|
| [factorial(2)]   |    | [factorial(2)]   |
| n = 2            |    | n = 2            |
| return 2 * ?     |    | return 2 * 1 = 2 |
|------------------|    |------------------|
| [factorial(1)]   |
| n = 1            |
| return 1 * 1 = 1 |
|------------------|
```
After all of our recursive calls complete, the value 6 is what is ultimately returned to the main method.

Congratulations! You now have a basic understanding of recursion!

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
