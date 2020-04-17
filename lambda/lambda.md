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
public interface KeepItReal {

    public double apply(double x);

} // KeepItReal
```

### Using a Regular Class

The usual way to implement an interface like `KeepItReal` is to write a regular class in its
own `.java` file. Here are two different regular classes that implement the interface:

```java
public class SquareIt implements KeepItReal {

    public double apply(double x) {
        return x * x;
    } // apply
    
} // SquareIt
```

```java
public class CubeIt implements KeepItReal {

    public double apply(double x) {
        return x * x * c;
    } // apply
    
} // CubeIt
```

The apply method inside each of these classes is not `static` (it cannot be because
it's abstract in the parent interface), so we need to create objects of each class
in order to call `apply`. As usual, we will assign the object reference to the
interface type:

```java
// in any class
public static void main(String[] args) {
    KeepItReal pow2 = new SquareIt();
    KeepItReal pow3 = new CubeIt();
    double n = 10;
    System.out.println(pow2.apply(n)); // 100
    System.out.println(pow3.apply(n)); // 1000
} // main
```

Now, let's take a deep dive into `pow2`. This variable has type `KeepItReal` and it
currently refers to a `SquareIt` object. When we call `pow2.apply(n)`, we're calling
`apply(n)` on that `SquareIt` object. Something similar is happening with `pow3`.

### Using a Simple Lambda

With the previous example in mind, let's omit `SquareIt.java` and `CubeIt.java` entirely.
The code below does almost the same using **lamdbda expressions**
without the need to create those separate `.java` files:

```java
// in any class
public static void main(String[] args) {
    KeepItReal pow2 = (double x) -> {
        return x * x;
    };
    KeepItReal pow3 = (double x) -> {
        return x * x * x;
    };
    double n = 10;
    System.out.println(pow2.apply(n)); // 100
    System.out.println(pow3.apply(n)); // 1000
} // main
```

Consider the `pow2` variable used in this modified example. 
This variable has type `KeepItReal` and it currently refers to some object
(it has to; it's a reference variable that is not `null`). When we call 
`pow2.apply(n)`, we're calling `apply(n)` on that that object. 

Here is a closer look at the `pow2` variable and the expression we're
using to assign a value to it:

```java
KeepItReal pow2 = (double x) -> {
    return x * x;
};
```

As usual, everything between the first `=` in this snippet and the last `;` 
is evaluated, then its value is assigned to `pow2`. Again, `pow2` is a
reference variable, so the value returned by this expression must be a
reference to some object. Objects are made from classes, so you might
have some questions:
* What is the class name for this object? **It has no name!** 
* Where is the class defined? **It's defined by the expression itself!**
* what do we know about this class? **It implements the functional interface!**
* What methods does it have? Besides the methods inherited from the `Object`, **it only has one method!**
* What is the name of the one method? **It's named by the interface!**
* What does the one method do when called? **It executes the body of the lambda expression!**


## Layout of a Lambda

## Method References

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
