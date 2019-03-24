# Maven Tutorial

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
   export MAVEN_HOME=/home/grads/mec/maven
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
   Apache Maven 3.6.0 (97c98ec64a1fdfee7767ce5ffb20918da4f719f3; 2018-10-24T14:41:47-04:00)
   Maven home: /home/grads/mec/maven
   Java version: 1.8.0_192, vendor: Oracle Corporation, runtime: /home/grads/mec/jdk1.8.0_192/jre
   Default locale: en_US, platform encoding: UTF-8
   OS name: "linux", version: "2.6.32-754.11.1.el6.x86_64", arch: "amd64", family: "unix"
   ```
   
## Creating a Project

Now that Maven is installed, let's create a simple project! Maven supports both interactive
and non-interactive modes for project creation. As the former is often intimidating for beginners,
we will start with Maven's non-interactive mode. We'll make a note about how to use the interactive
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
   
1. With those terms in mind, let's create a project driectory for this turorial called `cs1302-mvn` 
   with a primary package called `cs1302.mvn` using following command:
   
   ```
   $ mvn -B archetype:generate \
     -DartifactId=cs1302-mvn \
	 -DgroupId=cs1302.mvn \
	 -DarchetypeArtifactId=maven-archetype-quickstart \
	 -DarchetypeVersion=1.4
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
   
   You probably notice that created some starter code for you! Here, the
   `src/main/java` subdirectory is the defualt package location for source code.
   The `src/test/java` directory is the default package location for unit tests,
   a topic that will be covered at a later point int time. A simple driver class
   with a fully qualified name of `cs1302.mvn.App` was created for you in
   `src/main/java/cs1302/mvn/App.java`. The last file that you see above, `pom.xml`,
   contains the configuration settings for the Project Object Model (POM), which
   is what Maven uses to do its magic.

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
   
1. That's it! Your project is now setup to use Java 8.

You can also edit the `pom.xml` to add dependcies:



## References

* [[1] What is Maven?](https://maven.apache.org/what-is-maven.html)
* [[2] Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
