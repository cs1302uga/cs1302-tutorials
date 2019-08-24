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

### What is Inheritance?

In its simplest terms, **inheritance** in Java is a way to create a new class based on an 
existing class. Consider these relatively small classes, `Person` and `Employee`, that
**do not** take advantage of inheritance (implementation details for each method are
intentionally not included):

```java
import java.time.LocalDate;

public class Person {

    private String name;
    private LocalDate dateOfBirth;
    
    public Person(String name, LocalDate dateOfBirth) {
        ...
    } // Person
    
    public String getName() {
        ...
    } // getName
    
    public LocalDate getDateOfBirth() {
        ...
    } // getDateOfBirth
    
    public int computeAge() {
        ...
    } // computeAge

} // Person
```

```java
import java.time.LocalDate;

public class Employee {

    private String name;
    private LocalDate dateOfBirth;
    private LocalDate dateOfHire;
    private long id;
    
    public Employee(long id, String name, LocalDate dateOfBirth, LocalDate dateOfHire) {
        ...
    } // Employee
    
    public long getId() {
        ...
    } // getId
    
    public String getName() {
        ...
    } // getName
    
    public String getDateOfBirth() {
        ...
    } // getDateOfBirth
    
    public String getDateOfHire() {
        ...
    } // getDateOfHire
    
    public int computeAge() {
        ...
    } // computeAge

} // Employee
```

These two classes are almost the same, which resulted in a lot of repetition. This repetition
is problematic because it results in more code that must be maintained. It's even worse if you
identify a bug that needs to be fixed in both classes. Fortunately, we can remove most of this 
repitition by using inheritance. We'll keep the `Person` class as-is, however, we'll rewrite
the `Employee` class to make it simpler. To do this, we use the `extends` keyword in the class
header:

```java
import java.time.LocalDate;

public class Employee extends Person {

    private LocalDate dateOfHire;
    private long id;
    
    public Employee(long id, String name, LocalDate dateOfBirth, LocalDate dateOfHire) {
        ...
    } // Employee
    
    public long getId() {
        ...
    } // getId
    
    public String getDateOfHire() {
        ...
    } // getDateOfHire

} // Employee
```

Conceptually, this results in the same `Employee` class as the first example with
the exact same functionality but less code. One way to think of the `extends`
keyword is as glorified copy-paste. 

Now consider a new class called `Employee`.

### The "is-a" Relationship

### 

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
