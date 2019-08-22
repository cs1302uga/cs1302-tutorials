# Interpreter Scripts

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## What is an Interpreter Script?

An interpreter script is a regular text file that contains a sequence of commands
that should be executed by some interpreter progrsm. Instread of launching the
interpreter, typing out the commands (or launching it with input redirection), 
we can, instead, place all of the commands in a script file. 

An interpreter script needs to satisfy the following requirements:
* Execute permission enabled (to whoever will use it); and 
* First line is of the form:

  ```
  #! interpreter [optional-arg]
  ```

The interpreter must be a valid pathname for an executable program which is not 
itself a script. When executed, then interpreter will be invoked with the following
command-line arguments:

```
interpreter [optional-arg] filename arg...
```

The remaining text is executed is executed in the invoked interpreter.

## Bash Script


   
<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
