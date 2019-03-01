package cs1302.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Represents a small example app written using JavaFX.
 */
public class ExampleApp extends Application {

    @Override
    public void start(Stage stage) {

	Text hello = new Text("Hello World!!!");

	HBox root = new HBox();
	root.getChildren().add(hello);

	Scene scene = new Scene(root);

	stage.setScene(scene);
	stage.sizeToScene();
	stage.setTitle("ExampleApp!");
	stage.show();
	
    } // main

} // ExampleApp

