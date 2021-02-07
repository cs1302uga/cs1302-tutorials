.. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Fall%202020-blueviolet
   :alt: Approved for: Spring 2021

.. external links
.. |uml_tutorial| replace:: UML Class Diagrams
.. _uml_tutorial: https://github.com/cs1302uga/cs1302-tutorials/blob/master/uml/uml.md
.. |jls11_access_control| replace:: JLS 11 Section 6.6
.. _jls11_access_control: https://docs.oracle.com/javase/specs/jls/se11/html/jls-6.html#jls-6.6
.. |wikipedia_singleton| replace:: Singleton pattern
.. _wikipedia_singleton: https://en.wikipedia.org/wiki/Singleton_pattern

.. image:: img/in-progress.svg

Visibility Reading
##################

|approval_notice|

Introduction
************

Java has four different **visibility** options that can be used to facilitate
**access control**, i.e., to control access to certain things that we declare.
When you declare that something has a particular **visibility** in your code,
you communicate to the compiler the set of locations that are allowed to access
that thing. The term "access" simply means to "use from elsewhere in the code."
With that in mind, throughout this tutorial we will often say that various things
are "visible from" some location; this wording just means that the thing
"can be accessed from" or "can be used from" that location based on its
visibility.

The table below shows all four visibility options that are available in Java,
three of which have an associated **visibility modifier** that we can
include in various declarations throughout our code. The set of potential
visibility options for a declaration may also depends on its level and
other factors.

===============  ================  ==========  ==============  =================  ================
Visibility Name  Modifier Keyword  UML Symbol  Top-Level [1]_  Member-Level [2]_  Local-Level [3]_
===============  ================  ==========  ==============  =================  ================
private          ``private``       ``-``                       |Y|
package private  ..                ``~``       |Y|             |Y|
protected        ``protected``     ``#``                       |Y|
public           ``public``        ``+``       |Y|             |Y|
===============  ================  ==========  ==============  =================  ================

.. [1] A **top-level declaration** is the outermost declaration in a ``.java`` file.
       Some things that can be declared at the top-level include classes and
       interfaces.

.. [2] A **member-level declaration** is any declaration of a class or interface member.
       Members can include, where applicable, the constructors, methods, variables, constants,
       etc. (both static or non-static/instance) of the class or interface; however, the
       never includes Local-level declarations.

.. [3] A **local-level declaration** is any variable declaration that is local, in
       scope, to a particular method. The local variables of a method include
       its parameter and any variables declared within the body of the
       method.

In this tutorial, we cover each available visibility option with a few examples,
often illustrated using a combination of `UML diagrams <uml_tutorial>`__ and code
snippets. The order in which the visibilities are covered is deliberate; we start
with the option that makes something visible from the least number of locations,
then work through the rest in the order of increasing visibility.

We will take some liberties when discussing examples involving multiple
labelled lines (e.g., ``LINE1``) of code; for example, whenever we consider
whether or not a line will work, we will make a good faith assumption that
all other labelled lines of code will also work, even if we find out later
that they don't. If you want to work through such examples yourself (i.e.,
write the code), you may need to comment out or remove some labelled lines of
code whenever you focus your attention a single, labelled line.

While you are likely already familiar private and public visibility, please do not
assume that you already understand how it works. Over the years, we have
found that many students have a somewhat flawed conceptual model for how
private visibility works that is actually more complicated than what it
actually is. Regardless of your experiece, you should work through each
visibility example in this tutorial until you are able to:

1. correctly determine the visibility outcome and justification; and
2. write your own code that illustrates a similar visibility scenario.

We encourage you to make Piazza posts about your examples, ask questions,
and help others to understand the important details of visibility.p

Private Visibility
******************

Instead of saying that something has **private** visibility, we usually
just say that it's private. In Java, only member-level declarations
are allowed to be private. Private members are considered the least visible;
they are **only visible from lines of code in the same class**.


===============  ==========  ============  ===========  =========
Visibility       Visible From
---------------  ------------------------------------------------
Name             Same Class  Same Package  Child Class  Elsewhere
===============  ==========  ============  ===========  =========
private          |Y|
===============  ==========  ============  ===========  =========

* In Java, the ``private`` modifier must be included in a member's declararion for
  it to be considered private by the compiler.
* In UML, the ``-`` symbol is used just before a member's identifier to
  illustrate that it's private.
