# Command-Line Arguments

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

Many of the programs that you've interacted with on Unix supported extra options
and/or required extra information when you entered their commands at the terminal
shell. For example, consider the following `javac` command:

```
$ javac -d bin -cp bin src/cs1302/example/Hello.java
```

The text that is entered immediately following `java`, in the exampe above, is
passed into the program as __command-line arguments__. This allows the program
to proceed with execution without prompting the user for input. Let's try it!

1. Create a directory for this tutorial called `cs1302-cla`, then change into it.

1. Create a `src` and `bin` directory, then create a class called `ArgTester` in 
   a package called `cs1302.cla` such that `src` is the _default package for source code_.
   
1. In your `ArgTester` class, add the following `main` method:

   ```java
   public static void main(String [] args) {
       System.out.println("arguments:")
       for (int i = 0; i < args.length; i++) {
           String arg = args[i];
           System.out.printf("arg%d = %s\n", i, arg);
       } // for
   } // main
   ```
   
1. Compile the `ArgTester` class, specifying `bin` as the _default package for compiled code_.

1. Run `cs1302.cla.ArgTester` as usual using the `java` command. Here is what the command looks
   like with the expected program output, assuming you are running it from the `cs1302-cla`
   directory: 
   
   ```
   $ java -cp bin cs1302.cla.ArgTester
   arguments:
   ```
   
   As you can see, no iterations of the for-loop executed. This is because 
   
1. As you can 

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
