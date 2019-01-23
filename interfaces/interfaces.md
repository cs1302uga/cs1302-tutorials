# Interfaces Tutorial

This tutorial introduces the reader to Java interfaces and polymorphism.

**NOTE:** Many of the code examples in this tutorial are adapted from
[[1]](https://www.pearson.com/us/higher-education/program/Lewis-Java-Foundations-Introduction-to-Program-Design-and-Data-Structures-4th-Edition/PGM76634.html)
under academic fair use.

### Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working 
with a command-line text editor (e.g. emacs, vi, etc.). The reader should be familiar with compiling
and running Java code contained in packages and working with the Javadoc tool. 

To get the most out of this tutorial, you should follow along and take notes.

### Getting Started

The steps in this tutorial assume that you are logged into the Nike server. 

1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-interfaces`:

   ```
   $ curl -s -L https://git.io/fhgce | bash
   ```
  
1. Change into the `cs1302-interfaces` directory that was just created and look around. There should be
   multiple Java files contained within the directory structure. To see a listing of all of the 
   files under the current directory, use the `find` command as follows:
   
   ```
   $ find .
   ```
   
   Inspect each `.java` file that was listed. You will notice that some of them contain interfaces
   instead of classes. They can be identified by looking for the `interface` keyword instead of
   the `class` keyword in the overall type declaration specified near the top of the file. We say
   "type" declaration because a `.java` file can be used to declare any kind of reference type in
   Java, including classes, interfaces, and class-based enumerations. A **reference type** in Java
   is any type that can serve as the type for a variable that refers to an object. Such a variable
   is known as a **reference variable**. We will elaborate on this terminology a little more later 
   in this tutorial.

### What is an Interface?

In its simplest form, a Java **interface** is a reference type composed of abstract methods and 
constants. An **abstract method** is a non-static method without an implementation. Constants
are variables (static or not) that are declared using the `final` keyword. As of Java 8, the
technical definition for an *interface* allows it to contain only the following:
abstract methods, constants, static methods, nested types, and default implementation
methods [[2]](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html).
Nested types and default methods will not be covered in this tutorial. 

Interfaces are used to specify that a type *can do* a set of things specified by its
abstract methods and constants. An interface serves as a contract for the classes that 
claim to implement the interface. Multiple classes can implement the same interface,
each providing their own implementation of the contracted functionality. For this
reason, it is important that the documentation for an interface describe *what* a method
does and necessarily *how* it should do it. Such documentation is usually written using
Javadoc comments in the interface.

### Declaring an Interface

1. Interfaces, just like classes, have a fully qualified name. Their source code should be 
   positioned and named within your project the same as with classes. That is, an interface
   called `cs1302.interfaces.Encryptable` has an implied position within the package
   directories of your source code and should be placed in an `Encryptable.java` file.
   The first big syntax difference between a class and an interface is illustrated in
   [`Encryptable.java:21`](src/cs1302/interfaces/contract/Encryptable.java#L21):
   
   ```java
   public interface Encryptable {
   ```
   
   Note the use of the `interface` keyword instead of `class` in the type header.
   

1. The second big syntax difference involves the inclusion of abstract methods, illustrated
   by the `encrypt()` method in [`Encryptable.java:27`](src/cs1302/interfaces/contract/Encryptable.java#L27).
   
   ```java
   public void encrypt();
   ```
   
   Notice that the `encrypt()` method does not contain an implementation. The signature of the method 
   ends with a semicolon. An abstract method may not have an implementation. The following is **NOT** an
   abstract method:
   
   ```java
   public void encrypt() { }
   ```
   
   While the `{ }` may not do anything, it is, in fact, an implementation that does nothing. Compare that
   to the actual abstract method signature presented above that ends with a semicolon, thus lacking an
   implementation. 
   
   **NOTE:** In Java, the declaration of an abstract method in the source code for an interface may omit
   the `public` visibility modifier. If `public` is omitted in this context, the abstract method is
   still assumed to have `public` visibility. This behavior is different for classes, a topic that will
   be covered more in depth at a later time when the nuances of visibility are presented. 

1. Generate, host, and view the API documentation website for the starter code provided with this tutorial.
   Find the `Encryptable` interface on the website and compare the documentation provided there with what
   you see in the Javadoc comments included in `Encryptable.java`. 

### Implementing an Interface

1. In Java, a class can implement an interface using the `implements` keyword, e.g., as seen 
   in [`Secret.java:15`](src/cs1302/interfaces/impl/Secret.java#L15):

   ```java
   public class Secret implements Encryptable {
   ```
   
   If the interface is not in the same package as the implementing class, then you will need to add
   an `import` statement or use the fully qualified name of the interface. If more than one interface
   is being implemented, then they can be written in a comma separated list following the
   `implements` keyword.
   
1. When a class properly implements an interface in Java, it is required to provide implementations 
   for each of the abstract methods defined in the interface. If you inspect the source code for the 
   `cs1302.interfaces.impl.Secret` class, you will see the abstract methods from `Encryptable` 
   implemented. Notice that the implementatons contain method bodies (instead of their signatures
   ending with a semicolon). A specific example can be seen with the `encrypt()` method 
   in [`Secret.java:44`](src/cs1302/interfaces/impl/Secret.java#L15):
   
   ```java
   public void encrypt() {
   ```
   
   Again, this differs from the abstract method defined in the interface only in so far as it has an
   implementation. The other aspects of the method signature are the same.
   
   **NOTE:** As mentioned earlier, it is syntactically correct for an abstract method in an interface
   to omit the `public` keyword. Remember, in this context, the method is still assumed to be have
   public visibility. Therefore, the implementation of such a method in an implementing class will
   need to include the `public` visibility modifier.
   
1. Compare the Javadoc comments in the source code for the interface with the comments written in the
   source code for the implementing `Secret` class. In some cases, new comments are provided. In others,
   it appears as though Javadoc comments are omitted. In the latter case, this is actually not true. 
   View the API documentation website for both the `Encryptable` interface and the `Secret` class--bring
   them up side-by-side, if possible. All of the methods in `Secret` are documented! 
   
   This happens because the Javadoc tool has the ability to inherit comments from an interface when omitted
   or when explicitly requested in the implementing class's Javadoc comment using the `{@inheritDoc}` tag. 
   Pay close attention to the difference between these two scenarios, both in the source code and in the
   generated API documentation website.

### Using an Interface

1. As mentioned earlier, interfaces are reference types in Java. This means that they can be serve as 
   the type for a reference variable. You should be familiar with the use of class names for reference
   variable types. The code snippet below illustrates both scenarios:
   
   ```java
   Secret s;
   Encryptable e;
   ```
   
1. Reference variables are called as such because they refer to objects. However, you can only create
   objects from classes! Therefore, what can an `Encryptable` variable refer to? The answer is that 
   a variable with an interface as its type can refer to any object of class that implements that
   interface. The code snippet below illustrates this:
   
   ```java
   Encryptable e = new Secret("some message");
   ```
   
1. When an object is referred to via a reference variable with an interface type, the only methods
   than can be called using that variable are the ones declared in the interface, regardless of whether
   the object's class declared other methods. For example, even though the
   `getAbout()` method is declared in the [`SuperSecret`](src/cs1302/interfaces/impl/SuperSecret.java#L81)
   class and therefore _is_ part of a `SuperSecret` object, it would not be available via an
   `Encryptable` variable. The following two code snippets illustrate this difference:
   
   ```java
   SuperSecret ss = new SuperSecret("some secreter message?");
   ss.encrypt();                  // OK
   ss.decrypt();                  // OK
   String about = ss.getAbout();  // OK -- variable type is SuperSecret   
   ```
   
   ```java
   Encryptable ss = new SuperSecret("some secreter message?");
   ss.encrypt();                  // OK
   ss.decrypt();                  // OK
   String about = ss.getAbout();  // NOT OK! -- variable type is Encryptable   
   ```
   
   **NOTE:** The only other methods available via a reference variable with an interface type
   are the methods listed in the 
   [`java.lang.Object`](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html)
   class, which are common to all objects in Java. We will come back to the `Object` class
   in a future tutorial or reading, but it includes methods like `equals` and `toString`. 
   
1. You might be wondering why the previous example is useful? If so, that's okay. In general,
   you should try to be specific with the types you use for variables. However, the ability
   to assign object references to variables with interface types leads to a power programming
   technique known as **polymorphism**. Polymorphism is a fancy word, derived from the Greek
   words _poly_ and _morphus_, which roughly translates to _many bodies_. It's a powerful
   programming technique that leverages our ability to have a variable appear to take on
   many forms (or bodies) depending on the object it refers to. 
   
   Consider the following code snippet:
   
   ```java
   Encryptable e;
   
   e = new Secret("some secret message");
   e.encrypt();
   System.out.println(e); // invoke toString() method
   
   e = new SuperSecret("some secreter message?");
   e.encrypt()
   System.out.println(e); // invoke toString() method
   ```
   
   Notice how we were able to refer to two different objects using the same variable! When
   `e.encrypt()` is called the first time, it invokes the `Secret` class version of the
   method, because that's the type of object being referred to. When `e.encrypt()` is
   called the second time, it invokes the `SuperSecret` version of the method for a
   similar reason. The exact same thing happens with the implicit call to `toString()`
   when printing the objects. 
   
 1. You may have noticed some repetition in the previous example. Without loss of generality,
    the two parts of the code snippet that seem to repeat can be refactored into a method.
    Something similar to this is done in the `main` and `test` methods of the 
    [`cs1302.interfaces.SecretDriver`](src/cs1302/interfaces/SecretDriver.java) class.
    You should inspect both methods in the source code closely. What should they do? 
    What do they do? 
    
    To answer the second question, compile and run the code provided
    with this tutorial. Since there are multiple dependencies, the order of compilation
    matters:
    
    1. `src/cs1302/interfaces/contract/Encryptable.java`
    1. `src/cs1302/interfaces/impl/Secret.java`
    1. `src/cs1302/interfaces/impl/Secret.java`
    1. `src/cs1302/interfaces/SecretDriver.java`
    
    Remember, you may need to specify the class path in addition to the destination
    when using `javac` to compile Java code that depends on other Java code. If you need a
    refresher on this subject, then refer to the 
    [Java Packages Tutorial](https://github.com/cs1302uga/cs1302-tutorials/blob/master/packages.md).

### References

* [[1] Lewis, DePasquale, and Chase. _Java Foundations_. Fourth Edition.](https://www.pearson.com/us/higher-education/program/Lewis-Java-Foundations-Introduction-to-Program-Design-and-Data-Structures-4th-Edition/PGM76634.html)
* [[2] Oracle Java Tutorials: Interfaces](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
