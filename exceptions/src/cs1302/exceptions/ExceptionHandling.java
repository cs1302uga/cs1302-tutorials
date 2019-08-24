package cs1302.exceptions;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/**
 * A short example to demonstrate handling multiple exceptions on the same
 * block of code.
 */
public class ExceptionHandling {

    private static final String FILE = "numbers.txt";

    public static void main (String[] args) {

        try {
            File notesFile = new File(FILE);
            Scanner input = new Scanner(notesFile);
            System.out.println(input.nextDouble());
        } catch (NullPointerException npe) {
            System.out.println("The filename must be non-null");
        } catch (FileNotFoundException fnfe) {
            System.out.println("The file was not found");
        } catch (InputMismatchException ime) {
            System.out.println("The value read from the file is not a double");
        } // try
    } // main
} // Checked
