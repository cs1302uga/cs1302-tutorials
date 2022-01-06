# Refresher: Reference Types and Return Values

![Approved for: Spring 2022](https://img.shields.io/badge/Approved%20for-Spring%202022-purple)

<div class="contents" depth="2">

</div>

## Preliminaries

This reading assumes the reader understands the similarities and
difference between primitive types and reference types in Java
as explained in [an earlier reading](variables.md).

## Reference Types and Return Values

When a method uses a reference type as its return type, then any
compatible reference can be returned by that method. The simplest
illustration of this concept is a method that has a return type of
`Object`:

``` java
public static Object doSomething() {
    ...
    return x; // where x is some reference variable of any type (String, Scanner, etc.)
}
```

In this example, the variable `x` either has `null` as its value or a
reference to some object. In either case, since `Object` is always a
compatible superclass, the value of `x` can be returned. **However, you
should treat this as if you are assigning \`\`x\`\` to a variable of
type \`\`Object\`\` via the \`\`return\`\` statement.** That is, the
reference value being returned will now be treated as a reference of
type `Object`, regardless of the type of object actually being referred
to.

To illustrate this, consider the following:

``` java
public static Object doSomething() {
    String x = "cool";
    return x;
}
```

``` java
public static void doSomethingElse() {
    Object a = doSomething(); // OK
    String b = doSomething(); // NOT OK; incompatible types
}
```

Even though *we* know the reference returned by `doSomething` refers to
an object of type `String`, the reference itself was returned as type
`Object` and an `Object` reference cannot be assigned to a `String`
variable. **That being said,** we can use a typecast to force the
compiler to convert the `Object` reference into a `String` reference,
making the assignment compatible:

``` java
public static void doSomethingElse() {
    Object a = doSomething();          // OK
    String b = (String) doSomething(); // OK
}
```

At no point in this example did we change the type of the object being
referred to. We did, however, change the type of the reference. The call
to `doSomething()` returned a reference value with type `Object`. That
value was typecasted into a reference value with type `String`. Finally,
that `String` reference was assigned to `b`. **Tricky stuff!**

**Be careful with typecasts!** We can use them to make code compile that
shouldn’t. Consider the following example, using the same `doSomething`
method from the previous examples:

``` java
public static void doSomethingElse() {
    Object a = doSomething();            // OK
    Scanner b = (Scanner) doSomething(); // OK at compile-time
}
```

The above example compiles! Why? Well, we told the compiler that the
returned reference value should be converted to a reference value with
type `Scanner` using a typecast. Assigning a `Scanner` reference to a
`Scanner` variable is okay. However, we know this will probably cause an
issue because the object being referred to actually has type `String`.
Although this compiles, if you were to run the code, you would get a
[ClassCastException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ClassCastException.html)
since a `Scanner` variable cannot actually refer to a `String` object.
This exception is thrown to indicate that the code has attempted to cast
an object reference to an incompatible reference type.

<hr/>

[![License: CC BY-NC-ND
4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

Copyright © Michael E. Cotterell, Bradley J. Barnes, and the University
of Georgia. This work is licensed under a Creative Commons
Attribution-NonCommercial-NoDerivatives 4.0 International License to
students and the public. The content and opinions expressed on this Web
page do not necessarily reflect the views of nor are they endorsed by
the University of Georgia or the University System of Georgia.
