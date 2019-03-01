package cs1302.gui;

import javafx.application.Application;

public class ExampleDriver {

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

