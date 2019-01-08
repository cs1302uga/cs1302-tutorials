# File Permissions Tutorial

## Prerequisites

This tutorial assumes that the reader has a knowledge of basic Unix commands.

## File Status Information

In Unix, both regular files and directory files have associated metadata that is, for the most part,
independent of a file's contents. This metadata is commonly referred to as the _status information_ 
for the file and is accessible in a couple different ways. 

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

## File Mode
 

## More stuff

1. 


<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
