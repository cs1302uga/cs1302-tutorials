# JavaFX Custom Component Tutorial

![Approved for: Spring 2024](https://img.shields.io/badge/Approved%20for-Spring%202024-blue)

JavaFX is a library for creating and delivering applications with graphical user interfaces (GUIs)
in Java. In this tutorial, we will use JavaFX 17. The API documentation for
the many packages included with JavaFX can be found 
[here](https://openjfx.io/javadoc/17/).

## Course-Specific Learning Outcomes
* **LO2.a:** Identify redundancy in a set of classes and interfaces, then refactor
using inheritance and polymorphism to emphasize code reuse.
* **LO7.a:** (Partial) Design and implement a graphical user interface in a software project.

## Getting Started

1. First, you should ensure that your terminal emulator supports X-forwarding. This allows
   the GUI parts of your application to be forwarded to your local computer in addition to
   the terminal output. To check this, work through the rest of this section of the
   tutorial. First, log in to Odin using the `ssh` command with the `-XY` option.
   As always, replace `username` with your Odin username.
   
   ```
   $ ssh -XY username@odin.cs.uga.edu
   ```
   
   If you encounter problems in this step, then please ensure that you have followed the
   instructions provided at the beginning of the semester for
   [MacOS users](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/setup/MacOS.md) or
   [Windows 10 users](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/setup/Windows.md).
   
   Also, the `-X` and `-Y` options can be used individually with or without each other. 
   See the manual page for `ssh` for more information about the differences between
   these two options.
   
1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-components`:

   ```
   $ curl -s -L https://git.io/fjfie | bash
   ```
   
1. Change into the `cs1302-components` directory that was just created and look around. There should be
   multiple Java files contained within the directory structure. To see a listing of all of the 
   files under the `src` subdirectory, use the `find` command as follows:
   
   ```
   $ find src
   ```
   
1. Compile and run the provided code without any errors or warnings. If you need a reminder of the compile commands, review
   the relevant parts of the [JavaFX Tutorial](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/javafx/javafx.md). 
   We recommend creating a compile script so you don't have to retype these commands. If you completed the previous steps 
   correctly, your app should look similar to the screenshot below. You may also find it helpful to review
   the scene graph for this app (also seen below).
   
   <table>
   <tr>
      <td><img src="https://github.com/cs1302uga/cs1302-tutorials/raw/alsi/components/ScreenShot.png?raw=true" width=300>
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

1. The default size for the image in the ImageView container is 500x500 (even though the image says 300x300). 
   Do a quick Google search for "500x500 images" and load one or two of the images you find to ensure the
   app functions properly. **Note:** The `Image` class only supports the BMP, GIF, JPEG, and PNG file types.
   
## Creating a Custom Component

1. Up to this point, you've been building apps using provided JavaFX
   components such as `TextField` and `ImageView`. In this next set 
   of steps, we will walk you through the creation of your own custom, 
   reusable component based on the set of existing components contained 
   in the application.
   
   Consider the following containment hierarchy:
   
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
   
   In this scenario, the root `HBox` of the scene graph contains two
   separate, but identical, `VBox` objects. Building this app would
   require the programmer to repeat the exact same code to create
   these two `VBox` objects. Now, imagine that there are hundreds
   of these in an app. Custom components allow us to avoid this type
   of redundancy!
   
   Consider the following scene graph where we replace the lower `VBox` 
   sub-graphs with `ImageLoader`, a custom component we will create 
   in the next step. The resulting scene graph is much cleaner (kinda looks
   like a giraffe, no?)!

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
   
1. Now consider the sub-graph for the `ImageLoader` component we
   will create. We want to avoid repeating this work by replacing this redundant
   part of the original scene graph with a single `ImageLoader` component:

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
   
   Note that the root of this sub-graph is a `VBox`. With this in mind, 
   create a class called `ImageLoader` in the `cs1302.gui` package
   that extends the `VBox` class (additional details are provided
   below; please read them carefully). As this class extends `VBox`,
   it "is-a" `VBox` and inherits all of the members of `VBox`
   (although only `public` and `protected` members will be directly
   visible).

   1. The class should contain the `static` constants from
      the `ImageApp` class. They can be cut and pasted directly
      from that class, perhaps changing them to `protected`
      visibility if you wish to do so. That way they can be accessed
      by the other classes in the package.

   1. Your `ImageLoader` class should contain instance variables for the
      nodes in the sub-graph above (`HBox`, `TextField`, `Button`, and `ImageView`).
      You do not need an instance variable for `VBox` because the `ImageLoader` itself
      is a `VBox`!
      For the most part, the required instance variables can be cut and paste from the
      `ImageApp` class. Any instance variables that you move
      into the `ImageLoader` class can be removed from `ImageApp`. You can
      also remove any imports that are no longer needed in `ImageApp`.
	  
   1. In `ImageLoader`, add a default constructor that explicitly
      calls `super()`. After the call to `super()`, the constructor
      should instantiate the other nodes in the `ImageLoader` sub-graph
      (`HBox`, `ImageView`, `TextField`, and `Button`). Since 
      `ImageLoader` extends `VBox`, it "is-a" `VBox`. Therefore, you
      can call any `VBox` methods using `this` as the calling object.
      Use this knowledge to add your newly created nodes to the
      sub-graph rooted at `this` similar to how they are 
      added to the `VBox` node in the `ImageApp` class. 
      Your code will likely look something like the code below with 
      additional statements to instantiate the components and connect
      them:
	  
      ```java
      public ImageLoader() {
         super();
         // instantiate objects for the component's sub-graph, then
         // add them to the ImageLoader (i.e., this)...
         this.getChildren().addAll(urlLayer, imgView);
      } // ImageLoader
	  ```
	  
   1. Remove the code to create the subgraph
      (`HBox`, `ImageView`, `TextField`, and `Button`) from the constructor and `init` methods
      of `ImageApp`. All of that code will now be run when we create a new `ImageLoader`
      object. This includes the code to initialize the `ImageView` and set up the button handler.
      
   1. Take a moment to think about what you are doing. You have created your own custom class that extends
      the JavaFX `VBox` class. This class is essentially a `VBox` with some of the components
      built into it. Once we complete this class, we will be able to add objects of this class to a
      scene graph and all the messy details of creating that object will be hidden inside of `ImageLoader`!
      
   1. Move the `loadImage` method from `ImageApp` to `ImageLoader`. This is the method that is
      called when the button is clicked. Don't forget to set the handler on your `ImageLoader`'s button.
      
   1. You've probably noticed that `ImageApp` has significantly decreased
      in size.  We moved a lot of that code over into our custom component!
      Now, instantiate two objects of type `ImageLoader` within the constructor
      of `ImageApp`.
      
   1. Create an `HBox` instance variable in the `ImageApp` class and instantiate 
      it within the constructor. This
      will serve as the container for our `ImageLoader` objects. Set
      the `spacing` property of the `HBox` to `10` by passing `10` into the `HBox`
      constructor. Now, in the `init` method, add the two `ImageLoader` objects to 
      the `HBox` object of `ImageApp`.
      
   1. Make sure you pass the reference to your newly created `HBox` object into the
      `Scene` constructor within the `start` method of `ImageApp`. Previously, the 
      root of our scene graph was a `VBox`. Now it's an `HBox` that contains two
      `ImageLoader` objects.
      
   1. Compile and run your new app and load up a few 500x500 images.  You 
      should see something like the image below:
      
      <img src="https://github.com/cs1302uga/cs1302-tutorials/raw/alsi/components/TwoPaneScreenShot.png?raw=true" width=300>
      
   1. Imagine all the ways you could use your new custom component! Also, think
      of other custom components you could build by extending existing JavaFX
      components. We will explore more uses of the `ImageLoader` component in 
      class.

<hr/>

**Feedback?** 
Please help us make this better!
If you have any feedback or suggestions for this reading or tutorial, then use 
the link below to reach the feedback form.

[![Submit Feedback](https://img.shields.io/badge/-Submit&nbsp;Feedback-red.svg?style=for-the-badge)](https://docs.google.com/forms/d/e/1FAIpQLSfBgZM_-G-9nKmX7F83k0Tgp1OlqBnrkt6vsxlIqLypc_keUQ/viewform?usp=pp_url&entry.1081181680=cs1302-components&entry.1901270436=https://github.com/cs1302uga/cs1302-tutorials/blob/master/components/components.md)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
