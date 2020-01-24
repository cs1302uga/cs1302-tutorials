package cs1302.interfaces.contract;

/**
 * This class represents the interface for an object that can
 * be styled and unstyled. The expectation is that the 
 * {@code toString()} method of a styled object behaves
 * normally. Once an object is styled via {@link #style()}, 
 * the {@code toString()} method produces some styled string 
 * representation. In order to have the {@code toString()}
 * method revert to its normal behavior, the object must be
 * unstyled via {@link #unstyle()}. This imposes a state
 * requirement on the underlying object (i.e., styled or
 * unstyled) that is left to the implementation. 
 * 
 * @version 2.0
 */
public interface Styleable {

    /**
     * Represents the state of a {@code Styleable} object.
     */
    
    enum State {
        /** Indicates that this object is currently styled. */
        STYLED,
        /** Indicates that this object is currently unstyled. */
        UNSTYLED,
        /** The value is not set. This serves as a default value. */
        UNKNOWN
    };
    
    /**
     * Styles the object. This method has no effect if the 
     * object is already styled.
     */
    public void style();

    /**
     * Unstyles the object. This method has no effect if the
     * object is not currently styled.
     */
    public void unstyle();

    /**
     * Returns the state of this {@code Styleable} object.
     * Implementations of this interface prior to version 2.0
     * will return {@code UNKNOWN}.
     * @since 2.0
     * @return the state of the object.
     */
    //public Styleable.State getState();
} // Styleable
