package cs1302.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;

/**
 * A basic JavaFX 8 program which takes a user specified URL and loads it
 * into an {@code ImageView}.
 *
 */
public class ImageApp extends Application {

    /** The root container for the application scene graph */
    VBox vbox;

    /** The container for the url textfield and the load image button */
    HBox urlLayer;
    TextField url;
    Button loadButton;

    /** The container for the loaded image */
    ImageView imgView;

    /** A default image which loads when the application starts */
    private static final String DEFAULT_IMG =
        "http://cobweb.cs.uga.edu/~mec/cs1302/gui/default.png";

    /** Default height and width for Images */
    private static final int DEF_HEIGHT = 500;
    private static final int DEF_WIDTH = 500;

    public ImageApp() {
        System.out.println("2) Creating an instance of the ImageApp Application");

        // Initialize the instance variables
        vbox = new VBox();
        urlLayer = new HBox(8);
        imgView = new ImageView();
        loadButton = new Button("Load");
        url = new TextField("https://");
    } // ImageApp

        @Override
    public void init() {
        System.out.println("3) Executing the init method");

        // Connect the components in the scene graph
        vbox.getChildren().addAll(urlLayer, imgView);
        urlLayer.getChildren().addAll(url, loadButton);
        HBox.setHgrow(url, Priority.ALWAYS);
        // load the default image
        Image defaultImage = new Image(DEFAULT_IMG);

        // add the image to the imageview
        imgView.setImage(defaultImage);

        // Create the handler for our button using an anonymous class.
        // This approach isn't used but it is left in so students can
        // compare with the lambda below.
        /*
        EventHandler<ActionEvent> mouseClickHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    this.loadImage(e);
                } // handle
            };
        */

        // Create the handler for our button using a lambda expression.
        // This is a more concise than using an anonymous class but it
        // accomplishes the same goal.
        EventHandler<ActionEvent> mouseClickHandler = (ActionEvent e) -> {
            this.loadImage(e);
        };

        loadButton.setOnAction(mouseClickHandler);
    } // init

    /**
     * The entry point for our image viewer application.
     *
     * @param stage A reference to the stage object (window) created by the system.
     */
    public void start(Stage stage) {
        System.out.println("4) Executing the start method");

        // Add the root of the scene graph to the stage.
        Scene scene = new Scene(vbox);

        // Set up the stage and set it to be visible
        stage.setScene(scene);
        stage.setTitle("1302 Image Viewer!");
        stage.sizeToScene();
        stage.show();
    } // start

    /**
     * Students will provide javadoc comments here.
     *
     * @param e source event
     */
    private void loadImage(ActionEvent e) {
        try {
            Image newImg = new Image(url.getText(), DEF_HEIGHT, DEF_WIDTH, false, false);
            imgView.setImage(newImg);
        } catch(IllegalArgumentException iae) {
            System.out.println("The supplied URL is invalid");
        } // try
    } // loadImage

    @Override
    public void stop() {
        // Won't be used by us. Typically used for application cleanup.

        System.out.println("6) Executing the stop method");
    } // stop

} // ImageApp