* The ``javadoc`` program does not include private declarations in a
  documentation website by default; however, they can be included  by
  adding the ``-private`` command-line argument (that option will
  also include anything that is more visible than private -- so
  everything).

Example 1
=========

This is a classic example that many students are familiar with.
To get started, let's consider the UML diagram below and the code snippet that
follows it.

.. image:: img/private_1.svg

.. code-block:: java

   // inside Person.java
   public void setAge(int age) {
       if (!checkAge(age)) { // <---- LINE1 ✓
           throw new IllegalArgumentException("invalid age");
       } else {
           this.age = age; // <---- LINE2 ✓
       } // if
   } // setAge

On the line labelled ``LINE1``, the code attempts to access
``checkAge``, an instance method of the current object (i.e., it's
the same as ``this.checkAge``) declared within the same class.
Although that method is private, it's visible from ``LINE1``
because private members are always visible from within the same
class. A similar argument can be made for the code on ``LINE2``,
which attempts to access the private intance variable ``age``.

Example 2
=========

According to |jls11_access_control|_, the developers of Java
incorporated visibility into the language "to prevent the users of a package or class
from depending on unnecessary details of the implementation of that package or class."
To illustrate this idea, let's consider the UML diagram below and the code snippet
that follows it.

.. image:: img/private_2.svg

.. code-block:: java

   // inside OtherClass.java
   public void updateAges(Person[] persons) {
       for (int i = 0; i < persons.length; i++) {
           int newAge = persons[i].getAge() + 1;
           if (checkAge(newAge)) { // <---- LINE1 ✗
               persons[i].setAge(newAge);
           } // if
       } // for
   } // updateAges

On the line labelled ``LINE1``, the code attempts to access the
``checkAge`` method, an instance method delcared within another
class. Since that method is private, it's not visible from this line
because private members are only visible from within the class where
they are declared. If you try to compile ``OtherClass.java``, then
you get the following error::

    OtherClass.java: error: checkAge() has private access in Person

The error above is exactly what the author of ``Person`` wanted to happen. They
intended for ``checkAge`` to only be used by other methods within the ``Person`` class.
To make the method not visible from outside the class, they declared it private. Had they
declared it public, for example, then the example would have compiled; however, the call
to ``checkAge`` would add unnecessary redundancy since it's called again inside the call
to ``setAge`` on the next line (see the previous example for the inside of ``setAge``).

We're not sure how the author of ``OtherClass`` knew about the ``checkAge`` method, but
the error message lets them know that it's not for them to use. Had they referred to the
Javadoc/API documentation for the ``Person`` class, it's unlikely that the private method
would have been included (private members are not included in the `javadoc` output by default).
If it's private, then it's not for others, and if it's not even listed in the documentation,
then that's less stuff that other programmers need to understand before they're able
to use your code.

Example 3
=========

We mentioned earlier that some students have a flawed conceptual model for
private visibility. Their idea of what it means for something to be private
is more restrivtice than it actually is. **Could this be you?**
To find out, let's consider the UML diagram below and the code snippet for
a copy constructor [4]_ that follows it.

.. image:: img/private_1.svg

.. code-block:: java

   // inside Person.java
   public Person(Person other) {
       setName(other.name); // <---- LINE1
       setAge(other.age); // <---- LINE2
   } // setAge

On the lines labelled ``LINE1`` and ``LINE2``, the code attempts to access
the private instance members ``name`` and ``age`` of the ``Person`` object
referred to by ``other``. Before we continue, ask yourself whether or not
this will work.

When asked, many students will say that this will not compile, then
they are shocked and amazed when they see that it does. That's right,
**it does compile**. Although ``other.name`` and ``other.age`` are private,
they're visible from ``LINE1`` and ``LINE2`` because those lines are in
the same class as the declarations.

    Remember, private members are always visible from lines in the same class.

While a reference to an object does allow us to find members of the object
via ``.memberName`` (for some ``memberName``), our ability to access the
member from the current location (line of code) depends only on where the
member is declared and its visibility. In general, **visibility itself has
nothing to do with objects; it's all about where the code is written**.

.. [4] A **copy constructor** for a class called ``A`` is the constructor
       ``A(A other)``; if a class has a copy constructor, then calling it
       with a reference to some existing object of the class as its parameter
       should result in the newly constructed object being a copy. The result
       is not always guaranteed to be a deep copy, so you should always check
       the constructor documentation and class documentation for more
       information before you make any assumptions.

