.. |javac| replace:: :program:`javac`

javac
=====

Synopsis
--------

|javac| [*OPTION*\...] *FILE*\...

Description
-----------

|javac| compiles each ``.java`` *FILE* containing Java source code into
a ``.class`` file containing bytecode for the Java Virtual Machine (JVM).

Options
-------

.. option:: -cp CLASSPATH

   Specifies **CLASSPATH** (i.e., the **class path**), a colon-separated list of
   default package directory paths and JAR file paths that |javac| should
   search through when a compiled dependency is needed that does not come with
   Java. If no class path is specified, then |javac| will use the present
   working directory.

.. option:: -d DESTPATH

   Specifies **DESTPATH** (i.e., the **destination path**), the location where
   |javac| should place the default package for the code it compiles. If
   no destination path is specified, then each ``.class`` file is placed
   in the same directory as its corresponding ``.java`` file.

Examples
---------

One File With No Dependencies
*****************************

To begin, let's assume you are trying to compile the Java source code for a
class called ``Driver`` in the ``cs1302.main`` package and that the path to
your default package for source code is ``src``, as seen in the following
figure:

.. code-block::

   .
   |-- bin/
   |-- src/
       |-- cs1302/
           |-- main/
               |-- Driver.java

To compile ``src/cs1302/main/Driver.java`` to ``bin``, use:

.. code-block::

   $ javac -d bin src/cs1302/main/Driver.java

If |javac| emits no error, then ``Driver.class`` should now be located under
``bin`` in a subdirectory corresponding to its package:

.. code-block::

   .
   |-- bin/
   |   |-- cs1302/
   |       |-- main/
   |           |-- Driver.class
   |-- src/
       |-- cs1302/
           |-- main/
               |-- Driver.java

Two Files With Dependencies
***************************

TODO.

Disclaimers
-----------

The content and opinions expressed on this page do not necessarily reflect
the views of nor are they endorsed by the University of Georgia or the
University System of Georgia. Oracle and Java are registered trademarks of
Oracle and/or its affiliates [1]_.

.. [1] https://www.oracle.com/legal/trademarks.html


AUTHOR
------

**javac(1t)** by |mepcott|_

.. |mepcott| replace:: Michael E. Cotterell
.. _mepcott: mepcott@uga.edu
