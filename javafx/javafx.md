**NOTE: This tutorial is in-progress. Do not regard it as ready until this message is removed.**

# JavaFX Tutorial

JavaFX is a library for creating and delivering applications with graphical user interfaces (GUIs)
in Java. In this couse, we will use JavaFX 8 as it comes with Java 8. The API documentation for
the many packages included ith JavaFX can be found 
[here](https://docs.oracle.com/javase/8/javafx/api/toc.htm).

## Getting Started

1. First, you need to ensure that your terminal emulator supports X-forwarding. This allows
   the GUI parts of your application to be forwarded to your local computer in addition to
   the terminal output. To check this, work through the rest of this section of the
   tutorial. First, login to Nike using the `ssh` command along with the `-XY` option. 
   As always, be sure to replace `username` with your Nike username.
   
   ```
   $ ssh -XY username@nike.cs.uga.edu
   ```
   
   If you encounter problems in this step, then please ensure that you have followed the
   instructions provided at the beginning of the semester for
   [MacOS users](https://github.com/cs1302uga/cs1302-exercises/blob/master/misc/MacOS.md) or
   [Windows 10 users](https://github.com/cs1302uga/cs1302-exercises/blob/master/misc/Windows10.md).
   
1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-javafx`:

   ```
   $ curl -s -L https://git.io/fhAR8 | bash
   ```
   
1. Change into the `cs1302-javafx` directory that was just created and look around. There should be
   multiple Java files contained within the directory structure. To see a listing of all of the 
   files under the `src` subdirectory, use the `find` command as follows:
   
   ```
   $ find src
   ```
   
1. Compile the source code, specifying `bin` as the default package for compiled code. You may
   need to create the `bin` directory.
   
1. Attempt to run the `cs1302.gui.ExampleDriver` class in the usual manner. If successful, then
   two things should happen:
   
   1. A small, but blank, GUI app will appear on your screen. This may appear in another
      desktop or minimized if your computer utilizes multiple windows.
	  
   1. The following error message will appear:
      ```
      libGL error: No matching fbConfigs or visuals found
      libGL error: failed to load driver: swrast
      Prism-ES2 Error : GL_VERSION (major.minor) = 1.4
      ```
	  
1. The error message that displays is related to the JavaFX graphics renderer. By default, it
   attempts to perform hardware-accelerated rendering. However, we need to enable to software-based
   rendering in order for it to work nicely with X-forwarding. Close out of the small GUI app. Now, rerun 
   the driver but add `-Dprism.order=sw` in addition to the usualy options when executing the related `java`
   command.
   
1. If the small GUI app appears again with no render errors, then you are okay to proceed!

## Walkthrough 

1. In JavaFX, applications (or apps) are created by creating a class that extends the
   [`javafx.application.Application`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html)
   class and overriding the `start` method. In the starter code, inspect the class declaration inside of `ExampleApp.java`.
   We will explain the inside of the `start` method shortly.
   
1. Assuming you have a JavaFX app class (e.g., `ExampleApp`), the perferred way of launching an instance of the
   app is by using the static `Application.launch` inside of some driver class. In the starter code, inspect the
   `main` method inside of `ExampleDriver.java`.
   
   * The `try-catch` statement is included to ensure that if any exceptions propogate up to `main` method, then
     the user is presented with a nicer message than the usual stack trace. We also do this because one such
     exception relates to a timeout issue with the X-forwaring -- in this case, we want to inform the user that
     they can logout, then log back in to resolve the problem.
     
1. The [`launch`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html#launch-java.lang.Class-java.lang.String...-)
   launches the app by initiating the JavaFX application life-cycle, a sequence of steps that occur over the life
   of the launched application. The JavaFX runtime does the following, in order, whenever an application is launched:

   1. Constructs an instance of the specified `Application` subclass;
   1. Calls the app's [`init`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html#init--)
      method, which the programmer may have overidden;
   1. Creates a `Stage` object and calls the app's `start` method with a reference to that `Stage` object;
   1. Waits for the app to finish, which happens when either of the following occur:
      
      * the app calls [`Platform.exit`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html#exit--)
      * the last window has been closed and the `implicitExit` attribute on `Platform` is true -- this is the default.
    
   1. Calls the app's [`stop`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html#stop--) 
      method, which the programmer may have overidden;

## References


