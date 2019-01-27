package cs1302.interfaces.contract;

/**
 * This class represents the interface for an object that can
 * be encrypted and decrypted. The expectation is that the 
 * {@code toString()} method of an unencrypted object behaves
 * normally. Once an object is encrypted via {@link #encrypt()}, 
 * the {@code toString()}  method produces some encrypted string 
 * representation. In order to have the {@code toString()}
 * method revert to its normal behavior, the object must be
 * decrypted via {@link #decrypt()}. This imposes a state
 * requirement on the underlying object (i.e., encrypted or
 * unencrypted) that is left to the implementation. 
 * 
 * <p>
 * This code example is adapted from Java Foundations 4th Edition
 * under academic fair use.
 *
 * @version 2.0
 * @see <a href="https://www.pearson.com/us/higher-education/program/Lewis-Java-Foundations-Introduction-to-Program-Design-and-Data-Structures-4th-Edition/PGM76634.html"> Java Foundations </a>
 */
public interface Encryptable {

    /**
     * Represents the state of an {@code Encryptable} object.
     */
    /*
    enum State {
        ENCRYPTED, 
        UNENCRYPTED, 
        UNKNOWN
    };*/
    
    /**
     * Encrypts the object. This method has no effect if the 
     * object is already encrypted.
     */
    public void encrypt();

    /**
     * Decrypts the object. This method has no effect if the 
     * object not currently encrypted.
     *
     */
    public void decrypt();

    /**
     * Returns the state of this {@code Encryptable} object.
     * Implementations of this interface prior to version 2.0
     * will return {@code UNKNOWN}.
     * @since 2.0
     * @return the state.
     */
    //public Encryptable.State getState();
    
} // Encryptable
