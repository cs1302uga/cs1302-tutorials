package cs1302.generics;

/**
 * A driver class to test the generic shipping container implementation.
 */
public class Driver {
    public static void main(String[] args) {
        // Instantiate objects of type Drone and Camera.
        Drone myDrone = new Drone(400.0);
        Camera myCamera = new Camera(4.1);

        // Create a raw type shipping container containing our Drone.

        // Create parameterized type shipping container  containing our Drone and our Camera.
        // Shorten declarations using the Diamond Operator.

        // Get the contents of raw type container. Requires casting - may cause runtime exception.

        // Get contents of parameterized type containers. No casting - errors caught at runtime.
        // Generics allow stronger type checks at compile time.

    } // main

} // Driver
