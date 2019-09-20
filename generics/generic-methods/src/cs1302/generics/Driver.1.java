package cs1302.generics;

import java.io.File;
import java.io.PrintStream;

public class Driver {

    public static void main(String[] args) {

        String someName = null;
        PrintStream someStream = null;
        File someFile = new File(".");

        // ...

        String name;
        if (someName == null) {
            name = "Bob";
        } else {
            name = someName;
        } // if

        PrintStream out;
        if (someStream == null) {
            out = System.out;
        } else {
            out = someStream;
        } // if

        File myFile;
        if (someFile == null) {
            myFile = new File(".");
        } else {
            myFile = someFile;
        } // if

        // ...

        System.out.println("Looks like this runs well...");

    } // main

} // Driver
