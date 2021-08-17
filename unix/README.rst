.. .. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Spring%202021-success
..    :alt: Approved for: Spring 2021
.. sectnum::

.. |approval_notice| image:: https://img.shields.io/badge/Status-Not%20Ready-red.svg

======
 Unix
======

|approval_notice|

.. contents:: **Table of Contents**
   :depth: 2

Introduction
============

Unix is a family of computer operating systems that derive from work performed
in the 1970s at the Bell Labs research center by Ken Thompson, Dennis Ritchie,
and others. In this course, we use the term Unix to refer to systems that are
Unix-like; i.e., their user interface and basic set of utilities are
reminiscent of a Unix system.

In Unix, the primary end-user, command-line interface to the system is a program
called the *shell*. This program can be usually be accessed via another program
called a *terminal emulator*. In the shell, you enter *commands* at a *prompt*.
Here are some examples of what such a prompt might look like:

.. code-block:: shell

   bash$

.. code-block:: shell

   zsh%

The shell is just another way to interact with computer. These days, all
students are familiar with the point-and-click graphical windowing systems
provided in operating systems like Windows, macOS, and even phones.
However, only some realize that the windowing system they're used to is
only one way to interact with a computer. Consider the figure below.

.. figure:: img/intro1.png
   :alt: Local terminal (left) and Finder app (right) on macOS.

In the figure, two different interfaces to the same computer running the Unix-like
macOS operating system are presented. On the left, the window of a terminal
emulator (or a "terminal window," for short) is displayed containing the output
of the commands ``ls`` and ``tree`` (discussed later). On the right, the graphical
Finder app is displayed containing some of the same information shown in the
terminal window.

Tutorial
========

Note About Prompts
------------------

The text that a shell displays for its prompt will differ depending
the shell program itself and a user's profile settings. Consider the
two prompts shown in the introduction. Intentionally presented, the
``bash$`` prompt is similar to what a |bash|_ shell would produce, and
the ``zsh%`` prompt is similar to what a |zsh|_ shell would produce.
In this tutorial, we will not present any features that are specific
to either of these shells; instead, we will focus on general Unix
commands using syntax that is supported by both.

.. |bash| replace:: Bash
.. _bash: https://en.wikipedia.org/wiki/Bash_(Unix_shell)

.. |zsh| replace:: Zsh
.. _zsh: https://en.wikipedia.org/wiki/Z_shell

To keep things simple, we will follow a standard convention of denoting
the prompt with a single ``$`` character. Therefore, if you see a line
of text in this tutorial that begins with a single ``$`` character, then it
means that the text after the ``$`` character represents a command that
should be typed at a prompt. In such examples, the first ``$`` character
is **not** part of the command. Consider the following example:

.. code-block:: shell

   $ date

.. code-block:: shell

   $ $ date
   bash: $: command not found...

Glossary
========

command
    Valid text input for a shell that instructs the shell to work
    with the operating system to accomplish some task (e.g.,
    running another program).

prompt
    The text produced by the shell to request user prompt.

shell
    A computer program that wraps access to an operating system's
    services via a command-line or graphical interface. If a shell
    provides a command-line interface, then services and other programs
    are managed via commands.

terminal
   A computer hardware interface for text entry and display. For example, a
   laptops's keyboard and display might be considered its terminal.

terminal emulator
    A computer program that emulates a terminal, usually whinin a window.
    If the emulated terminal is connected to the same computer that
    the terminal emulator program is running on, then it's often
    referred to as a *local terminal*; however, if the emulated
    terminal is connected to some other computer (e.g., a remote server),
    then it's called a *remote terminal*.

.. copyright and license information
.. |copy| unicode:: U+000A9 .. COPYRIGHT SIGN
.. |copyright| replace:: Copyright |copy| Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
.. |license| replace:: CC BY-NC-ND 4.0
.. _license: http://creativecommons.org/licenses/by-nc-nd/4.0/
.. |license_image| image:: https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg
                   :target: http://creativecommons.org/licenses/by-nc-nd/4.0/
.. standard footer
.. footer:: |license_image|

   |copyright| This work is licensed under a |license|_ license to students
   and the public. The content and opinions expressed on this Web page do not necessarily
   reflect the views of nor are they endorsed by the University of Georgia or the University
   System of Georgia.

----

.. include:: navigation.rst
