package cs1302.generics;

import cs1302.Utility;

/**
 * Represents a container for {@code String} objects. {@code StringContainer}
 * objects cannot store {@code null} values.
 */
public class StringContainer {

    /** The contents of this container. */
    private String contents;

    /**
     * Constructs a {@code cs1302.generics.StringContainer} object
     * with the specified {@code String}. The specified value cannot
     * be {@code null}.
     *
     * @param contents the contents for the {@code StringContainer}
     * @throws NullPointerException if the contents are null.
     */
    public StringContainer(String contents) {
	set(contents);
    } // StringContainer

    /**
     * Modifies the contents of this {@code StringContainer} object.
     * The specified value cannot be {@code null}.
     *
     * @param contents the contents for the {@code StringContainer}
     * @throws NullPointerException if the contents are null.
     */
    public void set(String contents) {
	Utility.nullCheck("StringContainer: set", contents);
	this.contents = contents;
    } // set

    /**
     * Returns the contents of this {@code StringContainer} object.
     */
    public String get() {
	return contents;
    } // get

} // Container
