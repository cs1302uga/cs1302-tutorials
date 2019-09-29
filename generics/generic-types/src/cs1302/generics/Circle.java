package cs1302.generics;

/**
 * A circle is an {@link Ellipse} where both focul points are in the same location. It therefore
 * defines a curve in a plane surrounding a single focul point, such that the distance to the focul
 * point is constant for every point on the curve. The line through the focul point is called the
 * diameter. Half of this length is called the radius, denoting the distance from the focul
 * point to any point on the curve.
 */
public class Circle extends Ellipse {

    /**
     * Constructs a {@link Circle} object with the specified radius length.
     *
     * @param radius the radius of
     */
    public Circle(double radius) {
        /* A circle is the same as an ellipse where the semi-major and semi-minor axis lengths are
         * equal.
         */
        super(radius, radius);
        setName("Circle");
    } // Circle

    /**
     * Returns the exact perimeter length of this {@link Circle}. This method is implemented by
     * calling the {@link #getCircumference()} method of this {@code Circle}.
     *
     * @return the perimeter of this {@code Circle}
     */
    @Override
    public double getPerimeter() {
        return getCircumference();
    } // getPerimeter

    /**
     * Returns the circumference of this {@code Circle}.
     *
     * @return the circumference of this {@code Circle}
     */
    public double getCircumference() {
        return Math.PI * getDiameter();
    } // getCircumference

    /**
     * Returns the diameter of this {@code Circle}.
     *
     * @return the diameter of this {@code Circle}
     */
    public double getDiameter() {
        return 2.0 * getRadius();
    } // getDiameter


    /**
     * Returns the radius of this {@code Circle}.
     *
     * @return the radius of this {@code Circle}
     */
    public double getRadius() {
        /* The radius value of a circle is the same as its  semi-major axis length.
         */
        return getSemiMajorAxisLength();
    } // getRadius

} // Circle
