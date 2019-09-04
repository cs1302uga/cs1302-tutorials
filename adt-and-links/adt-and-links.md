# ADTs and Lists Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

An **Abstract Data Type** (ADT) is a high-level (abstract) description of a data type which includes the operations (methods)
available on the data type as well as what users of the data type should expect when using those operations. 
The exact implementation details, including the underlying structure, are left to the programmer. 
There are often many useful implementations for a given ADT. The decision of which implementation depends on which one would
be more efficient for the application at hand. You can think of an ADT as a well-commented interface describing 
the operations that an implementing class must contain.

**Consider a vending machine description as a physical analagy for an ADT.** From the outside perspective, 
users of a vending machine are only concerned with whether or not a machine _can_ vend. They do necessarily 
care _how_ a machine vends. An operation for a vending machine might be described by the following method:

* `void vendItem(double payment, int item) throws UnavailableItemException, InsufficientPaymentException`
  * Users should be able to call a method called `vendItem`, specifying a payment amount and item number.
    If valid and proper input is supplied, then the vending machine should vend the item to the user.
  * If the item is unavailable, then the method throws an `UnavailableItemException` which causes the
    vending machine's display to update accordingly.
  * Likewise, if an insufficient amount for the specified item is supplied, then the method throws
    an `InsufficientPaymentException` which also causes the vending machine's display to update accordingly.
    
In this analogy, we have a VendingMachine ADT with one operation called `vendItem`. Not only should
any vending machine implementation have a `vendItem` operation, but it should operate, from the user's
perspective, as described in the ADT description. **What does the ADT not describe?** It doesn't describe 
how the actual vending occurs. One vending machine implementation might use conveyer belts to vend an item,
while another vending machine implementation might use robotic arms to do the same. That is, different
vending machine manufacturers are responsible for the inner workings of their implementations so long
as the `vendItem` operation works as previously described. Take special care to notice that the ADT
description did not only describe what should happen under normal circumstances. It described what
should happen under exceptional circumstances as well. It did so without ever saying _how_ any of that
should be done.

## The List ADT

A common ADT is the List ADT. When thinking of a List as an abstract type, you can think of a List as an ordered collection 
of objects. There are a number of important operations needed for a List. Some common list operations include, but are not
limited to:

* Creating a new list.
* Retrieving an object in the list at a particular index.
* Adding an object to the list at a particular index.
* Removing an object from the list at a particular index.
* Clearing the list.
* Print the contents of the list.

The exact method signatures and behaviors of these methods may differ across various List ADT definitions. In this tutorial,
we will use the following definitions:

* `List()` - creates a new List object with an initial size of zero.
* `String get(int index)` - retrieves the object (String in this case) at the specified index. 
  This method throws an `IndexOutOfBoundsException` if the index is out of range 
  `(index < 0 || index >= size())`
* `boolean add(int index, String s)` - inserts the specified object (String in this case) at the specified index. 
  The method shifts the object currently at that position (if any) and any subsequent objects to the right 
  (i.e. it adds one to  their indices).
* `String remove(int index)` - removes and returns the string at the specified position in the list. 
  Shifts any subsequent elements to the left (i.e. subtracts one from their indices).
* `void clear()` - Removes all of the objects from the list. 
  The list will be empty after this call returns.
* `String makeString(String separator)` - Returns a string representation of this list with every string in
  the sequence separated by the specified seprator string.

Notice that the description and operations given above were independent of any underlying data structure or implementation.
When thinking of _using_ an ADT, try to avoid worrying about those details.

**List ADT Example**

In the example below, we provide a few List ADT method calls. For each method call, we provide an associated picture of how
the List would look at a high level. It is worth highlighting again that we can understand this code without knowing any
details of the underlying implementation. We recommend tracing through the code to see if you can get the same output
shown below.

   ```java
   List myList = new List();
   myList.add(0, "Bread");
   myList.add(0, "Cheese");
   myList.add(1, "Milk");
   myList.add(3, "Ice Cream");
   System.out.println("Removed: " + myList.remove(0));
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
Program Output:
   ```
   Removed: Cheese
   List Size: 3
   List Contents: Milk, Bread, Ice Cream
   ```
   
## Implementations of the List ADT

Now that we've seen how the List ADT is intended to function, we can focus on *how* to make it work. The List ADT is commonly 
implemented using either an array or a linked list. Each approach has benefits and drawbacks. 

### List ADT - Array Implementation

The main benefit of an array-based List implementation is extremely fast access to List elements. This is due to the fact
that arrays are laid out in a contiguous block of primary memory. The main drawback of an array-based approach is that
an array's size is fixed. Therefore, if you want to increase the size of an array, you are forced to create a new array of a 
larger size and then copy over the elements of the old array to the new, larger array. As you can imagine, this requires
a lot of work for the computer and we would like to minimize the number of times that arrays are resized.

Let's take a look at how an array-based implementation would look internally for some of our List ADT methods. In the 
examples below, we show the same code as above along with each example also having an associated image demonstrating the
internal state of the array-based List object named `myList`.

## Linked Lists require Nodes

Explain the idea of a Node in this section.

## List ADT - Linked List Implementation

The main benefit of an linked list implementation is the fact that these structures can easily increase size by adding a 
new, dynamically allocated node object. The main drawback of an is that elements are harder to access. Each access requires
the program to traverse from the head of the list to the desired index. Compare this to arrays where the program can 
jump directly to the requested index. As you can imagine, traversing the links is especially costly if we need to access
an element that is located toward the end of a long linked list.

Let's take a look at how an linked list implementation would look internally for some of our List ADT methods. In the 
examples below, we show the same code as above along with each example also having an associated image demonstrating the
internal state of the linked list based object named `myList`.

## List ADT - Examples with Both Implementations

1. **Construct a new list.**

   ```java
   List myList = new List();
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
   ```
   List Size: 0
   List Contents:
   ```
   
   <table>
   <tr>
   <td><img width="500" src="res/Array1.png"></td>
   <td>Array notes...</td>
   </tr>
   <tr>
   <td><img width="500"  src="res/Linked1.png"></td>
   <td>Linked list notes...</td>
   </tr>
   </table>
   
