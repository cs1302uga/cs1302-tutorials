# Java Packages Tutorial

![Approved for: Fall 2019](https://img.shields.io/badge/Approved%20for-Fall%202019-brightgreen)

## Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working with a command-line text editor (e.g. emacs, vi, etc.). To get the most out of this tutorial, you should follow along and take notes.

## Course-Specific Learning Outcomes

* **LO1.a:** Navigate and modify files, directories, and permissions in a multi-user Unix-like environment.
* **LO1.c:** Create and modify textfiles and source code using a powerful terminal-based text editor such as Emacs or Vi.
* **LO1.d:** Use shell commands to compile new and existing software solutions that are organized into multi-level packages
  and have external dependencies.
  
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
 
5. Congratulations, you've successfully completed this tutorial! Please read the sections below for
   some important information regarding class path and `import` statements. 

## Code Dependencies

When Java code uses other Java code, that creates a dependency. Most of the programs that you've
written have used code provided by Oracle under the various `java` subpackages. When you compile,
those dependencies are automatically included on the class path. However, when your code depends 
on code that's not included with Java (e.g., code that you or someone else has written), you need
to let `javac` know where the _compiled_ version of that depedency is.

1. Let's extend the code we just finished. Create a `cs1302.util.HelloUtility` class under `src`.
   **Remember,** this implies a specific directory structure and package statement requirement 
   with respect to `HelloUtility.java`.
   
1. In the source code for `cs1302.util.HelloUtility` class, add the following method:

   ```java
   public static void excitingHello() {
       System.out.println("HELLO!!!!");
   } // excitingHello
   ```
   
1. Save, then compile the `.java` file for the `cs1302.util.HelloUtility` class as usual, 
   under `bin`.

1. Modify the source code for your `cs1302.hello.HelloWorld` class to call the static method in
   `cs1302.util.HelloUtility`. To do this, you may:
   
   1. Add an import statement between the `package` statement and class declation:
   
      ```java
      import cs1302.util.HelloUtility;
      ```
   
   1. Call the method in `main` using the simple class name:
   
      ```java
      HelloUtility.excitingHello();
      ```
      
   Completing these two steps create dependency. Now, the `cs1302.hello.HelloWorld` class
   depends on the `cs1302.util.HelloUtility` class.
      
1. If you try to compile the source code for your `cs1302.hello.HelloWorld` class exactly as you 
   did before, then it will not work. Try it! The error message can be a little confusing. Assuming 
   you didn't make any spelling mistakes, the error output is just `javac` saying that it cannot 
   find something. Since we know it actually exists, we can just tell `javac` where to find it 
   using `-cp`. 
   
1. Remember that when your code depends on other code that you have written, you need to let 
   `javac` know where the _compiled_ version of that depedency is. Since you compiled under `bin`,
   that's where you should tell `javac` to look. Try to compile it again, but this time, be sure
   to include the `-cp bin` option in addition to `-d bin` option.
   
1. It works! Run it as expected.

## Further Important Notes

### Setting the Class Path

Both `javac` and `java` allow you specify the class path using the `-cp` or `-classpath` command-line
option. The usual syntax is as follows:

```
-cp some/path
```

If more than one default package is needed, then a colon `:` can be used to separate each path in a
list of multiple paths:

```
-cp path1:path2
```

Each path can be a path to a directory or a `.jar` file (usually used for third party libraries).

**VERY IMPORTANT NOTE:** The class path should always point to a default package for _compiled_ code. 
If you are compiling a `.java` file that depends on an already compiled class, then you will need to 
specifiy the class path to the corresponding default package for that dependency when 
invoking `javac`.

### Import Statements

In Java, you do not have to import classes that are in the same package. However, it's interesting to
note that `import` statements are actually never required in Java. We just use them for convenience. 
Assuming the corresponding default package for class's package is on the class path when compiling 
and/or running, you can always refer to a class by its fully qualified name. Consider two uses of
[`java.util.Random`](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html) below:

```java
// assuming the class was imported
Random rng = new Random();
```

```java
// assuming the class was NOT imported
java.util.Random rng = new java.util.Random();
```

As you can imagine, the latter (without an import statement) might get annoying and repetetive.
Therefore, we usually prefer to use an `import` statement for the convenience it provides.
Why would anyone prefer to use the fully qualified name instead of the simple name for a class?
It enables you to use two classes from different packages with the same simple name at the
same time!

### The `java.lang` Package

Java automatically performs a wildcard import of the `java.lang` package (i.e., `import java.lang.*;`) in 
every Java file, without requiring the programmer to explicitly write it. That is why you can use classes
such as `java.lang.String` and `java.lang.System` by their simple names without importing!

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
