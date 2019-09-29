package cs1302.generics;

import java.util.Arrays;

/**
 * Driver class for {@code cs1302-ce14}.
 */
public class Driver {

    public static void main(String[] args) {

        // SOME ARRAYS FOR TESTING
        
        Shape[] shapes = new Shape[] {
            new Circle(2.0),
            new Ellipse(3.2, 2.8),
            new Rectangle(Math.PI, 4.0)
        };

        Circle[] circles = new Circle[] {
            new Circle(4.5),
            new Circle(1.0),
            new Circle(2.3)
        };

        Rectangle[] rectangles = new Rectangle[] {
            new Square(2.0),
            new Rectangle(1.0, 4.0),
            new Square(1.41421356237),
            new Rectangle(2.0, 1.0)
        };

        // YOUR CODE DOWN HERE

    } // main

} // Driver
