# Refresher: Reference Variables

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

## Reference Types Overview

In Java, the reference types ([§4.3](https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.3)) 
are class types, interface types, and array types.

An **_object_** is really just a collection of variables that are defined by a class. It is not uncommon 
to descibe Java objects as dynamically constructed instances of a class. When an object is constructed,
its collection of variables is stored contiguously in some location in memory, which we usually call
the object's **_reference_**. This is important because, in Java, the possible values of a reference type 
are references to compatible objects (or `null`). 

### Example 1: Refer to No Object

Consider the following initialization:

```java
Scanner s = null;
```

The variable `s` has `Scanner` as its type and `null` as its value. In Java, `null` is a reference 
that can always be assigned or cast to any reference type, and it is used to denote that no object
is being referred to. Therefore, we might say that `s` does not currently refer to any object. 

**Under the Hood:** Let `s_location` denote the memory location that stores `s`'s value. The 
following diagram illustrates that a value `null` is stored at this memory location:

```
s_location: [ null ]  
```

It is more common to diagram this using the variable name:

```
  +------+
s | null |
  +------+
```

### Example 2: Refer to Some Object

Now consider the following initialization:

```java
Scanner s = new Scanner(System.in);
```

There is a lot happening on that line: i) the variable `s` is declared with `Scanner` as its
type; ii) a `Scanner` object is constructed; then iii) the object's reference is assigned to
`s`. Since the value of `s` is now a reference to some `Scanner` object, we might say that
`s` refers to some `Scanner` object. 

**Under the Hood:** Let `s_location` denote the memory location that stores `s`'s value and
`object_reference` denote the memory location of the constructed `Scanner` object. The 
following diagram illustrates that values are stored at these two memory locations:

```
      s_location: [ object_reference ]  
             ...
object_reference: [ scanner object contents ... ]
```

It is more common to diagram this using the variable name and an arrow to the object (since
we may not know or care what the actual value stored in `s_location` is):

```
  +--+     +----------------+
s | -|----◆|                |
  +--+     |                |
           +----------------+
```

**Important:** When a method is called using `s`, the object that `s` refers to is known as
the **_calling object_**. Non-static methods called using `s` are said to operate on the 
calling object. Since the reference in the value of `s` can change over time, the calling 
object can also change. 

### Example 3: Many Variables Refer to One Object

Now consider the following initializations:

```java
Scanner s = new Scanner(System.in);
Scanner t = s;
```

The first line is the same as in Example 2. On the second line: i) the variable `t` is declared 
with `Scanner` as its type; ii) the value of `s` is retrieved by the computer; then iii) the 
value is assigned to `t`. Since the value of `t` is now  the same as the value of `s`, 
we might say that `s` and `t` both refer to the same `Scanner` object.

```
  +--+     +----------------+
s | -|----◆|                |
  +--+     |                |
           +----------------+
             ◆
  +--+       |
t | -|-------+
  +--+
```

This is an interesting scenario becuase the calling object for `s` and the calling object for `t`
are now the same object! 

## Reference Types, Assignment Values, and Return Values

Consider the following reference type variable declaration:

```
SomeType varName;
```

The values that can be assigned to `varName` are `null` and any reference to an object whose
type is compatible with `SomeType`. In Java, types are compatible if they are the same or
if the variable type is a superclass or interface of the reference type being assigned.

When you invoke a constructor using `new SomeClassName()` (or similar), the type of the 
reference produced by the expression is the same as the class name. This reference 
can be assigned to any compatible variable or returned in any method with a compatible
return type. 

### Common Examples

```java
Scanner scan = new Scanner(System.in); // same types
String str = "Hello";                  // same types
Object obj = new String("cool");       // compatible superclass
Comparable<String> = str;              // compatible interface
```

### Return Value Example

When a method uses a reference type as its return type, then any compatible reference
can be returned by that method. The simplest illustration of this concept is a
method that has a return type of `Object`:

```java
public static Object doSomething() {
    ...
    return x; // where x is some reference variable
}
```

In this example, the variable `x` either has `null` as its value or a reference
to some object. In either case, since `Object` is always a compatible superclass,
the value of `x` can be returned. **However, you should treat this as if you are
assigning `x` to a variable of type `Object` via the `return` statement.** That is,
the reference value being returned will now be treated as a reference of type
`Object`, regardless of the type of object actually being referred to.  

To illustrate this, consider the following:

```java
public static Object doSomething() {
    String x = new Str("cool");
    return x;
}
```

```java
public static void doSomethingElse() {
    Object a = doSomething(); // OK
    String b = doSomething(); // NOT OK; incompatible types
}
```

Even though _we_ know the reference returned by `doSomething` refers to an object of
type `String`, the reference itself was returned as type `Object` and an `Object`
reference cannot be assigned to a `String` variable. **That being said,** we can
use a typecast to force the compiler to convert the `Object` reference into a
`String` reference, making the assignment compatible:

```java
public static void doSomethingElse() {
    Object a = doSomething();          // OK
    String b = (String) doSomething(); // OK
}
```

At no point in this example did we change the type of the object being referred to.
We did, however, change the type of the reference. The call to `doSomething()`
returned a reference value with type `Object`. That value was typecasted into
a reference value with type `String`. Finally, that `String` reference was
assigned to `b`. **Tricky stuff!**

**Be careful with typecasts!** We can use them to make code compile that shouldn't.
Consider the following example, using the same `doSomething` method from the previous
examples:

```java
public static void doSomethingElse() {
    Object a = doSomething();            // OK
    Scanner b = (Scanner) doSomething(); // OK at compile-time
}
```

The above example compiles! Why? Well, we told the compiler that the returned
reference value should be converted to a reference value with type `Scanner`
using a typecast. Assigning a `Scanner` reference to a `Scanner` variable is
okay. However, we know this will probably cause an issue because the object
being referred to actually has type `String`. Although this compiles, if you
were to run the code, you would get a 
[`ClassCastException`](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassCastException.html)
since a `Scanner` variable cannot actually refer to a `String` object. 
This exception is thrown to indicate that the code has attempted to cast an 
object to a reference type of which it is not compatible with.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
