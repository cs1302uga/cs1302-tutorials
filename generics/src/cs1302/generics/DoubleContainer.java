package cs1302.generics;

import cs1302.Utility;

/**
 * Represents a container for {@code Double} objects. {@code DoubleContainer}
 * objects cannot store {@code null} values.
 */
public class DoubleContainer {

    /** The contents of this container. */
    private Double contents;

    /**
     * Constructs a {@code cs1302.generics.DoubleContainer} object
     * with the specified {@code Double}. The specified value cannot
     * be {@code null}.
     *
     * @param contents the contents for the {@code DoubleContainer}
     * @throws NullPointerException if the contents are null.
     */
    public DoubleContainer(Double contents) {
	set(contents);
    } // DoubleContainer

    /**
     * Modifies the contents of this {@code DoubleContainer} object.
     * The specified value cannot be {@code null}.
     *
     * @param contents the contents for the {@code DoubleContainer}
     * @throws NullPointerException if the contents are null.
     */
    public void set(Double contents) {
	Utility.nullCheck("DoubleContainer: set", contents);
	this.contents = contents;
    } // set

    /**
     * Returns the contents of this {@code DoubleContainer} object.
     */
    public Double get() {
	return contents;
    } // get

} // Container
