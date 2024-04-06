# JSON

## Quick Introduction to JSON

JSON stands for "JavaScript Object Notation", a text format for storing
data in strings based on the JavaScript programming language. You do not
need to know JavaScript to use JSON — the two are completely
independent, and many libraries exist in various programming languages
to let us work with the format instead of writing it by hand. Here is a
small example of a JSON-formatted string constructed by hand using Java
code:

```java
String nl = "\n";
String jsonString = "{     " + nl
    + "  \"name\": \"Jay\"," + nl
    + "  \"age\": \"19\",  " + nl
    + "  \"classes\": [    " + nl
    + "    \"CSCI 1302\",  " + nl
    + "    \"CSCI 1730\"   " + nl
    + "  ]                 " + nl
    + "}                   ";
System.out.println(jsonString);
```

Here is the output for that example — once printed, the JSON-formatted
string looks really nice:

```json
{
  "name": "Jay",
  "age": "19",
  "classes": [
    "CSCI 1302",
    "CSCI 1730",
    "CSCI 2610"
  ]
}
```

In a JSON-formatted string,

-   `""` is used to denote a *key* or literal *value*;
-   `{}` is used to denote an *object*; and
-   `[]` is used to denote an array.

With that in mind, let's consider a Java class that might organize its
instance data in a similar to way to the object described by the
JSON-formatted string from earlier:

```java
public class Student {
    String name;
    int age;
    String[] classes;
} // Student
```

Now that we have a Java class to represent the same kind of information,
we can use Google's Gson\_ library to parse the JSON-formatted string
directly into a `Student` object using the fromJson\_ method. The
example below assumes that Gson is added as a project dependency and
that a `Gson` object is available via `GSON` — instructions describing
how to add Gson to a Maven project are included later in this reading:

```java
// parse JSON-formatted string into a Student object
Student jay = GSON.fromJson(jsonString, Example4.Student.class);

// inspect the result
System.out.println(jay.name);
System.out.println(jay.age);
System.out.println("Classes:");
for (int i = 0; i < jay.classes.length; i++) {
    String className = jay.classes[i];
    System.out.println(" - " + className);
} // for
```

Here is the expected output:

```
Jay
19
Classes:
 - CSCI 1302
 - CSCI 1730
 - CSCI 2610
```

**NOTE:** An extended version of this example can be seen in
`cs1302.web/cs1302.web.Example4`\_.

## Complete Examples

Several complete and working code examples accompany this reading so
that readers can see JSON and Gson used in some real-world situations.
To download these examples, follow the instructions
[here](http.rst#complete-examples).

In addition to the examples listed in the HTTP reading, the following
examples are specific to this JSON reading:

| Name                             | Description                                                                                                 |
|----------------------------------|-------------------------------------------------------------------------------------------------------------|
| `cs1302.web/cs1302.web.Example4` | Create a JSON-formatted string by hand, then parse it into a Java object using using Google's Gson library. |

To run an example, use the provided `compile.sh` script along with the
name of the example:

```
$ ./compile.sh cs1302.web/cs1302.web.Example4
```

## Adding Gson to a Maven Project

To use Gson in a Maven project, Gson must be added as a dependency in
the project's `pom.xml` file — refer to the Importing Dependencies\_
page in Maven's documentation for more information:

```xml
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.10.1</version>
</dependency>
```

The latest version available is
![Maven Central Version](https://img.shields.io/maven-central/v/com.google.code.gson/gson).

Once added as a dependency, Gson will automatically be available on the
classpath. For example, you could include the following near the top a
class to make a `Gson` object available within that class — the code
will compile using `mvn compile` so long as the Gson dependency is added
to the `pom.xml` file correctly:

```java
private static Gson GSON = new GsonBuilder()
    .setPrettyPrinting()
    .create();
```

<div class="footer">

[![license\_image](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

Copyright © Michael E. Cotterell, Bradley J. Barnes, and the University
of Georgia. This work is licensed under a CC BY-NC-ND 4.0\_ license to
students and the public. The content and opinions expressed on this Web
page do not necessarily reflect the views of nor are they endorsed by
the University of Georgia or the University System of Georgia.

</div>
