# Default Methods Tutorial

This tutorial introduces the reader to default methods in Java.

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
   curl -s -L https://git.io/fhKfi | bash

   ```
  
1. Change into the `cs1302-default-methods` directory that was just created and look around.
   There should be multiple Java files contained within the directory structure. To see a listing
   of all of the files under the current directory, use the `find` command as follows:
   
   ```
   $ find .
   ```
   
   Inspect each `.java` file that was listed. You will notice that there are three classes that
   implement the `Encryptable` interface using different types of encryption.

1. Compile the code you downloaded using `bin` as the default directory for compiled code. Pay
   close attention to dependencies to ensure proper compilation order.

1. Run the `SecretDriver` class. Notice how the messages are encrypted using various encryption
   algorithms.

1. Generate, host, and view the API documentation website for the starter code provided with this tutorial.

### Modifying an Interface

Since a class that implements an interface *must* implement all methods of the interface, any changes to
an interface has a direct impact on all implementing classes. Therefore, the decision to modify an
interface should not be taken lightly. Imagine if Oracle decided to add a few methods to a Java
interface used by millions of programmers around the world. This decision would have a direct impact
on all of those developers. 

1. To see the impact of such a change on a *much* smaller scale, uncomment the method `getState` and
   the `State` enumeration in the `Encryptable` interface. 

1. Compile the `Encryptable` interface using `bin` as the default directory for compiled code. If you
   added the code correctly in the previous step, there should be no syntax errors.

1. Compile `BasicSecret.java`, `Secret.java`, and `SuperSecret.java`.

   *Aside* Compilation Shortcut:
   From the `cs1302-default-methods` directory, you can attempt to compile `BasicSecret.java`,
   `Secret.java` and `SuperSecret.java` simultaneously using the following command:

   ```
   javac -d bin -cp bin src/cs1302/interfaces/impl/*.java
   ```

   Note the use of the wildcard character `*`. When you execute this command, the bash shell expands the
   `*` into all filenames ending in ".java" in the `impl` directory. This provides three files as
   command-line arguments to `javac`. An equivalent (but longer) way to write the above command would be:

   ```
   javac -d bin -cp bin src/cs1302/interfaces/impl/SuperSecret.java src/cs1302/interfaces/impl/Secret.java
    /src/cs1302/interfaces/impl/BasicSecret.java
   ```

1. Compilation of the three `Encryptable` classes results in the following compile-time errors:

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
   `isEncrypted` in Encryptable. Since these classes don't implement all of the methods in `Encryptable`,
   they won't compile.
   
### Default Methods

Given that we only have three classes that implement `Encryptable` we _could_ go through them one at a 
time add the `getState` method to each. However, this is not always feasible. *Default Methods* allow 
methods to be defined within the interface. Since the method is defined at the interface level, the
implementation is often quite general. 

1. As an example, modify the `Encryptable` interface by replacing the current `getState` abstract 
   method with the following default method alternative:


   ```java
   default State getState() {
      return State.UNKNOWN;
   }
   ```

1. Compile `BasicSecret.java`, `Secret.java`, and `SuperSecret.java`.

   ```
   javac -d bin -cp bin src/cs1302/interfaces/impl/*.java
   ```

   This time it compiled!  

You probably already have noticed the benefit of default methods. Since we provided a default 
implementation for the `getState` method in the interface, we don't have to go through each 
implementing class and add the method. 

So, what's the downside? Well, all of our classes return "UNKNOWN" as their state. This isn't the
desired behavior. Instead, we would like it if all classes returned their actual state. In order
to make this work, we could implement the `getState` method in any implementing class that would
benefit from this new behavior.
 
### Overriding a Default Method

1. 

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
