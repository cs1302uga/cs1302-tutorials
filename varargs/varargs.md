# Variable Arguments (Varargs) Reading

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

In Java, varargs enable us to write methods that accept a variable number
of arguments of the same type. Consider the usual way to do this using an
array. Here is an example of a `printlns` method in a class called `Helper`
that prints out each element of an array on its own line 
(full code [here](src/cs1302/util/Helper.java)):

```java
/**
 * Calls {@code out.println(arg)} for each {@code arg} in {@code args}.
 * @param out   desired output stream
 * @param args  arguments to print
 */
public static void printlns(PrintStream out, String[] args) {
    for (String arg : args) {
        out.println(arg);
    } // for
} // printlns
```

Then, [elsewhere](src/cs1302/util/Driver.java), you might call 
the `printlns` method like this:

```
// elsewhere
Helper.printlns(System.out, new String[] { "a", "b", "c" });
Helper.printlns(System.out, new String[] { "d", "e" });
Helper.printlns(System.out, new String[] { "f" });
Helper.printlns(System.out, new String[] { "g", "h", "i", "j", "k" });
```

However, the code snippet above is a little tedious because it requires
the creation and use of any array. **The following will not work as-is, 
but it would be nice _if we could make it work_:**

```java
// elsewhere
Helper.printlns(System.out, "a", "b", "c");
Helper.printlns(System.out, "d", "e");
Helper.printlns(System.out, "f");
Helper.printlns(System.out, new String[] { "g", "h", "i", "j", "k" });
```

To facilitate this, your first instinct might be to create a set
of method _overloads_ for the `printlns` method. That's a good thought,
however, it would also be tedious. How many parameters will the
method need? Is it three? Two? One? We don't really know ahead of time. 
This is where a **varags declaration** comes into play:

```java
/**
 * Calls {@code out.println(arg)} for each {@code arg} in {@code args}.
 * @param out   desired output stream
 * @param args  arguments to print
 */
public static void printlns(PrintStream out, String... args) {
    for (String arg : args) {
        out.println(arg);
    } // for
} // printlns
```

Changing `String[] args` to `String... args` accomplishes two things:

1. the `printlns` method can now be called with zero or more trailing
   `String` parameters; and
   
1. the `printlns` method can _still_ be called with a `String[]` parameter.

**Try it!** Modify the supplied [`Helper`](src/cs1302/util/Helper) class to
make its `printlns` method accept a variable number of trailing `String`
arguments. The lines of code that would not work earlier should all work
after you've changed the `args` parameter to have a varargs declaration.

## Technical Details

1. A method may only contain a single `varargs` declaration.

1. If a method contains a varargs declaration, then it must be at the end.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
