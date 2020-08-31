package cs1302.interfaces.impl;

import cs1302.interfaces.contract.Styleable;

/**
 * This class representes a <i>fancy</i> message.
 *
 */
public class Fancy implements Styleable {

    private String message;
    private boolean styled;

    /**
     * Constructs a new {@code Fancy} object with a specified
     * message.
     * {@code Fancy} objects are initially unstyled.
     * @param msg the message to store in the object.
     */
    public Fancy(String msg) {
        message = msg;
        styled = false;
    } // Fancy

    @Override
    public void style() {
        styled = true;
    } // style

    @Override
    public void unstyle() {
        styled = false;
    } // unstyle

    /**
     * Returns a {@code String} representation of this {@code Fancy}
     * object, consistent with {@link cs1302.interfaces.contract.Styleable}.
     * @return the {@code String} value of this object
     */
    public String toString() {
        String content;
        if (styled) {
            content = "*** " + message + " ***";
        } else {
            content = message;
        } // if
        return String.format("Fancy(%s)", content);
    } // toString
} // Fancy
