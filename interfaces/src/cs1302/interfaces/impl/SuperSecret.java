package cs1302.interfaces.impl;

import java.util.Random;
import cs1302.interfaces.contract.Encryptable;

/**
 * This class representes a <i>super secret</i> message.
 * <p>
 * This code example is adapted from Java Foundations 4th Edition
 * under academic fair use.
 *
 * @see <a href="https://www.pearson.com/us/higher-education/program/Lewis-Java-Foundations-Introduction-to-Program-Design-and-Data-Structures-4th-Edition/PGM76634.html"> Java Foundations </a>
 *
 */
public class SuperSecret implements Encryptable {

    private String message;
    private boolean encrypted;
    private String shifts;
    private Random generator;

    /**
     * Constructs a new {@code SuperSecret} object with a specified
     * message and establishes a value for the encryption shift.
     * {@code SuperSecret} objects are initially unencrypted.
     * @param msg the message to store in the object.
     */
    public SuperSecret(String msg) {
	message   = msg;
	encrypted = false;
	generator = new Random();
	shifts    = "";
	int length = generator.nextInt(64) + 32;
	// generate random length sequence of shifts
	for (int i = 0; i < length; ++i) {
	    shifts += (char) (generator.nextInt(250) + 5);
	} // for
    } // SuperSecret

    /**
     * Encrypts this {@code SuperSecret} object using a random length,
     * rotating Caesar cipher.
     * <p>
     * <b>Interface documentation:</b><br> 
     * {@inheritDoc}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Caesar_cipher">
     * Caesar Cipher Wikipedia </a>
     */
    public void encrypt() {
	encrypted = true;
    } // encrypt

    public void decrypt() {
	encrypted = false;
    } // decrypt

    /**
     * Returns a {@code String} representation of this {@code SuperSecret}
     * object, consistent with {@link cs1302.interfaces.contract.Encryptable}.
     * @return the {@code String} value of this object
     */
    public String toString() {
	String content = "";
	if(encrypted) {
	    // shift each character in the message by the rotated shift amount
	    for(int index = 0; index < message.length(); index++) {
		int shift = shifts.charAt(index % shifts.length());
		content += (char) (message.charAt(index) + shift);
	    } // for
	} else {
	    content = message;
	} // if
	return String.format("SuperSecret(%s)", content);
    } // toString

    /**
     * Returns a {@code String} containing information about this object.
     * @return the about information
     */
    public String getAbout() {
	return String.format("SuperSecret utilizes a random length, " +
			     "rotating Caesar cipher on a string of " +
			     "length %d.", message.length());
    } // getAbout
    
} // SuperSecret


