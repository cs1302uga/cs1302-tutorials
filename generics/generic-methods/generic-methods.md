# Generic Methods Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

This tutorial introduces the reader to generic methods in Java.

### Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working
with a command-line text editor (e.g. emacs, vi, etc.). The reader should be familiar with compiling
and running Java code contained in packages.

To get the most out of this tutorial, you should follow along and take notes.

### Course-Specific Learning Outcomes

* **LO2.d:** (Partial) Implement new generic methods, interfaces, and classes in a software solution.

## Video Example

In this video example, we identify some segments of code that are nearly
identical except for the fact that they operate on different datatypes, then 
we refactor the code into a datatype-independent method using generics.

https://youtu.be/B6fCVtWHXXI

<a href="https://youtu.be/B6fCVtWHXXI">
<img src="https://img.youtube.com/vi/B6fCVtWHXXI/0.jpg?201909191514" alt="Generic Method Example">
</a>

**Code from the Video:**

* `Driver.java`

## Generic Method Signatures

Here is the general layout for a generic method in Java:

```
[visibility] [static] <PlaceholderType> ReturnType methodName([params])
```

* `<PlaceholderType>` denotes the reference types that are replaced when
  the method is invoked. 
  
  * Enclosed in angle brackets: `<`, `>`.
  * Multiple placeholders are allowed. In that scenario, the types
    should be comma-separated, e.g., `<T, U>`.   
  * The placeholder type can, but is not required to be, the return
    type of the method.    
  * The placeholder type can, but is not required to be, the type
    for any of the method's parameters.     
  * The placeholder type can, but is not required to be, the type
    for any of the method's local variables.
    
Here are some examples:

* Example with generic return-type only: 
  ```java
  public static <T> T foo(int a, int b)
  ```
  
* Example with generic parameter:
  ```java
  public static <T> int foo(String a, T b)
  ```
  
* Example with generic return-type and generic parameter:
  ```java
  public static <T> T foo(int a, T b)
  ```
  
* Example of a non-static generic method:
  ```java
  public <T> T foo(int a, T b)
  ```
  
* Here is a non-static generic method in a generic class:
  ```java
  public class SomeClass<T> {
  
      public <R> T foo(T a, R b) {
          ...
      } // foo
  
  } // someClass
  ```
  This is likely the most complicated combination of generic classes and generic
  methods you would see. In the example above, the class has its own placeholder
  type `T`, which can be used throughout the class in any non-static method. The
  `foo` method is non-static and has its own placeholder type `R`. You might see
  something like this:
  ```java
  SomeClass<Integer> sc = new SomeClass<Integer>();
  String str = sc.<String>foo(12, "help");
  ```

## Required Additional Reading

Now that you're familiar with some of the basic concepts relate to generic
methods in Java, you need to read the official Oracle reading:

* [The Java™ Tutorials: Generic Methods](https://docs.oracle.com/javase/tutorial/java/generics/methods.html)

If you have any questions related to the Generic Methods reading provided by
Oracle, then feel free to ask them on the course Piazza.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