Package Private Visibility
**************************

Instead of saying that something has **package private** visibility, we usually
just say that it's package private. In Java, only top-level and member-level
declarations are allowed to package private. Things that are package private are slightly
more visible than things that are private; they are **only visible from lines of code
in the same package**.

===============  ==========  ============  ===========  =========
Visibility       Visible From
---------------  ------------------------------------------------
Name             Same Class  Same Package  Child Class  Elsewhere
===============  ==========  ============  ===========  =========
package private  |Y|         |Y|
===============  ==========  ============  ===========  =========

* In Java, **there is no modifier keyword for package private visibility**. For
  top-level declarations and member-level declarations within a class, the omission
  of a visibility modifier will cause the compiler to treat the declared
  thing as package private. This is behavior is different for member-level declarations
  within an interface, where the omission of a visibility modifier defaults
  a declaration's visibility to public.
* In UML, the ``~`` symbol is used just before an identifier to
  illustrate that it's package private. Some UML programs may not support displaying
  the visibility for top-level declarations; in those cases, a quick hack
  is to include the ``~`` as part of the name. Although omitting a visibility
  modifier in Java code may default to package private, the same is not true
  for UML class diagrams; they default to public visibility (covered in a
  later section).
* The ``javadoc`` program does not include package private declarations in a
  documentation website by default; however, they can be included by
  adding the ``-package`` command-line argument (that option will
  also include anything that is more visible than package private -- so
  everything except private).

Example 4
=========

Here is our first example involving a top-level declaration. To get started,
let's consider the UML diagram below and the two code snippets that follow it.

.. image:: img/package_private_1.svg

