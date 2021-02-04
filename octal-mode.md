# Octal Mode

![Approved for: Spring 2021](https://img.shields.io/badge/Approved%20for-Spring%202021-success)

## Prerequisites

* [Unix Tutorial Five](http://www.ee.surrey.ac.uk/Teaching/Unix/unix5.html)

## Course-Specific Learning Outcomes

* **LO1.a:** Navigate and modify files, directories, and permissions in a multi-user Unix-like environment.
* **LO1.b:** (Partial) Execute, redirect, pipe, and manage programs/processes in a multi-user Unix-like environment.
  
## Status Information and Mode

In Unix, both regular files and directory files have associated metadata that is, for the most part,
independent of a file's contents. This metadata is commonly referred to as the _status information_ 
for the file and is accessible in a couple different ways. 

On Odin, try the following:

1. Create a new, empty regular file called `newfile` using the following command:

   ```
   $ touch newfile
   ```

2. Now, use `ls` to display some of the file's status information:

   ```
   $ ls -l newfile
   ```

3. To see more detailed status information, use the `stat` command:

   ```
   $ stat newfile
   ```

## Octal Mode

One very important part of a file's status information is its _mode_, which contains information
about the file's type (e.g., regular file or directory file) and its associated permissions. In
the output to `ls -l`, you see the symbolic mode. However, another way to express the permission
portion of the mode is using octal notation.

**NOTE:** Although the symoblic mode may be more readable in many cases, octal notation is vastly
more prevalent in literature and examples that you may find online. 

Consider the output to the following command:

```
$ stat newfile
```
   
Observe the four digit number listed near the symbolic mode, listed by the first line containing
"Access". It is likely `0644`. The line you you are looking for will look like this:

```
Access: (0644/-rw-r--r--)  Uid: (1575035314/  bjb211)   Gid: (1575000513/    myid)
```

Each bit in the binary representation of this number represents a permission bit in the mode.
Technically, there are twelve permission bits, however, we only cover the first nine as they are
the most commonly used. For `0644`, the first nine bits, starting from the right, would leave us 
with the number `644` with a binary representation of `110100100`. 

The notation is called octal because there are eight possiblilities for each digit, each
corresponding to a sequence of three bits that describe the read, write, and execute permissions
for a particular class of user for the file. 

| Octal | Binary | Symbolic |
|-------|--------|----------|
| `0`   | `000`  | `---`    | 
| `1`   | `001`  | `--x`    | 
| `2`   | `010`  | `-w-`    | 
| `3`   | `011`  | `-wx`    | 
| `4`   | `100`  | `r--`    | 
| `5`   | `101`  | `r-x`    | 
| `6`   | `110`  | `rw-`    | 
| `7`   | `111`  | `rwx`    | 

In the table above, the numbers `0` through `7` are written along with their corresponding three digit 
binary representation. If read from left to right, the binary representation lets us know the
read, write, and execute permissions, respectively. If there is a `1`, then that permission is set.
If there is a `0`, then that permission is not set. For convenience, the table also includes the
corresponding symbolic mode for each digit. 

Since each file has a set of permissions for each of the three different classes of users 
(i.e., the user, group, and other), three digits are needed to express the standard full set of nine
permissions. In the case of a file with octal mode `644`, the file has the following permissions:

| Class | Octal | Binary | Symbolic |
|-------|-------|--------|----------|
| User  | `6`   | `110`  | `rw-`    |
| Group | `4`   | `100`  | `r--`    |
| Other | `4`   | `100`  | `r--`    |

Try the following on Odin:

1. Create regular files and directory files varying modes using combinations of 
   `touch`, `mkdir`, and `chmod`. The `chmod` command accepts both symbolic and octal
   notations for the mode. Try using one notation and determine the other by hand!
   
   Remember, if you get stuck, then you can see both mode notations at the same time 
   by using `stat` on Odin. 

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
