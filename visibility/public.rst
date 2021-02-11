.. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Spring%202021-success
   :alt: Approved for: Spring 2021

Visibility Reading
##################

|approval_notice|

Public Visibility
*****************

Instead of saying that something has **public** visibility, we usually
just say that it's public. In Java, only top-level and member-level declarations
are allowed to be public. Things that are public are considered
to be the most visible; they are **always visible**.

===============  ==========  ============  ===========  =========
Visibility       Visible From
---------------  ------------------------------------------------
Name             Same Class  Same Package  Child Class  Elsewhere
===============  ==========  ============  ===========  =========
public           |Y|         |Y|           |Y|          |Y|
===============  ==========  ============  ===========  =========

* In Java, the ``public`` modifier must be included in a declararion for
  it to be considered public by the compiler.
* In UML, the ``+`` symbol is used just before a member's identifier to
  illustrate that it's public. It is also common practice to assume
  that a class in a UML class diagram is public if no visibility
  symbol is included.
* The ``javadoc`` program includes public declarations in a
  documentation website by default. If you want ``javadoc``
  to only include public declarations, then the ``-public``
  command-line argument can be used.

.. #############################################################################

.. util
.. |Y| unicode:: U+2713
.. |N| unicode:: U+2717

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
