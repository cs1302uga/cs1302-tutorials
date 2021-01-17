# Command-Line Arguments

![Approved for: Spring 2021](https://img.shields.io/badge/Approved%20for-Spring%202021-success)

Many of the programs that you've interacted with on Unix supported extra options
and/or required extra information when you entered their commands at the terminal
shell. For example, consider the following `javac` command:

```
$ javac -d bin -cp bin src/cs1302/example/Hello.java
```

The text that is entered immediately following `javac`, in the exampe above, is
passed into the program as __command-line arguments__. This allows the `java` program
to proceed with execution without prompting the user for input. We can have our Java
programs take in command-line arguments as well. Let's try it!

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
   
1. Take a few minutes to carefully read through the code above. Try and understand what it's doing.
   Note: Up until this point, you've always typed `String[] args` as a parameter to `main` but you've
   never used it. That parameter is a reference to an array of command-line arguments.

1. Compile the `ArgTester` class, specifying `bin` as the _default package for compiled code_.

1. Run `cs1302.cla.ArgTester` as usual using the `java` command. Here is what the command looks
   like with the expected program output, assuming you are running it from the `cs1302-cla`
   directory: 
   
   ```
   $ java -cp bin cs1302.cla.ArgTester
   arguments:
   ```
   
   As you can see, no iterations of the for-loop executed. This is expected as the `args` array would
   be empty in this scenario (there are no command-line arguments provided).  
   
1. Now try the following command:

   ```
   $ java -cp bin cs1302.cla.ArgTester one two three
   ```
   
   What happened? It looks like the for-loop iterated. The array referred to by `args` is not
   empty. That's right, we've actually used the `args` array for something! Here's the
   expected output:
   
   ```
   arguments:
   arg0 = one
   arg1 = two
   arg2 = three
   ```
   
   In Java, the `args` array of a standard `main` method is used to capture command-line
   arguments and make them available to the program.
   
1. Now that we see how to access the command-line arguments in our code, let's see how
   different command-line arguments are parsed. Try the following commands:
   
   ```
   $ java -cp bin cs1302.cla.ArgTester "one two" three
   ```
   
   ```
   $ java -cp bin cs1302.cla.ArgTester --help "some topic"
   ```
   
   ```
   $ java -cp bin cs1302.cla.ArgTester --string "my \"awesome\" day"
   ```
   
2. That's it! The rest is purely in the realm of code. We've shown you how command-line
   arguments are passed into a Java program (i.e., via the `args` array). Now, experiment
   by adding some command-line options of your own.
   
   
<hr/>

**Feedback?** 
Please help us make this better!
If you have any feedback or suggestions for this reading or tutorial, then use 
the link below to reach the feedback form.

[![Submit Feedback](https://img.shields.io/badge/-Submit&nbsp;Feedback-red.svg?style=for-the-badge)](https://docs.google.com/forms/d/e/1FAIpQLSfBgZM_-G-9nKmX7F83k0Tgp1OlqBnrkt6vsxlIqLypc_keUQ/viewform?usp=pp_url&entry.1081181680=cs1302-cla&entry.1901270436=https://github.com/cs1302uga/cs1302-tutorials/blob/master/cla/cla.md)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