1. **Add element into index position 0.**

   ```java
   myList.add(0, "Bread");
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
   ```
   List Size: 1
   List Contents: Bread
   ```
   
   <table>
   <tr>
   <td><img width="500" src="res/Array2.png"></td>
   <td>Array notes...</td>
   </tr>
   <tr>
   <td><img width="500"  src="res/Linked2.png"></td>
   <td>Linked list notes...</td>
   </tr>
   </table>
   
1. **Add element into index position 0.**

   ```java
   myList.add(0, "Cheese");
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
   ```
   List Size: 2
   List Contents: Cheese, Bread
   ```
   
   <table>
   <tr>
   <td><img width="500" src="res/Array3.png"></td>
   <td>Array notes...</td>
   </tr>
   <tr>
   <td><img width="500"  src="res/Linked3.png"></td>
   <td>Linked list notes...</td>
   </tr>
   </table>
   
1. **Add element into index position 1.**

   ```java
   myList.add(1, "Milk");
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
   ```
   List Size: 3
   List Contents: Cheese, Milk, Bread
   ```
   
   <table>
   <tr>
   <td><img width="500" src="res/Array4.png"></td>
   <td>Array notes...</td>
   </tr>
   <tr>
   <td><img width="500"  src="res/Linked4.png"></td>
   <td>Linked list notes...</td>
   </tr>
   </table>
      
1. **Add element into index position 3.**

   ```java
   myList.add(3, "Ice Cream");
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
   ```
   List Size: 4
   List Contents: Cheese, Milk, Bread, Ice Cream
   ```
   
   <table>
   <tr>
   <td><img width="500" src="res/Array5.png"></td>
   <td>Array notes...</td>
   </tr>
   <tr>
   <td><img width="500"  src="res/Linked5.png"></td>
   <td>Linked list notes...</td>
   </tr>
   </table>

1. **Remove first element.**  
   
   ```java
   System.out.println("Removed: " + myList.remove(0));
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
   ```
   Removed: Cheese
   List Size: 3
   List Contents: Milk, Bread, Ice Cream
   ```
   
   <table>
   <tr>
   <td><img width="500" src="res/Array6.png"></td>
   <td>Array notes...</td>
   </tr>
   <tr>
   <td><img width="500"  src="res/Linked6.png"></td>
   <td>Linked list notes...</td>
   </tr>
   </table>

1. **Get first element.** 

   ```java
   System.out.println("Item at index 0: " + myList.get(0));
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
   ```
   Item at index 0: Milk
   List Size: 3
   List Contents: Milk, Bread, Ice Cream
   ```
   
   <table>
   <tr>
   <td><img width="500" src="res/Array6.png"></td>
   <td>Array notes...</td>
   </tr>
   <tr>
   <td><img width="500"  src="res/Linked6.png"></td>
   <td>Linked list notes...</td>
   </tr>
   </table>

1. **Clear.** Say something here....

   ```java
   myList.clear();
   System.out.println("List Size: " + myList.size());
   System.out.println("List Contents: " + myList.makeString(","));
   ```
   ```
   List Size: 0
   List Contents:
   ```
   
   <table>
   <tr>
   <td><img width="500" src="res/Array7.png"></td>
   <td>Array notes...</td>
   </tr>
   <tr>
   <td><img width="500"  src="res/Linked1.png"></td>
   <td>Linked list notes...</td>
   </tr>
   </table>
   
<hr/>

## Conclusion

In general, different implementations have various pros and cons. Some of 
the pros and cons of the array-based approach and the linked approach are listed below:

|        | Array Approach    | Linked List Approach |
|--------|-------------------|----------------------|
| Pros   |                   |                      |
| Cons   |                   |                      |
[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
