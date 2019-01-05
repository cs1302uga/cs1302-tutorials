# Java Packages Tutorial

## Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working with a command-line text editor (e.g. emacs, vi, etc.).

## Java Packages

In Java, a **package** is a grouping of related types providing access protection and name space management. 
Note that types refers to classes, interfaces, enumerations, and annotation types [[1]](https://docs.oracle.com/javase/tutorial/java/package/packages.html).
The two primary benefits of packages are:

* **Name Space Management:**  Packages allow you to give a common name to a group of related types.
  For example, `java.util.Scanner` and `java.util.Random` are two utility classes provided in the `java.util` package.
  You and other programmers can easily determine that these types are related.
  
* **Access Protection:** Visibility in Java is not limited to `public` and `private`. 
  Packages and additional visibility modifiers enable programmers to declare things as visible only within a package.

In this tutorial, you will create multiple classes, group them into a package, and compile and run them.
The expectation is that you will follow along with this tutorial in a terminal emulator on Nike or some Unix machine.
You should ask questions on Piazza if you are unable to proceed or if some aspect of the tutorial is particularly confusing. 

## Setting up for a Package

1. Create a directory for this tutorial called `cs1302-packages` and change into it:

   ```
   $ mkdir cs1302-packages
   $ cd cs1302-packages
   ```
   
2. Setup the following subdirectory structure for the `cs1302.hello` package under a new subdirectory called `src`:

   ```
   cs1302-packages
    |--- bin
    |--- src
          |--- cs1302
                |--- hello
   ```
   
   Here is a breakdown of the different subdirectories and the roles that they play:
   * `bin` is the default (no-name) package directory for our compiled code;
   * `src` is the default (no-name) package directory for our source code;
   * `cs1302` is the `cs1302` package directory; and
   * `hello` is the `cs1302.hello` package directory.

## Default Package 

Here, the `src` directory is known as the **default package** for source code.
It is the base directory for packages that you will create. 
It is also an unnamed package where package-less `.java` files can be placed for convenience when developing small or 
temporary applications or when just beginning development 
[[2]](https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html). 
   
Let's try it! 
   
1. Using Emacs, create a basic "Hello, World!" program in a driver class
   called `HelloWorld` in the default package directory (`src`).
   For example, the contents of the `HelloWorld.java` file might be:
      
   ```java
   public class HelloWorld {
       public static void main(String[] args) {
           System.out.println("Hello, World!");
       } // main
   } // HelloWorld
   ```
    
2. Change directly into the default package directory and compile the program using `javac`:
   
   ```
   $ javac HelloWorld.java
   ```
      
3. List the contents of the directory to verify that `HelloWorld.class` was created.
   
4. Run the program using `java`:
   
   ```
   $ java HelloWorld
   ```
      
   Note that the `java` command takes the name of the class itself and not the name of the
   `.class` file. 
      
5. When executing the `java` command, Java assumes that the current working directory is the
   location of the default package. If that is not the case, then you must specify it using a
   command line option called `-cp` for _class path_. As its name suggests, the class path is
   the path to the default package for classes. 
      
   Change directly to the `cs1302-packages` directory and try to execute the following:
      
   ```
   $ java HelloWorld
   ```
      
   You should get the following error: 
      
   ```
   Error: Could not find or load main class HelloWorld
   Caused by: java.lang.ClassNotFoundException: HelloWorld
   ```
      
   The error message clearly states that the `HelloWorld` class containing the `main` method
   could not be found or loaded. 
  
   Now try running the program again, specifying the location of the default package where our compiled code resides.  
   Since we compiled the class into the 'src' directory, 'src' is the default package for our compiled code (for now).  
   Execute the following command to specify the class path:
      
   ```
   $ java -cp src HelloWorld
   ```
      
   This time it worked! 
      
   **PROTIP:** You can execute a Java program anywhere on the system as long as you know
   the fully qualified name of the class containing the `main` method and the location of that 
   compiled class's associated default package, assuming proper file permissions. 
      
6. You may have noticed in the previous step that the `.java` file and `.class` file
   for the `HelloWorld` class are in the same directory. Let's keep things clean and
   separate our source code from the compiled code (`.class` files). 
      
   First, delete the `HelloWorld.class` file:
      
   ```
   $ rm src/HelloWorld.class
   ```
      
   Now, compile the program, using `javac` with the `-d` option to specify a destination:
      
   ```
   $ javac -d bin src/HelloWorld.java
   ```
      
   Now, if you list the contents of the `bin` directory, you will see that it contains
   `HelloWorld.class`.
      
   Try running the program again, specifying the new class path using `-cp`:
      
   ```
   $ java -cp bin HelloWorld
   ```
      
   **PROTIP:** Remember, the default package should really only be used direcly
   for convenience when developing small or temporary applications or when just beginning development 
   [[2]](https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html).
   While types in the default package can access types in other packages, the reverse is not true.
   That is, types in named packages cannot access types in the defualt package.
      
7. Let's clean up! Delete the `HelloWorld.class` file that you created.

## Named Package

Now let's create a named package. To place a class (or interface) in named package, you must do two things:
1. Place the `.java` file in the appropriate package directory; and
2. Include a `package` statement at the top of the `.java` file.
   
Let's try it by placing the `HelloWorld` class into the `cs1302.hello` package!
   
1. Change directly into `cs1302-packages` directory.
   
2. Move the `HelloWorld.java` file into the `cs1302.hello` package directory.
   
   ```
   $ mv src/HelloWorld.java src/cs1302/hello/
   ```
      
   This satisfies the first requirement for placing a class in a named package.
      
3. Using Emacs, edit the `HelloWorld.java` file and add the following package
   statement at the top:
      
   ```java
   package cs1302.hello;
   ```
      
   This satisfies the second requirement for placing a class in a named package.
      
   In Java, a package statement, if included, must be the first line of code in
   the file (i.e., excluding comments and white space).
      
4. Compile the program:
   
   ```
   $ javac -d bin src/cs1302/hello/HelloWorld.java
   ```
      
   Note that the `HelloWorld.class` file was created under `bin/cs1302/hello`. The compiler automatically created
   the necessary package directories for our compiled code under `bin`.
      
5. Try to run the program using `javac` specify the classpath using `-cp` and include the
   fully qualified name of the class containing the `main` method:
   
   ```
   $ java -cp bin cs1302.hello.HelloWorld
   ```
      
   In this example, `cs1302.hello.HelloWorld` is known as the **fully qualified name** of the
   `HelloWorld` class in the `cs1302.hello` package. You have seen fully qualified names before--they
   are often used with `import` statements to make classes in other packages available using their
   simple class name. In this case, the `HelloWorld` part of `cs1302.hello.HelloWorld` is known as 
   the **name** or **simple name** of the class.
   
   **PROTIP:** Although packages correspond to directories, a fully qualified name uses `.` (dot) 
   for the name separator and not a slash.
 
5. Congratulations, you've successfully completed this tutorial!

## References

* [[1] Creating and Using Packages](https://docs.oracle.com/javase/tutorial/java/package/packages.html)
* [[2] Packages](https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
