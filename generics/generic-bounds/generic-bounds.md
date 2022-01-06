# Generic Bounds Reading

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

## Upper Bounds

Consider the generic type parameter `<T>` in the following generic method, which
has some of its body omitted to help us focus on the important parts: 

```java
/**
 * Finds the first ocurrence of some equal object. More formally, this methods returns a
 * reference to the first element {@code elem} in the array specified by {@code array} 
 * such that {@code o.equals(elem)} or {@code null} if no such item is found.
 *
 * @param <T>    element type
 * @param array  array to search
 * @param o      object to find
 * @return first ocurrence of some equal object or {@code null}
 */
public static <T> T findFirst(T[] array, T o) {
    ...
    if (o.equals(elem)) {
    ...
} // findFirst
```

When writing the code above, the only thing we know about the variable `o` is
that its type will be replaced with some reference type. This is important
because we wrote a line of code that calls a method using `o`. What methods
are available? As written, the only methods that we can call using `o` are
those that are defined in 
[`Object`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html).
We called `equals(elem)` using `o`. That's okay since the 
`equals` method is declared in the `Object` class. 

**What if we need to use other methods?** In order to do that, we need to
formally restrict the reference types that we can replace `T` with only
those types that have the method(s) we need. If we tell Java to only let
`T` be replaced with certain types, then you know variables of type `T`
will have the methods that are common to those types.

To restrict the types that can replace `T`, we usually use a **_lower bound_**.
For example, consider the generic type parameter `<T extends Comparable<T>>` 
in the following generic method, which has some of its body omitted to help 
us focus on the important parts: 

```java
/**
 * Counts the number of objects that are less than some object. More formally, 
 * this methods the number of elements {@code elem} in the array specified by 
 * {@code array} such that {@code (o.compareTo(elem) >= 0)}.
 *
 * @param <T>    element type
 * @param array  array to search
 * @param o      object to compare
 * @return number of objects that are less than some object
 */
public static <T extends Comparable<T>> int countLessThan(T[] array, T o) {
    ...
    if (o.compareTo(elem) >= 0) {
    ...
} // findFirst
```

In this example, `T` can no longer be replaced with any reference type. Instead
it can only be replaced with types that are compatible with `Comparable<T>`. 
Inside of the angle brackets, whatever we write after the `extends` keyword 
for a generic type parameter is called a upper bound, because you can only
replace the type parameter with that bound or reference types that are below
it in the overall hierarchy. Here are some examples of types that work
for `<T extends Comparable<T>>`:
* [`String`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html)
  works because it's compatible with `Comparable<String>`
* [`Integer`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html)
  works because it's compatible with `Comparable<Integer>` Integer implement 

If you inspect the [`Comparable`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Comparable.html)
interface, you'll see that it defines the `compareTo` method. This method is
not defined in `Object`, however, we can call it in the example using `o` because
we've restricted `T` using an upper bound to those reference types that have 
a `compareTo` method.

**Why did we use `extends`? Isn't `Comparable` an interface?** Yes. However, when
declaring an upper bound the keyword `extends` is used regardless of whether the 
bound is a parent class or an interface.

### Multiple Upper Bounds

If you need access to more methods, then you might need to impose multiple upper bounds
on a single generic type parameter. To do this, we use the following syntax:

```java
<T extends A & B & C>
```

In this example, `T` has three simultaneous upper bounds. Anything we replace `T` with
must be compatible with all three of those reference types. There are some restrictions
on what can serve as an upper bound when there is more than one. Only one upper bound
is allowed to be a class, and, if present, it must appear first in the list. The rest
of the upper bounds must be interfaces. 

Here is an example with multiple upper bounds:

```java
<T extends Comparable<T> & Appendable>
```

In this example, `T` can be replaced with something like 
[`CharBuffer`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/CharBuffer.html)
since `CharBuffer` is compatible with both `Comparable<CharBuffer>` and `Appendable`.

### Extending the First Example

```
TODO replace with UML

+---------+
| Object  |
+---------+
     △
     |
+---------+
| Shape   |
+---------+
     △
     |
+---------+
| Ellipse |
+---------+
     △
     |
+---------+
| Circle  |
+---------+
```

```java
/**
 * Finds the first ocurrence of some equal object. More formally, this methods returns a
 * reference to the first element {@code elem} in the array specified by {@code array} 
 * such that {@code o.equals(elem)} or {@code null} if no such item is found.
 *
 * @param <T>    element type
 * @param array  array to search
 * @param o      object to find
 * @return first ocurrence of some equal object or {@code null}
 */
public static <A, T extends A> T findFirst(A[] array, T o) {
    ...
    if (o.equals(elem)) {
    ...
} // findFirst
```

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
