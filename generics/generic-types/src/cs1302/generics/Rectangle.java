package cs1302.generics;

/**
 * A rectangle is a shape with four right angles.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Rectangle">Wikipedia: Rectangle</a>
 */
public class Rectangle extends Shape {

    /** Length of the {@code Rectangle} object */
    private double length;

    /** Width of the {@code Rectangle} object */
    private double width;

    /**
     * Constructs a {@link Rectangle} object with the specified
     * length and width.
     *
     * @param length the length of the rectangle
     * @param width the width of the rectangle
     */
    public Rectangle(double length, double width) {
        setName("Rectangle");
        this.length = length;
        this.width = width;
    } // Rectangle

    /**
     * Returns the length of this {@code Rectangle}
     *
     * @return the length of this {@code Rectangle}
     */
    public double getLength() {
        return length;
    } // getLength

    /**
     * Returns the width of this {@code Rectangle}.
     *
     * @return the width of this {@code Rectangle}
     */
    public double getWidth() {
        return width;
    } // getWidth

    @Override
    public double getArea() {
        return length * width;
    } // getArea()

    /**
     * Returns the perimeter of this {@code Rectangle}
     * @return the perimeter
     */
    @Override
    public double getPerimeter() {
        return 2 * length + 2 * width;
    } // getPerimeter

} // Rectangle
