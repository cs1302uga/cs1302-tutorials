.. sectnum::
.. |approval_notice| image:: https://img.shields.io/badge/Approval+Pending-Still+In+Development-red
.. .. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Fall%202021-blue

===============
 Git Branching
===============

|approval_notice|

.. contents:: **Table of Contents**
   :depth: 3

Introduction
============

Every Git repository has at least one "`branch <gitglossary_branch>`_," usually
called ``master`` or ``main``\ [1]_, that represents the project's main line of
development (i.e., its main progression over time). When you create a new
repository using ``git init``, the specific branch name that is used is
governed by the ``init.defaultBranch`` setting.

.. code:: sh

   $ git config --global init.defaultBranch

.. code:: sh

   main

If the value of your ``init.defaultBranch`` is ``master``\ [1]_, then you are
encouraged to change it to ``main`` using the following command:

.. code:: sh

   $ git config --global init.defaultBranch main

.. [1] At the time of this writing, the default value for ``init.defaultBranch``
       is ``master``; however, in 2020, the Git development community and its
       partners reflected on the fact that "master" has negative connotations for
       many groups of people and started encouraging the use of more inclusive
       branch names like ``main``. Popular services like GitHub and GitLab
       already default their ``init.defaultBranch`` value to ``main``.

.. references
.. _gitglossary_branch: https://git-scm.com/docs/gitglossary#Documentation/gitglossary.txt-aiddefbranchabranch

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
