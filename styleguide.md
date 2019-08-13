**UNDER CONSTRUCTION; IN PROGRESS**
<hr/>

# CS1302 Code Style Guide

1. [Guidelines](#guidelines)
1. [Recommended Emacs Configuration](#recommended-emacs-configuration)
1. [How to Check](#how-to-check)
   1. [Setup Checkstyle](#setup-checkstyle)
   1. [Run Checkstyle](#run-checkstyle)

## Guidelines

### FileTabCharacter

No tab characters (`\t`) are allowed in the whitespace of the source code.
This does not include tabs in string literals. For example, the following
snippet is okay:

```java
String s = "word1\tword2";
```

**Rationale:** 
Use of tabs instead of spaces may result in different text editors displaying
same file in different ways, depending on the tab width configured for the
given editor. Developers should not need to configure the tab width of their 
text editors in order to be able to read source code.

### LineLength



### OuterTypeFilename



## Recommended Emacs Configuration

## How to Check

### Setup Checkstyle

### Run Checkstyle

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
