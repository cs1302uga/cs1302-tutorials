# Unix Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

Unix is a family of computer operating systems that derive from work performed
in the 1970s at the Bell Labs research center by Ken Thompson, Dennis Ritchie, 
and others. In this course, we use the term Unix to refer to systems that are
Unix-like, i.e., their user interface and basic set of utilities are 
reminiscent of a Unix system. 

In Unix, the primary end-user, text-based interface to the system is a program 
called the _shell_. This program can be usually be accessed via a 
_terminal emulator_ if a graphical windowing system is available or directly
otherwise. In the shell, you enter _commands_ at a _prompt_. Here is an
example of what such a prompt might look like:

```
bash$
```

The shell is just another way to interact with computer. These days, all
students are familiar with the point-and-click graphica windowing systems
provided in operating systems like Windows, macOS, and even phones.
However, only some realize that the windowing system they're used to is
only one way to interact with a computer. 

**NOTE:** Everyone's shell prompt text might look a little different depending
on system type and user customizations. Therefore, we will use the convention 
of denoting the prompt with a single `$`. Lines in this tutorial that begin with 
a single `$`, therefore, are examples of commands that can be typed at a
prompt. The first `$` is **not** part of a command.

## Files and Directories

When working in a shell, you are always working in some _directory_, which
is just another word for folder. The directory that you are in is commonly
referred to as your _current working directory_ or _present working directory_.
In fact, the second term provides you a hint for a command that can be used
to see where you are:

```
bash$ pwd
/home/users/mary
```

As seen above, the `pwd` command can be used to print out your present
working directory. In this case, this directory is called `mary` and
the _absolute path_ to `mary` is `/home/users/mary`. An abolsute path
just tells us the sequence of directories that need to be traversed
if we wanted to reach a file from the root of the filesystem. Any path
that begins with `/` is an absolute path. Here is an illustration of 
how the directories in the example might be organized:

```
/
└── home
    └── users
        ├── bob
        ├── kim
        ├── mary
        └── susan
```

If the present working directory is `mary`, then `users` is called
the _parent directory_ and `bob` might be called a _sibling directory_.

## Tutorial

The remainder of this document is presented in a tutorial style that
assumes you have logged into Nike.

### Useful Commands

1. When you first login, you will want to see your **present working directory**,
   i.e., where you are. To do this, use the `pwd` command:
   
   ```
   $ pwd
   ```
   
1. To **list the contents of the directory** you are currently in, you can
   use the `ls` command:
   
   ```
   $ ls
   ```
   
1. Now, use the following command to download and execute a shell script that 
   retrieves some files used for this tutorial and places them into a subdirectory 
   called `cs1302-unix`:

   ```
   $ curl -s -L https://git.io/fjbdg | bash
   ```
   
   Use `ls` confirm the `cs1302-unix` directory was created.
   
1. Let's navigate around. To **change into a directory**, 
   you can use the `cd` command:
   
   ```
   $ cd cs1302-unix
   ```
   
   In this scenario, `cs1302-unix` is a _relative path_ (as opposed
   to an absolute path), i.e., it's relative to your present working
   directory. 
   
   After changing into the directory, use `pwd` to see the
   directory's absolute path. You can use `cd` followed by the
   directory's absolute path to change to that location regardless
   of where you.   

1. To see more information about the files in your current directory, you
   can enable the **long listing format** for `ls` using the `-l` option.
   
   ```
   $ ls -l
   ```
   
   Notice how the output is different from that of a simple call to `ls`. 
   It should be similar to the following:
   
   ```
   total 8
   drwxr-xr-x 4 mec users 4096 Aug 21 16:25 notes
   -rw-r--r-- 1 mec users    9 Aug 21 16:25 README.md
   ```
   
   Let's break down this output:
   
   | Type | Permissions | Link Count | User  | Group   | Size   | Timestamp      | Filename    |
   |------|-------------|------------|-------|---------|--------|----------------|-------------|
   | `d`  | `rwxr-xr-x` | `4`        | `mec` | `users` | `4096` | `Aug 21 16:25` | `notes`     |
   | `-`  | `rw-r--r--` | `1`        | `mec` | `users` | `9`    | `Aug 21 16:25` | `README.md` |
   
   We won't explicitly cover link count here, but here is information about each of 
   the other parts:
   
   * __Type:__ In Unix, directories contain files. Some of these are are _regular files_, which
     are denoted with a `-` in this spot, while others are _directory files_, which are denoted
     with a `d`. A regular file is what you would normally think of as a file 
     (e.g., `mp3`, `zip`, `doc`, etc.), regardless of the file extension. Most Unix systems
     support other types of files, however, users only directly interact with regular and
     directory files on a regular basis.
   
   * __Permissions:__ Read, write, and execute permissions for user, group, and others. We'll
     cover permissions in detail in a future tutorial.
   
   * __User:__ User who own's the file. In your output, this should match your Nike username. 
     Certain permissions only apply to the file's user.
   
   * __Group:__ Group associated with the file. In your output, this should be whatever group
     you are a member of on the system (e.g., `ugrads`). Certain permissions only apply to users
     in the file's group.
   
   * __Size:__ Size of the file, in bytes. If you want to see more friendly units (e.g., `MB`, `GB`, etc.),
     then use the `-h` option in addition to `-l` (or combine them as `-lh`).
   
   * __Timestamp:__ Time when the file was last modified. If the file hasn't been modified since it was
     created, then this denotes the creation time.

