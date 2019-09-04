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

## Running JDB

Here is the synopsis for the `jdb` command:

```
$ jdb [options] [classname] [arguments]
```

* `options` - command-line options.
* `classname` - fully qualified name of the main class to debug.
* `arguments` - arguments passed to the `main` method of the class.

### Example



## Breakpoints

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
