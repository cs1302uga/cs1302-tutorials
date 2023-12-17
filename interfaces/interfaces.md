# Interfaces Tutorial

![Approved for: Fall 2023](https://img.shields.io/badge/Approved%20for-Fall%202023-green)

This tutorial introduces the reader to Java interfaces and polymorphism.

### Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working 
with a command-line text editor (e.g. emacs, vi, etc.). The reader should be familiar with compiling
and running Java code contained in packages and working with the Javadoc tool. 

To get the most out of this tutorial, you should follow along and take notes.

## Course-Specific Learning Outcomes

* **LO2.e:** (Partial) Utilize existing generic methods, interfaces, and classes in a software solution.
* **LO3.c:** Generate user-facing API documentation for a software solution.
* **LO4.b:** Utilize interface-based polymorphism in a software solution.

### Getting Started

The steps in this tutorial assume that you are logged into the Odin server. 

1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-interfaces`:

   ```
   $ curl -s -L https://git.io/fhgce | bash
   ```
  
1. Change into the `cs1302-interfaces` directory that was just created and look around. There should be
   multiple Java files contained in the `src` directory. To see a listing of all of the 
   files in `src`, use the `find` command as follows:
   
   ```
   $ find src
   ```
   
   Execute the `cat` command on each `.java` file that was listed to view the contents. You will 
   notice that some of 
   them contain interfaces instead of classes. They can be identified by looking for the `interface` 
   keyword instead of the `class` keyword in the overall type declaration specified near the top of 
   the file. We say "type" declaration because a `.java` file can be used to declare any kind of 
   reference type in Java, including classes, interfaces, and class-based enumerations. A 
   **reference type** in Java is any type that can serve as the type for a variable that refers to 
   an object. Such a variable is known as a **reference variable**. We will elaborate on this 
   terminology in the context of interfaces a little more later in this tutorial. If you are 
   unfamiliar with these terms in general, please review the 
   [Reference Variable Refresher](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/refresher/variables.md)
   from last week. You are encouraged to ask questions about any parts that you find confusing.

### What is an Interface?

In its simplest form, a Java **interface** is a reference type composed of **abstract methods** and 
**constants**. An **abstract method** is a non-static method without an implementation (body). Think of
creating a class, adding the method signatures, but not putting any code in the methods. This will seem
strange at first but the benefit should become clear as you work through the example. **Constants**
are variables (static or not) that are declared using the `final` keyword. As of Java 8, the
technical definition for an *interface* allows it to contain only the following:
abstract methods, constants, private methods, static methods, nested types, and default implementation
methods [[2]](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html). In 1302, our interfaces
will mostly contain only abstract methods and constants. Nested types and default methods will not 
be covered in this tutorial.

Interfaces are used to specify that a type *can do* a set of things specified by its
abstract methods and constants. An interface serves as a *contract* for the classes that 
claim to implement the interface. Multiple classes can implement the same interface,
each providing their own implementation of the contracted functionality. For this
reason, it is important that the documentation for an interface describe *what* a method
does and not necessarily *how* it should do it. Such documentation is usually written using
Javadoc comments in the interface.

### Real World Example

Remember, we said an interface is a *contract* for the classes that claim to implement it. Take a 
moment to think about how contracts are used in the real world (forget Java for a minute). 

Hopefully, you came up with a definition of the word contract and maybe a few situations where 
contracts are used. 

In the real world, we think of a contract as a formal and binding agreement 
between two or more parties. Let's use the professional athlete as an example. Athletes sign a contract which is a 
binding agreement between the athlete and the team/organization that ensures the athlete will compete
in his/her sport. The contract states that the athlete must "compete" but it doesn't say specifically 
*how* they will compete. The details of exactly how an athlete should compete are usually not mentioned in the contract. 
The contract simply binds the athlete to compete. The specific details and decisions that occur while the competition
is ongoing is up to the athlete. Once the contract is written, it could be signed by athletes
in a wide variety of sports. The signer could be a track athlete, a baseball player, a football
player, a racecar driver, etc. Again, the signer determines the details of how they will compete. The contract only 
binds them to the action of competing.

Now, let's tie this back to programming. In the example above, `compete` is the abstract method 
that would be placed in the `Athlete` interface (contract) since all athletes must be able to compete. 
The abstract method represents the action that is required of the signer of the contract. In other words, 
the method is what the signer is obligated to do when they agree to implement the interface (a.k.a. 
sign the contract). The implementation details of the `compete` method area not given in the interface 
itself but instead they are written in the implementing class (signer). Again, the implementing class 
(contract signer) can be any type of athlete as they are all required to compete.

This may all still seem a bit strange and why we do this in programming may not yet be clear.
Hang in there! Let's work through an example in Java. Try to keep this terminology in mind as you work
through this tutorial.

### Declaring an Interface

1. Interfaces, just like classes, have a fully qualified name. Their source code should be 
   positioned and named within your project the same as with classes. That is, an interface
   called `cs1302.interfaces.contract.Styleable` has an implied position within the package
   directories of your source code (`cs1302/interfaces/contract`) and should be placed 
   in a `Styleable.java` file within the implied directory. The first big syntax difference 
   between a class and an interface is illustrated in 
   [`Styleable.java`](src/cs1302/interfaces/contract/Styleable.java):
   
   ```java
   public interface Styleable {
   ```
   
   Note the use of the `interface` keyword instead of `class` in the type header. In this example,
   `Styleable` is the contract that can be signed by other classes.

1. The second big syntax difference involves the inclusion of abstract methods, illustrated
   by the `style()` method in [`Styleable.java`](src/cs1302/interfaces/contract/Styleable.java).
   
   ```java
   public void style();
   ```
   
   Notice that the `style()` method does not contain an implementation. The signature of the method 
   ends with a semicolon. An abstract method must not have an implementation. The following is **NOT** an
   abstract method:
   
   ```java
   public void style() { }
   ```
   
   While the `{ }` may not do anything, it is, in fact, an implementation that does nothing. Compare that
   to the actual abstract method signature presented above that ends with a semicolon, thus lacking an
   implementation. 
   
   If you open the `Styleable.java` file, you will see that it also contains an abstract `unstyle` method.
   Remember, that the abstract method(s) represent what the signer of the contract must be able to do. If a class
   implements the `Styleable` interface, it is obligated to have a concrete (non-abstract) `style` and a concrete
   `unstyle` method. If an implementing class does not have implementations for one or both of these methods,
   it will not compile.
   
   **NOTE:** In Java, the declaration of an abstract method in the source code for an interface may omit
   the `public` visibility modifier. If `public` is omitted in this context, the abstract method is
   still assumed to have `public` visibility. This behavior is different for classes, a topic that will
   be covered more in depth at a later time when the nuances of visibility are presented. 

1. Generate, host, and view the API documentation website for the starter code provided with this tutorial.
   Find the `Styleable` interface on the website and compare the documentation provided there with what
   you see in the Javadoc comments included in `Styleable.java`. If you don't remember how to do this,
   then please refer back to your notes for the 
   [Javadoc and API Documentation](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/javadoc/javadoc.md)
   tutorial.

### Implementing an Interface

Remember, the `Styleable` interface is just the contract. We also need to have at least one class
that implements (signs) the contract. We will use two different implementing classes to demonstrate
that the implementation specifics are up to the implementing class (not the interface) just as the
specific details regarding how an athlete will compete are up to the athlete (not the contract). 

1. In Java, a class can implement an interface using the `implements` keyword, e.g., as seen 
   in [`Fancy.java`](src/cs1302/interfaces/impl/Fancy.java):

   ```java
   public class Fancy implements Styleable {
   ```
   
   In this example, `Fancy` is the implementing class that has signed the `Styleable` contract. It signs
   the contract as soon as you add `implements Styleable` to the class declaration as seen above. Again, `Fancy`
   will not compile unless it has a concrete (non-abstract) implementation of both the `style` and `unstyle` methods.
   
   **Note:** If the interface is not in the same package as the implementing class, then you will need to add
   an `import` statement or use the fully qualified name of the interface. If more than one interface
   is being implemented, then they can be written in a comma separated list following the
   `implements` keyword.
   
1. As we mentioned before, when a class properly implements an interface in Java, it is required to 
   provide implementations 
   for each of the abstract methods defined in the interface. If you inspect the source code for the 
   `cs1302.interfaces.impl.Fancy` class, you will see the abstract methods from `Styleable` 
   implemented. Notice that the implementatons contain method bodies (instead of their signatures
   ending with a semicolon). A specific example can be seen with the `unstyle()` method 
   in [`Fancy.java`](src/cs1302/interfaces/impl/Fancy.java):
   
   ```java
   public void unstyle() {
   ```
   
   Again, this differs from the abstract method defined in the interface only in so far as it has an
   implementation. The other aspects of the method signature (return type, visibility, name) are the same.
   
   **NOTE:** As mentioned earlier, it is syntactically correct for an abstract method in an interface
   to omit the `public` keyword. Remember, in this context, the method is still assumed to have
   public visibility. Therefore, the implementation of such a method in **an implementing class will
   need to include the `public` visibility modifier**.
   
1. Compare the Javadoc comments in the source code for the `Styleable` interface with the comments 
   written in the source code for the implementing `Fancy` class. In some cases, new comments are 
   provided. In others, it appears as though Javadoc comments are omitted. In the latter case, this 
   is actually not true. View the API documentation website for both the `Styleable` interface and 
   the `Fancy` class — bring them up side-by-side, if possible. All of the methods in `Fancy` are 
   documented, even `style()` and `unstyle()` which have no Javadoc comments in the source code!
   
   This happens because the Javadoc tool has the ability to inherit comments from an interface when omitted
   or when explicitly requested in the implementing class's Javadoc comment using the `{@inheritDoc}` tag. 
   Pay close attention to the difference between these two scenarios, both in the source code and in the
   generated API documentation website.
   
   **Note:** The `Fancy` class will not pass `checkstyle` unless you add a Javadoc comment to the method
   containing `{@inheritDoc}`. You may need to do this if you need a method to pass `checkstyle` when it
   inherits its comments from an interface or parent class (covered later in the course).

### Using an Interface

In this section, we will motivate **why** we set up the interface/implementing class relationship. The main
benefit is type compatibility between the interface type and the implementing class type(s). Leveraging this
compatibility will lead to more elegant code that works with objects of any class that implement the interface.
Connecting this to our real world example, we could write code that works with all athletes instead of having
to write separate (but similar) code for each type of athlete. Would you rather write one method that works
for all athletes or write individual methods for basketball players, football players, track athletes, golfers, 
etc.? With that in mind, let's go back to our code example:

1. Interfaces are reference types in Java. This means that they can be serve as 
   the type for a reference variable. You should be familiar with the use of class names for reference
   variable types. The code snippet below illustrates both scenarios:
   
   ```java
   Fancy f;
   Styleable s;
   ```
   
1. Reference variables are called as such because they refer to objects. However, you can only create
   objects from classes (not interfaces)! Therefore, what can a `Styleable` variable refer to? The answer is that 
   a variable with an interface as its type can refer to an object of any class that implements that
   interface. The code snippet below illustrates this:
   
   ```java
   Styleable s = new Fancy("some message");
   ```
   
1. When an object is referred to via a reference variable with an interface type, the only methods
   than can be called using that variable are the ones declared in the interface, regardless of whether
   the object's class declared other methods. For example, even though the
   `getAbout()` method is declared in the [`SuperFancy.java`](src/cs1302/interfaces/impl/SuperFancy.java)
   class and therefore _is_ part of a `SuperFancy` object, it would not be available via an
   `Styleable` variable. The following two code snippets illustrate this difference:
   
   ```java
   SuperFancy sf = new SuperFancy("some fancier message?");
   sf.style();                    // OK
   sf.unstyle();                  // OK
   String about = sf.getAbout();  // OK -- variable type is SuperFancy
   ```
   
   ```java
   Styleable s = new SuperFancy("some fancier message?");
   s.style();                    // OK
   s.unstyle();                  // OK
   String about = s.getAbout();  // NOT OK! -- variable type is Styleable
   
   ```
   
   **NOTE:** The only other methods available via a reference variable with an interface type
   are the methods listed in the 
   [`java.lang.Object`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html)
   class, which are common to all objects in Java. We will come back to the `Object` class
   in a future tutorial or reading, but it includes methods like `equals` and `toString`. 
   
1. You might be wondering why the previous example is useful? If so, that's okay. In general,
   you should try to be specific with the types you use for variables. However, the ability
   to assign object references to variables with interface types leads to a powerful programming
   technique known as **polymorphism**. Polymorphism is a fancy word (might be fun to style it in
   a super fancy way!), derived from the Greek words _poly_ and _morphus_, which roughly translates 
   to _many bodies_. Polymorphism leverages our ability to have a variable appear to take on many 
   forms (or bodies) depending on the object it refers to. 
   
   Consider the following code snippet:
   
   ```java
   Styleable s;
   
   s = new Fancy("some fancy message");
   s.style();
   System.out.println(s); // invoke toString() method
   
   s = new SuperFancy("some fancier message?");
   s.style()
   System.out.println(s); // invoke toString() method
   ```
   
   Notice how we were able to refer to two different objects using the same variable! When
   `s.style()` is called the first time, it invokes the `Fancy` class version of the
   method, because that's the type of object being referred to. When `s.style()` is
   called the second time, it invokes the `SuperFancy` version of the method for a
   similar reason. The exact same thing happens with the implicit call to `toString()`
   when printing the objects. 
   
 1. You may have noticed some repetition in the previous example. Without loss of generality,
    the two parts of the code snippet that seem to repeat can be refactored into a method.
    Something similar to this is done in the `main` and `test` methods of the 
    [`cs1302.interfaces.StyleDriver`](src/cs1302/interfaces/StyleDriver.java) class.
    You should inspect both methods in the source code closely. What should they do? 
    What do they do? 
    
    Write what you expect the output to be from an execution of `StyleDriver`. Then, 
    compile and run the code provided with this tutorial. Since there are multiple 
    dependencies, the order of compilation matters:
    
    1. `src/cs1302/interfaces/contract/Styleable.java`
    1. `src/cs1302/interfaces/impl/Fancy.java`
    1. `src/cs1302/interfaces/impl/SuperFancy.java`
    1. `src/cs1302/interfaces/StyleDriver.java`
    
    Remember, you may need to specify the class path in addition to the destination
    when using `javac` to compile Java code that depends on other Java code. If you need a
    refresher on this subject, then refer to the 
    [Java Packages Tutorial Part 1](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/packages1.md) and
    [Java Packages Tutorial Part 2](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/packages2.md).

### Common Functionality among Disparate Classes

Below is a video that demonstrates how to use interfaces to write code involving 
disparate classes with common functionality that is clean, easier to read, and 
easier to modify. Here, we use the term disparate to describe classes that are
dissimilar in _what_ they describe but still, perhaps, share some common 
functionality.

https://www.youtube.com/watch?v=kcBV6tlg44I

The source code for the video can be accessed [here](https://github.com/cs1302uga/cs1302-tutorials/tree/alsi/interfaces/donator/src/cs1302/interfaces).

<a href="https://www.youtube.com/watch?v=kcBV6tlg44I">
<img src="https://img.youtube.com/vi/kcBV6tlg44I/0.jpg?20190821" alt="IMAGE ALT TEXT">
</a>

### References

* [[1] Oracle Java Tutorials: Interfaces](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)

<hr/>

**Feedback?** 
Please help us make this better!
If you have any feedback or suggestions for this reading or tutorial, then use 
the link below to reach the feedback form.

[![Submit Feedback](https://img.shields.io/badge/-Submit&nbsp;Feedback-red.svg?style=for-the-badge)](https://docs.google.com/forms/d/e/1FAIpQLSfBgZM_-G-9nKmX7F83k0Tgp1OlqBnrkt6vsxlIqLypc_keUQ/viewform?usp=pp_url&entry.1081181680=cs1302-interfaces&entry.1901270436=https://github.com/cs1302uga/cs1302-tutorials/blob/master/interfaces/interfaces.md)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>

<small>
Some of the code examples in this tutorial are inspired by code included with 
Lewis, DePasquale, and Chase. <i>Java Foundations</i>. Fourth Edition.
</small>
