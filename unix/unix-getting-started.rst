.. sectnum::
.. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Spring%202024-blue

=======================
 Unix: Getting Started
=======================

|approval_notice|

.. contents:: **Table of Contents**
   :depth: 3

How to engage with this tutorial
================================

When working through any tutorial in 1302, it is expected that you will fully engage with the material. In
other words, it is not sufficient to skim-read the content. You should carefully read and process
and then follow along by typing the commands into your terminal emulator, taking notes as you go.
We recommend writing the answers to any questions asked in this tutorial in your notes along with some
context. These notes will be helpful for studying. If you have any questions as you are working through
the tutorial, you are encouraged to post on the course Piazza page. Your questions will not only help
you fill gaps in your knowledge but also give us insight on potential updates to the tutorials.

Fully engaging with the content will improve your understanding of the content and help you retain 
the information long term.

Introduction
============

Unix is a family of computer operating systems that derive from work performed
in the 1970s at the Bell Labs research center by Ken Thompson, Dennis Ritchie,
and others. In this course, we use the term Unix to refer to systems that are
Unix-like; i.e., their user interface and basic set of utilities are
reminiscent of a Unix system.

In Unix, the primary end-user, command-line interface to the system is a program
called the *shell*. This program can usually be accessed via another program
called a *terminal emulator*. In the shell, you enter *commands* at a *prompt*.
Here are some examples of what such a prompt might look like:

.. code-block:: shell

   bash$

.. code-block:: shell

   zsh%

The shell is just another way to interact with the computer. Think of it as an interface
that allows you to type your commands on the keyboard instead of clicking icons on the
screen. Both interfaces allow you to do the same things, just in a different way.
These days, all students are familiar with the point-and-click graphical windowing systems
provided in operating systems like Windows, macOS, and even phones.
However, only some realize that the windowing system they're used to is
only one way to interact with a computer. Consider the figure below.

.. figure:: img/intro1.png
   :alt: Local terminal (left) and Finder app (right) on macOS.

In the figure, two different interfaces to the same computer running the Unix-like
macOS operating system are presented. On the left, the window of a terminal
emulator (or a "terminal window," for short) is displayed containing the output
of the commands ``ls`` and ``tree`` (discussed later). On the right, the graphical
Finder app is displayed containing some of the same information. Take a moment
to convince yourself that both images display roughly the same information 
in two different ways. On the left, the output of the ``tree`` command looks very 
similar to the output on the right. The only difference is that the user typed 
the command with the keyboard instead of double-clicking a folder icon as they would
on the right.

Prompt and Circumstance
=======================

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
is **not** part of the command. To emphasize this, consider the following
example of the ``date`` command:

.. code-block:: shell

   $ date

When you try to execute the command on your own, do not include the
first ``$`` character in what you type or paste into your terminal emulator.  If
you forget to omit it, then your shell will interpret it as part of the
command itself. This is illustrated in the figures below.

.. figure:: img/prompt-correct.png?2

.. figure:: img/prompt-incorrect.png?2

Required Setup
==============

Before you continue, you need to make sure that your computer is setup so
that you can follow along with the tutorial. If you are using a Windows
computer or a Mac, then please follow the instructions below that best match
your computer's operating system.

* `Setup on macOS <https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/setup/MacOS.md>`__
* `Setup on Windows 10 <https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/setup/Windows.md>`__
  (should also work for Windows 11)

If you are using a Linux computer or a computer running a Unix-like operating
system (other than macOS), then do not fret -- they almost always come with a
terminal emulator installed. If you are not sure how to access the terminal
emulator provided by your operating system, then please consult your operating
system's documentation.

Local Terminal
==============

When you run a terminal emulator on your local (personal) computer (laptop or desktop), 
the default is for it to connect to the same computer it is running on. In other 
words, any commands you type will execute on your local computer. For example,
executing the ``ls`` command will list all of the files in the directory the shell
is working from on your local computer. This *local terminal*
provides a shell with a command-line interface to your computer. Consider the
figure below.

