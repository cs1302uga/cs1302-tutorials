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
 */
public interface Styleable {

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

} // Styleable
