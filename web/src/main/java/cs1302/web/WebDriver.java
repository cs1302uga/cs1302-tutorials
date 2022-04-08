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
            Optional.<Exception>of(ex)
                .filter(e -> e instanceof UnsupportedOperationException)
                .filter(e -> e.getMessage().contains("Unable to open DISPLA"))
                .ifPresent(e -> WebDriver.printDisplayWarning());
            WebDriver.printException(ex);
            System.exit(1);
        } // try
    } // main

    /**
     * Print an exception and its stack trace.
     * @param e the exception
     */
    private static void printException(Exception e) {
        System.err.println(WebDriver.HRULE);
        System.err.println(e);
        System.err.println(WebDriver.HRULE);
        e.printStackTrace();
    } // printException

    /**
     * Print a message that warns the user about a {@code DISPLAY} issue.
     */
    private static void printDisplayWarning() {
        System.err.println(WebDriver.HRULE);
        String message = ""
            + "If you see SSH_ in the output below, then the you are "
            + "connected via SSH, but X-forwarding is either: "
            + "i) not enabled for the current connection; or "
            + "ii) experiencing network issues (e.g., lag, timeout, etc.).";
        System.err.println(message);
        System.err.println("Cannot connect to the display!");
        List.of("DISPLAY", "SSH_CLIENT", "SSH_TTY", "SSH_CONNECTION")
            .stream()
            .map(key -> String.format("%s=%s", key, System.getenv(key)))
            .forEach(System.err::println);
    } // printDisplayWarning

} // WebDriver
