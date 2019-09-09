package cs1302.util;

import java.io.PrintStream;

/**
 * Helper utility class.
 */
public class Helper {

    /**
     * Calls {@code out.println(arg)} for each {@code arg} in {@code args}.
     * @param out   desired output stream
     * @param args  arguments to print
     */
    public static void printlns(PrintStream out, String[] args) {
        for (String arg : args) {
            out.println(arg);
        } // for
    } // printlns

} // Helper
