# Interpreter Scripts

![Approved for: Fall 2023](https://img.shields.io/badge/Approved%20for-Fall%202023-green)

## What is an Interpreter Script?

An interpreter script is a regular text file that contains a sequence of commands
that should be executed by some interpreter program. Instead of launching the
interpreter, typing out the commands (or launching it with input redirection), 
we can, instead, place all of the commands in a script file. 

An interpreter script needs to satisfy the following requirements:
* Execute permission enabled (to whoever will use it); and 
* First line is of the form:

  ```
  #! interpreter [optional-arg]
  ```
  
  Historically, his first line is known as a _shebang_ since it starts with
  the number sign followed by an exclamation mark.

The interpreter must be a valid pathname for an executable program which is not 
itself a script. When executed, then interpreter will be invoked with the following
command-line arguments:

```
interpreter [optional-arg] filename arg...
```

The remaining text is executed in the invoked interpreter.

## Bash Script

One of the easiest examples to understand is a _shell script_. When the
shell being used is Bash, we usually call it a _bash script_. In a Bash script,
you can include a sequence of commands that you would normally type into the
terminal shell. Let's create one!

1. Use the `which` command to determine the absolute path of the `bash`
   executable file.
   
   ```
   $ which bash
   ```
   
1. Based on the output of `which`, use `stat` or `ls -l` to confirm that
   `bash` has execute permission. For example, you might use the following
   command, replacing `/path/to/bash` with the output of `which`:
   
   ```
   $ stat /path/to/bash
   ```
   
1. By convention, the `.sh` file extension is used for shell scripts.
   Create a regular text file called `cs1302-script.sh` and setup the first
   line so that it conforms to the beforementioned requirements for an
   interpreter script. Replace `interpreter` with the output of `which`,
   and exclude an optional arguments for now. It should look this:

   ```
   #!/bin/bash
   ```
   
1. In `cs1302-script.sh`, add some lines containing commands that you 
   would normally type at the terminal shell. Any terminal commands will work!
   
   For example, you might include the following:

   ```
   echo "my bash script is running"
   ls -alh
   ```
   
   or, if you have a class with a FQN of `cs1302.test.Mystuff`, you could script
   the compilation and execution of this class by putting the following terminal commands
   in a script (assuming your present working directory is the top-level project directory):
   
   ```
   javac -d bin src/cs1302/test/MyStuff.java
   java -cp bin cs1302.MyStuff
   ```
   
1. From the terminal shell itself, use the `chmod` command to enable
   user execute permission for `cs1302-script.sh`:
   
   ```
   chmod u+x cs1302-script.sh
   ```
   
1. Verify that the permissions are changed using `ls -l` or `stat`.
   
1. Execute the script. To do this simple provide an absolute path to
   the script name or an abbreviated absolute path using the `.`
   directory. For example, you might try one of the following, the
   first of which will need to be modified slightly based on the
   absolute path of your script:
   
   ```
   $ /path/to/cs1302-script.sh
   ```
   
   ```
   $ ./cs1302-script.sh
   ```
   
   Since `.` is an alias for the current directory, the second example
   will only work if the script is in the current directory.
   
   **ASIDE:** Remove user execute permission, attempt to execute
   the script, then see what happens.
 
 1. Notice the output. It executed both commands!
 
 1. That's it! You now know know the basics of Bash scripts. There is a lot
    more than can be done, but we recommend that you look those things
    up when you need them. For further reading, the nicely written and free
    _BASH Programming_ book by Mike G can be found 
    [here](http://tldp.org/HOWTO/Bash-Prog-Intro-HOWTO.html).
 
### Display Commands in Bash Script Execution

If you would like your bash script to display the commands that it
executes, then add `-x` as an `[optional-arg]` in the shebang or
add `set -x` as the first command in your script.

```
#!/bin/bash -x
```
   
```
#!/bin/bash
  
set -x
```

### Stop on Error in Bash Script Execution

If you would like your bash script to stop executing the commands if one
of them terminates with an error, then add `-e` as an `[optional-arg]` 
in the shebang or add `set -e` as the first command in your script.

```
#!/bin/bash -e
```
   
```
#!/bin/bash
  
set -e
```

**You can combine `-e` and `-x` as `-ex`.**

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
