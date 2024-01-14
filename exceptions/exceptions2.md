
# Exceptions Part 2

![Approved for: Spring 2024](https://img.shields.io/badge/Approved%20for-Spring%202024-blue)

## Prerequisites

Before engaging with this tutorial, readers should first complete 
[Exceptions Part 1](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/exceptions/exceptions1.md).
To get the most out of this tutorial, you should follow along and take notes.

## Course-Specific Learning Outcomes

* **LO2.b:** Define, throw, and propagate exceptions appropriately in a software solution.

## Table of Contents

* [Checked vs. Unchecked Exceptions](#checked-vs-unchecked-exceptions)
* [Identifying Checked vs. Unchecked Exceptions](#identifying-checked-vs-unchecked-exceptions)
* [Multiple Catch Blocks](#multiple-catch-blocks)
* [Explicitly Throwing Exceptions & Exception Propagation](#explicitly-throwing-exceptions--exception-propagation)

## Checked vs. Unchecked Exceptions

In Java, exceptions are either _checked_ or _unchecked_.
Checked exceptions must be explicitly caught or propagated by the programmer,
whereas unchecked exceptions may or may not be handled by the programmer.
Let's look at an example.

1. On Odin, create the class `exceptions.Unchecked` containing the following code:

   ```java
   package exceptions;

   public class Unchecked {
       public static void main(String [] args) {
          int result = 4/0;
          System.out.println(result);
       } // main
   } // Test
   ```

1. Do you see where the exception object will be created and thrown? Go ahead and 
   compile and run `exceptions.Unchecked`. You should get an error message similar to the following
   when you run the program:

   ```
   Exception in thread "main" java.lang.ArithmeticException: / by zero
      at exceptions.Unchecked.main(Unchecked.java:5)
   ```
   An [`ArithmeticException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html)
   (specifically, a divide by zero) caused our program to crash. Since `ArithmeticException` is an **unchecked exception**, the
   Java compiler did not force us (the programmer) to catch or propagate this exception.  It is completely up to the programmer to decide
   whether or not to handle exceptions of this type. Since we didn't handle it, our program crashed. You've probably experienced other, 
   unchecked exceptions such as:
   [`StringIndexOutOfBoundsException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/StringIndexOutOfBoundsException.html),
   [`NullPointerException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/NullPointerException.html),
   [`NumberFormatException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/NumberFormatException.html), etc.

1. On Odin, create the class `exceptions.Checked` containing the following code:

   ```java
   package exceptions;

   import java.util.Scanner;
   import java.io.File;

   public class Checked {
       public static void main (String[] args) {
           File notesFile = new File("notes.txt");
           Scanner input = new Scanner(notesFile);
           System.out.println(input.nextLine());
       } // main
   } // Checked
   ```

   In this program, we are reading the first line of the file `notes.txt`. The first line of the `main` method creates a `File` object
   which is referred to by `notesFile`.  Then, it passes this object reference to the `Scanner` constructor.  As you may have guessed, the
   `input` object will read its input from the file (not the keyboard).  We will create the `notes.txt` file in a later step.

1. Compile `Checked.java`.  You should get an error similar to the following:

   ```
   src/exceptions/Checked.java:9: error: unreported exception FileNotFoundException; must be caught or declared to be thrown
      Scanner input = new Scanner(notesFile);
                      ^
   ```
   This error indicates that the `Scanner` constructor throws a `FileNotFoundException`.  `FileNotFoundException` is a
   **checked exception**.  When a method or constructor call throws a checked exception, the programmer must either:
      * surround the relevant call with a try-catch; or
      * add a throws clause to the enclosing method (i.e., the method containing the call) to propagate this exception if it occurs.

   In `Checked.java`, the enclosing method is `main`.  We never want to add a `throws` clause to the `main` method as there is no code in
   our program above the `main` method to handle the exception. To fix `Checked.java`, we will use the first approach.

1. Now, let's modify `Checked.java` to include an appropriate try-catch:

   ```java
   package exceptions;

   import java.util.Scanner;
   import java.io.File;
   import java.io.FileNotFoundException;

   public class Checked {
       public static void main (String[] args) {
           File notesFile = new File("notes.txt");
           Scanner input = null;
           try {
               input = new Scanner(notesFile);
           } catch(FileNotFoundException e) {
               System.out.println(e.getMessage());
           }
           System.out.println(input.nextLine());
       } // main
   } // Checked
   ```

1. Create a `notes.txt` file in the directory where you will execute the program and add a single line of text to the file.
   **Important Note:** The `notes.txt` file should be in the directory where you run the `java` command to run the program. If you
   run the program from the parent directory of `bin` and `src`, that's where the file should be.

1. Execute `exceptions.Checked`.  It should print the first line of `notes.txt`.

## Identifying Checked vs. Unchecked Exceptions

For now, we will use a simple method for checking if an exception is checked or unchecked.

1. Visit the Java API page for [`ArithmeticException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html).

1. At the top of the page, there is a hierarchy of Java classes which looks like this:

   <img src="../img/ArithmeticException.png" width="50%" alt="ArithmeticException Unchecked Example">

   You can tell that `ArithmeticException` is an unchecked exception because `java.lang.RuntimeException` is listed in the hierarchy. If
   you cannot find `java.lang.RuntimeException` in the hierarchy for a given exception, then that tells you it is a checked exception.

1. For an example of a checked exception, see
[`FileNotFoundException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/FileNotFoundException.html)

## Multiple Catch Blocks

Here is a video that demonstrates how to handle multiple exceptions at the same
time using multiple catch blocks:

https://www.youtube.com/watch?v=j-GNWvLNLjs

<a href="https://www.youtube.com/watch?v=j-GNWvLNLjs">
<img src="https://img.youtube.com/vi/j-GNWvLNLjs/0.jpg?20190726" alt="IMAGE ALT TEXT">
</a>

## Explicitly Throwing Exceptions & Exception Propagation

Now that you have seen how to handle exceptions in code written by others
that can throw exceptions, it's important to understand how and why you can
throw exceptions yourself. In Java, the `throw` keyword is used to explicitly
throw an exception. Here is an example where we create an
[`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html)
and explicitly throw it:

```java
throw new IllegalArgumentException("nums array cannot be empty");
```

Since this line of code is known to throw an exception, we would
usually want to handle the exception by placing the line in a try block.
That may not always be ideal. Instead of handling the exception immediately,
let's make it someone else's responsibility by using the `throws` keyword
in the signature of the method containing the line:

```java
public int computeAverage(double[] nums) throws IllegalArgumentException {
    if (nums.length == 0) {
        throw new IllegalArgumentException("nums array cannot be empty");
    } // if
    double sum = 0;
    for (double num : nums) {
        sum += num;
    } // for
    return sum / nums.length;
} // computeAverage
```

Using the `throws` keyword, we told Java that the `IllegalArgumentException`
will not be handled directly in this method. Instead, it will be _propagated_
up to the calling method, i.e., the method (or methods), somewhere else, that is
calling `computeAverage`. In that other method, the programmer
can either handle the exception (using a try-catch) or choose to propagate it again
by repeating the `throws` in the calling method's signature.

In Java, checked exceptions must either be handled directly using a try-catch
or propagated up using `throws`. Note that while it is possible to place a `throws`
in the signature of a program's `main` method, doing so is _strongly_ discouraged
as exceptions propagated past `main` will always cause the program to crash.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
