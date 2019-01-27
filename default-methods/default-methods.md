# Default Methods Tutorial

This tutorial introduces the reader to default methods in Java, a powerful way to evolve an interface.

**NOTE:** Many of the code examples in this tutorial are adapted from
[[1]](https://www.pearson.com/us/higher-education/program/Lewis-Java-Foundations-Introduction-to-Program-Design-and-Data-Structures-4th-Edition/PGM76634.html)
under academic fair use.

### Prerequisites

This tutorial assumes that the reader has a knowledge of creating and implementing Java interfaces
with a command-line text editor (e.g. emacs, vi, etc.). The reader should be familiar with compiling
and running Java code contained in packages and working with the Javadoc tool. 

To get the most out of this tutorial, you should follow along by executing and modifying the code
and take notes.

### Getting Started

The steps in this tutorial assume that you are logged into the Nike server. 

1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-default-methods`:

   ```
   $ curl -s -L https://git.io/fhKfi | bash
   ```
  
1. Change into the `cs1302-default-methods` directory that was just created and look around.
   There should be multiple Java files contained within the directory structure. To see a listing
   of all of the files under the `src` subdirectory, use the `find` command as follows:
   
   ```
   $ find src
   ```
   
   Inspect each `.java` file that was listed. You will notice that there are three classes that
   implement the `Encryptable` interface using different types of encryption.

1. Compile the code you downloaded using `bin` as the default directory for compiled code. Pay
   close attention to dependencies to ensure proper compilation order.

1. Run the `SecretDriver` class. Notice how the messages are encrypted using various encryption
   algorithms.

1. Generate, host, and view the API documentation website for the starter code provided with this tutorial.
   Use `cs1302-default-methods-doc` as the name of the symbolic link.

1. Look through the documentation website you just created. You might notice that the page for the 
   `Encryptable` interface lists all known implementing classes just below the interface name near
   the top of the page. 
   
### Modifying an Interface

Since a conrete (i.e., instantiable) class that implements an interface **must** implement all methods 
of the interface, any change to an interface has a direct impact on all implementing classes. Therefore,
the decision to modify an interface should not be taken lightly. Imagine if Oracle decided to add a few 
methods to a Java interface used by millions of programmers around the world. This decision would have 
a direct impact on all of those developers!

1. To see the impact of such a change on a **much** smaller scale, go to the source code for the 
   `Encryptable` interface and uncomment the `getState` method along with the `State` enumeration.

1. Recompile only the `Encryptable` interface using `bin` as the default directory for compiled code. If 
   you uncommented the code correctly in the previous step, there should be no compile-time errors.

1. Compile `BasicSecret.java`, `Secret.java`, and `SuperSecret.java`.

   **Aside Compilation Shortcut:**
   From the `cs1302-default-methods` directory, you can compile `BasicSecret.java`,
   `Secret.java`, and `SuperSecret.java` simultaneously using the following command:

   ```
   $ javac -d bin -cp bin src/cs1302/interfaces/impl/*.java
   ```

   Note the use of the wildcard character `*`. When you execute this command, the bash shell expands the
   `*` into all filenames ending in `.java` in the `impl` directory. This provides three files as
   command-line arguments to `javac`. An equivalent (but longer) way to write the above command would be:

   ```
   $ javac -d bin -cp bin src/cs1302/interfaces/impl/SuperSecret.java src/cs1302/interfaces/impl/Secret.java
    /src/cs1302/interfaces/impl/BasicSecret.java
   ```
   
   Just to be clear, both of the command examples provided above supply the same number of command-line
   argumensts to `javac`.
   
1. Compilation of the three `Encryptable` classes in the step above results in the following compile-time 
   errors if steps 2 and 3 were followed correctly:

   ```
   src/cs1302/interfaces/impl/BasicSecret.java:15: error: BasicSecret is not abstract and does not override   abstract method isEncrypted() in Encryptable
   public class BasicSecret implements Encryptable {
   ^
   src/cs1302/interfaces/impl/Secret.java:15: error: Secret is not abstract and does not override abstract method isEncrypted() in Encryptable
   public class Secret implements Encryptable {
   ^
   src/cs1302/interfaces/impl/SuperSecret.java:15: error: SuperSecret is not abstract and does not override abstract method isEncrypted() in Encryptable
   public class SuperSecret implements Encryptable {
   ```

   Notice that each of the errors state that the implementing class "does not override abstract method 
   `getState` in `Encryptable`". Since these classes don't implement all of the methods in `Encryptable`,
   they won't compile.
   
### Default Methods

Given that we only have three classes that implement `Encryptable` and access to the source code, we _could_ 
go through them one at a time and add the `getState` method to each. However, this is not always feasible 
(remember the Oracle example). A nice alternative is to use a *Default Method* which allows the programmer
to provide a default implementation for the method. Since the method is defined at the interface level, the 
implementation should be general.

1. As an example, modify the `Encryptable` interface by replacing the current `getState` abstract 
   method with the following public method alternative:

   ```java
   public Encryptable.State getState() {
      return Encryptable.State.UNKNOWN;
   } // getState
   ```

   Notice that the return type is `Encryptable.State`. Since an enumerated type in an interface is implicitly
   static, we refer to the type using the interface name.
   
1. Recompile only the `Encryptable` interface using `bin` as the default directory for compiled code. You 
   will see a compile-time error which includes a message stating "interface abstract methods cannot have
   body". The problem occurred because we added what looks like an instance method to the interface. Since
   all non-static methods in an interface are implicitly abstract, we cannot provide an implementation in 
   this way.

1. In order to provide a default implementation for `getState`, we must explicitly declare the method as 
   `default`. The `default` keyword is place after the visibility modifier, if present, and before the 
   return type. Why is this keyword required? Well, default methods were not originally part of the Java 
   programming language. They were added in Java 8. While the compiler could be updated to allow default
   implementations in interfaces without the need for a `default` keyword, the reason likely boiled down
   to two reasons: i) millions of Java programmers were already accustomed to existing syntax within 
   Java interfaces; and ii) it's easy for the compiler to identify these methods by the keyword. 

1. If you haven't done so already, add the `default` keyword to the `getState` method signature. Recompile 
   only the `Encryptable` interface using `bin` as the default directory for compiled code. Now it should 
   compile properly.

1. Compile `BasicSecret.java`, `Secret.java`, and `SuperSecret.java`.

   ```
   $ javac -d bin -cp bin src/cs1302/interfaces/impl/*.java
   ```

   This time they compiled!  

You have probably already noticed the benefit of default methods. Since we provided a default 
implementation for the `getState` method in the interface, we don't have to go through each 
implementing class and add the method. However, please note that the default implementation is
a general implementation and does not properly reflect the state of the `Encryptable` object. It
simply allows the existing implementing classes to compile without modification.

To summarize, there are two important benefits to default methods:
* They give programmers who develop and maintain the implementing
classes a **choice** of whether or not to implement the new method. 
* They allow interface designers to evolve an interface without
disrupting existing implementations.
 
### Overriding a Default Method

If a programmer chooses to implement a default method, they can override the behavior in the implementing
class. In our example, let's implement `getState` in the `SuperSecret` class so it returns the actual state
of the object instead of `UNKNOWN`.

1. Go to the source code for the `SuperSecret` class and make the following changes:

   * Add a private instance variable of type `Encryptable.State` called `state`. 
     This instance variable will represent the state of this `Encryptable` object.  Remember, the state can 
     be any of the constants contained in the enumeration (`ENCRYPTED`, `UNENCRYPTED`, or `UNKNOWN`).
   
   * In the constructor, set the value of the `state` instance variable to `Encryptable.State.UNENCRYPTED`.

   * In the `encrypt` and `decrypt` methods, set the state of the object appropriately.

1. Recompile **only** `SuperSecret` using `bin` as the default package for compiled code. Since we didn't
   change the other implementing classes, they don't need to be recompiled.

1. To override the default method of `Encryptable`, add the following code to the `SuperSecret` class:

   ```java
   @Override
   public Encryptable.State getState() {
      return this.state;
   } // getState
   ```

1. Notice that the signature of the `getState` method in `SuperSecret` matches the signature of the default
   method in the `Encryptable` interface, except the `default` keyword is omitted. To have the compiler
   verify that the signatures match, we added the `@Override` annotation. If we make a mistake when typing 
   the method signature, the compiler will let us know that the signatures do not match.
   
1. Try it. Change the method name to `getStat` (instead of `getState`) in `SuperSecret`.  You will see
   the following error message indicating that the method does not override a method of `Encryptable`:
   
   ```
   src/cs1302/interfaces/impl/SuperSecret.java:61: error: method does not override or implement a method from a supertype
       @Override
       ^
   ```

   If the annotation were omitted, the `SuperSecret` class would have compiled but would contain two separate
   methods: `getStat` and `getState`.  Calling `getState` would return `UNKNOWN` and calling `getStat` would
   return the state of the calling object. In general, this is a difficult mistake to catch without compiler
   assistance. Therefore, the use of the `@Override` annotation, although not required, is always recommended 
   when your intent is to override.
   
1. Change `getStat` back to `getState` and recompile your `SuperSecret` class. If done properly, the code should now
   compile.

1. Go to the source code for `SecretDriver`. In the `test` method, identify the lines that print to standard
   output the `Encryptable` object that `e` refers to (should be three). After each line, add a `println` 
   statement that prints the value returned by `e.getState()`. After recompiling and running the `SecretDriver`
   class, your output should look something like the output provided below. **NOTE:** The encrypted message 
   contents may differ slightly due to the random nature of the encryption algorithms employed.
   
   ```
   # Secret Test
   Secret(Hello, world...)
   UNKNOWN
   Secret(©ÆÍÍÐØÐÓÍÅ)
   UNKNOWN
   Secret(Hello, world...)
   UNKNOWN
   # SuperSecret Test
   SuperSecret(Hello, world!!!)
   UNENCRYPTED
   SuperSecret(_ŝ²ºÐ½_§ôŝ»ïú)
   ENCRYPTED
   SuperSecret(Hello, world!!!)
   UNENCRYPTED
   # BasicSecret Test
   Secret(Hello, world?)
   UNKNOWN
   Secret(Ifmmp-!xpsme@)
   UNKNOWN
   Secret(Hello, world?)
   UNKNOWN
   ```
   
   Notice the output related to `SuperSecret`.  Compare that to the output related to `Secret` and `BasicSecret`.
   
That's it!  You've completed the default methods tutorial.  Hopefully, you've recognized some of the pros and cons
related to the different strategies for involving an interface. In some scenarios, it may be appropriate to add
more abstact methods to an interface at the cost of modifying existing implementing classes. In other scenarios,
it may be more appropriate (or even necessary) to add default methods at the cost of providing only a general 
implementation to the existing implementing classes. Other scenarios may necessitate a mix of both approaches. 

### References

* [[1] Lewis, DePasquale, and Chase. _Java Foundations_. Fourth Edition.](https://www.pearson.com/us/higher-education/program/Lewis-Java-Foundations-Introduction-to-Program-Design-and-Data-Structures-4th-Edition/PGM76634.html)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
