package cs1302.generics;

import java.io.File;
import java.io.PrintStream;

/**
 * Driver class for cool generic method
 */
public class Driver {

    /**
     * Gets a value from a variable if not {@code null} or a default value.
     *
     * @param value         value to potentially get
     * @param defaultValue  default value to use
     * @return {@code value} if not {@code null}; {@code defaultValue} otherwise
     */
    private static <ValueType> ValueType get(ValueType value, ValueType defaultValue) {
        if (value == null) {
            return defaultValue;
        } else {
            return value;
        } // if
    } // get

    public static void main(String[] args) {

        String someName = null;
        PrintStream someStream = null;
        File someFile = new File(".");

        // ...

        String name = get(someName, "Bob");
        PrintStream out = get(someStream, System.out);
        File myFile = get(someFile, new File("/"));

        // ...

        System.out.println("Looks like this runs well...");

    } // main

} // Driver
