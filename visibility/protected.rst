.. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Spring%202021-success
   :alt: Approved for: Spring 2021

Visibility Reading
##################

|approval_notice|

Protected Visibility
********************

Instead of saying that something has **protected** visibility, we usually
just say that it's protected. In Java, only member-level declarations
are allowed to be protected. Protected members are slightly more visible
than things that are package private; they are **only visible from
lines of code in the same package or a child class (regardless of its
package)**.

===============  ==========  ============  ===========  =========
Visibility       Visible From
---------------  ------------------------------------------------
Name             Same Class  Same Package  Child Class  Elsewhere
===============  ==========  ============  ===========  =========
protected        |Y|         |Y|           |Y|          |N|
===============  ==========  ============  ===========  =========

* In Java, the ``protected`` modifier must be included in a member's declararion for
  the compiler to consider it a protected member.
* In UML, the ``#`` symbol is used just before a member's identifier to
  illustrate that it's protected.
* The ``javadoc`` program includes protected declarations in a
  documentation website by default. If you don't want to include
  protected members, then you can tell ``javadoc`` to only include
  public declarations (as explained in the section on public visibility).

Example 1
=========

In this example, we'll consider two situations that where a protected
member is visible and one that's not. To get started, let's consider
the UML diagram below and the three code snippets that follow it.
There are two snippets for the ``Book`` class constructor, each
representing an alternative approach (i.e., in reality, we would
see one or the other, but not both).

.. image:: img/protected_1.svg

.. code-block:: java

   // inside Book.java (cs1302.books package) -- FIRST APPROACH
   public Book(String title, double price) {
       super(price); // <---------------------------- LINE1
       this.title = title;
   } // Book

.. code-block:: java

   // inside Book.java (cs1302.books package) -- SECOND APPROACH
   public Book(String title, double price) {
       setPrice(price); // <------------------------- LINE2
       this.title = title;
   } // Book

.. code-block:: java

   // inside BookDriver.java (cs1302.books package)
   public static void main(String[] args) {
       Book lotr = new Book("The Lord of the Rings", 11.99);
       lotr.setPrice(lotr.getPrice() * 0.8); // <---- LINE3
   } // main

The visibility situation for each labelled line is summarized in the table
below.

====  ===================  =========  ===========  ========  =============  ===========  ========
..    Member                                       Accessed                              ..
----  -------------------------------------------  ------------------------------------  --------
LINE  Name                 Declared   In           From      Same Package?  From Child?  Visible?
====  ===================  =========  ===========  ========  =============  ===========  ========
1     ``Product(price)``   protected  ``Product``  ``Book``  |N|            |Y|          |Y|
2     ``setPrice(price)``  protected  ``Product``  ``Book``  |N|            |Y|          |Y|
3     ``setPrice(price)``  protected  ``Product``  ``Book``  |N|            |N|          |N|
====  ===================  =========  ===========  ========  =============  ===========  ========

In ``LINE1`` and ``LINE2``, the ``price`` variable was not visible (it's
package private and the labelled lines are attempting access from another
package). The author's two constructor approaches utilize indirection to
initialize a non-visible inheritted member, something that discussed
further `here <#non-visible-inherited-members>`_.

Example 2
=========

In this example, we remind ourselves that protected members are
visible from the same package. To illustrate this, let's consider
the UML diagram below and the code snippet that follows it.

.. image:: img/protected_1.svg

.. code-block:: java

   // inside StoreDriver.java (cs1302.store package)
   public static void main(String[] args) {
       Book lotr = new Book("The Lord of the Rings", 11.99);
       lotr.setPrice(lotr.getPrice() * 0.8); // <---- LINE1
   } // main

The visibility situation for each labelled line is summarized in the table
below.

====  ===================  =========  ===========  ========  =============  ===========  ========
..    Member                                       Accessed                              ..
----  -------------------------------------------  ------------------------------------  --------
LINE  Name                 Declared   In           From      Same Package?  From Child?  Visible?
====  ===================  =========  ===========  ========  =============  ===========  ========
1     ``setPrice(price)``  protected  ``Product``  ``Book``  |Y|            |N|          |Y|
====  ===================  =========  ===========  ========  =============  ===========  ========

