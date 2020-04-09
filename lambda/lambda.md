# Lambda Expressions

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

In Java, a __*lambda expression*__ is a special syntax for creating an object that implements
a [functional interface](https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.8).
More precisely, a lambda expression allows a programmer to simultaneously define and instantiate
a nameless class that implements an interface containing just one abstract method (aside from the 
methods of [`Object`](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html)).

Here is an example of a functional interface:
```java
@FunctionalInterface
public interface TodoReplaceName {

    public double apply(double x);

} // TodoReplaceName
```

### Interactive example
Cube composer is a game inspired by functional programming. It will explain how functions such as map and filter are used with lambda expressions. Link to game:
```
https://david-peter.de/cube-composer/
```


## Layout of a Lambda

## Method References

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
