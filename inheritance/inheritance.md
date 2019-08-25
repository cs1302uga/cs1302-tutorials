# Inheritance

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

This tutorial introduces the reader to Java inheritance and polymorphism via inheritance.

### Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working
with a command-line text editor (e.g. emacs, vi, etc.). The reader should be familiar with compiling
and running Java code contained in packages and working with the Javadoc tool.

To get the most out of this tutorial, you should follow along and take notes.

## Course-Specific Learning Outcomes

*

### Getting Started

The steps in this tutorial assume that you are logged into the Nike server. 

1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-interfaces`:

   ```
   $ curl -s -L https://git.io/fjAqO | bash
   ```
  
1. Change into the `cs1302-inheritance` directory that was just created and look around. There should be
   multiple Java files contained within the directory structure. To see a listing of all of the 
   files under the current directory, use the `find` command as follows:
   
   ```
   $ find .
   ```

### What is Inheritance?

In its simplest terms, **inheritance** in Java is a way to create a new class based on an
existing class. Without inheritance, you would be forced to take one of two approaches:

1. create a new class that is a copy-paste of the original class with additional code,
   as needed; or

1. create a new class that only contains the additional code.

The first approach results in a lot of duplicate code, thus increasing code maintenance
and decreasing code reuse. The second approach is perhaps a little nicer, however, it
would require that every object of the new class be bundled somehow with an object of
original class in order to have all the data and methods available.

In Java, inheritance actually combines the ideas in these two approaches without the
need for any manual copy-paste of the source code. This is demonstrated in the following
video example:

https://www.youtube.com/watch?v=V5Y85rfMfPw

The starter code for the example in the video can be found [here](person/) and
under the `person` subdirectory of the `cs1302-inheritance` directory you
downloaded at the beginning of this tutorial.

<a href="https://www.youtube.com/watch?v=V5Y85rfMfPw">
<img src="https://img.youtube.com/vi/V5Y85rfMfPw/0.jpg?20190821" alt="IMAGE ALT TEXT">
</a>

Try to replicate what you saw in the video:

1. Change into the `person` subdirectory of `cs1302-inheritance`.

1. Create a `bin` directory.

1. Follow along with the video!

### Basic Terminology

Here are some terms that you should know related to inheritance in Java.

* **parent class**, **super class**, **base class** - the original class you want to extend.

* **child class**, **subclass** - the new class you want to create based on the parent class.

* **class hierarchy** - a collection of classes related by inheritance.

### What is Inherited? 

In Java, when one class extends another, the child class inherits almost everything from the
parent class. Specifically, we are usually concerned with the face that child class inherits
instance variables and methods from its parent. These _members_ are accessible, depending on
their visibility, within the child class the same way they would be accessible in the parent
class. 

### The "is-a" Relationship

### Constructors

Constructors are __not inherited in the usual sense__. That is, a parent constructor
does not become a constructor in the child class when inheritance is involved. However, child
constructors can invoke parent constructors via the `super` keyword.

### Method Overrides

NOTE: Be sure to mention `super`.

### The `Object` Class

According the Java Language Specification, the
[`java.lang.Object`](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html)
class is the superclass for all other classes [1]. If a class does not explicitly
extend another class, then it implicitly extends `Object`. Therefore, `Object` is at 
the top of all inheritance hierarchies in Java. 

### Abstract Classes

### Food for Thought

Discuss visibility here or in a separate tutorial?

### References

* [1] [Java Language Specification 4.3.2. The Class `Object`](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.3.2)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
