# JDB Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

The **Java Debugger** (JDB) is a simple command-line debugger for Java classes. 
The `jdb` command and its options start JDB. Just like with other debuggers,
JDB supports setting breakpoints, stepping, and value inspection.

## Getting Started

1. Login to your Nike account.

1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-jdb`:

   ```
   $ curl -s -L UPDATE-THIS-LINK | bash
   ```
   
1. Change into the `cs1302-jdb` directory that was just created and look around. There should be
   multiple Java files contained within the directory structure. To see a listing of all of the 
   files under the `src` subdirectory, use the `find` command as follows:
   
   ```
   $ find src
   ```
   
1. **In order to use the debugger, all code must be compiled with the `-g` command-line option.** 
   Compile each of the source code files under `src` to `bin`, specifying `-g` in addition and `-d bin`
   (and `-cp bin` when needed).
   
   ```
   $ javac -g -d bin src/cs1302/jdb/Person.java
   $ javac -g -d bin -cp bin src/cs1302/jdb/Driver.java
   ```
   
1. **Run the code.**

   ```
   $ java -cp bin cs1302.jdb.Driver
   ```
   ```
   mean = 3.333333
   Person(name = Brad) and Person(name = Mike) present this tutorial.
   ```
   
   The mean should be the average of `{ 5, 5, 5 }`, which is `5`, but it's not!
   **It looks like there is a bug!**
   Let's use the debugger to figure out what's wrong.

## Running JDB

1. To run JDB on code compiled with `-g`, you need to know three pieces of information:

   1. `sourcepath`, i.e., the location of the default package for source code (in our case, `src`);
   1. `classpath`, i.e., the location of the default package for compiled code (in our case, `bin`); and
   1. `classname`, i.e., the fully qualified name of the class containing your `main` method (in our case, `cs1302.jdb.Driver`).
   
   With this information, you can run JDB, for example:
   
   ```
   $ jdb -sourcepath src -classpath bin cs1302.jdb.Driver
   ```
   
   **If your program takes command-line arguments, then you can write them after the `classname` as usual.**

   If JDB starts correctly, then you should see the following:
   
   ```
   Initializing jdb ...
   >
   ```
   
   Just as with the Bash shell examples where `$` denotes the shell prompt, here a `>`
   or `]` will be used to denote the JDB prompt.

1. **Set a breakpoint at the beginning of the `main` method.** To do this, we don't need
   to explicitly know the line number. We just need to know the class that contains
   the `main` method. Type the following:
   
   ```
   > stop in cs1302.jdb.Driver.main
   ```
   
   You should see the following output:
   
   ```
   Deferring breakpoint cs1302.jdb.Driver.main.
   It will be set after the class is loaded.
   ```
   
1. **Run the program in JDB.** Now that a breakpoint is set, run the program by
   typing the following:
   
   ```
   > run
   ```
   
   You should see the following output:
   
   ```
   run cs1302.jdb.Driver
   Set uncaught java.lang.Throwable
   Set deferred uncaught java.lang.Throwable
   >
   VM Started: Set deferred breakpoint cs1302.jdb.Driver.main
   
   Breakpoint hit: "thread=main", cs1302.jdb.Driver.main(), line=37 bci=0
   37            double[] myNums = new double[] { 5.0, 5.0, 5.0 };
   ```
   
   As you can see, JDB ran the program until it hit the breakpoint in
   `main`, which in this example was on line 37. When JDB breaks on a line,
   it does so before executing the line. Therefore, in this example,
   the next line of code to be executed is line 37.
   
1. **List the lines near the current line.** JDB already showed us what's
   on line 37. Type the following to see the lines around line 37:
   
   ```
   ] list
   ```
   
   You should see the following:
   
   ```
   33        } // computeMean
   34
   35        public static void main(String[] args) {
   36
   37 =>         double[] myNums = new double[] { 5.0, 5.0, 5.0 };
   38            double mean = computeMean(myNums);
   39            System.out.printf("mean = %f\n", mean);
   40
   41            Person brad = new Person("Brad");
   42            Person mike = new Person("Mike");
   ```

   Notice that JDB pointed to line 37 in the output, which is the next
   line of code that should execute.
   
1. **Execute the next line of code.** 

   ```
   ] next
   ```
   
   You should see the following:
   
   ```
   >
   Step completed: "thread=main", cs1302.jdb.Driver.main(), line=38 bci=22
   38            double mean = computeMean(myNums);
   ```
   
   JDB successfully executed 37. After that, the next line to be executed
   is line 38, as seen in the output.
   
1. **Inspect a variable.** On line 37, an array variable was declared and
   initialized to refer to an array object containing three elements.
   Let's inspect the variable two different ways. 
   
   1. To print the value stored in the variable, type the following:
   
      ```
      ] print myNums
      ```
   
      You should see the following:
   
      ```
       myNums = instance of double[3] (id=399)
      ```
   
      As the output suggests, the variable contains a reference to an array object
      of length 3. While this is useful, we probably want to see what's inside
      the array. Try `print myNums[0]` to print the value stored in the first
      array position.
      
   1. To dump, i.e., display the contents of an entire object referred to by
      a reference variable, you can use the `dump` command. Type the following:
   
      ```
      ] dump myNums
      ```
   
      You should see the following:
      
      ```
       myNums = {
      5.0, 5.0, 5.0
      }
      ```
      
      This works for all objects, not just arrays.
      
1. **Step into the method being called on the next line of code.**
   
## JDB Quick Reference

```
> stop at <class>:<line_number>
```

```
> stop in <class>.<method_name>
```

## Stepping

## Inspecting

## Concluding Remarks

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
