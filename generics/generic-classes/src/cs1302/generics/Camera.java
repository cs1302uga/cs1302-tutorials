package cs1302.generics;

/**
 * A simple class representing a camera. Each {@code Camera}
 * object has a corresponding resolution measured in megapixels (MP).
 */
public class Camera {

    /** The resolution of the camera. Measured in MP.*/
    private double resolution;

    /**
     * Constructs a {@code Camera} object with the specified
     * resolution.
     *
     * @param resolution the specified resolution of the camera.
     */
    public Camera(double resolution) {
        setResolution(resolution);
    } // Camera

    /**
     * Returns the resolution of this {@code Camera} object.
     *
     * @return the resolution of this {@code Camera} object.
     */
    public double getResolution() {
        return resolution;
    } // getResolution

    /**
     * Sets the resolution of this {@code Camera} object. If the specified
     * resolution is less than 1, the resolution will default to {@code 1.0}.
     *
     * @param resolution the new resolution of this {@code Camera}.
     */
    public void setResolution(double resolution) {
        if (resolution >= 1.0) {
            this.resolution = resolution;
        } else {
            resolution = 1.0;
        } // if
    } // setResolution

} // Camera
