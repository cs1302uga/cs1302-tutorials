package cs1302.interfaces.impl;

import cs1302.interfaces.contract.Styleable;

/**
 * This class representes a <i>basic fancy</i> message.
 *
 */
public class BasicFancy implements Styleable {

    private String message;
    private boolean styled;

    /**
     * Constructs a new {@code BasicFancy} object with a specified
     * message.
     * {@code BasicFancy} objects are initially unstyled.
     * @param msg the message to store in the object.
     */
    public BasicFancy(String msg) {
        message = msg;
        styled = false;
    } // BasicFancy

    public void style() {
        styled = true;
    } // style

    public void unstyle() {
        styled = false;
    } // unstyle

    /**
     * Returns a {@code String} representation of this {@code BasicFancy}
     * object, consistent with {@link cs1302.interfaces.contract.Styleable}.
     * @return the {@code String} value of this object
     */
    public String toString() {
        String content;
        if(styled) {
            //capitalize the first letter
            content = Character.toUpperCase(message.charAt(0)) + message.substring(1) ;
        } else {
            content = message;
        } // if
        return String.format("Basic Fancy(%s)", content);
    } // toString
} // Fancy
