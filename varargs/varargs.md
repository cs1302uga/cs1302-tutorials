# Variable Arguments (Varargs) Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

In Java, varargs enable us to write methods that accept a variable number
of arguments of the same type. Consider the usual way to do this using an
array:

```java
// assume import for java.io.PrintStream
public static void printlns(PrintStream out, String[] args) {
    for (String arg : args) {
        out.println(arg);
    } // for
} // printlns
```

```
// elsewhere
printlns(out, new String[] { "a", "b", "c" });
```

## Getting Started


<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
