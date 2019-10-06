# Generic Types Tutorial

![Approved for: Fall 2019](https://img.shields.io/badge/Approved%20for-Fall%202019-brightgreen)

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
assign a type that is common to all actual parameters of the parameterized type. If an exact
type match cannot be found, a common parent will be used. Since `findMatchingArea` is bounded
above by `Shape`, the common parent cannot be above `Shape` in the inheritance heirarchy. In 
other words, `Object` cannot be used in this scenario.

For example, consider the following code snippet:
   
```java
Shape shape = new Ellipse(4.5, 2);
Ellipse[] ellipses = circles;
Shape s = findMatchingArea(ellipses, shape, 0.001);
```
      
Here, the array is of type `Circle[]`, the reference to the array is of type `Ellipse[]`. 
This is valid since arrays are [covariant](https://dzone.com/articles/covariance-and-contravariance). 
The `shape` reference is of type `Shape` and refers to an `Ellipse` object. 
When `findMatchingArea` is called, the actual parameters do not have a common 
type (`Ellipse[]` and `Shape`) but they have a common parent that is within the bound. 
In this example, that common parent is `Shape`. Therefore, the generic type parameter `T`
is replaced by `Shape` for both of the formal parameters and the return type.

1. For each of the code snippets below, write the type that `T` is replaced with under
   each method call. Explain your answer. Also, indicate whether the call is valid.

   1. **Snippet 1:**
     
      ```java
      Rectangle r = new Rectangle(4.5, 2);
      Shape s     = findMatchingArea(rectangles, r, 0.001);
      ```

   1. **Snippet 2:**
   
      ```java
      Rectangle r = new Square(4.5);
      Square s    = findMatchingArea(rectangles, r, 0.001);
      ```
   
   1. **Snippet 3:**

      ```java
      Rectangle r = new Square(4.5, 2);
      Shape s     = findMatchingArea(circles, r, 0.001);
      ```

   1. **Snippet 4:**

      ```java
      Rectangle r = new Square(4.5, 2);
      Shape s     = Driver.<Circle>findMatchingArea(circles, r, 0.001);
      ```

   1. **Snippet 5:**

      ```java
      Shape s      = new Shape(4.5, 2);
      Shape result = Driver.findMatchingArea(shapes, s, 0.001);
      ```

   1. **Snippet 6:**

      ```java
      Shape s      = new Shape(4.5, 2);
      Shape result = Driver.findMatchingArea(shapes, 2.5, 0.001);
      ```

1. Now, let's keep the method call the same but change the type of the reference we are storing the return value in.
   Which of the following are valid? Explain.

   ```java
   Rectangle a = findMatchingArea(rectangles, new Square(2.0), 0.001);
   Square b    = findMatchingArea(rectangles, new Square(2.0), 0.001);
   Shape c     = findMatchingArea(rectangles, new Square(2.0), 0.001);
   ```

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
