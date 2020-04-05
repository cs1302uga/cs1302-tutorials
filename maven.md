# Maven Tutorial

![Approved for: Spring 2020](https://img.shields.io/badge/Approved%20for-Spring%202020-blue)

## Maven

This tutorial is designed to help you learn about how to setup and work with Java-based 
Maven projects. According to its authors, the [Apache Maven](https://maven.apache.org/)
tool, named after a [Yiddish word](https://en.wikipedia.org/wiki/Maven) meaning 
_accumulator of knowledge_, is a software project management and comprehension tool. 
It is one of many "build tools" available for the Java language. A **build tool** is 
a program that scripts or automates the process of cleaning, compiling, running, and 
packaging code. So long as your source code adheres to a proper package structure
(i.e., proper package directory structure and `package` statements), then Maven can
usually figure out the rest -- even with dependencies!

## Install Maven on Nike

In order to use Apache Maven on Nike, you will need to add it to your executable 
[`PATH` environmental variable](http://www.linfo.org/path_env_var.html). 

1. To do that, add the following lines to the end of your `~/.bash_profile` file on Nike:

   ```
   export MAVEN_HOME=/usr/local/maven/apache-maven-3.6.1
   export PATH=$MAVEN_HOME/bin:$PATH
   ```

1. After making the change in the previous step, you can either logout / login or 
   `source` your `~/.bash_profile` for the changes to take effect. If done correctly, these 
   changes should take effect every time you login to Nike. 
   
   ```
   $ source ~/.bash_profile
   ```
   
1. To confirm that Apache Maven is installed correctly, type the following command:

   ```
   $ mvn --version
   ```
   
   It should print out your installed version of Maven, for example:
   
   ```
   Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-04T15:00:29-04:00)
   Maven home: /usr/local/maven/apache-maven-3.6.1
   Java version: 1.8.0_192, vendor: Oracle Corporation, runtime: /usr/local/alt-java/jdk1.8.0_192/jre
   Default locale: en_US, platform encoding: UTF-8
   OS name: "linux", version: "2.6.32-754.11.1.el6.x86_64", arch: "amd64", family: "unix"
   ```
   
## Creating a Project

Now that Maven is installed, let's create a simple project! Maven supports both interactive
and non-interactive modes for project creation. As the former is often intimidating for beginners,
we will start with Maven's non-interactive (batch) mode. We'll make a note about how to use the interactive
mode later in this tutorial once you are more familiar with the tool.

1. In order to create a simple Maven project, you will need to remember the four options listed
   in the table below. Maven has more options, but the ones presented below are the bare minimum
   that are needed to create a new project. In the table, the literal option name is provided,
   along with its general name and description.
   
   | Option                  | Name              | Description | 
   |-------------------------|-------------------|-------------|
   | `-DgroupId`             | GroupId           | A group ID is a universally unique identifier for a project. Typically, you want this to be the fully qualified name of your project's primary package (e.g., `cs1302.mvn`). |
   | `-DartifactId`          | ArtifactId        | An artifact is something that is either produced or used by a project. The artifact ID is used to name the directory that Maven creates as well as other things (e.g., JAR files). |
   | `-DarchetypeArtifactId` | Archetype         | An archetype is a template for the project. For most Java-based projects, the `maven-archetype-quickstart` archetype can be used. |
   | `-DarchetypeVersion`    | Archetype Version | The archetype / template version to use. At the time of this writing, the latest version of the `maven-archetype-quickstart` archetype is `1.4`. |
   
1. With those terms in mind, let's create a project directory for this turorial called `cs1302-mvn` 
   with a primary package called `cs1302.mvn` using following command (be careful to type the command 
   exactly as given). You can press <Enter> after each `\` to contine the command on the next line. This
   is equivalent to typing it all on one line but many people prefer to break a long command up on separate
   lines:
   
   ```
   $ mvn -B archetype:generate \
     -DartifactId=cs1302-mvn \
     -DgroupId=cs1302.mvn \
     -DarchetypeArtifactId=maven-archetype-quickstart \
     -DarchetypeVersion=1.4
   ```
   
   If you omit the `-DarchetypeVersion`, then the oldest version is automatically used.
   If you omit the `-DarchetypeArtifactId`, then most Maven installations default to
   `maven-archetype-quickstart` for the archetype.
   
   Here is the command on a single line:
   
   ```
   $ mvn -B archetype:generate -DartifactId=cs1302-mvn -DgroupId=cs1302.mvn -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
   ```
   
1. Look inside the directory that Maven just created:

   ```
   $ find cs1302-mvn
   ```
   
   It should looks similar to this:
   
   ```
   cs1302-mvn/
   cs1302-mvn/src
   cs1302-mvn/src/main
   cs1302-mvn/src/main/java
   cs1302-mvn/src/main/java/cs1302
   cs1302-mvn/src/main/java/cs1302/mvn
   cs1302-mvn/src/main/java/cs1302/mvn/App.java
   cs1302-mvn/src/test
   cs1302-mvn/src/test/java
   cs1302-mvn/src/test/java/cs1302
   cs1302-mvn/src/test/java/cs1302/mvn
   cs1302-mvn/src/test/java/cs1302/mvn/AppTest.java
   cs1302-mvn/pom.xml
   ````
   
   You probably notice that it created some starter code for you! Here, the
   `src/main/java` subdirectory is the default package location for source code.
   This is a small change from previous exercises and tutorials where the default package 
   for source code was `src`. A simple driver class with a fully qualified name of 
   `cs1302.mvn.App` was created for you in `src/main/java/cs1302/mvn/App.java`. Remember, 
   the name of the default package for source code is not included in the fully qualified name.
   
   The `src/test/java` directory is the default package location for unit tests,
   a topic that will be covered at a later point in time. The last file that you see 
   above, `pom.xml`, contains the configuration settings / metadata for the Project 
   Object Model (POM), which is what Maven uses to do its magic.
   
1. In the future, you might try omitting the `-B` (batch mode) and subsequent options
   to use Maven's interactive mode. Instead of specifiying some of the project properies
   as command-line options, Maven will prompt you for their values. 

## Updating the POM

By default, the `maven-archetype-quickstart` archetype (version `1.4`) is configured
to use Java 7 (`1.7`)! We can remedy this by updating the project's `pom.xml` file.

1. Change into the `cs1302-mvn` driectory, then change the values of the
   `maven.compiler.source` and `maven.compiler.target` to `1.8`. It should look
   similar to the following:
   
   ```xml
   <properties>
     <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
     <maven.compiler.source>1.8</maven.compiler.source>
     <maven.compiler.target>1.8</maven.compiler.target>
   </properties>
   ```
   
   That's it! After making that change, your project is now setup to use Java 8.

1. You can also add / update project dependencies. In the past, you may have done this
   by manually including a JAR file on your class path. With Maven, we can add the 
   describe the dependency in the POM and Maven will download and add it to the 
   class path for us! For example, the `pom.xml` file in your `cs1302-mvn` project already
   contains the following dependencies:
   
   ```xml
   <dependencies>
     <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.11</version>
       <scope>test</scope>
     </dependency>
   </dependencies>
   ```
   
   This adds a dependency called [JUnit 4.11](https://search.maven.org/artifact/junit/junit-dep/4.11/pom)
   To add more dependecies, you would simply add an additional `<dependency></dependency>` tag
   with appropriate values before the closing `</dependencies>` tag. Many libraries are packages
   for Maven. You can try searching for some on [Maven Central](https://search.maven.org/).

## Using Maven

If you haven't already done so, change into the `cs1302-mvn` directory that you created earlier
using Maven. 

1. To use Maven, we need to talk about _phases_. Each Maven lifecycle **phase** is 
   responsible for doing a particular sequence of actions. Phases are sometimes also
   referred to as **goals**. They help us automate some of the 
   commands that we're used to typing out manually. Here is a list of the basic phases that
   you need to know:
   
   | Phase       | Description |
   |-------------|-------------|
   | `compile`   | Compile the source code of the project. | 
   | `clean`     | Remove compiled files from the project. |
   | `site`      | Generate a website for the project that includes the API documention. |
   | `package`   | Take the compiled code and package it into a JAR file. |
   | `test`      | Execute unit tests, if available, using the project's unit testing framework. |
   | `exec:java` | Execute the class specified by `-Dexec.mainClass` with dependencies added to the class path. |
   | `archetype:generate` | Generate a project directory based on an archetype. |
   | `javadoc:javadoc` | Generate API documentation only. |
   
   To execute a phase using Maven, simply type the phase name after the `mvn` command.
   Pay careful attention to the output of each command as it usually provides lots of
   useful information about what worked or didn't work related to that command.
   **The _first time_ you execute a phase after creating / updating the POM, Maven
   may need to download some files;** this is normal behavior. 
   
1. Let's try compiling the code:

   ```
   $ mvn compile
   ```
   
   The first time that you do this for a particular project, Maven might neeed to download some
   of the dependencies defined in the POM -- it should not need to download every time. If
   compilation is successful, then you should see something similar to the following:
   
   ```
   [INFO] Scanning for projects...
   [INFO]
   [INFO] -----------------------< cs1302.mvn:cs1302-mvn >------------------------
   [INFO] Building cs1302-mvn 1.0-SNAPSHOT
   [INFO] --------------------------------[ jar ]---------------------------------
   [INFO]
   [INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ cs1302-mvn ---
   [INFO] Using 'UTF-8' encoding to copy filtered resources.
   [INFO] skip non existing resourceDirectory cs1302-mvn/src/main/resources
   [INFO]
   [INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ cs1302-mvn ---
   [INFO] Changes detected - recompiling the module!
   [INFO] Compiling 1 source file to cs1302-mvn/target/classes
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time:  1.417 s
   [INFO] Finished at: 2019-03-24T16:26:39-04:00
   [INFO] ------------------------------------------------------------------------
   ```
   
   If you pay careful attention to the output, you will see that Maven used
   the `target/classes` subdirectory as the default package for compiled code.
   With this in mind, we can run the `cs1302.mvn.App` class as follows:
   
   ```
   $ java -cp target/classes cs1302.mvn.App
   Hello World!
   ```
   
1. From time to time, you may need to delete the compiled files from your project
   (e.g., after moving a type from one package to another). This is commonly 
   referred to as _cleaning_ the project. To clean your project using Maven,
   use the following command:
   
   ```
   $ mvn clean
   ```
   
   If done correctly, you should see something similar to the following:
   
   ```
   [INFO] Scanning for projects...
   [INFO]
   [INFO] -----------------------< cs1302.mvn:cs1302-mvn >------------------------
   [INFO] Building cs1302-mvn 1.0-SNAPSHOT
   [INFO] --------------------------------[ jar ]---------------------------------
   [INFO]
   [INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ cs1302-mvn ---
   [INFO] Deleting cs1302-mvn/target
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time:  0.357 s
   [INFO] Finished at: 2019-03-24T16:33:35-04:00
   [INFO] ------------------------------------------------------------------------
   ```
   
1. Now, let's package the project into a JAR file. In the past, you may have done
   this manually using the `jar` command. Here, we'll use the `package` phase:
   
   ```
   $ mvn package
   ```
   
   If done correctly, you should see something similar to the following (output
   condensed):
   
   ```
   [INFO] Scanning for projects...
   [INFO]
   [INFO] -----------------------< cs1302.mvn:cs1302-mvn >------------------------
   [INFO] Building cs1302-mvn 1.0-SNAPSHOT
   [INFO] --------------------------------[ jar ]---------------------------------
   [INFO]
   [INFO] --- maven-jar-plugin:3.0.2:jar (default-jar) @ cs1302-mvn ---
   [INFO] Building jar: cs1302-mvn/target/cs1302-mvn-1.0-SNAPSHOT.jar
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time:  2.738 s
   [INFO] Finished at: 2019-03-24T16:45:03-04:00
   [INFO] ------------------------------------------------------------------------
   ```
   
   If you pay careful attention to the output, you will see that Maven created
   a `cs1302-mvn-1.0-SNAPSHOT.jar` in the `target` subdirectory.
   With this in mind, we can run the `cs1302.mvn.App` class as follows:
   
   ```
   $ java -cp target/cs1302-mvn-1.0-SNAPSHOT.jar cs1302.mvn.App
   Hello World!
   ```
   
   The version number `1.0-SNAPSHOT` comes directly from the `<version>`
   tag contained in the POM. If your project is constantly being updated
   within a single version number, as often is the case in development,
   then keeping the `-SNAPSHOT` is actually recommended. In this scenario,
   we're developing version `1.0` of the project. Every time we compile,
   that might not be the _final_ version `1.0` that we want to release.
   As such, we use `1.0-SNAPSHOT` to indicate that this is a _snapshot_ 
   of version `1.0` in order to inform users of the project that the code
   may not be in its final state.
   
1. Try using the `site` phase. The output will show you where the files
   for the website are created. You can copy or symlink these to a location
   under your `~/public_html` directory to host the site on Nike just as
   you did for API documentation websites created using the Javadoc tool
   in the past.
   
   The generated site is stored in `target/site`. You might use something
   like the following command to create a symbolic link named `project-site`
   (adjust as needed) under your `~/public_html` directory:
   
   ```
   $ ln -s $(pwd)/target/site ~/public_html/project-site
   ```
   
   **Doesn't work!?** If Maven complains about missing classes, then you may need 
   to update the `mvn-site-plugin` to a newer version than the default. 
   The dependency information that should be included / updated in your `pom.xml` file 
   can be found 
   [here](https://maven.apache.org/plugins/maven-site-plugin/dependency-info.html).
   **Remember to backup your POM (i.e., make a backup copy) before making edits!**

1. In some of the examples above, we walked you through how to run a driver
   class after compiling for packaging the project. There is also a way
   to run a driver class driectly using Maven using the `exec:java` phase:
   
   ```
   $ mvn exec:java -Dexec.mainClass="cs1302.mvn.App"
   [INFO] Scanning for projects...
   [INFO]
   [INFO] -----------------------< cs1302.mvn:cs1302-mvn >------------------------
   [INFO] Building cs1302-mvn 1.0-SNAPSHOT
   [INFO] --------------------------------[ jar ]---------------------------------
   [INFO]
   [INFO] --- exec-maven-plugin:1.6.0:java (default-cli) @ cs1302-mvn ---
   Hello World!
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time:  0.747 s
   [INFO] Finished at: 2019-03-24T17:03:22-04:00
   [INFO] ------------------------------------------------------------------------
   ```
   
   As you can see, this requires a little more typing (to include the `-Dexec.mainClass` option)
   and adds additional output to the output of the program. There are some more caveats
   involved when using the `exec:java` phase:
   
   * It does not automatically recompile code if changed -- you will need to manually perform
     the `compile` phase, as needed.
	 
   * It redirects standard output to an output stream that does not flush automatically. This
     can cause issues if your program reads and writes from standard input and standard output,
	 respectively. This can usually be mitigated by peforming `System.out.flush()` whenever
	 you need to synchronize between a print and subsequent read. For example:
	 
	 ```java
	 System.out.print("enter a number: ");
	 System.out.flush();
	 int num = input.nextInt();
	 ```
	 
   However, if your project has a runtime dependency, i.e., a library that is required during
   runtime, then the `exec:java` phase will automatically ensure that it is on the class path
   when the driver class is executed! This is especially convenient when there are multiple
   such dependencies.
   
## References

* [[1] What is Maven?](https://maven.apache.org/what-is-maven.html)
* [[2] Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

<hr/>

**Feedback?**
Please help us make this better!
If you have any feedback or suggestions for this reading or tutorial, then use
the link below to reach the feedback form.

[![Submit Feedback](https://img.shields.io/badge/-Submit&nbsp;Feedback-red.svg?style=for-the-badge)](https://docs.google.com/forms/d/e/1FAIpQLSfBgZM_-G-9nKmX7F83k0Tgp1OlqBnrkt6vsxlIqLypc_keUQ/viewform?usp=pp_url&entry.1081181680=cs1302-maven&entry.1901270436=https://github.com/cs1302uga/cs1302-tutorials/blob/master/maven.md)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