1. To **list recursively**, i.e., list the contents of all directories under a particular path, 
   you can use the `ls` command with the `-R` option:
   
   ```
   $ ls -R notes
   ```
   
   You should see output similar to the following:
   
   ```
   notes:
   cs1302  cs1730
   
   notes/cs1302:
   ce00  ce01
   
   notes/cs1302/ce00:
   NOTES.md
   
   notes/cs1302/ce01:
   NOTES.md
   
   notes/cs1730:
   ```
   
1. Alternatively, you can try the `find` command to find files in the directory 
   hierarchy:
   
   ```
   $ find notes
   ```
   
   You should see output similar to the following:
   
   ```
   notes
   notes/cs1730
   notes/cs1302
   notes/cs1302/ce01
   notes/cs1302/ce01/NOTES.md
   notes/cs1302/ce00
   notes/cs1302/ce00/NOTES.md
   ```
   
1. From either of the previous two outputs, you can infer the following 
   structure under the `notes` directory:
   
   ```
   notes
   ├── cs1302
   │   ├── ce00
   │   │   └── NOTES.md
   │   └── ce01
   │       └── NOTES.md
   └── cs1730
   ```

1. Use the `cd` command to navigate around these directories 


   `mkdir`
   
   If the command is successful, then it should not display
   any output. **This is common for most utility commands.**
   Use `ls` to confirm the directory was created. Also,
   try to execute the same command again.
   
   ```
   $ ls
   cs1302-unix  Documents  README.txt
   ```
   
   ```
   $ mkdir cs1302-unix
   mkdir: cannot create directory `cs1302-unix': File exists
   ```

1. Use `mkdir` to create a directory inside of `cs1302-unix` called `stuff`.
   Change into it, determine its absolute path, and list its contents.

1. At any point in time, you can use `cd` without any arguments to change
   back into your home directory. Let's do that:
   
   ```
   $ cd
   ```
   
1. Now, let's navigate back into `stuff` using a single call to `cd`:

   ```
   $ cd cs1302-unix/stuff
   ```
   
   Again, `cs1302-unix/stuff` is a relative path. Compare it to the
   output of `pwd` to see the differences.
   
   
## Input/Output Redirection and Pipes

## Getting Help

## Manual Pages

### Understanding the Synopsis

When inspecting a manual page for a program, we're often concerned with what command-line
options can be used to customize the behavior of the program. One of the first sections
that will appear in a manual page is the `SYNOPSIS`, and it details this information.
Here is an example for the `ls` command:

```
ls [OPTION]... [FILE]...
```

This is enough information to infer that when a user executes the `ls` command, they 
can include:
* zero or more options (`OPTION`); and
* zero or more files (`FILE`).

Consider `[OPTION]...`, the square brackets (`[`, `]`) let us know that providing
a value for `OPTION` is not required, and the `...` let us know that more than
one `OPTION` can be provided.

Here is the general form:

```
utility_name [-a] [-b] [-c option_argument]
    [-d|-e] [-f[option_argument]] [operand...]
```

More detailed information about how to interpret a synopsis can be found in
[POSIX.1-2017 12.1 Utility Argument Syntax](https://pubs.opengroup.org/onlinepubs/9699919799/basedefs/V1_chap12.html#tag_12_01).

<hr/>

## References

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
