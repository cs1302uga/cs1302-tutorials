# Lambda Expressions

![Status: Not Ready](https://img.shields.io/badge/Status-Not%20Ready-red.svg)

## Introduction

In Java, a __*lambda expression*__ is a special syntax for creating an object that implements
a [functional interface](https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.8).
More precisely, a lambda expression allows a programmer to simultaneously define and instantiate
a nameless class that implements an interface containing just one abstract method (aside from the 
methods of [`Object`](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html)).

Here is an example of a functional interface:
```java
public interface KeepItReal {

    public double apply(double x);

} // KeepItReal
```

### Using a Regular Class

The usual way to implement an interface like `KeepItReal` is to write a regular class in its
own `.java` file. Here are two different regular classes that implement the interface:

```java
public class SquareIt implements KeepItReal {

    public double apply(double x) {
        return x * x;
    } // apply
    
} // SquareIt
```

```java
public class CubeIt implements KeepItReal {

    public double apply(double x) {
        return x * x * c;
    } // apply
    
} // CubeIt
```

The apply method inside each of these classes is not `static` (it cannot be because
it's abstract in the parent interface), so we need to create objects of each class
in order to call `apply`. As usual, we will assign the object reference to the
interface type:

```java
// in any class
public static void main(String[] args) {
    KeepItReal pow2 = new SquareIt();
    KeepItReal pow3 = new CubeIt();
    double n = 10;
    System.out.println(pow2.apply(n)); // 100
    System.out.println(pow3.apply(n)); // 1000
} // main
```

Now, let's take a deep dive into `pow2`. This variable has type `KeepItReal` and it
currently refers to a `SquareIt` object. When we call `pow2.apply(n)`, we're calling
`apply(n)` on that `SquareIt` object. Something similar is happening with `pow3`.

### Using a Simple Lambda

With the previous example in mind, let's omit `SquareIt.java` and `CubeIt.java` entirely.
The code below does almost the same using **lamdbda expressions**
without the need to create those separate `.java` files:

```java
// in any class
public static void main(String[] args) {
    KeepItReal pow2 = (double x) -> {
        return x * x;
    };
    KeepItReal pow3 = (double x) -> {
        return x * x * x;
    };
    double n = 10;
    System.out.println(pow2.apply(n)); // 100
    System.out.println(pow3.apply(n)); // 1000
} // main
```

Consider the `pow2` variable used in this modified example. 
This variable has type `KeepItReal` and it currently refers to some object
(it has to; it's a reference variable that is not `null`). When we call 
`pow2.apply(n)`, we're calling `apply(n)` on that that object. 

Here is a closer look at the `pow2` variable and the expression we're
using to assign a value to it:

```java
KeepItReal pow2 = (double x) -> {
    return x * x;
};
```

As usual, everything between the first `=` in this snippet and the last `;` 
is evaluated, then its value is assigned to `pow2`. Again, `pow2` is a
reference variable, so the value returned by this expression must be a
reference to some object. Objects are made from classes, so you might
have some questions:
* What is the class name for this object? **It has no name!** 
* Where is the class defined? **It's defined by the expression itself!**
* what do we know about this class? **It implements the functional interface!**
* What methods does it have? Besides the methods inherited from the `Object`, **it only has one method!**
* What is the name of the one method? **It's named by the interface!**
* What does the one method do when called? **It executes the body of the lambda expression!**

We call the lambda expression in the example a simple lambda expression
because it looks almost identical to the method we defined in the `squareIt` class.
With this in mind, we usually suggest that beginners take the following
steps when trying to implement functional interfaces using lambda expressions:

1. **Identify the functional interface that is involved.**
   ```java
   KeepItReal pow2; 
   ```
   In this example, we see that the variable `pow2` has the functional interface 
   `KeepItReal` as its type.
   
2. **Identify the one method.**
   The one method for `KeepItReal` is called `apply`. You can scroll up to see
   its signature.
   
3. **Prentend to write the method.**
   Really, just pretend to write it after the `=` operator:
   ```java
   KeepItReal pow2 = apply(double x) {
       return x * x;
   }; 
   ```   
   Of course, **this will not compile**, but it's really close to a lambda.

4. **Remove the method name and add an arrow to make it a simple lambda expression.**
   When you add the arrow, you write the arrow (`->`), place it between the parameter
   list and the openning curly brace:
   ```java
   KeepItReal pow2 = (double x) -> {
       return x * x;
   }; 
   ```
   That's a valid lambda expression!

## Another Example (Copied from Mike's Fall 2020 Piazza Post)

<hr>

### Implement using a Regular Class

##### 1. Override the Method in a New Class

Assuming you have a non-empty `GenList<String>`, you might write the following class (in its own `.java` file) that implements `Comparator<String>` (adding a package statement, imports, and javadoc comments, as needed):
```java
public class StringComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    } // compare

} // StringComparator
```

##### 2. Create the Object 

Once you have that, try the following:
```java
Comparator<String> cmp = new StringComparator();
```

##### 3. Use the Object

Assuming you `GenList<String>` variable is called `sl` (inferred from your post), you can call the `min(Comparator<String>)` method:
```java
String min = sl.min(cmp);
```

##### 4. What it Does

As described earlier, the `min` method, internally, will call `c.compare(String, String)` as loops over the elements of the list. In this particular case, it will be the `compare(String, String)` in the **object created by `new StringComparator()`**, which is now referred to by `cmp`.

<hr>

### Implement using a Lambda Expression

Instead of creating the object using `new StringComparator()`, let's use a lambda expression. The lambda expression is just a short way for you to create the object, just like what we did above, by focussing only on the method definition. Until you're more comfortable, I usually recommend the following approach:

##### 1. Write the Method In-Place

Replace the object instantiation (i.e., `new StringComparator()`) with the method we wrote in the `StringComparator` class. **This won't compile,** but it's a good way to get started. Don't forget the semicolon at the end in the following example, since we're only replacing `new StringComparator()`.
```java
Comparator<String> cmp = public int compare(String s1, String s2) {
    return s1.compareTo(s2);
};
```

##### 2. Create the Object using a Lambda Expression

To change this into a lambda, add an arrow between the parameter list and body, then remove the following (if present): visibility modifier, return type, and method name. Java can figure those things out the same way that we figured them out when we wrote the `StringComparator` class... Java figures it out because we're assigning it to a `Comparator<String>` and that information is described by the abstract method in that interface. **The following result is a valid lambda and should be directly usable.** We'll make it shorter later.
```java
Comparator<String> cmp = (String s1, String s2) -> {
    return s1.compareTo(s2);
};
```

Notice that we didn't provide any argument values; i.e., we're not calling the method being described. That happens later when the created object is used. **To be clear, the lambda expression is doing the same thing as parts (1) and (2) in the first example (Implement using a Regular Class).**

##### 3. Use the Object

Assuming you `GenList<String>` variable is called `sl` (inferred from your post), you can call the `min(Comparator<String>)` method:
```java
String min = sl.min(cmp);
```

##### 4. What it Does

As described earlier, the `min` method, internally, will call `c.compare(String, String)` as loops over the elements of the list. In this particular case, it will be the `compare(String, String)` in the **object created by the lambda expression**, which is now referred to by `cmp`.

##### 5. Making the Lambda Shorter

* Java can figure out the method parameter types based on the signature of the abstract method in the target interface (just like it can figure out the return type). Therefore, the following is also an assignment statement involving a valid lambda expression:
```java
Comparator<String> cmp = (s1, s2) -> {
    return s1.compareTo(s2);
};
```
* If the body of the lambda contains only a single statement, then the curly braces can be omitted and the statement can be place immediately after the arrow (removing the `return` keyword, if present). If the method that the lambda represents (in this case, the `compare` metho) is supposed to return a value (in this case, an `int` value), then Java will use the evaluation of the single statement after the arrow as the return value when the method is later called on the resulting object (i.e., later, when `cmp.compare(String, String)` is called). Therefore, the following is also an assignment statement involving a valid lambda expression:
```java
Comparator<String> cmp = (s1, s2) -> s1.compareTo(s2);
```

<hr>

### Implement using a Method Reference

At the end of the lambda expression example, we had a short lambda expression that simply calls another method (i.e., `s1.compareTo(s2)`) in its body. To get to the point, there is already another method that does what we want. Instead of writing a lambda expression that calls this method, let's refer to it using a method reference so that Java makes the lambda expression for us. 

##### 1. Determine the Type Layout

Since we're going to assign the method reference to a variable of type `Comparator<String>`, we need to understand the type layout of its abstract method. The signature for `compare` (omitting variables names) is:
```java
int compare(String, String)
```
This can be rewritten in a pseudo-lambda form that I call a (type) layout:
```java
(String, String) -> int
```

##### 2. Create the Object using a Method Reference 

Our goal is to refer to the `compareTo` method in the `String` class. Since that method is not a constructor or a static method, any call to this particular `compareTo` method will require a `String` object to call the method on (i.e., we need something like a `String s` so that we can call `s.compareTo(String)`). We can refer to this **instance method** two difference ways:

1. **Provide a `String` reference ourself.** Suppose we have a single `String s` and we want to create a `Comparator<String>` that always uses that single `String` and some arbitrary `String`. In this case, the following method reference can be used: `s::compareTo`. However, the layout for this method reference is `(String) -> int` since only one string is arbitrary. It's a valid method reference, but it's not compatible with `Comparator<String>`, which has the layout of `(String, String) -> int`. This is probably more clear when you consider the equivalent lambda expression: `(str) -> s.compareTo(s, str)`. Also, what is the `s` we assumed we had? Who knows; it doesn't apply in this situation. By the way, this kind of method reference was called a "[r]eference to an instance method of a particular object" in the reading. To summarize:
```java
// (String, String) -> int
Comparator<String> cmp = s::compareTo; // won't work; incompatible
//                          (String) -> int
```

2. **Let Java automatically add a `String` parameter to provide a `String` reference.** To do this, we use the class name as the context for the method reference (i.e., the part before the `::`). The resulting method reference is `String::compareTo`. At first glance, this may look closer to a static call, but remember: i) a method reference doesn't call the method, it just refers to it; and ii) Java can tell the method is not static. As mentioned earlier, this `compareTo` method will require a `String` object to call the method on when it's used later. We didn't provide one when writing the method reference, so Java will add one for us! This is probably more clear when you consider the equivalent lambda expression: `(str1, str2) -> s.compareTo(str1, str2)`. The layout for this method reference is `(String, String) -> int` (the same as it's equivalent lambda expression). This is compatible! It will work! By the way, this kind of method reference was called a "[r]eference to an instance method of an arbitrary object of a particular type" in the reading. To summarize:
```java
// (String, String) -> int
Comparator<String> cmp = String::compareTo; // will work; compatible
//                          (String, String) -> int
```

Now that we know which kind of method reference to use, let's create the object using it:

```java
Comparator<String> cmp = String::compareTo;
```

Notice again that we didn't provide any argument values; i.e., we're not calling the method being described. That happens later when the created object is used. **To be clear, the method reference is doing the same thing as parts (1) and (2) in the first example (Implement using a Regular Class).** 

> Where are the method parameters? 

They're over in the `String` class where the method we're referring to is defined.

##### 3. Use the Object

Assuming you `GenList<String>` variable is called `sl` (inferred from your post), you can call the `min(Comparator<String>)` method:
```java
String min = sl.min(cmp);
```

##### 4. What it Does

As described earlier, the `min` method, internally, will call `c.compare(String, String)` as loops over the elements of the list. In this particular case, it will be the `compare(String, String)` in the **object created by the method reference**, which is now referred to by `cmp`.

<hr>

## Layout of a Lambda

## Method References

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
