# JavaFX Custom Component Tutorial

JavaFX is a library for creating and delivering applications with graphical user interfaces (GUIs)
in Java. In this tutorial, we will use JavaFX 8 as it comes with Java 8. The API documentation for
the many packages included ith JavaFX can be found 
[here](https://docs.oracle.com/javase/8/javafx/api/toc.htm).

## Getting Started

1. First, you need to ensure that your terminal emulator supports X-forwarding. This allows
   the GUI parts of your application to be forwarded to your local computer in addition to
   the terminal output. To check this, work through the rest of this section of the
   tutorial. First, login to Nike using the `ssh` command along with the `-XY` option.
   As always, be sure to replace `username` with your Nike username.
   
   ```
   $ ssh -XY username@nike.cs.uga.edu
   ```
   
   If you encounter problems in this step, then please ensure that you have followed the
   instructions provided at the beginning of the semester for
   [MacOS users](https://github.com/cs1302uga/cs1302-exercises/blob/master/misc/MacOS.md) or
   [Windows 10 users](https://github.com/cs1302uga/cs1302-exercises/blob/master/misc/Windows10.md).
   
   Also, the `-X` and `-Y` options can be used individually with orwithout each other. 
   See the manual page for `ssh` for more information about the differences between
   these two options.
   
1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-components`:

   ```
   $ curl -s -L https://git.io/fhAR8 | bash
   ```
   
1. Change into the `cs1302-components` directory that was just created and look around. There should be
   multiple Java files contained within the directory structure. To see a listing of all of the 
   files under the `src` subdirectory, use the `find` command as follows:
   
   ```
   $ find src
   ```
   
1. Compile and run the provided code without any errors or warnings. The program should look very similar
   to the app you created in class exercise 19. See the overall containment heirarchy and image below:
   
   <table>
   <tr>
      <td><img src="https://github.com/cs1302uga/cs1302-ce21/blob/master/ScreenShot.png?raw=true" width=300>
      <td><pre><code>                                     --|
                         Stage            |
                           |              |
                         Scene            |
          |--              |              |
          |               VBox            | Overall
          |               / \             | Containment
   Scene  |              /   \            | Hierarchy
   Graph  |            HBox  ImageView    |
          |            / \                |
          |           /   \               |
          |    TextField  Button          |
          |--                           --|</code></pre></td>
   </tr>
   </table>

1. The default size for the image in the ImageView container is 500x500. Do a quick google search for
   "500x500 images" and load one or two of the images to make sure the app is functioning properly.

1. If you completed the steps correctly, your app should look similar to
   the screenshot provided above. Congratulations on compiling a good
   looking app!
   
## Creating a Custom Component

1. Now that you have your app compiled, let's create a custom component.
   Up to this point, you've been using provided components such as
   `TextField` and `ImageView`. In this next set of steps, we will 
   walk you through the creation of a custom, reusable component based on
   the set of existing components contained in the application.
   Consider the following containment hieararchy:
   
   ```
                                                             --|
                         Stage                                 |
                           |                                   |
                         Scene                                 |
          |--              |                                   |
          |               HBox                                 |
          |                |\                                  |
          |                | \------------------\              |
          |                |                    |              |
          |               VBox                 VBox            | Overall
          |               / \                  / \             | Containment
   Scene  |              /   \                /   \            | Hierarchy
   Graph  |            HBox  ImageView      HBox  ImageView    |
          |            / \                  / \                |
          |           /   \                /   \               |
          |    TextField  Button    TextField  Button          |
          |--                                                --|
   ```
   
   In this scenario, there is a lot of work duplicated. Let's
   instead denote the lower `VBox` sub-graphs as an `ImageLoader`,
   a custom component that we will create in the next step:

   ```
                                                             --|
                         Stage                                 |
                           |                                   |
                         Scene                                 |
          |--              |                                   | Overall
          |               HBox                                 | Containment
   Scene  |                |\                                  | Hierarchy
   Graph  |                | \------------------\              |
          |                |                    |              |
          |           ImageLoader          ImageLoader         |
          |--                                                --|
   ```
   
1. Now consider the sub-graph for the `ImageLoader` component that we
   will create:

   ```
          |--
          |               VBox
          |               / \
   Sub-   |              /   \
   Graph  |            HBox  ImageView
          |            / \
          |           /   \
          |    TextField  Button
          |--
   ```
   
   Note that root of this sub-graph is a `VBox`. With this in mind, 
   create a class called `ImageLoader` in the `cs1302.ce21` package
   that extends the `VBox` class. As this class extends `VBox`,
   it "is-a" `VBox` and inherits all of the members of `VBox`
   (although only `public` and `protected` members will be directly
   visible).

   1. The class should contain the `static` constants from
      the `ImageApp` class. The can be cut and paste directly
	  from that class, perhaps changing them to `protected`
	  visibility if you wish to do so.

   1. Your class should have instance variables for the other
      nodes in the sub-graph. For example, you will need
	  an instance variable called `urlLayer` of type `HBox`
	  as well as instance variables for the remaining nodes.
	  For the most part, these can be cut and paste from the
	  `ImageApp` class.
	  
   1. Your class should have a default constructor that explicitly
      calls `super()`. After the call to `super`, the constructor
	  should instantiate the other nodes and add them to the
	  sub-graph rooted at `this` similarly to how they are 
	  added to the `VBox` node in the `ImageApp` class. 
	  For example, you might start by doing this:
	  
	  ```java
	  public ImageLoader() {
          super();
          / instantiate other objects
          this.getChildren().addAll(urlLayer, imgView);
      } // ImageLoader
	  ```

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