.. figure:: img/local-terminals.png
   :alt: iTerm 2 on macOS Big Sur (left) and MobaXterm 21.2 on Windows 10 (right)

In the figure, we see two different terminal emulators running on two different
computers: the iTerm 2 terminal emulator on the left is running on macOS
and the MobaXterm terminal emulator on the right is running on Windows. While
the commands that were entered by the user are similar in both images, the output
is different because the commands are executing on different machines.

Most, if not all, of the basic commands that we present in the tutorial
should work within a local terminal. When they do not, you should seek out
and consult the documentation for your combination of terminal emulator and
operating system.

Remote Terminal
===============

In this course, you will rarely be working from your local computer. Instead,
we will instruct you to use a local terminal to launch a program that will
connect their terminal emulator to a remote computer (often referred to as a
server). Once connected, that *remote terminal* provides a shell with a command-line
interface to that remote computer. Once connected, any commands you type will execute
on the remote computer instead of on your local machine. So, typing ``ls`` will list
the contents of the remote directory instead of a directory found on your computer.
Consider the figure below.

.. figure:: img/remote-terminals.png
   :alt: iTerm 2 on macOS Big Sur (left) and MobaXterm 21.2 on Windows 10 (right)
         both connected to a third, remote machine.

In the figure, we see two different terminal emulators running on two different
machines; however, both are connected to the same remote computer. Just like
you need credentials (e.g., a personal username and password) to access certain
websites or online apps, you will also need credentials to initiate a remote
terminal session. In the figure, the same user credentials were used in both
images, which means that they are both logged in as the same user on
the remote computer. Since a terminal emulator is, itself, a program, you can
even have two terminal emulators on the same machine connected to the remote computer,
as illustrated in the figure below.

.. figure:: img/multi-user.png
   :alt: One terminal on one machine (left) and two terminals on a second machine (right)
         all connected to a remote computer (center).

Most Unix-like operating systems support multiple users and multiple sessions
per user. Next, we will show you how to establish a remote terminal
session with our departmental server. After that, you are encouraged to try logging 
in more than once to see what it's like.

Logging into a Remote Unix Machine
==================================

In this section, you will login to your account on the computer science departmental server called
Odin. We will do all of our programming this semester on this Unix server so you become comfortable
working in a command-line Unix environment.

Access to Odin is restricted behind a firewall. In order to access Odin from off-campus, you
will need to connect to UGA's remote access VPN using the instructions found
`here <https://eits.uga.edu/access_and_security/infosec/tools/vpn/>`_.

Important Note
++++++++++++++

For CSCI 1302, you are expected to connect to Odin using SSH, and the programs
described in the instructions provided by your instructor. While other programs may exist
that also allow you to establish an SSH connection to Odin, use of certain programs
is explicitly forbidden in CSCI 1302 since they consume large amounts of remote system
resources, disrupting other students' use of the system.
You should NOT use any of the following programs to connect to Odin:

* `Video Studio Code Remote Development Extension <https://code.visualstudio.com/docs/remote/remote-overview>`_
* `code-server <https://github.com/coder/code-server>`_
* `JetBrains Remote Development Toolset <https://www.jetbrains.com/remote-development/>`_

Using the programs mentioned above or programs like the ones mentioned above
without permission violates sections 4.2 and 4.3 of the 
`UGA Policies on the Use of Computers <https://t.uga.edu/2FS>`_.


``ssh``
+++++++

Your username on Odin is your UGA MyID and the password is the same one that
is associated with your MyID. Once you are successfully logged into the VPN,
open up a local terminal in your terminal emulator and execute the ``ssh``
(secure shell) command shown below to establish a secure connection -- be
sure to replace ``username`` with your MyID. When you type in your password,
it will not display anything on the screen -- this is the expected behavior.
Simply type in your password, then press the return key to continue.

