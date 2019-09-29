package cs1302.generics;

/**
 * A square is a rectangle with all sides having equal length.
 *
 */
public class Square extends Rectangle {
    /**
     * Constructs a {@link Square} object with the specified
     * length and width.
     *
     * @param sideLength the length of the sides of the {@code Square}
     */
    public Square(double sideLength) {
        super(sideLength, sideLength);
        setName("Square");
    } // Square
} // Square
