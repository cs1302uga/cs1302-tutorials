# JavaFX 11 Bookmarks and Notes

## Packages of Interest

   * [javafx](https://openjfx.io/javadoc/11/)
   
## RE: App Class

   * [javafx.application](https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/package-summary.html) - (package) Provides the application life-cycle classes.
   * [javafx.application.Application](https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Application.html) - (class) Base class from which JavaFX 
     application driver classes should extend.
   * [javafx.application.Application.launch](https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Application.html#launch(java.lang.Class,java.lang.String...)) - 
     (method) Used to start the aplication life-cycle for your application. It is typically called from the main method.
   * [javafx.application.Platform](https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Platform.html) - (class) Provides utility 
     methods to help ensure the application life-cycle.
   * [javafx.stage](https://openjfx.io/javadoc/11/javafx.graphics/javafx/stage/package-summary.html) - (package) Provides the top-level container 
     (i.e., "window") classes for JavaFX content.
   * [javafx.stage.Stage](https://openjfx.io/javadoc/11/javafx.graphics/javafx/stage/Stage.html) - (class) The top level JavaFX container. 
   
## RE: Scene Graph
   
   * [javafx.scene](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/package-summary.html) - (package) Provides 
     the core set of classes for the JavaFX Scene Graph API.
   * [javafx.scene.Scene](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/Scene.html) - (class) The container for all content in a scene graph.
   * [javafx.scene.Node](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/Node.html) - (class) Base class for scene graph nodes (e.g., components, etc.).
   * [javafx.scene.Group](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/Group.html) - (class) Light-weight, non-resizable container used for absolute positioning.
   
## RE: Layout Panes

   * [javafx.scene.layout](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/layout/package-summary.html) - (package)
   * [javafx.scene.layout.Pane](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/layout/Pane.html) - (class) Base class for layout panes. 
     By itself, Pane is similar to javafx.scene.Group, except that it is resizable.
   * [javafx.scene.layout.HBox](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/layout/HBox.html) - (class) Lays out its children in a single horizontal row.
   * [javafx.scene.layout.VBox](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/layout/VBox.html) - (class) Lays out its children in a single vertical column.
   * [javafx.scene.layout.BorderPane](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/layout/BorderPane.html) - (class) Lays out children in top, left, 
     right, bottom, and center positions.
   * [javafx.geometry.Insets](https://openjfx.io/javadoc/11/javafx.graphics/javafx/geometry/Insets.html) - (class) Used to describe a set of inside offsets 
     for the 4 side of a rectangular area. Often used with the setPadding method.
   
## RE: Controls

   * [javafx.scene.control](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/package-summary.html) - (package)
   * [javafx.scene.control.Control](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/Control.html) - (class) Base class for all user interface controls.
   * [javafx.scene.control.Button](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/Button.html) - (class) A simple button control.
   * [javafx.scene.control.Label](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/Label.html) - (class) Label is a non-editable text control.
   * [javafx.scene.text.Text](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/text/Text.html) - (class) Defines a node that displays text.
   * [javafx.scene.control.TextField](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/TextField.html) - (class) Text input component.
   
## RE: Event Handling

   * [javafx.event](https://openjfx.io/javadoc/11/javafx.base/javafx/event/package-summary.html) - (package) Provides basic framework for FX events, their delivery and handling.
   * [javafx.event.Event](https://openjfx.io/javadoc/11/javafx.base/javafx/event/Event.html) - (class) Base class for FX events.
   * [javafx.event.ActionEvent](https://openjfx.io/javadoc/11/javafx.base/javafx/event/ActionEvent.html) - (class) An Event representing some type of action.
   * [javafx.event.EventHandler<T extends Event>](https://openjfx.io/javadoc/11/javafx.base/javafx/event/EventHandler.html) - (interface) Handler 
     for events of a specific class / type.
  
## RE: Menus

   * [javafx.scene.control.MenuBar](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/MenuBar.html) - (class) A MenuBar control 
     traditionally is placed at the very top of the user interface, and embedded within it are Menu objects.
   * [javafx.scene.control.Menu](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/Menu.html) - (class) A popup menu of actionable 
     items which is displayed to the user only upon request.
   * [javafx.scene.control.MenuItem](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/MenuItem.html) - (class) MenuItem 
     is intended to be used in conjunction with Menu to provide options to users.

## RE: Images

   * [javafx.scene.image](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/image/package-summary.html) - (package) Provides the 
     set of classes for loading and displaying images.
   * [javafx.scene.image.Image](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/image/Image.html) - (class) Represents image 
     data. It does not display the image itself.
   * [javafx.scene.image.ImageView](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/image/ImageView.html) - (class) Represents 
     a Node used to display an image loaded with the Image class.

## RE: Styling and CSS
   
   * [setStyle(String)](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/Node.html#setStyle(java.lang.String)) - (method) Sets 
     CSS style associated with a specific Node.
   * [JavaFX CSS Reference Guide](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/doc-files/cssref.html)
   * [Styling UI Controls with CSS](https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/apply-css.htm)
   
<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
