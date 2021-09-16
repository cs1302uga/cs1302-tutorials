# Command-Line Arguments

![Approved for: Fall 2021](https://img.shields.io/badge/Approved%20for-Fall%202021-blue)

When working in a Unix-like environment, programs are launched when the user
enters the program's name into the shell (i.e., they type it in, then press
return). Many programs allow or even require the user to specify additional
pieces of information, called **command-line arguments**, after the program's
name in order to adjust the program's settings and supply values that the
program needs to execute. For example, consider using `ls` to list all of the
entries in `~/public_html` using the long listing format (`-l`), including
files that being with a dor (`-a`):

```
$ ls -l -a ~/public_html
```

When the program is launched, the command-line arguments are passed into
to the program through its `main` method. Like many Unix programs, `ls` is
written in C, but support for command-line arguments extends to other
languges as well, including Java. Let's try it!

1. Create a directory for this tutorial called `cs1302-cla`, then change into it.

1. Create a `src` and `bin` directory, then create the `.java` file for a class
   called `ArgTester` in a package called `cs1302.cla` such that the `src` directory
   is the _default package for source code_.

   ```
   cs1302-cla
   ├── bin
   └── src
       └── cs1302
           └── cla
               └── ArgTester.java
   ```

1. In `ArgTester.java`, add the appropriate package statement, then
   add the declaration for the `ArgTester` class.

1. In your `ArgTester` class, add the following `main` method:

   ```java
   public static void main(String [] args) {
       System.out.println("arguments:");
       for (int i = 0; i < args.length; i++) {
           String arg = args[i];
           System.out.printf("args[%d] = %s\n", i, arg);
       } // for
   } // main
   ```

1. Take a few minutes to carefully read through the code above. Try and understand what it's doing.
   Note: Up until this point, you've always typed `String[] args` as a parameter to `main` but you've
   never used it. That parameter is a reference to an array of command-line arguments.

1. Compile the `ArgTester` class, specifying `bin` as the destination directory.

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
   args[0] = one
   args[1] = two
   args[2] = three
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
