package cs1302.shapes;

/**
 * This class represents a circle object. Each {@code Circle} has an
 * associated {@code radius} value. Given a {@code Circle}, various
 * other values can be calculated such as diameter, area, and perimeter
 * via the {@link #getDiameter()}, {@link #getArea()}, and 
 * {@link #getPerimeter()} methods, respectively.
 *
 * <p>
 * A {@code Circle} object cannot have a negative {@code radius}. The
 * constructor and setter methods in this class ensure this condition
 * via exceptions.
 */
public class Circle {

    private double radius;

    /**
     * Constructs a {@code Circle} object with the given {@code radius}. 
     * The value for {@code radius} cannot be a negative number. 
     * @param radius the given radius value
     * @throws Exception if {@code radius} is negative
     */
    public Circle(double radius) throws Exception {
        checkRadius();
        this.radius = radius;
    } // Circle

    /**
     * Returns the radius of this {@code Circle}.
     * @return the radius
     */
    public double getRadius() {
        return radius;
    } // getRadius

    public void setRadius(double radius) throws Exception {
        checkRadius();
        return radius;
    } // getRadius

    public double getDiameter() {
        return 2.0 * radius;
    } // getDiameter

    public double getArea() {
        return Math.PI * radius * radius;
    } // getArea

    /**
     * Computes and returns the perimeter of this {@code Circle} based on
     * its computed diameter as returned by {@link #getDiameter()} method.
     * @return the computed perimeter
     */
    public double getPerimeter() {
        return Math.PI * getDiamter();
    } // getArea

    /**
     * Returns a string representation of this {@code Circle} object
     * in the following format: {@code Circle(radius = value)}.
     * @return a string representation of the object
     */
    public String toString() {
        return String.format("Circle(radius = %f)", radius);
    } // toString

    /**
     * Checks the value of the given {@code radius} and throws an
     * exception if the value is negative. The purpose of this method is
     * to help the {@code Circle} class provide consistency in
     * the way it generates the exception for negative {@code radius}
     * values.
     * @param radius the given radius value
     * @throws Exception if {@code radius} is negative
     */
    private void checkRadius(double radius) throws Exception {
        if (radius < 0) {
            throw new Exception("radius cannot be negative: " + radius);
        } // if
    } // checkRadius

} // Circle

