.. .. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Spring%202021-success
..    :alt: Approved for: Spring 2021

.. |approval_notice| image:: https://img.shields.io/badge/Status-Not%20Ready-red.svg

===============
 Unix Tutorial
===============

|approval_notice|

Introduction
************

Unix is a family of computer operating systems that derive from work performed
in the 1970s at the Bell Labs research center by Ken Thompson, Dennis Ritchie,
and others. In this course, we use the term Unix to refer to systems that are
Unix-like; i.e., their user interface and basic set of utilities are
reminiscent of a Unix system.

In Unix, the primary end-user, command-line interface to the system is a program
called the *shell*. This program can be usually be accessed via another program
called a *terminal emulator*. In the shell, you enter *commands* at a *prompt*.
Here is an example of what such a prompt might look like:

.. code-block:: plain

   bash$

The shell is just another way to interact with computer. These days, all
students are familiar with the point-and-click graphical windowing systems
provided in operating systems like Windows, macOS, and even phones.
However, only some realize that the windowing system they're used to is
only one way to interact with a computer.

**TODO:** Place figure here...

----

**NOTE:** Everyone's shell prompt text might look different depending
on system type and user profile settings. Therefore, we will use the convention
of denoting the prompt with a single `$`. Lines in this tutorial that begin with
a single `$`, therefore, are examples of commands that can be typed at a
prompt. The first `$` is **not** part of a command.

----

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

shell
    A computer program that wraps access to an operating system's
    services via a command-line or graphical interface. If a shell
    provides a command-line interface, then services and other programs
    are managed via commands.

prompt
    The text produced by the shell to request user prompt.

command
    Valid text input for a shell that instructs the shell to work
    with the operating system to accomplish some task (e.g.,
    running another program).

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
