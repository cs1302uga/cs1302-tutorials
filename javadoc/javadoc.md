__IN PROGRESS; THIS TUTORIAL IS STILL BEING WRITTEN__

# Javadoc

This tutorial assumes that you are logged into the Nike server. 

### Getting Started

TODO intro

1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-javadoc`:

   ```
   $ curl -s -L https://git.io/fh0nG | bash
   ```
  
1. Change into the `cs1302-javadoc` directory that was just created and look around. There should be
   multiple Java files contained within the directory structure. To see a listing of all of the 
   files under the current directory, use the `find` command as follows:
   
   ```
   $ find .
   ```
   
   Inspect each `.java` file that was listed. You will notice that some of them contain special
   multiline comments that begin with `/**` instead of `/*`. These are known as Javadoc comments,
   and they are used to provide API documentation in Java. 
   
### Generating the API Documentation Website

The Javadoc tool (`javadoc`) parses the Javadoc comments in a set of Java source files and 
produces a corresponding set of HTML pages describing classes, interfaces, constructors, 
methods, and fields. This effectively creates a website for the API documentation.

1. Make sure you are in `cs1302-javadoc`. Use the command presented below to generate the API 
   documentation website for the code contained in this tutorial and place it in the `doc`
   subdirectory. 

   ```
   $ javadoc -d doc -sourcepath src -subpackages cs1302
   ```
   
   Here is a brief description of the command-line option that were used:
   * `-d` -- specifies the destination directory where javadoc saves the generated HTML files;
   * `-sourcepath` -- specifies the search paths for finding source files (`.java`); and
   * `-subpackages` -- specifies the packages for which documentation should be generated. 

   Be sure to verify that files were generated and placed in the `doc` directory before continuing.

### Hosting the API Documentation Website

Generating the API documentation is great, but seeing it is even better. You can use the steps
provided below to host the generated website using your Nike account. Alternative approaches 
to the explicit instructions provided below are explored in the FAQ section towards the end of 
this tutorial. 

1. Ensure that you have a `public_html` directory in your home directory. If the `~/public_html` 
   directory does not exist, then you should create it. The purpose of this directory on Nike (and
   on many systems) is to support user websites, which will be illustrated in the following steps. 
   You are fully responsible for anything that you host through your Nike website.

1. Use `ln`, as described below, to create a symbolic link (shortcut) in your `public_html` 
   directory to the `doc` subdirectory containing the API documentation website that you 
   created in a previous step. The exact command is presented below--it assumes you are currently 
   in the `cs1302-javadoc` directory. 

   ```
   $ ln -s $(pwd)/doc ~/public_html/cs1302-javadoc-doc
   ```
   
   In the command above, the abolute path to our link's target (in this case, `doc`) must be provided. 
   Since our intended target is in the current directory, we know that its absolute path
   is the same as the absolute path of the current directory followed by `/` followed by
   the name of our target. We could manually figure out the desired path with the help of `pwd` 
   or we can use `$(pwd)`, as seen above, to fill in the output of `pwd` instead. 
   
   In this scenario, the symbolic link is called `cs1302-javadoc-doc`. You can see it if you
   change into your `public_html` directory and perform an `ls -l`. The entry for 
   `cs1302-javadoc-doc` in the long listing indicates that the file is a symbolic link in
   two different ways: i) an `l` is prefixed in the mode instead of `-` or `d`; and ii) the
   filename lists an arrow pointing to the link target. 

1. Navigate to the following URL in your web browser, replacing `user` with your Nike
   username:

   ```
   http://cs.uga.edu/~user/cs1302-javadoc-doc/
   ```

   Congratulations! If you followed the steps correctly, then you should see the API
   documentation website that you generated earlier. Does this website look similar to any 
   other websites that you may have visited? 

### FAQ

1. __Why not tell `javadoc` to generate the API documentation website directly in__ 
   __`~/public_html/cs1302-javadoc-doc` instead of `doc`?__
   
   That is definitely an option, assuming the `~/public_html/cs1302-javadoc-doc` directory
   already exists. We chose the symbolic link approach in the tutorial because
   it helps keep the `javadoc` command nice and short and to provide an example of
   a symbolic link. 
   
2. __Why not use `cp` or `mv` to copy or move (respectively) the generated API documentation__
   __website directly to `~/public_html/cs1302-javadoc-doc` instead using `ln`?__
   
   Since our use of `ln` created a symbolc link to the `doc` directory, we can now
   regenerate the API documentation website in `doc` and have those changes be automatically
   reflected in `~/public_html/cs1302-javadoc-doc`. 

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
