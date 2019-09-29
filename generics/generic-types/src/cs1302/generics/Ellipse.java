package cs1302.generics;

/**
 * An ellipse is a shape comprising of a curve in a plane surrounding two focal points such that
 * the sum of the distances to the two focal points is constant for every point on the curve. The
 * line through the focal points is called the major axis, and the line perpendicular to it through
 * the center is called the minor axis. If you halve each of these lengths, then they are known as
 * the semi-major axis and semi-minor axis, respectively.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Ellipse">Wikipedia: Ellipse</a>
 */
public class Ellipse extends Shape {

    /** Length of the semi-major axis. */
    private double a;

    /** Length of the semi-minor axis. */
    private double b;

    /**
     * Constructs an {@link Ellipse} object with the specified semi-major and semi-minor axis
     * lengths.
     *
     * @param a the length of the semi-major axis
     * @param b the length of the semi-minor axis
     */
    public Ellipse(double a, double b) {
        setName("Ellipse");
        this.a = a;
        this.b = b;
    } // Ellipse

    /**
     * Returns the length of the semi-major axis.
     *
     * @return the length of the semi-major axis.
     */
    public double getSemiMajorAxisLength() {
        return a;
    } // getSemiMajorAxisLength

    /**
     * Returns the length of the semi-minor axis.
     *
     * @return the length of the semi-minor axis.
     */
    public double getSemiMinorAxisLength() {
        return b;
    } // getSemiMinorAxisLength

    @Override
    public double getArea() {
        return Math.PI * a * b;
    } // getArea()

    /**
     * Return an approximation of the perimeter, computed using a Ramanujan approximation. As this
     * is an approximation, users of this method should not treat it as an exact perimeter value.
     *
     * @return an approximation of the perimeter
     * @see <a href="https://www.mathsisfun.com/geometry/ellipse-perimeter.html">Math is Fun: Perimeter of an Ellipse</a>
     */
    @Override
    public double getPerimeter() {
        double h = ((a - b) * (a - b)) / ((a + b) * (a + b));
        double p = Math.PI * (a + b) * (1.0 + ((3.0 * h) / (10 + Math.sqrt(4.0 - 3.0 * h))));
        return p;
    } // getPerimeter

} // Ellipse