.. code-block:: shell

   $ ssh username@odin.cs.uga.edu

.. figure:: img/login-demo.svg

If you have trouble logging into Odin, then please
contact support@cs.uga.edu as soon as possible.

Remember, when typing into a remote terminal, the commands that you enter
are executed on the remote computer - not on your personal computer.

.. table::

   =====================  ======================================================================
   Command                 Description
   =====================  ======================================================================
   ``ssh user@hostname``  Start a secure shell connection to ``hostname`` and login as ``user``.
   =====================  ======================================================================

``pwd``
+++++++

When you login to Odin, you are placed in your *home directory* (home folder).
You can think of this as your own personal folder where your files will be stored
on Odin. Any code you write in 1302 will be in a subfolder of your home directory.

You can see where your home directory is on the system with the help of the
``pwd`` (print working directory) command. It always displays the absolute
"path" of the directory that you are presently in. It is called an absolute
path, because it describes the path of directories that you would need to
traverse to get from the root of the file system (i.e., the ``/`` directory)
to the current working directory one directory at a time.

.. code-block:: shell

   $ pwd

.. figure:: img/pwd-demo.svg?1

.. code-block:: plain

   /
   └── home
       └── myid
           └── mepcott

* What is the absolute path of your home directory?
* What character does an absolute path always start with, and
  what does it represent?

.. table::

   ========  ======================================================================
   Command   Description
   ========  ======================================================================
   ``pwd``   Print the absolute path of the current working directory.
   ========  ======================================================================

``date``, ``exit``, ``whoami``
++++++++++++++++++++++++++++++

Listed below are some easy commands that you can try out immediately, some
of which you may have seen in earlier examples.

.. table::

   ==========  ======================================================================================
   Command     Description
   ==========  ======================================================================================
   ``date``    Print the system date and time.
   ``exit``    Exit the current shell.
   ``whoami``  Print the user name associated with the current user.
   ==========  ======================================================================================

``.bash_profile`` (Required Command)
++++++++++++++++++++++++++++++++++++

To continue with this tutorial, the **CSCI 1302 shell profile** needs
to be enabled on your Odin account. Enabling this profile is also required
to complete coursework in CSCI 1302. A *shell profile* includes commands
and setting customizations that take effect when the profile is *sourced* (loaded).
This step will set up your programming environment for 1302. It will tell the system
where to find the Java compiler and other tools that we will use throughout the 
semester.

If you see output that starts with ``[cs1302]`` when you login to Odin,
then the CSCI 1302 shell profile is enabled on your account. Your output does not have
to match the image below exactly as we often change the shell profile. If you see similar
output, you can proceed to the next section of this tutorial.
  
.. figure:: img/cs1302-profile-check-demo.svg?1


* If you do not see any of that when you login, then the CSCI 1302 shell profile
  is not enabled on your account. To enable it, execute the command below. The
  command adds a line to the ``.bash_profile`` file in your home directory so
  that the profile is sourced each time you login. You won't have to run this command
  again this semester.
  
  **NOTE:** Unlike some of the examples you've seen with ``mepcott`` (i.e., Dr. Cotterell's
  username), the ``mepcott`` in the following command should NOT be replaced with 
  your username. The command is provided by Dr. Cotterell to enable the
  CSCI 1302 shell profile on your account.

  .. code-block:: shell

     $ /usr/local/mepcott/cs1302.enable
     
  .. code-block:: shell
  
     #           |-------|
     #               |
     #    MUST USE "mepcott" HERE

  .. figure:: img/cs1302-profile-enable-demo.svg


**Make sure that you logout, then login again before continuing.**
When you log back in, you should see output similar to what is shown in the video
at the start of this section.

Congratulations! If you see the output above when you login to Odin, you have set up your 
Odin account. You're now ready to login to a remote computer and develop software!

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
