
package cs1302.pushcounterfx;

import javafx.application.Application;
import javafx.stage.Stage;

public class PushCounterApp extends Application {

    public void start(Stage stage) {
	stage.setTitle("PushCounterFX!");
	stage.show();
    } // main

    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (Exception e) {
	    System.err.println(e);
	    System.err.println("Likely due to X11 timeout. Logout and log back in...");
	    System.exit(1);
	} // try
    } // main

} // PushCounterApp

