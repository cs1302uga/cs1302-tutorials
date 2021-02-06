package cs1302.models;

/**
 * A collection of methods that should only be used from within the
 * {@link cs1302.models} package.
 */
class Utility {

    /**
     * Sole constructor. It's private because we never intend for it to be
     * invoked.
     */
    private Utility() {}

    /**
     * Return the lesser of two numbers.
     * @param a  the first number
     * @param b  the second number
     * @return the lesser of {@code a} and {@code b}
     */
    public static double min(double a, double b) {
        if (a < b) {
            return a;
        } else {
            return b;
        } // if
    } // min

} // Utility
