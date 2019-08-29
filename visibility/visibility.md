# Visibility Tutorial

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

Java has four different visibilities that can be assigned to methods 
and instance variable decalarations, three of which are indicated 
with modifier keywords:

| Visibility Name | Modifier Keyword |
|-----------------|------------------|
| private         | `private`        |
| package private |                  |
| protected       | `protected`      |
| public          | `public`         |

Depending on where a declaration occurs, omitting a visibility modifier
keyword means something different. 

* In an an interface, the omission of a visibility modifier keyowrd 
  implies the method has public visibility. In fact, the only visibility
  that is allowed in an interface decalarion is public.
  
* In a class, the omission of a visibility modifier keyword implies 
  the method or instance variable has _package private_ visibility.
  All other visibilities are allowed, however, they must be explicitly
  set using a visibility modifier keyword.

## Why Visibilities?

Visibilities exist so that you can control what code is visibile to other
code. It may be desirable to only mak certain things in a class visible to
the class itself, while it's perfectly okay to make other things visible
to everything else. In particular, the two scenarios describe private and
public visibility, respectively. However, Java has two additional visibilities
to accomodate some of the scenarios that in-between.

## Interpretation

| # | Member Declaration | `private` | _package private_ | `protected` | `public` |
|---|--------------------|-----------|-------------------|-------------|----------|
| 1 | same class         | ✓         | ✓                 | ✓           | ✓        |
| 2 | same package       |           | ✓                 | ✓           | ✓        |
| 3 | parent class       |           |                   | ✓           | ✓        |
| 4 | none of the above  |           |                   |             | ✓        |

1.

2.

3.

4.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