.. code-block:: java

   // inside Utility.java
   package cs1302.models;

   class Utility { // <---- LINE1
       // ... rest omitted

.. code-block:: java

   // inside Driver.java
   package cs1302.store;

   import cs1302.models.Utility; // <---- LINE2

   // ... rest omitted

On the line labelled ``LINE1``, the author omitted a visibility modifier
in their top-level declaration of the ``Utility`` class. As discussed earlier,
this causes the class to default to package private visibility. On ``LINE2``,
which exists in ``Driver.java`` in a different package [5]_, an attempt is made to
import the ``Utility`` class. Since that class is package private, it's not
visible from this line because things that are package private are only visible
from within the same package. If you try to compile ``Driver.java``, then
you get the following error::

    Driver.java: Utility is not public in cs1302.models; cannot be accessed from outside package

The error above is exactly what the author of ``Utility`` class wanted to happen. They
intended for ``Utility`` itself to only be used by code residing within the
``cs1302.models`` package. To make the method not visible from outside the package,
they omitted a visibility modifier in the top-level class declaration. Had they
declared it public, for example, then the example would have compiled.

As an aside, the UML diagram for this example also includes a private
constructor [6]_! You can read about it in the footnote.

.. [5] In Java, two files are said to be in **different packages** whenever
       their package statements are not identical. Don't let the directory
       structure for some packages confuse you into believing something that's
       not true. For example, although the directory for package
       ``cs1302.foo.bar`` might be inside the directory for package ``cs1302.foo``,
       they're both considered by the compiler to be in different packages.

.. [6] A **private constructor** may seen counterintuitive -- after all, constructors
       are used to make objects of a class, and constructor calls often
       occur in other classes. There are, however, some valid use cases:

       * **Prevent Object Creation:** If your class only contains static methods
         and constants and it doesn't make sense to turn it into an interface,
         then you might explicitly declare one do-nothing constructor that is
         private and has no parameters. This will prevent the compiler from
         automatically creating its own default constructor, and it will prevent
         users of your class from creating objects that aren't needed.

       * **Restrict Object Creation:** It may be desirable to utilize a private
         constructor along with a static method of some other visibility in
         a carefully setup way to restrict the total number of objects of a
         particular class that can be created. We won't go into the details
         here, the |wikipedia_singleton|_ is example of this idea that
         restricts to total number of objects to be no greater than one.

Example 5
=========

In this example, we'll see how package private visibility can be used at
the member-level for access control. Suppose a factory has a contract with a store to
produce some product. Throughout the year, the store mfay need to request
changes to its contract based on sales, buyer interest, etc. The driver
program on the factory's side should be able to access methods to request,
approve, and deny contract-related changes; however, the overall class design
should not allow for the store to approve or deny changes in this scenario.
To see how we can utilize package private visibility to accomplish this,
let's consider the UML diagram below and the two code snippets that follow it.

.. image:: img/package_private_2.svg

.. code-block:: java

   // inside FactoryDriver.java (cs1302.factory package)
   public static void main(String[] args) {
       Factory factory = new Factory();
       factory.requestChange("decrease price"); // <------- LINE1
       factory.approveChange("decrease price"); // <------- LINE2
       factory.denyChange("decrease price"); // <---------- LINE3
   } // main

.. code-block:: java

   // inside Driver.java (cs1302.store package)
   public static void main(String[] args) {
       Factory factory = new Factory();
       factory.requestChange("increase quantity"); // <---- LINE4
       factory.approveChange("increase quantity"); // <---- LINE5
       factory.denyChange("increase quantity"); // <------- LINE6
   } // main

The lines labelled ``LINE1``, ``LINE2``, and ``LINE3`` each attempt
to access a different member of the ``Factory`` class **from the same package**.
The table below summarizes the scenario for each line.
Class names in the ``In`` and ``From`` columns have been omitted since they're not
relevant for this particular example.

====  ====================  ===============  ==================  ==================  ========
LINE  Member                Declared         In                          From        Visible?
====  ====================  ===============  ==================  ==================  ========
1     ``requestChange``     public           ``cs1302.factory``  ``cs1302.factory``  |Y|
2     ``approveChange``     package private  ``cs1302.factory``  ``cs1302.factory``  |Y|
3     ``denyChange``        package private  ``cs1302.factory``  ``cs1302.factory``  |Y|
====  ====================  ===============  ==================  ==================  ========

Likewise, the lines labelled ``LINE4``, ``LINE5``, and ``LINE6`` each attempt
to access a different member of the ``Factory`` class **from a different package**.
The table below summarizes the scenario for each line.

====  ====================  ===============  ==================  ==================  ========
LINE  Member                Declared         In                          From        Visible?
====  ====================  ===============  ==================  ==================  ========
4     ``requestChange``     public           ``cs1302.factory``  ``cs1302.store``    |Y|
5     ``approveChange``     package private  ``cs1302.factory``  ``cs1302.store``    |N|
6     ``denyChange``        package private  ``cs1302.factory``  ``cs1302.store``    |N|
====  ====================  ===============  ==================  ==================  ========

If you try to compile ``Driver.java`` as written, then you get the following error::

    Driver.java: error: approveChange(String) is not public in Factory; cannot be accessed from outside package

Through careful use of visibility, the author of the ``Factory`` class was able to
achieve the access control described near the beginning of this example. That is,
code within the ``cs1302.factory`` package can request, approve, and deny contract-related requests,
while code in other packages can only request changes. It's a beautiful, simple setup,
but it did require the author to understand package private visibility.

In the error message that was recently discussed, the wording "is not public in"
may lead you to believe that only public members are visible from another
package; **that's not the case**. We'll provide an example in the next section
on protected visibility that illustrates this.

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
protected        |Y|         |Y|           |Y|
===============  ==========  ============  ===========  =========

* In Java, the ``protected`` modifier must be included in a member's declararion for
  the compiler to consider it a protected member.
* In UML, the ``#`` symbol is used just before a member's identifier to
  illustrate that it's protected.
* The ``javadoc`` program includes protected declarations in a
  documentation website by default. If you don't want to include
  protected members, then you can tell ``javadoc`` to only include
  public declarations (as explained in the section on public visibility).


Public Visibility
*****************

TODO

Summary of Visibilities
***********************

In the table below, we summarize the locations that declarations
with a particular visibility are visible from.

===============  ==========  ============  ===========  =========
..               Visible From
---------------  ------------------------------------------------
Declared As      Same Class  Same Package  Child Class  Elsewhere
===============  ==========  ============  ===========  =========
public           |Y|         |Y|           |Y|          |Y|
protected        |Y|         |Y|           |Y|
package private  |Y|         |Y|
private          |Y|
===============  ==========  ============  ===========  =========

Important Notes (Do Not Skip)
*****************************


## Private Visibility

<center>
  <img src="private_1.svg" alt="UML class diagram of Person.java">
</center>

We leave out the usual private visibility examples in this section and instead
focus on addressing the common misconception that objects have something to do
with visibility. **Visibility has nothing to do with objects in Java.**
Instead, visibility has to do with classes. To illustrate this, consider the
following UML diagram for a `Point2D` class which is used to represent
immutable (i.e., non-modifiable) points described by `(x,y)` coordinates:

<center>
<img src="Point2D.png">
</center>

One of the constructors of this class, the one that takes in a reference to some other
`Point2D` object, is intended to serve as a _copy constructor_. That is, when that
particular constructor is invoked, the new object should be a deep copy of the object
referred to by the `other` parameter. Here is the usual way this is implemented:

```java
/**
 * Represents immutable {@code (x,y)} coordinates in a two-dimensional space.
 */
public class Point2D {

    private double x; // x coordinate
    private double y; // y coordinate

    ...

    /**
     * Constructs a new {@code Point2D} object that is copy of the object
     * referred to by {@code other}.
     *
     * @param other  object to copy
     */
    public Point2D(Point2D other) {
        this.x = other.x; // other.x is declared in the same class
        this.y = other.y; // other.y is declared in the same class
    } // Point2D

    ...

} // Point2D
```

The lines of code that seem counterintuive to most students are the two lines
inside of the copy constructor:

```java
this.x = other.x;
this.y = other.y;
```

While `other.x` and `other.y` are declared as private, `other` refers to an object
of the same `Point2D` class. Since `other.x` and `other.y` are declared in the same
class, those two lines of code can see `other.x` and `other.y` as presented in the
example. **The fact that `other` refers to another object does not matter.**

**Regarding Inheritance of Private Members:** You may recall from the Inheritance-related
readings that **child classes do inherit private instance variables and methods** from their
parent. However, since those variables are declared private in another class, the
inheriting class cannot see them directly. In scenarios like this, programmers often use
inherited getter and setter methods declared with protected and public visibility to
indirectly access inherited private members. **Another common pattern** is to initialize
some inherited private variables in a child constructor indirectly by explicitly using
`super` to invoke a parent constructor.

## Package Private Notes

Some people refer to _package private_ visibility as the _default visibility_ for
methods and instance variables of a Java class. However, **the term _default_ should
be avoided when talking about visibility** so that the concept is not confused with
Java's default methods feature for interfaces.

When you declare something in a class without a visibility modifier, it has package
private visibility. Something that has package private visibility is only visible
to lines of code within the same package. That is, a line of code can only see
something that is package private if that something is declared somewhere in the
same package. To illustrate this, consider the UML diagram below:

<center>
<img src="PackagePrivate.png">
</center>

In the example above, we have three classes, each containing one or more static
methods. The `Math` and `Statistics` classes are both in the same package, while
the `MathTutorApp` class is in some other package. The associations in the
diagram illustrate that both the `Statistics` class and the `MathTutorApp` use,
in some way, the `Math` class.

Most of the static methods in the diagram are declared with public visibility,
however, the two-parameter overload for `Math.sqrt` is noted as having package
private visibility. In this case, the programmer realized that the two-parameter
`sqrt` method might be complicated for users, so they made a concious decision
to limits its visibility to the package level. At the same time, they provided
an easier to use `sqrt` method that is publicly visible. Within the `Math` class,
the two `sqrt` methods might look something like this (**do not neccesarily concern
yourself with Euler's method; instead keep in mind that the package private
`sqrt` method is not suitable for public access**):

```java
package cs1302.util;

/**
 * Contains utility methods for mathematical operations.
 */
public class Math {

    /**
     * Returns the square root of {@code n} using Euler's method with
     * the specified initial {@code estimate}. This method should
     * only be used within the current package because we cannot
     * guarantee the user will provide anything meaningful for the
     * {@code estimate}. In general, this method is faster if a
     * good {@code estimate} is supplied.
     *
     * @param n         number to find the square root of
     * @param estimate  initial estimate
     * @return square root of {@code n}
     */
    static double sqrt(double n, double estimate) {
        ...
    } // sqrt

     /**
     * Returns the square root of {@code n}.
     *
     * @param n  number to find the square root of
     * @return square root of {@code n}
     */
    public static double sqrt(double n) {
        ...
    } // sqrt

} // Math
```

In this example, methods in the `Statistics` class can access both the one-parameter
and two-parameter `Math.sqrt` method. Here is an example:

```java
package cs1302.util;

/**
 * Contains utility methods for statistical operations.
 */
public class Statistics {

    ...

    /**
     * Returns the standard deviation of the supplied {@code values} based
     * on their population variance.
     *
     * @param values  values to find the standard deviation of
     * @return standard deviation of {@code values}
     */
    public static double stddev(double[] values) {
        double varianceEst = variance(values);
        // next line compiles; the two-parameter Math.sqrt is visible from here
        double stdDevEst   = Math.sqrt(varianceEst, 0.25 * varianceEst);
        return stdDevEst;
    } // stddev

} // Statistics
```

However, **methods in the `MathTutorApp` class can only access the one-parameter `Math.sqrt` method,**
because relative to any lines in the `MathTutorApp` class, only the public `Math.sqrt` method visible.
The two-parameter `Math.sqrt` method is not visible because it's declared in another package and has
package private visibility. Consider the following code example:

```java
package some.other.package;

import cs1302.util.Math;

/**
 * Driver class for Company XYZ's Math Tutor application.
 */
public class MathTutorApp {

    ...

    public static void main(String[] args) {

        ...

        double n = 1024.0;

        // next line compiles; one-parameter Math.sqrt is visible from here
        double stdDev1 = Math.sqrt(n);

        // next line will NOT compile; two-parameter Math.sqrt is not visible from here
        double stdDev2 = Math.sqrt(n, -100.0);

        ...

    } // main

} // MathTutorApp
```

Hopefully this example illustrates that, just as with other visibilities,
making a method or instance variable package private is a design choice.
You should carefully consider whether access to something is suitable
only within its declared package versus making it available to all other
classes.

## Protected Visibility Notes

In a Java class, instance variables and methods that are declared with _protected visibility_
are only visible to lines of code that are either in the same package as the declaring class
or in a subclass of the declaring class. It is similar to package private visibility except
that it does allow lines of code in other packages to see the declared instance variable or
method if the declaring class is a parent. To illustrate these points, consider
the following, non-exhaustive example:

<center>
<img src="Protected.png">
</center>

To simplify the example, we consider whether otherwise valid lines of code in each
class in the diagram can see the `attribute` variable in the `Game` class. In the
table below, the "Visible?" column denotes whether or not the `attribute` variable
is visible, assuming a proper reference to an object containing `attribute` is
provided:

| Class         | Visible? | Comment                                     | Note |
|---------------|----------|---------------------------------------------|------|
| `Game       ` | ✓        | `attribute` is declared in the same class   |      |
| `TypeOneGame` | ✓        | `attribute` is declared in the same package | also inherits `attribute` |
| `TypeTwoGame` | ✓        | `attribute` is declared in the same package | also inherits `attribute` |
| `Utility`     | ✓        | `attribute` is declared in the same package |      |
| `YourGame`    | ✓        | `attribute` is declared in a parent class   | also inherits `attribute` |
| `Tester`      | ✗        | `attribute` is not visible                  |      |

There are two additional points that should be considered regarding this
example. The classes `TypeOneGame`, `TypeTwoGame`, and `YourGame` all have
access to:

1. their own inherited `attribute` variable; **and**
1. `attribute` variables in objects of each other, assuming
   a proper reference to an object is given.

To illustrate the second point, consider the following lines of code,
which you should assume, for the sake of this example, are located inside
a method in `YourGame`:

```java
// inside some method in YourGame
TypeOneGame tog = ...  // not-null; refers to a valid object
int a = tog.attribute; // COMPILES; yes, this works
```

Remember, **visibility has nothing to do with objects in Java.**
Instead, visibility has to do with classes. In the third line of
code, `attribute` via `tog.attribute` is visible because:

1. a proper reference to an object contain `attribute` is given (via `tog`); and
1. relative to that line of code, `attribute` is delcared in a parent class of
   the `YourGame` class which is where these lines are located.

## Public Visibility

When you declare a method or instance variable with public visibility, you
are explicitly stating that you are okay with that thing being accessed
from anwhere, including in lines of code that you potentially do not write.
If that kind of access is inappropriate, then you should carefully consider
one of the other visibilities.

## Closing Remarks

You should carefully consider the different scenarios described in this reading
and try to reproduce them in an actual Java programming environment to see what
the Java compiler will and will not let you do.

## Glossary

visibility

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