Example 3
=========

Since a direct parent of a direct parent is still considered a parent in Java, it's possible
for a child class to inherit protected members not originally declared in its direct parent.
To illustrate this, let's consider the UML diagram below and the code snippet that follows it.

.. image:: img/protected_3.svg

.. code-block:: java

   // inside A.java (cs1302.baz package)
   public void doStuff() {
       doThis(); // <------- LINE1
       doThat(); // <------- LINE2
   } // doStuff

.. code-block:: java

   // inside Driver.java (cs1302.buz package)
   // assume cs1302.baz.A is imported
   public static void main(String[] args) {
       A a = new A();
       a.doStuff(); // <---- LINE3
   } // main

The visibility situation for each labelled line is summarized in the table
below.

====  ===================  =========  =====  ==========  =============  ===========  ==================  =====  ========
..    Member                                 Accessed                                Reference                  ..
----  -------------------------------------  --------------------------------------  -------------------------  --------
LINE  Name                 Declared   In     From        Same Package?  From Child?  Variable            Type   Visible?
====  ===================  =========  =====  ==========  =============  ===========  ==================  =====  ========
1     ``doThis()``         protected  ``C``  ``A``       |N|            |Y|          ``this`` (implied)  ``A``  |Y|
2     ``doThat()``         protected  ``B``  ``A``       |N|            |Y|          ``this`` (implied)  ``A``  |Y|
3     ``doStuff()``        public     ``A``  ``Driver``  |N|            |Y|          ``a``               ``A``  |Y|
====  ===================  =========  =====  ==========  =============  ===========  ==================  =====  ========

The calls on ``LINE1`` and ``LINE2`` to inherited protected members are visible based on
the rules that we have covered so far in this reading. The part that is most noteworthy
is the observation that ``A`` is considered a child of ``C`` by the compiler, even
though it's not a direct child.

The call to ``doStuff()`` on ``LINE4`` does not involve protected visibility; however,
it is interesting. Although calls to ``doThis()`` and ``doThat()`` would not be visible
on ``LINE3``, a call to a visible method that has access still works. This is similar
to what we often see with visible "getter" methods that access private instance variables.

More on Inheritance and Visibility
**********************************

You may recall from the inheritance-related readings that **child classes
inherit instance members** from their parent. In such a scenario, it's
usually pretty clear that inherited members are declared elsewhere
(in the parent class); however, some situations involving overloading,
shadowing, and initialization can be tricky to determine.

Overload Resolution
===================

Since Java allows authors to override an inherited
method, it's possible for there to be multiple declarations that sometimes
have different visibilities. While most overrides preserve the visibility
of the original declaration, it's also possible for them to be declared
more visible in the child. This can make some situations a little tricky
to parse, but the general rule of thumb is this:

    If you try to access ``var.someMethod`` on some line of code, then
    the visibility that's used by the compiler is determined by type of the
    variable ``var``, itself, and not the type of the object that ``var``
    refers to. Java's dynamic binding [8]_ will still bind the call to the
    override that's closest to object's type (e.g., to allow for polymorphism).

Perhaps that's a little dense. You may find it easier to remember this:

    The variable type is used for visibility and the object type is used
    for binding.

.. [8] The term **binding** usually refers to the association between a
       method call and a particular method body. Java uses **dynamic binding**,
       which means that its binding occurs at runtime. This choice was
       made by the designers of the language to facilitate its polymorphism
       and method override features.

Non-Visible Inherited Members
=============================

It's often possible to access access non-visible inherited members indirectly
via a member that is visible.

* For inherited variables, the child class might utilize a visible getter or setter.
  That usually works so long as the instance variable is not shadowed (i.e.,
  declared again in the child, a practice that is highly discouraged).
* For inherited methods, the child class may have access to a visible overload
  that internally calls the private method.

If we apply the second idea to constructors, then a child class constructor may be able to
access non-visible inherited variables (e.g., to initialize them) using a call to a
visible ``super()`` (or some overload of ``super``); this works really well when
the parent constructor initializes it's own declared instance variables.
This is considered **a common pattern** that exemplifies *separation of concerns* and
*encapsulation* as each class is responsible for its own variables.

