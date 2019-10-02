# Generic Types Reading

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

### Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working
with a command-line text editor (e.g. emacs, vi, etc.). The reader should be familiar with compiling
and running Java code contained in packages.

To get the most out of this tutorial, you should follow along and take notes.

### Course-Specific Learning Outcomes

* **LO2.d:** (Partial) Implement new generic methods, interfaces, and classes in a software solution.

### Getting Started

The steps in this tutorial assume that you are logged into the Nike server.

1. Use the following command to download and execute a shell script that retrieves
   the starter code for this tutorial and places it into a subdirectory
   called `cs1302-generic-types`:

   ```
   $ curl -s -L https://git.io/JecL5 | bash
   ```

1. Change into the `cs1302-generic-classes` directory that was just created and look around. 
   There should be multiple Java files contained within the directory structure. To see a listing 
   of all of the source code files, use the `find` command as follows:

   ```
   $ find src
   ```

1. Use the starter code to follow along with the exercise below.

## Generic Types

1. In `Driver.java` implement the following generic method:

   ```java
   /**
    * Given a reference to an array specified by {@code array} and a reference
    * specified by {@code val}, finds the first object {@code o} in {@code array} 
    * such that {@code Math.abs(val.getArea() - o.getArea()) < delta}, then returns its
    * reference; returns {@code null} if no match is found. 
    *
    * @param <T>    element type
    * @param array  array to search
    * @param val    reference to the object to match
    * @param delta  difference that is considered unequal
    * @return a reference to the object from {@code array} with an area matching
    * that of the object referenced by {@code val} or {@code null} if no match
    * is found.
    */
   public static <T extends Shape> T findMatchingArea(T[] array, T val, double delta) {
       ...
   } // findMatchingArea
   ```

## Type Inference

When a type argument is not explicitly provided in a call to a generic method, the JVM will
assign a type that is common to both actual parameters to the method. It will try to find an
exact match and, if it can't, find a common parent.

For example, consider the following code snippet:
   
```java
Shape shape = new Ellipse(4.5, 2);
Ellipse[] ellipses = circles;
Shape s = findMatchingArea(ellipses, shape);
```
      
Here, the array is of type `Circle[]`, the reference to the array is of type `Ellipse[]`. This is valid
since arrays are [covariant](https://dzone.com/articles/covariance-and-contravariance). The `shape` reference
is of type `Shape` and references an `Eliipse` object. When `findMatchingArea` is called, the actual parameters
do not have a common type (`Ellipse[]` and `Shape`) but they have a common parent, `Shape`. Therefore, the
generic type parameter `T` is replaced by `Shape` making `Shape` the return type.

1. For each of the code snippets below, write the type that `T` is replaced with under
   each method call. Explain your answer. Also, indicate whether the call is valid.

   1. **Snippet 1:**
     
   ```java
   Rectangle r = new Rectangle(4.5, 2);
   Shape s = findMatchingArea(rectangles, r);
   ```

   1. **Snippet 1:**
   
   ```java
   Rectangle r = new Square(4.5);
   Square s = findMatchingArea(rectangles, r);
   ```
   
   1. **Snippet 1:**

   ```java
   Rectangle r = new Square(4.5, 2);
   Shape s = findMatchingArea(circles, r);
   ```

   1. **Snippet 1:**

   ```java
   Rectangle r = new Square(4.5, 2);
   Shape s = Driver.<Circle>findMatchingArea(circles, r);
   ```

   1. **Snippet 1:**

   ```java
   Shape s = new Shape(4.5, 2);
   Shape result = Driver.findMatchingArea(shapes, s);
   ```


1. **Equivalence Example:**
      ```java
      Rectangle a = findMatchingArea(rectangles, new Square(2.0));
      Square b    = findMatchingArea(rectangles, new Square(2.0));
      Shape c     = findMatchingArea(rectangles, new Square(2.0));
      ```
   
   

1. In the `main` method, write a few lines of code to test your method.
   In your notes, write how you know it is properly working. Try an example
   where you think the types of the actual parameters will work and one where
   you expect it not to work.

1. **Compile all of the code, then run it.** If you run into any issues,
   then revisit either your `fill` implementation or the test code you
   included in your `main` method. **Be sure that everything compiles
   and runs as expected before continuing.**
   
1. Now that everything on this branch compiles, ensure that all changes 
   in the current branch have been staged and committed. 

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
