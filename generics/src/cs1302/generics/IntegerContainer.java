package cs1302.generics;

import cs1302.Utility;

/**
 * Represents a container for {@code Integer} objects. {@code IntegerContainer}
 * objects cannot store {@code null} values.
 */
public class IntegerContainer {

    /** The contents of this container. */
    private Integer contents;

    /**
     * Constructs a {@code cs1302.generics.IntegerContainer} object
     * with the specified {@code Integer}. The specified value cannot
     * be {@code null}.
     *
     * @param contents the contents for the {@code IntegerContainer}
     * @throws NullPointerException if the contents are null.
     */
    public IntegerContainer(Integer contents) {
	set(contents);
    } // IntegerContainer

    /**
     * Modifies the contents of this {@code IntegerContainer} object.
     * The specified value cannot be {@code null}.
     *
     * @param contents the contents for the {@code IntegerContainer}
     * @throws NullPointerException if the contents are null.
     */
    public void set(Integer contents) {
	Utility.nullCheck("IntegerContainer: set", contents);
	this.contents = contents;
    } // set

    /**
     * Returns the contents of this {@code IntegerContainer} object.
     */
    public Integer get() {
	return contents;
    } // get

} // Container
