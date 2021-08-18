.. sectnum::
.. |approval_notice| image:: https://img.shields.io/badge/Status-Not%20Ready-red.svg

=============================
 Unix: Intermediate Tutorial
=============================

|approval_notice|

.. contents:: **Table of Contents**
   :depth: 3

----

This tutorial assumes that the reader is a Computer Science student
at the University of Georgia with access to the department's
instructional server called Odin. If you were assigned this reading in a
CSCI class at UGA, then you probably have access.

Piping and File Redirection
---------------------------

``|``
+++++

``>``, ``>>``
+++++++++++++

``<``
+++++

``wc``
++++++

.. code-block:: shell

   $ cd ~/cs1302-unix/books
   $ wc moby_dick.txt

.. code-block:: shell

   $ cd ~/cs1302-unix/books
   $ wc -l moby_dick.txt

``grep``
++++++++

.. code-block:: shell

   $ cd ~/cs1302-unix/books
   $ grep "BOOK" moby_dick.txt

.. code-block:: shell

   $ cd ~/cs1302-unix/books
   $ grep -E "^BOOK " moby_dick.txt

.. code-block:: shell

   $ cd ~/cs1302-unix/books
   $ grep -v "BOOK" moby_dick.txt

Job Control
-----------

``C-c``, ``C-z``
++++++++++++++++

``ps``, ``jobs``, ``fg``
++++++++++++++++++++++++

More About Shells
-----------------

``echo``, ``env``, ``bash``
+++++++++++++++++++++++++++

On Odin, the shell that is launched when you login (i.e., the *login shell*)
is GNU `bash <bash>`__. On most Unix-like systems, the login procedure not only
launches a login shell, it also exposes the location of the login shell program
via a ``$SHELL`` variable that is available in your shell's running environment.
You can display this and other environment variables in a line of text using the
``echo`` command. Once ``echo`` is finished running, control returns to the shell
that launched it -- this is the usual behavior for programs launched via shell
commands.

.. code-block:: shell

   $ echo "login shell: $SHELL"

.. figure:: img/echo-shell-demo.svg

* Is ``bash`` still the login shell when you login?
  If not, then please inform your instructor!

If you are interested in the environment variables that are available to you,
then you can list them using the ``env`` (environment) command -- the variable
names are displayed without the ``$`` prefix.

.. code-block:: shell

   $ env

.. figure:: img/env-demo.svg

* What is the value of your ``$HOME`` variable?

Remember, the shell itself is a program, and one of the things it does is help
us run other programs. To see what version of ``bash`` is running, you run
``bash`` itself with ``--version`` supplied as a command-line argument.

.. code-block:: shell

   $ bash --version

.. figure:: img/bash-version-demo.svg

* Odin may have received updates since this tutorial was written.
  What version of ``bash`` is available when you are logged in?

The GNU of GNU bash is an extensive collection of free programs
maintained by the Free Software Foundation. The word "GNU" is a |racronym|_ that
stands for "GNU's Not Unix!" While the GNU project provides implementations of
most programs that people associate with Unix, some Unix-like operating
systems utilize different implementations. Don't let that deter you! The
different implementations of a "Unix program" usually support a common set
of features and a common command-line interface for those features. When
you encounter an implementation that offers an additional feature or
strays from the norm, then there are various ways to get help directly
from the machine the program is running on. We will cover some different
ways to get help later in this tutorial.

.. |racronym| replace:: recursive acronym
.. _racronym: https://en.wikipedia.org/wiki/Recursive_acronym

Subshells
+++++++++

If you run a shell inside your login shell, then a *subshell* is launched.
A subshell is not the login shell, even if they are instances of the same
shell program. A more precise definition for *login shell* is the *instance*
of the shell program that is launched when you login. For example, if you
run ``bash`` supplying the ``--norc`` command-line argument, then a ``bash``
subshell is launched with no customizations (e.g., no custom prompt text).
Just like most programs launched by a shell, when a subshell exits, control
returns to the shell that launched it.

.. code-block:: shell

   $ bash --norc

.. figure:: img/bash-bash-demo.svg?3

* What is the difference between the two ``exit`` commands in
  the example shown above?

The ``bash`` program can also be used to execute scripts containing sequences
of commands and more. We will cover the creation and execution of scripts in
another reading.

Other Important Commands
------------------------

``stat``
+++++++++

``touch``
+++++++++

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
