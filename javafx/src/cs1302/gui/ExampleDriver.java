package cs1302.gui;

import javafx.application.Application;

/**
 * Driver class for the {@link ExampleApp} class.
 */
public class ExampleDriver {

    /**
     * Main entry-point into the application.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
	try {
	    Application.launch(ExampleApp.class, args);
	} catch (Exception e) {
	    System.err.println("The exception listed below occurred. If it pertains to DISPLAY,");
	    System.err.println("then please logout, then log back in passing -XY to ssh.");
	    System.err.println(e);
	    System.exit(1);
	} // try
    } // main
    
} // ExampleDriver

