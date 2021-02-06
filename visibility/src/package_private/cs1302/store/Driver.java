package cs1302.store;

//import cs1302.models.Utility;
import cs1302.factory.Factory;

/**
 * Example store program.
 */
public class Driver {

    public static void main(String[] args) {
        Factory factory = new Factory();
        factory.requestChange("increase quantity"); // <---- LINE4
        factory.approveChange("increase quantity"); // <---- LINE5
        factory.denyChange("increase quantity"); // <------- LINE6
    } // main

} // Driver
