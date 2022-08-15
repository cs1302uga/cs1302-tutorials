# Java Packages Tutorial - Part 1

![Approved for: Fall 2022](https://img.shields.io/badge/Approved%20for-Fall%202022-darkgreen)

## Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands and experience working with a command-line 
text editor (e.g. emacs, vi, etc.). To get the most out of this tutorial, you should **follow along, take notes, execute any given commands,
run any code examples, repeat as necessary**. Simply reading (or skimming) is not sufficient for learning this material.

## Course-Specific Learning Outcomes

* **LO1.a:** Navigate and modify files, directories, and permissions in a multi-user Unix-like environment.
* **LO1.c:** Create and modify textfiles and source code using a powerful terminal-based text editor such as Emacs or Vi.
* **LO1.d:** Use shell commands to compile new and existing software solutions that are organized into multi-level packages
  and have external dependencies.
  
## Java Packages

In Java, a **package** is a grouping of related types providing access protection and name space management. 
Note that types refers to classes, interfaces, enumerations, and annotation types [[1]](https://docs.oracle.com/javase/tutorial/java/package/packages.html). In other words, a package allows you to
organize your source code. Proper code organizatioin becomes increasingly important as the size of the project
increases.

The two primary benefits of packages are:

* **Name Space Management:**  Packages allow you to give a common name to a group of related types.
  For example, `java.util.Scanner` and `java.util.Random` are two utility classes provided in the `java.util` package.
  You and other programmers can easily determine that these types are related. They are both utilities that you have
  probably used in your code. It is important to note that you could have two types with the same simple name that 
  are located in different packages. For example, you could have a `java.util.Random` class and a `edu.cs.uga.Random`
  class. Both classes have the same name (`Random`) but they can be differentiated by their package names.
  
* **Access Protection:** Visibility in Java is not limited to `public` and `private`. 
  Packages and additional visibility modifiers enable programmers to declare things as visible only within a package.

In this tutorial, you will create multiple classes, group them into a package, then compile and run them.
The expectation is that you will follow along with this tutorial in a terminal emulator on Odin or some Unix machine.
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

Here, the `src` directory is the location of the **default package** (proper noun) 
for source code. The default package for source code serves as the base directory 
for named packages that you will create. You can think of it as the top-level directory
for your package directories (more soon). The default package can also contain classes 
(and other types) that are not in a named package. When developing small or temporary 
applications, it is a convenient place for package-less `.java` files 
[[2]](https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html).

Let's dive in! Before we get into creating named packages, we will start by compiling
directly into the default package as a warmup. The steps below show you how to create, 
compile, and execute a class in the default package (i.e., a class in a package-less 
`.java` file). Once you are comfortable with that, move on to the next section to learn about
named packages.
   
1. Using Emacs, create a basic "Hello, World!" program in a driver class
   called `HelloWorld` in the default package directory for source code (the `src` directory).
   For example, the contents of the `HelloWorld.java` file might be:
      
   ```java
   public class HelloWorld {
       public static void main(String[] args) {
           System.out.println("Hello, World!");
       } // main
   } // HelloWorld
   ```
    
2. Change directories into the default package directory (`src`) and compile the program using `javac`:
   
   ```
   $ javac HelloWorld.java
   ```
      
3. List the contents of the directory with `ls` to verify that `HelloWorld.class` was created.
   
4. Run the program using `java`:
   
   ```
   $ java HelloWorld
   ```
      
   Note that the `java` command takes the name of the class itself and not the name of the
   `.class` file. 
      
5. When executing the `java` command, Java assumes that the current working directory is the
   location of the default package. If that is not the case, then you must specify it using a
   command line option called `-cp` for _class path_. As its name suggests, the **class path** is
   the path to the default package for classes. 
      
   Change back into the `cs1302-packages` directory and try to execute your program with the 
   following command:
      
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
   Since we compiled the class into the `src` directory, `src` is the default package for our compiled code (for now).  
   Execute the following command to specify the class path:
      
   ```
   $ java -cp src HelloWorld
   ```
      
   This time it worked! 
      
   **PROTIP:** You can execute a Java program anywhere on the system as long as you know
   the fully qualified name of the class containing the `main` method and the location of that 
   compiled class's associated default package, assuming proper file permissions. 
      
6. You may have noticed in the previous step that the `.java` file and `.class` file
   for the `HelloWorld` class are in the same directory. Let's keep our directories clean and
   separate our source code (`.java` files) from the compiled code (`.class` files). 
      
   First, delete the `HelloWorld.class` file from the `src` directory:
      
   ```
   $ rm src/HelloWorld.class
   ```
      
   Verify that your present working directory is still `cs1302-packages` then compile your 
   program using `javac` with the `-d` option to specify a destination for the compiled code:
      
   ```
   $ javac -d bin src/HelloWorld.java
   ```
   
   When you're first starting out in a Unix environment, you might feel lost at times. Remember, 
   it's always important to know your present working directory (pwd) as that is the location where 
   the commands you enter will be run. In the command above, our pwd is `cs1302-packages`. Since the
   `HelloWorld.java` file is not in that directory, we had to specify the relative path `src/HelloWorld.java`
   to tell the compiler how to find that file relative to the pwd. We're telling the compiler "first, go into the
   `src` directory and then you'll see the file `HelloWorld.java`".
   
   Now, if you list the contents of the `bin` directory, you will see that it contains
   `HelloWorld.class`. Listing the `src` directory contents will show only `HelloWorld.java`. 
   Now our project is more organized!
      
   Try running the program again, specifying the new class path (`bin`) using `-cp`:
      
   ```
   $ java -cp bin HelloWorld
   ```

7. We will use the Unix `find` command often in this course to see all files in a directory and its 
   subdirectories. Go ahead and execute `find` from within the `cs1302-packages` directory. If you've
   followed the steps correctly up until now, you will see the following output:
   
   ```
   .
   ./bin
   ./bin/HelloWorld.class
   ./src
   ./src/cs1302
   ./src/cs1302/hello
   ./src/HelloWorld.java
   ```
   
   The output of the `find` command shows that we sucessfully separated the source code (`src`) from 
   the compiled code (`bin`). If you see any tilde (~) files, those are just backup copies of older versions 
   of your files. You can ignore those.

   **PROTIP:** Remember, source code should really only be placed directly in the default 
   package directory (`src`) for convenience when developing small or temporary applications or when 
   just beginning development [[2]](https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html).
   While types in the default package can access types in other packages, the reverse is not true.
   That is, types in named packages cannot access types in the default package.
      
8. Let's clean up! Delete the `HelloWorld.class` file that you created in the `bin` folder. Remember to
   use [tab completion](https://en.wikipedia.org/wiki/Command-line_completion) so you don't have to type long filenames!

## Named Package

Now let's create a named package. To place a class (or interface) in named package, you must do two things:

1. Place the `.java` file in the appropriate package directory; and
2. Include a `package` statement at the top of the `.java` file.
   
Let's try it by placing the `HelloWorld` class into the `cs1302.hello` package!
   
1. Change directly into `cs1302-packages` directory.
   
2. Move the `HelloWorld.java` file into the `cs1302.hello` package directory.
   You may need to create both the `cs1302` and `hello` directories before
   executing the following command.
   
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
      
4. Compile the program (don't forget tab completion - you don't want to type the whole command):
   
   ```
   $ javac -d bin src/cs1302/hello/HelloWorld.java
   ```
      
5. Execute the `find` command. Note that the `HelloWorld.class` file was created under `bin/cs1302/hello`.
   The output of `find` should look the same as the output below. Notice that The compiler automatically 
   created the necessary package directories for our compiled code under `bin`!
   
   **Note:**  If you see any tilde (~) files, those are just backup copies of older versions 
   of your files. You can ignore those. If you see any other differences between your
   output and the output below, you likely had a small error in a command or in your package statement in
   the `HelloWorld.java` file. Double check that you did everything correctly and, if you need assistance,
   post a screenshot of your `find` output to Piazza.
   
   ```
   .
   ./bin
   ./bin/cs1302
   ./bin/cs1302/hello
   ./bin/cs1302/hello/HelloWorld.class
   ./src
   ./src/cs1302
   ./src/cs1302/hello
   ./src/cs1302/hello/HelloWorld.java
   ```
 

6. Run the program using `javac` specify the classpath using `-cp` and include the
   fully qualified name (explained below) of the class containing the `main` method:
   
   ```
   $ java -cp bin cs1302.hello.HelloWorld
   ```
      
   <a id="fqn"/>In this example, `cs1302.hello.HelloWorld` is known as the **fully qualified name (or FQN)** of the
   `HelloWorld` class in the `cs1302.hello` package. You have seen fully qualified names before--they
   are often used with `import` statements (remember `java.util.Scanner`?). When you import a class, you
   can use the simple class name in that file instead of having to type the fully qualified name each time.
   In this case, `HelloWorld` is known as the **name** or **simple name** of the class.
   
   **PROTIP:** Although packages correspond to directories, a fully qualified name (FQN) uses `.` (dot) 
   for the name separator and not a slash.

7. Congratulations on compiling your code to a named package! **Don't delete your work** unless you want to work
   through the tutorial again for extra practice. The next tutorial will continue where this one left off.
   
 ## References

* [[1] Creating and Using Packages](https://docs.oracle.com/javase/tutorial/java/package/packages.html)
* [[2] Packages](https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html)

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
