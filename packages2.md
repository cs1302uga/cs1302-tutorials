# Java Packages Tutorial - Part 2

![Approved for: Fall 2022](https://img.shields.io/badge/Approved%20for-Fall%202022-darkgreen)

## Prerequisites

This tutorial assumes that the reader has completed 
[Java Packages Tutorial - Part 1](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/packages1.md). 
If you haven't completed that tutorial, please do so before moving on.

This tutorial also, assumes that the reader has a knowledge of basic Unix commands and experience working with a command-line 
text editor (e.g. emacs, vi, etc.). To get the most out of this tutorial, you should **follow along, take notes, execute any given commands,
run any code examples, repeat as necessary**. Simply reading (or skimming) is not sufficient for learning this material.

## Course-Specific Learning Outcomes

* **LO1.a:** Navigate and modify files, directories, and permissions in a multi-user Unix-like environment.
* **LO1.c:** Create and modify textfiles and source code using a powerful terminal-based text editor such as Emacs or Vi.
* **LO1.d:** Use shell commands to compile new and existing software solutions that are organized into multi-level packages
  and have external dependencies.
  
## Introduction

In the previous tutorial, [Java Packages Tutorial - Part 1](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/packages1.md),
you compiled and ran code that you placed into a named package. In that tutorial, you were working with a single Java
file. In this tutorial, we will compile and run an application that depends on code located in multiple Java files. 
When one Java file requires access to another file in order to run, it is called a **code dependency**. When there are
dependencies in our projects, we need to make sure to consider these dependencies when compiling and running our code.
This tutorial will walk you through this process.

**Note:** This tutorial picks up where the last tutorial left off. You should start by logging into Odin and changing
directories into the `cs1302-packages` directory you created while working through that tutorial.

## Code Dependencies

When Java code uses other Java code, that creates a dependency. Most of the programs that you've
written have used code provided by Oracle under the various `java` subpackages. When you compile,
those dependencies are automatically included on the class path. However, when your code depends 
on code that's not included with Java (e.g., code that you or someone else has written), you need
to let `javac` know where the _compiled_ version of that depedency is.

1. Let's extend the code we wrote in the previous tutorial. Create a `cs1302.util.HelloUtility` class under `src`.
   **Remember,** the fully qualified name (FQN) implies a specific directory structure and package statement 
   requirement with respect to `HelloUtility.java`. You will need to add the `util` directory in the proper
   place in your current directory hierarchy.
   
1. Write the code to declare the `cs1302.util.HelloUtility` class making sure to include the proper 
   class declaration and package statement at the top. Then, within the class declaration, add the 
   following method:

   ```java
   public static void excitingHello() {
       System.out.println("HELLO!!!!");
   } // excitingHello
   ```
   
1. Save, then compile the `.java` file for the `cs1302.util.HelloUtility` class as usual, 
   using `bin` as the destination for the compiled code. Once it compiles, make sure that the 
   output from `find` matches the output below:
   
   **Note:**  If you see any tilde (~) files, those are just backup copies of older versions 
   of your files. You can ignore those.
   
   ```
   .
   ./bin
   ./bin/cs1302
   ./bin/cs1302/hello
   ./bin/cs1302/hello/HelloWorld.class
   ./bin/cs1302/util
   ./bin/cs1302/util/HelloUtility.class
   ./src
   ./src/cs1302
   ./src/cs1302/hello
   ./src/cs1302/hello/HelloWorld.java
   ./src/cs1302/util
   ./src/cs1302/util/HelloUtility.java
   ```

1. Now, modify the source code for your `cs1302.hello.HelloWorld` class to call the static method in
   `cs1302.util.HelloUtility`. To do this, you may:
   
   1. Add an import statement between the `package` statement and class declation:
   
      ```java
      import cs1302.util.HelloUtility;
      ```
   
   1. Call the method in `main` using the simple class name:
   
      ```java
      HelloUtility.excitingHello();
      ```
      
   Completing these two steps create a **dependency**. Now, the `cs1302.hello.HelloWorld` class
   **depends** on the `cs1302.util.HelloUtility` class because it uses a method defined within that
   class.
      
1. If you try to compile the source code for your `cs1302.hello.HelloWorld` class exactly as you 
   did before, then it will not work because the compiler cannot find `cs1302.util.HelloUtility` 
   class on which `cs1302.hello.HelloWorld` depends. Try it! The error message should look like:
   
   ```
   src/cs1302/hello/HelloWorld.java:3: error: package cs1302.util does not exist
   import cs1302.util.HelloUtility;
                     ^
   src/cs1302/hello/HelloWorld.java:8: error: cannot find symbol
        HelloUtility.excitingHello();
        ^
   symbol:   variable HelloUtility
   location: class HelloWorld
   ```
   
   The error output is just `javac` saying that it cannot find something. In this case, it cannot
   find `cs1302.util.HelloUtility` as it is not in the same package as `cs1302.hello.HelloWorld`. 
   Since we know it actually exists, we can just tell `javac` where to find it using `-cp`. 
   
   Remember that when your code depends on other code that you have written, you need to let 
   `javac` know where the _compiled_ version of that depedency is. Since you compiled under `bin`,
   that's where you should tell `javac` to look. Try to compile it again, but this time, be sure
   to include the `-cp bin` option in addition to `-d bin` option. The program should now run as expected.
   
   The correct compilation command for `HelloWorld.java` is:
   
   ```
   javac -d bin -cp bin src/cs1302/hello/HelloWorld.java
   ```
   
   With the addition of `-cp bin`, it will be able to find the `HelloUtility` class that it is dependent on.

1. Run the code to make sure it works. Since we're running the `HelloWorld` class (it has the `main` method), 
   the command to run will not change from part 1 of this tutorial. Remember, the basic idea is to use the 
   `java` command along with the FQN of the class you want to run. You will also need `-cp` to tell Java 
   where to find the class to run.
   
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
[`java.util.Random`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Random.html) below:

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

<hr/>

**Feedback?** 
Please help us make this better!
If you have any feedback or suggestions for this reading or tutorial, then use 
the link below to reach the feedback form.

[![Submit Feedback](https://img.shields.io/badge/-Submit&nbsp;Feedback-red.svg?style=for-the-badge)](https://docs.google.com/forms/d/e/1FAIpQLSfBgZM_-G-9nKmX7F83k0Tgp1OlqBnrkt6vsxlIqLypc_keUQ/viewform?usp=pp_url&entry.1081181680=cs1302-packages&entry.1901270436=https://github.com/cs1302uga/cs1302-tutorials/blob/master/packages.md)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
