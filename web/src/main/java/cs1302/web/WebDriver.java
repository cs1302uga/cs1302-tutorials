package cs1302.web;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javafx.application.Application;
import javafx.stage.Screen;

/**
 * Driver class for the {@code cs1302-web} tutorial.
 */
public class WebDriver {

    private static final String HRULE
        = "--------------------------------------------------------------------------------";

    /**
     * Entry point for program.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            Application.launch(WebApp.class, args);
        } catch (Exception ex) {
            WebDriver.printException(ex);
            System.exit(1);
        } // try
    } // main

    /**
     * Print an exception and its stack trace.
     * @param e the exception
     */
    private static void printException(Exception ex) {
        System.err.println(WebDriver.HRULE);
        System.err.println(ex);
        Optional.<Exception>of(ex)
            .filter(e -> e instanceof UnsupportedOperationException)
            .filter(e -> e.getMessage().contains("Unable to open DISPLAY"))
            .ifPresent(e -> WebDriver.printDisplayWarning());
        System.err.println(WebDriver.HRULE);
        ex.printStackTrace();
    } // printException

    /**
     * Print a message that warns the user about a {@code DISPLAY} issue.
     */
    private static void printDisplayWarning() {
        System.err.println(WebDriver.HRULE);
        String message = "WARNING: "
            + "If you are connected via SSH, then X-forwarding is either: \n"
            + " 1. not enabled for the current connection; or \n"
            + " 2. experiencing network issues (e.g., lag, timeout, etc.).";
        System.err.println(message);
        System.err.println(WebDriver.HRULE);
        System.err.println("RELATED ENVIRONMENT VARIABLES: ");
        List.of("DISPLAY", "SSH_CLIENT", "SSH_TTY", "SSH_CONNECTION")
            .stream()
            .map(key -> String.format(" - %s=%s", key, System.getenv(key)))
            .forEach(System.err::println);
    } // printDisplayWarning

} // WebDriver
