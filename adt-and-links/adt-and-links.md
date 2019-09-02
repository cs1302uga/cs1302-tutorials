# ADTs and Lists Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

An Abstract Data Type (ADT) is a high-level (abstract) description of a data type which includes the operations (methods)
available on the data type. The exact implementation details, including the underlying structure, are left to the programmer. 
There are often many useful implementations for a given ADT. The decision of which implementation depends on which one would
be more efficient for the application at hand. You can think of an ADT as an interface describing the operations that an
implementing class must contain.

## The List ADT

A common ADT is the List ADT. When thinking of a List as an abstract type, you can think of a List as an ordered collection 
of objects. There are a number of important operations needed for a List. Some common list operations include, but are not
limited to:

   * Creating a new list.
   * Retrieving an object in the list at a particular index.
   * Adding an object to the list at a particular index.
   * Removing an object from the list at a particular index.
   * Clearing the list.

The exact method signatures and behaviors of these methods may differ across various List ADT definitions. In this tutorial,
we will use the following definitions:

   * `List()` - creates a new List object with an initial size of zero.
   * `String get(int index)` - retrieves the object (String in this case) at the specified index. This method throws
   an `IndexOutOfBoundsException` if the index is out of range `(index < 0 || index >= size())`
   * `boolean add(int index, String s)` - inserts the specified object (String in this case) at the specified index. The 
   method shifts the object currently at that position (if any) 
get
String get(int index)
Returns the string at the specified position in this list.
Parameters:
index - index of the string to return
Returns:
the string at the specified position in this list
Throws:
IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())




Notice that the description and operations given above were independent of any underlying data structure or implementation.
When thinking of an ADT, try to avoid worrying about those details.

## Implementations of the List ADT

A common approach is to either implement the List ADT using an array or a linked list. Since you're familiar with
arrays, we will start with a discussion of this approach.

## List ADT - Array Implementation


## List ADT - Linked List Implementation


   ```
                   /----------------\       /----------------\
   example ------->| str -> "Hello" |   /-->| str -> "World" |
                   | next ----------|--/    | next = null    |
                   \----------------/       \----------------/
   ```
   
<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
