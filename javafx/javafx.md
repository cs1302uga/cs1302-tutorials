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
   
1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-javafx`:

   ```
   $ curl -s -L https://git.io/fhAR8 | bash
   ```
   
1. Change into the `cs1302-javadoc` directory that was just created and look around. There should be
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
   render to work nicely with X-forwarding. Close out of the small GUI app. Now, rerun the driver but
   add `-Dprism.order=sw` in addition to the usualy options when executing the related `java`
   command.
   
1. If the small GUI app appears again with no render errors, then you are okay to proceed!

## References


