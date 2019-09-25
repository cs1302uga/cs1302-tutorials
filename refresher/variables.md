# Refresher: Variables

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

In computer programming, a **_variable_** is just an alias for a location in memory. Instead of
requiring programmers to remember explicit memory addresses, we let them use variables as a 
matter of convenience. Some languages (e.g., C and C++) also allow programmers to deal directly
with memory adresses. Every declared variable has the following:

* a **_value_**, the actual data stored in some memory location;
* a **_type_**, an attribute that tells the computer how to interpret the value and how much space
  is needed to store the value; and
* a **_name_**, an attribute that tells the computer the word that we want to use to refer to the
  associated data. 

According to
[Chapter 4 of the _Java Language Specification_](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.10.1),

> There are two kinds of types in the Java programming language: 
> primitive types ([§4.2](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.2)) and 
> reference types ([§4.3](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.3)). 
> There are, correspondingly, two kinds of data values that can be stored in variables, passed as arguments, 
> returned by methods, and operated on: 
> primitive values ([§4.2](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.2)) and 
> reference values ([§4.3](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.3)).

## Primitive Types

## Reference Types

In Java, an **_object_** is just a collection of variables that are defined by a class. It is common to
descibe Java objects as dynamically constructed instances of a class. When an object is constructed,
its collection of variables is stored contiguously in some location in memory, which we usually call
the object's address. This is important because, in Java, the possible values of a reference type 
are references to compatible objects (or `null`). For example, consider the following:

```java
Scanner s = null;
```



<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
