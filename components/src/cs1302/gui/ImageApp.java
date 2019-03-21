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

    Stage stage;
    Scene scene;

    /** The root container for the application scene graph */
    VBox vbox;

    /** The container for the url textfield and the load image button */
    HBox urlLayer;
    TextField urlField;
    Button loadImage;

    /** The container for the loaded image */
    ImageView imgView;

    /** A default image which loads when the application starts */
    private static final String DEFAULT_IMG =
        "http://cobweb.cs.uga.edu/~mec/cs1302/gui/default.png";

    /** Default height and width for Images */
    private static final int DEF_HEIGHT = 500;
    private static final int DEF_WIDTH = 500;

    /**
     * The entry point for our image viewer application.
     *
     * @param stage A reference to the stage object (window) created by the system.
     */ 
    public void start(Stage stage) {
        this.stage = stage;
        
        // Initializing the nodes for the scene graph
        vbox = new VBox();
        urlLayer = new HBox(10);
        urlField = new TextField("https://");
        loadImage = new Button("Load");

        // Adding the textfield and load image button the the containing hbox
        urlLayer.getChildren().addAll(urlField, loadImage);

        // Sets the textfield to grow, as necessary, to fill the hbox
        HBox.setHgrow(urlField, Priority.ALWAYS);

        // Load the default image with the default dimensions
        Image img = new Image(DEFAULT_IMG, DEF_HEIGHT, DEF_WIDTH, false, false);

        // Add the image to its container and preserve the aspect ratio if resized
        imgView = new ImageView(img);
        imgView.setPreserveRatio(true);

        // EventHandler for our button using a fancy method reference.
        //EventHandler<ActionEvent> loadImgHandler = this::loadImage;
        loadImage.setOnAction(this::loadImage);

        // Add the hbox and imageview to the containing vbox and set the vbox
        // to be the root of the scene
        vbox.getChildren().addAll(urlLayer, imgView);
        scene = new Scene(vbox);

        // Set up the stage and set it to be visible
        stage.setResizable(false);
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
            Image newImg = new Image(urlField.getText(), DEF_HEIGHT, DEF_WIDTH, false, false);
            imgView.setImage(newImg);
        } catch(IllegalArgumentException iae) {
            System.out.println("The supplied URL is invalid");
        } // try
        
    } // loadImage
    
} // ImageApp

