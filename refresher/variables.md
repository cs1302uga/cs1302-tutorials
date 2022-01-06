# Refresher: Reference Variables

![Approved for: Spring 2022](https://img.shields.io/badge/Approved%20for-Spring%202022-purple)

<div class="contents" depth="2">

</div>

## Introduction

In computer programming, a **variable** is just an alias for a location
in memory. Instead of requiring programmers to remember explicit memory
addresses, we let them use variables as a matter of convenience. Some
languages (e.g., C and C++) also allow programmers to deal directly with
memory addresses. Every declared variable has the following:

-   a **value**, the actual data stored in some memory location;
-   a **type**, an attribute that tells the computer how to interpret
    the value and how much space is needed to store the value; and
-   a **name**, an attribute that tells the computer the word that we
    want to use to refer to the associated data.

According to [Chapter 4 of the Java Language
Specification](https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html#jls-4.10.1),

> There are two kinds of types in the Java programming language:
> primitive types
> ([§4.2](https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html#jls-4.2))
> and reference types
> ([§4.3](https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html#jls-4.3)).
> There are, correspondingly, two kinds of data values that can be
> stored in variables, passed as arguments, returned by methods, and
> operated on: primitive values
> ([§4.2](https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html#jls-4.2))
> and reference values
> ([§4.3](https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html#jls-4.3)).

## Reference Types Overview

In Java, the **reference types**
([§4.3](https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html#jls-4.3))
are class types, interface types, and array types.

An **object** is really just a collection of variables that are defined
by a class. It is not uncommon to describe Java objects as dynamically
constructed instances of a class. When an object is constructed, its
collection of variables is stored contiguously in some location in
memory, which we usually call the object’s **reference**. This is
important because, in Java, the possible values of a reference type are
references to compatible objects (or `null`).

### Example 1: Refer to No Object

Consider the following initialization:

``` java
Scanner s = null;
```

The variable `s` has `Scanner` as its type and `null` as its value. In
Java, `null` is a reference that can always be assigned or cast to any
reference type, and it is used to denote that no object is being
referred to. Therefore, we might say that `s` does not currently refer
to any object.

**Under the Hood:** Remember,`s` is an alias for some location in the
computer’s primary memory (RAM). Since we don’t know exactly where `s`
will be located when the program runs, we will use an arbitrary number
to denote this location. Here, we will use the value `800` (we could’ve
chosen any arbitrary value). The mapping of `s` to a memory location all
happens under the hood and requires no explicit actions by the
programmer. The way this looks internally can be seen below. In the
diagram, we use `800` instead of `s` since that’s what really happens in
the computer. Since we assigned `null` to `s`, the value at location
`800` is `null`.

    800: [ null ]

Since using the variable name is more readable and doesn’t require us to
choose an arbitrary address, it is more common to diagram this using the
variable name:

    +------+
    s | null |
    +------+

### Example 2: Refer to Some Object

Now consider the following initialization:

``` java
Scanner s = new Scanner(System.in);
```

There is a lot happening on that line: i) the variable `s` is declared
with `Scanner` as its type; ii) a `Scanner` object is constructed; then
iii) the object’s reference is assigned to `s`. Since the value of `s`
is now a reference to some `Scanner` object, we might say that `s`
refers to some `Scanner` object.

**Under the Hood:** Let `800` again denote the memory location that
stores `s`’s value and `12048` denote the memory location of the
constructed `Scanner` object. Again, these values are arbitrary. The
diagram below demonstrates the values that are stored at these two
memory locations. Pay close attention to fact that `800` (or `s`) does
not store the `Scanner` object’s contents. Instead, `s` is the address
where the `Scanner` object resides in memory. Understanding this idea is
the key to understanding how reference variables work in Java.

    800: [ 12048 ]
               ...
    12048: [ scanner object contents ... ]

It is more common to diagram this using the variable name and an arrow
to the object (since we may not know or care what the actual value of
the memory address of `s`):

    +--+     +----------------+
    s | -|----◆|                |
    +--+     |                |
             +----------------+

**Important:** When a method is called using `s` (for example,
`s.nextLine()`), the object that `s` refers to is known as the **calling
object**. Non-static methods called using `s` are said to operate on the
calling object. Since the reference in the value of `s` can change over
time, the calling object can also change.

### Example 3: Many Variables Refer to One Object

Now consider the following initializations:

``` java
Scanner s = new Scanner(System.in);
Scanner t = s;
```

Before moving on, take a moment to draw a diagram on a sheet of paper
that shows how this looks under the hood. You can come up with any
arbitrary memory addresses that you wish.

Your drawing should look very similar to the previous example with an
extra variable for `t` that contains the same value as the variable `s`.
We provide the more common version of this diagram below.

The first line is the same as in Example 2. On the second line: i) the
variable `t` is declared with `Scanner` as its type; ii) the value of
`s` is retrieved by the computer; then iii) the value is assigned to
`t`. Since the value of `t` is now the same as the value of `s`, we
might say that `s` and `t` both refer to the same `Scanner` object.

    +--+     +----------------+
    s | -|----◆|                |
    +--+     |                |
             +----------------+
               ◆
    +--+       |
    t | -|-------+
    +--+

This is an interesting scenario because the calling object for `s` and
the calling object for `t` are now the same object!

## Reference Types and Assignment Values

Consider the following reference type variable declaration:

    SomeType varName;

The values that can be assigned to `varName` are `null` and any
reference to an object whose type is compatible with `SomeType`. In
Java, a reference to an object of a particular type is always compatible
with a variable of the same type. Here are some examples:

``` java
Scanner scan = new Scanner(System.in); // same types
String str = "Hello";                  // same types
```

Object references are also compatible with a variable when the type of
that is a superclass or interface of the reference type being assigned.
We will discuss this in more detail later in the semester once
interfaces and inheritance are introduced.

When you invoke a constructor using `new SomeClassName()` (or similar),
the type of the reference produced by the expression is the same as the
class name. This reference can be assigned to any compatible variable or
returned in any method with a compatible return type.

<hr/>

**Feedback?** Please help us make this better! If you have any feedback
or suggestions for this reading or tutorial, then use the link below to
reach the feedback form.

[![Submit
Feedback](https://img.shields.io/badge/-Submit%20Feedback-red.svg?style=for-the-badge)](https://docs.google.com/forms/d/e/1FAIpQLSfBgZM_-G-9nKmX7F83k0Tgp1OlqBnrkt6vsxlIqLypc_keUQ/viewform?usp=pp_url&entry.1081181680=cs1302-refresher-variables&entry.1901270436=https://github.com/cs1302uga/cs1302-tutorials/blob/master/refresher/variables.md)

<hr/>

[![License: CC BY-NC-ND
4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

Copyright © Michael E. Cotterell, Bradley J. Barnes, and the University
of Georgia. This work is licensed under a Creative Commons
Attribution-NonCommercial-NoDerivatives 4.0 International License to
students and the public. The content and opinions expressed on this Web
page do not necessarily reflect the views of nor are they endorsed by
the University of Georgia or the University System of Georgia.
