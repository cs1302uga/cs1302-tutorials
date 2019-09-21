# Generic Parents Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

## Generic Interfaces

1. Look at the [`java.lang.Comparable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html)
   interface. 
   
   * This generic interface is known as `Comparable<T>`, and its FQN is `java.lang.Comparable`.
   * The interface documentation goes into a lot of detail (as it should), but this interface 
     is responsible for defining the semantics of the 
     [`compareTo`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html#compareTo-T-)
     method. 
     
   The entire interface, sans documentation, is as follows:

   ```java
   package java.lang;
   
   public interface Comparable<T> {

       public int compareTo(T o);

   } // Comparable<T>
   ```
   
1. Now let's look at the [`java.lang.String`](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)
   class, which is known to implement the `Comparable` interface. 
   
   * The class declaration for the `String` class is:
     ```java
     public final class String implements Serializable, Comparable<String>, CharSequence
     ```
     As we can see, it implements `Comparable<String>`.
   * Sure enough, when we look at `String` class's 
     [`compareTo`](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#compareTo-java.lang.String-) 
     method, we see that `T` has been replaced with `String` as expected. 
     




<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