**Advanced Examples:**

**The examples in this subsection are considered advanced,** and students in CSCI 1302
will not be tested on them. They are provided for completeness and for those who
are interested. **Students may safely skip this section.**

Example 4
=========

.. |jls11_6_6_2| replace:: JLS 11 Section 6.6.2. Details on protected Access
.. _jls11_6_6_2: https://docs.oracle.com/javase/specs/jls/se11/html/jls-6.html#jls-6.6.2

Here is the official definition for protected access from |jls11_6_6_2|_:

.. epigraph::

   A protected member or constructor of an object may be accessed from outside
   the package in which it is declared only by code that is responsible for
   the implementation of that object.

.. |jls11_6_6_2_1| replace:: JLS 11 Section 6.6.2.1. Access to a protected Member
.. _jls11_6_6_2_1: https://docs.oracle.com/javase/specs/jls/se11/html/jls-6.html#jls-6.6.2.1

The official definition is nuanced because it requires us to understand what
it means by "code that is responsible for the implementation of that object."
Some details are provided in |jls11_6_6_2_1|_; however, we find the way they
are presented there to be a little too technical. Believe it or not, here is
a more digestible summary of the requirement:

.. epigraph::

   In a child class, access to a protected instance member of a parent in another
   package via a reference variable (or `this` or `super`) is only permitted when
   the variable type is the child type or a child of the child type.

To illustrate the impact of this requirement, let's consider the UML diagram below
and the two code snippets that follow it.

.. image:: img/protected_2.svg

.. code-block:: java

   // inside Student.java (cs1302.university package)
   public void printDetails() {
       System.out.println(this.getName());
       System.out.ptintln(this.getSSN()); <---------- LINE1
   } // printDetails

.. code-block:: java

   // inside Student.java (cs1302.university package)
   public static void main(String[] args) {
       Student student = new Student();
       System.out.println(student.getSSN()); <------- LINE2
       Person person = new Person();
       System.out.println(person.getSSN()); <-------- LINE3
   } // main

The visibility situation for each labelled line is summarized in the table
below.

====  ===================  =========  ===========  ===========  =============  ===========  ===========  ===========  ========
..    Member                                       Accessed                                 Reference                 ..
----  -------------------------------------------  ---------------------------------------  ------------------------  --------
LINE  Name                 Declared   In           From         Same Package?  From Child?  Variable     Type         Visible?
====  ===================  =========  ===========  ===========  =============  ===========  ===========  ===========  ========
1     ``getSSN()``         protected  ``Person``   ``Student``  |N|            |Y|          ``this``     ``Student``  |Y|
2     ``getSSN()``         protected  ``Person``   ``Student``  |N|            |Y|          ``student``  ``Student``  |Y|
3     ``getSSN()``         protected  ``Person``   ``Student``  |N|            |Y|          ``person``   ``Person``   |N|
====  ===================  =========  ===========  ===========  =============  ===========  ===========  ===========  ========

In practice, the scenario attempted on ``LINE3`` doesn't usually make sense. When a child class
wants to call a protected member declared in a parent, it's usually the inherited verson
of the method that is intended and not the version bound to objects of the parent. Since
it would not make sense to prevent the assignment of parent objects to reference variables
of the parent type, the official definition and its requirement align with usual intent
that we just described. Even though ``LINE3`` is in a child class (a valid location), it
does not have access to a protected member declared in a parent since the type of the
variable used to attempt that access is above the child class in the overall
inheritance hierarchy.

It is worth noting that the type of the variable used on ``LINE2`` (currently ``Student``)
can be child class of ``Student`` and the access will still work, assuming the object reference
assigned to the variable is compatible. While this is allowed from a visibility perspective, it's
considered bad practice because it creates a circular dependency between two classes.

We should also note that the situation on ``LINE1`` remains the same if we change ``this.getSSN()``
to ``getSSN()`` (i.e., a simple method call) since simple calls in an instance method are
assumed to be called on the same calling object as the one used the call the instance method
in which they reside (i.e., the same object referred to by ``this``) when available.

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
