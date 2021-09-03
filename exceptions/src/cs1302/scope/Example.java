package cs1302.scope;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Scoping example with try-catch blocks.
 */
public class Example {

    public static void main(String[] args) {

        try {
            String filename = args[0];
            File file = new File(filename);
        } catch (ArrayIndexOutOfBoundsException boundsException) {
            System.err.println("first command-line argument is missing");
        } // try

        try {
            PrintWriter output = new PrintWriter(file);
        } catch (FileNotFoundException notFoundException) {
            System.err.print("unable to open file for writing: ");
            System.err.println(notFoundException.getMessage());
        } // try

    } // main

} // Example
