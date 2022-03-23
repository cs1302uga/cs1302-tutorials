# JavaFX 17 Bookmarks and Notes

## Packages of Interest

   * [javafx](https://openjfx.io/javadoc/17/)
   
## RE: App Class

   * [javafx.application](https://openjfx.io/javadoc/17/javafx.graphics/javafx/application/package-summary.html) - (package) Provides the application life-cycle classes.
   * [javafx.application.Application](https://openjfx.io/javadoc/17/javafx.graphics/javafx/application/Application.html) - (class) Base class from which JavaFX 
     application driver classes should extend.
   * [javafx.application.Application.launch](https://openjfx.io/javadoc/17/javafx.graphics/javafx/application/Application.html#launch(java.lang.Class,java.lang.String...)) - 
     (method) Used to start the aplication life-cycle for your application. It is typically called from the main method.
   * [javafx.application.Platform](https://openjfx.io/javadoc/17/javafx.graphics/javafx/application/Platform.html) - (class) Provides utility 
     methods to help ensure the application life-cycle.
   * [javafx.stage](https://openjfx.io/javadoc/17/javafx.graphics/javafx/stage/package-summary.html) - (package) Provides the top-level container 
     (i.e., "window") classes for JavaFX content.
   * [javafx.stage.Stage](https://openjfx.io/javadoc/17/javafx.graphics/javafx/stage/Stage.html) - (class) The top level JavaFX container.
   * [javafx.scene.control.Alert](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Alert.html) - (class) A [dialog box](https://en.wikipedia.org/wiki/Dialog_box) that has one of [these](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Alert.AlertType.html) types.
   
## RE: Scene Graph
   
   * [javafx.scene](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/package-summary.html) - (package) Provides 
     the core set of classes for the JavaFX Scene Graph API.
   * [javafx.scene.Scene](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/Scene.html) - (class) The container for all content in a scene graph.
   * [javafx.scene.Node](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/Node.html) - (class) Base class for scene graph nodes (e.g., components, etc.).
   * [javafx.scene.Group](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/Group.html) - (class) Light-weight, non-resizable container used for absolute positioning.
   
## RE: Layout Panes

   * [javafx.scene.layout](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/layout/package-summary.html) - (package)
   * [javafx.scene.layout.Pane](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/layout/Pane.html) - (class) Base class for layout panes. 
     By itself, Pane is similar to javafx.scene.Group, except that it is resizable.
   * [javafx.scene.layout.HBox](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/layout/HBox.html) - (class) Lays out its children in a single horizontal row.
   * [javafx.scene.layout.VBox](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/layout/VBox.html) - (class) Lays out its children in a single vertical column.
   * [javafx.scene.layout.BorderPane](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/layout/BorderPane.html) - (class) Lays out children in top, left, 
     right, bottom, and center positions.
   * [javafx.scene.layout.TilePane](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/layout/TilePane.html) - lays out its children in a grid of uniformly sized "tiles".
   * [javafx.geometry.Insets](https://openjfx.io/javadoc/17/javafx.graphics/javafx/geometry/Insets.html) - (class) Used to describe a set of inside offsets 
     for the 4 side of a rectangular area. Often used with the setPadding method.
   * [javafx.scene.control.ToolBar](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/ToolBar.html) - (class) displays children horizontally (by default) or vertically
   
## RE: Controls

   * [javafx.scene.control](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/package-summary.html) - (package)
   * [javafx.scene.control.Control](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Control.html) - (class) Base class for all user interface controls.
   * [javafx.scene.control.Button](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Button.html) - (class) A simple button control.
   * [javafx.scene.control.Label](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Label.html) - (class) Label is a non-editable text control.
   * [javafx.scene.control.ProgressBar](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/ProgressBar.html) - (class) A horizontal progress bar. 
   * [javafx.scene.text.Text](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/text/Text.html) - (class) Defines a node that displays text.
   * [javafx.scene.control.Separator](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Separator.html) - (class)  horizontal or vertical separator line, often included as a child in a `Toolbar`
   * [javafx.scene.control.TextField](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/TextField.html) - (class) Text input component.
   
## RE: Event Handling

   * [javafx.event](https://openjfx.io/javadoc/17/javafx.base/javafx/event/package-summary.html) - (package) Provides basic framework for FX events, their delivery and handling.
   * [javafx.event.Event](https://openjfx.io/javadoc/17/javafx.base/javafx/event/Event.html) - (class) Base class for FX events.
   * [javafx.event.ActionEvent](https://openjfx.io/javadoc/17/javafx.base/javafx/event/ActionEvent.html) - (class) An Event representing some type of action.
   * [javafx.event.EventHandler<T extends Event>](https://openjfx.io/javadoc/17/javafx.base/javafx/event/EventHandler.html) - (interface) Handler 
     for events of a specific class / type.
  
## RE: Menus

   * [javafx.scene.control.MenuBar](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/MenuBar.html) - (class) A MenuBar control 
     traditionally is placed at the very top of the user interface, and embedded within it are Menu objects.
   * [javafx.scene.control.Menu](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Menu.html) - (class) A popup menu of actionable 
     items which is displayed to the user only upon request.
   * [javafx.scene.control.MenuItem](https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/MenuItem.html) - (class) MenuItem 
     is intended to be used in conjunction with Menu to provide options to users.

## RE: Images

   * [javafx.scene.image](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/image/package-summary.html) - (package) Provides the 
     set of classes for loading and displaying images.
   * [javafx.scene.image.Image](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/image/Image.html) - (class) Represents image 
     data. It does not display the image itself.
   * [javafx.scene.image.ImageView](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/image/ImageView.html) - (class) Represents 
     a Node used to display an image loaded with the Image class.

## RE: Styling and CSS
   
   * [setStyle(String)](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/Node.html#setStyle(java.lang.String)) - (method) Sets 
     CSS style associated with a specific Node.
   * [JavaFX CSS Reference Guide](https://openjfx.io/javadoc/17/javafx.graphics/javafx/scene/doc-files/cssref.html)
   * [Styling UI Controls with CSS](https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/apply-css.htm)
   
### Including a CSS file

Let's assume you have a Cascading Style Sheet (CSS) file located at `resources/theme-uga.css`. In order for 
the style declarations in that file to affect the nodes in a scene, the file needs to be added to a scene's 
collection of stylesheets:

```java
String stylesheet = // replace-me; see below
scene.getStylesheets().add(stylesheet);
```

Below, we describe two portable ways to refer to `resources/theme-uga.css` using the `stylesheet` variable 
in the example. Please pay attention to the subtle differences between each approach.

* If `resources` is in your top-level project directory -- for a Maven-based project, this means that 
  `resources` **is directly inside** the same directory as the project's `pom.xml` file, then you can 
  use `"file:resources/theme-uga.css"` to refer to `theme-uga.css`:
  
  ```java
  String stylesheet = "file:resources/theme-uga.css";
  scene.getStylesheets().add(stylesheet);
  ```
  
  **PRO:** This will work with or without Maven.
  
  **CON:** This will only work if the program is run in the directory immediately above 
  `resources`. If you try to run the program from some other location, then Java may not be 
  able to find the stylesheet. To remedy this, you will need to add the path to your top-level
  project directory to the class path (e.g., using `-cp`). 
  
* If `resources` is in `src/main` (i.e., it's relative path in your top-level project directory
  is `src/main/resources`), then you can use 
  `getClass().getClassLoader().getResource("theme-uga.css").toExternalForm()` to refer 
  to `theme-uga.css`:
  
  ```java
  String stylesheet = getClass().getClassLoader().getResource("theme-uga.css").toExternalForm();
  scene.getStylesheets().add(stylesheet);
  ```
  
  **PRO:** This will work even when packaged into a JAR file.
  
  **CON:** It's clunky. While Maven will add `src/main/resources` to the class path, you may need
  to manuall add that directory to the class path if running without Maven.
 

The next example shows what `theme-uga.css` might look like. It declares some reusable color variables in the `.root`
declaration, then it declares that all rendered `Rectangle` objects should have their fill color set to 
[Lake Herrick blue](https://brand.uga.edu/visual-style/#color).

```
.root {
    -uga-arch-black: #000000;
    -uga-bulldog-red: #ba0c2f;
    -uga-chapel-bell-white: #ffffff;
    -uga-glory-glory: #e4002b;
    -uga-lake-herrick: #00a3ad;
}

Rectangle {
    -fx-fill: -uga-lake-herrick;
}
```

**NOTE:** The base stylesheet that JavaFX automatically loads is called `modena.css`;
you can view it's contents 
[here](https://github.com/openjdk/jfx/blob/22d4343fe8563c2931910b98e8f18c6fd4a48f05/modules/javafx.controls/src/main/resources/com/sun/javafx/scene/control/skin/modena/modena.css).

## RE: Timelines

   * [`Timeline`](https://openjfx.io/javadoc/17/javafx.graphics/javafx/animation/Timeline.html) 
   * [`KeyFrame`](https://openjfx.io/javadoc/17/javafx.graphics/javafx/animation/KeyFrame.html) 

### Brief Timeline Explanation

A `Timeline` object can be used to execute blocks of code or change values (or both) sequentially at specific times and, 
if configured to do so, repeat the execution of that sequence one or more times (or indefinitely). 
It has [three states](https://openjfx.io/javadoc/17/javafx.graphics/javafx/animation/Animation.Status.html):
`RUNNING`, `PAUSED`, and `STOPPED`; and it also has a list of `KeyFrame` objects, each of which has
an associated *duration* specified by its `time` property. 

When a `Timeline` object is running (e.g., after calling its `play()` method), it generates
`ActionEvent` objects for each `KeyFrame`; the generation of these events triggers the sequential 
execution of any associated event handlers on the JavaFX Application Thread (JFXAT).

A `KeyFrame` may have a user-defined event handler or a *key value* (or both). 
It's common to see code in the wild that changes the value of a 
[JavaFX property](https://openjfx.io/javadoc/17/javafx.base/javafx/beans/property/package-summary.html)
object based on the key values associated with key frames in a `Timeline`.

### Timeline Example

Here is an example that prints the current time (using 
[`LocalTime`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/LocalTime.html)) to 
standard output every two (2) seconds (specified using
[`Duration`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/Duration.html)), 
indefinitely:

```java
EventHandler<ActionEvent> handler = event -> System.out.println(LocalTime.now());
KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), handler);
Timeline timeline = new Timeline();
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.getKeyFrames().add(keyFrame);
timeline.play(); // change to RUNNING
```
The `Timeline` object also hase a `pause` method to pause the execution of the timeline.
Remember, JavaFX event handlers are executed on the JavaFX Application Thread (JFXAT).
   
<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
