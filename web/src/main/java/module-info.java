/**
 * Provides the starter code for the <strong>cs1302-web</strong> tutorial.
 */
module cs1302.web {
    requires transitive java.logging;
    requires transitive java.net.http;
    requires transitive javafx.controls;
    requires transitive com.google.gson;
    opens cs1302.web;
} // module
