package cs1302.generics;

/**
 * A simple class representing a flying drone. Each {@code Drone}
 * object has a maximum height that it can fly.
 */
public class Drone {

    /** The max height this drone can fly (in feet). */
    private double maxHeight;

    /**
     * Constructs a {@code Drone} object with the specified
     * {@code maxHeight} value.
     *
     * @param maxHeight the maximum height this {@code Drone} can fly.
     */
    public Drone(double maxHeight) {
        setHeight(maxHeight);
    } // Drone

    /**
     * Returns the maximum height (in feet) that this {@code Drone}
     * object can fly.
     * @return the max height this {@code Drone} can fly.
     */
    public double getHeight() {
        return maxHeight;
    } // getHeight

    /**
     * Sets the maximum height (in feet) that this {@code Drone}
     * object can fly. If the specified {@code newHeight} is negative,
     * the maximum height will default to {@code 0.0}.
     *
     * @param newHeight the max height the drone can fly (in feet).
     */
    public void setHeight(double newHeight) {
        if (newHeight > 0.0) {
            this.maxHeight = newHeight;
        } else {
            newHeight = 0.0;
        } // if
    } // newHeight

} // Drone
