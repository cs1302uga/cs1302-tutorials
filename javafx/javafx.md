# JavaFX Starter Tutorial

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
   
   Also, the `-X` and `-Y` options can be used individually without the other. See the manual
   page for `ssh` for more information.
   
1. Use the following command to download and execute a shell script that retrieves 
   the starter code for this tutorial and places it into a subdirectory 
   called `cs1302-javafx`:

   ```
   $ curl -s -L https://git.io/fhAR8 | bash
   ```
   
1. Change into the `cs1302-javafx` directory that was just created and look around. There should be
   multiple Java files contained within the directory structure. To see a listing of all of the 
   files under the `src` subdirectory, use the `find` command as follows:
   
   ```
   $ find src
   ```
   
1. Compile the source code, specifying `bin` as the default package for compiled code. You may
   need to create the `bin` directory.
   
1. Attempt to run the `cs1302.gui.ExampleDriver` class in the usual manner. If successful, then
   one of two things should happen:
   
   1. A small, but blank, GUI app will appear on your screen. This may appear in another
      desktop or minimized if your computer utilizes multiple windows. However, the following 
      error message will appear in the terminal:
      
      ```
      libGL error: No matching fbConfigs or visuals found
      libGL error: failed to load driver: swrast
      Prism-ES2 Error : GL_VERSION (major.minor) = 1.4
      ```
      
   1. A scary error message similar to the following will appear and no GUI app will appear 
      on your screen (don't panic):
      
      ```
      libGL error: No matching fbConfigs or visuals found
      libGL error: failed to load driver: swrast
      X Error of failed request:  BadValue (integer parameter out of range for operation)
        Major opcode of failed request:  149 (GLX)
        Minor opcode of failed request:  24 (X_GLXCreateNewContext)
        Value in failed request:  0x0
        Serial number of failed request:  23
        Current serial number in output stream:  24
      ```

1. The error message that displays is related to the JavaFX graphics renderer. By default, it
   attempts to perform hardware-accelerated rendering. However, we need to enable to software-based
   rendering in order for it to work nicely with X-forwarding. Close out of the small GUI app. Now, rerun 
   the driver but add `-Dprism.order=sw` in addition to the usualy options when executing the related `java`
   command.
   
1. If the small GUI app containing a nice message appears with no renderer errors, then you are okay to proceed!
   **Note**: You may have to wait a few seconds to see the message.

## High-Level Walkthrough 

1. In JavaFX, applications (or apps) are created by creating a class that extends the
   [`javafx.application.Application`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html)
   class and overriding the `start` method. In the starter code, inspect the class declaration inside of `ExampleApp.java`.
   We will explain the inside of the `start` method shortly.
   
1. Assuming you have a JavaFX app class (e.g., `ExampleApp`), the perferred way of launching an instance of the
   app is by using the static `Application.launch` inside of some driver class. In the starter code, inspect the
   `main` method inside of `ExampleDriver.java`.
   
   * The `try-catch` statement is included to ensure that if any exceptions propogate up to `main` method, then
     the user is presented with a nicer message than the usual stack trace. We also do this because one such
     exception relates to a timeout issue with the X-forwaring -- in this case, we want to inform the user that
     they can logout, then log back in to resolve the problem.
     
1. The [`launch`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html#launch-java.lang.Class-java.lang.String...-)
   launches the app by initiating the JavaFX application life-cycle, a sequence of steps that occur over the life
   of the launched application. The JavaFX runtime does the following, in order, whenever an application is launched:

   1. Constructs an instance of the specified `Application` subclass;
   1. Calls the app's [`init`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html#init--)
      method, which the programmer may have overidden;
   1. Creates a `Stage` object and calls the app's [`start`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html#start-javafx.stage.Stage-)
      method with a reference to that `Stage` object;
   1. Waits for the app to finish, which happens when either of the following occur:
      
      * the app calls [`Platform.exit`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html#exit--)
      * the last window has been closed and the `implicitExit` attribute on `Platform` is true -- this is the default.
    
   1. Calls the app's [`stop`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html#stop--) 
      method, which the programmer may have overidden;

## Mid-Level Walkthrough 

1. Now, let's go back to `ExampleApp.java`. Insepct the `start` method, which is required to be overridden in concrete
   subclasses of the `Application` class.
   
   1. This method is considered the main entry point for a JavaFX app. 
      The `start` method is called after the `init` method has returned, and after the system is ready for the 
      app to begin running.
      
   1. The method's only parameter is a reference to a `Stage` object. The JavaFX `Stage` class is the top level 
      JavaFX container (i.e., consider it to be the window). 
      The primary `Stage` is constructed by the platform during step (iii) of the JavaFX application life-cycle. 
      Additional `Stage` objects may be constructed by the application.
      
   1. By default, a stage is not visible. You must initialize it with a reference to a `Scene` object 
      before making it visible. You should refer to the API documentation for the 
      [`Stage`](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html) class about
      different properties a stage can have, including decorations and modality options.
   
   1. What is a `Scene` object? In JavaFX, a scene is the container for all content in a scene graph. The term
      "scene graph" is just a fancy term for a structured hierarchy of the nodes / components contained in a
      particular scene. We use the term "node" because all such objects have an upper bound of 
      [`javafx.scene.Node`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html).
   
      * There can only be one `Scene` on a `Stage` at a time, and a `Scene` can only be on one `Stage` at a time. 
      * You may swap scenes on a `Stage` at any time so long as the call to `setScene` is made on the 
        JavaFX Application Thread (more on this later).
      
      One reason that JavaFX exmploys the use of scenes instead of directly adding nodes / components to a stage is to
      enable easier swapping between modes of an app. For example, consider a video game with a title screen,
      main game screen, and an options screen. In this scenario, each "screen" might be its own scene.
	
   1. The scene graph for the `ExampleApp` might look like this:
      ```
      root: HBox
          |
      hello: Text
      ```
      The "root" of this scene graph is an object of the [`HBox`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/HBox.html)
      container class. This object has one child, an object of the [`Text`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/text/Text.html) 
      class. 

1. Before you continue, you should note what each line in the `start` method of `ExampleApp.java` is doing by
   referring to the corresponsing API documentation. This is something that you will be doing a lot while
   working with JavaFX (and most other libraries).
   
   The scene graph was given in the previous step. You might also find the overall containment hierarchy for
   `ExampleApp` helpful.  It would look something like this:
      ```
      stage: Stage
          |
      scene: Scene
          |
      root: HBox
          |
      hello: Text
      ```
      The "root" of the containment hierarchy is an object of the 
      [`Stage`](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html)
      container class. The `stage` object contains the 
      [`Scene`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html).
   
## Events and Event Handlers

In JavaFX, nodes can generate [`Event`](https://docs.oracle.com/javase/8/javafx/api/javafx/event/Event.html)
objects. This can happen automatically or in response to user input. Each event has a corresponding event handler,
i.e., a method that is registered to handle the event when its generated. Let's add a button to `ExampleApp`, then
register an event handler for one of the events the button might generate.

1. In `ExampleApp.java`, import the [`Button`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Button.html),
   then add the following line immediately after the line that creates the `Text` object:
   ```java
   Button button = new Button("Click me!");
   ```
   This creates a `Button` object, however, it will NOT automatically appear in the app's GUI!

1. To add the `Button` object to the GUI, you need to add it to the scene.
   Here, we will add the button as a child of the `HBox` object already contained in the scene graph.
   This can be accomplished by adding the following line immediately after the line
   that adds the `Text` object to the `HBox`:
   ```java
   root.getChildren().add(button);
   ```
   If you are adding multiple children to a node, you may prefer to use the variadic method `addAll`
   instead of `add` on the `ObservableList<Node>` returned by the `getChildren` method.
   
1. Compile `ExampleApp.java`, then rerun `ExampleDriver`. You should now see the button!
   
   * Move your mouse over the button. You should notice that the styling of the button changes automatically
     as your mouse cursor enters and leaves the visual area of the button (the change is very subtle. look closely). 
     Click on the button. Similarly, the styling of the button will change. As you interact with the button, 
     you are actually causing the button to generate events! These events have default ebent handlers that cause the 
     button's style to change.
     
1. When a user clicks on a button, that causes the button to generate an
   [`ActionEvent`](https://docs.oracle.com/javase/8/javafx/api/javafx/event/ActionEvent.html) object.
   You can register an event handler for this event by calling the `setOnAction` method. Here is the
   signature of the `setOnAction` method:
   ```java
   public final void setOnAction(EventHandler<ActionEvent> value)
   ```

1. As you can see, to call the `setOnAction` method, we will need to supply a reference to an `EventHandler<ActionEvent>`
   object. It turns out that [`EventHandler<T>`](https://docs.oracle.com/javase/8/javafx/api/javafx/event/EventHandler.html)
   is an interface. This gives us many options. It also turns out that this interface is a functional interface,
   which means that we can use a lambda expression. We point out that `EventHandler<T>` is an interface before 
   to remind you that you can always implement the interface the interface in the usual way instead of using a
   lambda expression. Let's create a handler that prints a message to standard output
   whenever the user clicks on the button.
   
   1. Let's implement the `EventHandler<ActionEvent>` using a lambda expresison. To do this, consult the API documentation
      for the interface and inspect its abstract `handle` method. The lambda expression will represent that method. 
      Import the relevant types, then add the following line directly below the line where you created the button:
      ```java
      EventHandler<ActionEvent> buttonHandler = event -> System.out.println("you clicked me!");
      ```
      
   1. The previous step only created the `EventHandler<ActionEvent>` object. Now we need to register it with
      the button using the `setOnAction` method. Add the following line directly below that line you added 
      in the previous step:
      ```java
      button.setOnAction(buttonHandler);
      ```
   
1. Compile `ExampleApp.java`, then rerun `ExampleDriver`. Click on the button! If you followed the steps correctly,
   then you should see the text `you clicked me!` appear in your terminal window.
   
1. **Congratulations!** You have successfully created your first interactive GUI app! The next step is to add more
   nodes / components to the scene graph, then register event handlers that cause changes in those components
   instead of merely printing a message to standard output. This will be explored in the class exercises and
   some of your projects.

## References

* [JavaFX 8 Bookmarks and Notes](http://cobweb.cs.uga.edu/~mec/cs1302/gui/)
* [JavaFX 8 API Documentation](https://docs.oracle.com/javase/8/javafx/api/overview-summary.html)

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
