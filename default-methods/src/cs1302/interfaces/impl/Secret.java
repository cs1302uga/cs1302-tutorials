package cs1302.interfaces.impl;

import java.util.Random;
import cs1302.interfaces.contract.Encryptable;

/**
 * This class representes a <i>secret</i> message.
 * <p>
 * This code example is adapted from Java Foundations 4th Edition
 * under academic fair use.
 *
 * @see <a href="https://www.pearson.com/us/higher-education/program/Lewis-Java-Foundations-Introduction-to-Program-Design-and-Data-Structures-4th-Edition/PGM76634.html"> Java Foundations </a>
 *
 */
public class Secret implements Encryptable {

    private String message;
    private boolean encrypted;
    private int shift;
    private Random generator;

    /**
     * Constructs a new {@code Secret} object with a specified
     * message and establishes a value for the encryption shift.
     * {@code Secret} objects are initially unencrypted.
     * @param msg the message to store in the object.
     */
    public Secret(String msg) {
	message = msg;
	encrypted = false;
	generator = new Random();
	shift = generator.nextInt(250) + 5;
    } // Secret

    /**
     * Encrypts this {@code Secret} object using a Caesar cipher.
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
     * Returns a {@code String} representation of this {@code Secret}
     * object, consistent with {@link cs1302.interfaces.contract.Encryptable}.
     * @return the {@code String} value of this object
     */
    public String toString() {
	String content = "";	
	if(encrypted) {
	    //shifts each character in the message by the shift amount
	    for(int index = 0; index < message.length(); index++) {
		content += (char)(message.charAt(index) + shift);
	    } // for
	} else {
	    content = message;
	} // if
	return String.format("Secret(%s)", content);
    } // toString
    
} // Secret
