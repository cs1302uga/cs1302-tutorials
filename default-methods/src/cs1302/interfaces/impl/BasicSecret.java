package cs1302.interfaces.impl;

import java.util.Random;
import cs1302.interfaces.contract.Encryptable;

/**
 * This class representes a <i>basic secret</i> message. The encryption algorithm is very weak.
 * <p>
 * This code example is adapted from Java Foundations 4th Edition
 * under academic fair use.
 *
 * @see <a href="https://www.pearson.com/us/higher-education/program/Lewis-Java-Foundations-Introduction-to-Program-Design-and-Data-Structures-4th-Edition/PGM76634.html"> Java Foundations </a>
 *
 */
public class BasicSecret implements Encryptable {

    private String message;
    private boolean encrypted;
  
    /**
     * Constructs a new {@code BasicSecret} object.
     * {@code Secret} objects are initially unencrypted.
     * @param msg the message to store in the object.
     */
    public BasicSecret(String msg) {
	message = msg;
	encrypted = false;
    } // Secret

    /**
     * Encrypts this {@code BasicSecret} object by shifting the ordinal value
     * of each character forward by one.
     * <p>
     * <b>Interface documentation:</b><br> 
     * {@inheritDoc}
     *
     */
    public void encrypt() {
	encrypted = true;
    } // encrypt

    public void decrypt() {
	encrypted = false;
    } // decrypt

    /**
     * Returns a {@code String} representation of this {@code BasicSecret}
     * object, consistent with {@link cs1302.interfaces.contract.Encryptable}.
     * @return the {@code String} value of this object
     */
    public String toString() {
	String content = "";	
	if(encrypted) {
	    //shifts each character in the message by the shift amount
	    for(int index = 0; index < message.length(); index++) {
		content += (char)(message.charAt(index) + 1);
	    } // for
	} else {
	    content = message;
	} // if
	return String.format("Secret(%s)", content);
    } // toString
    
} // Secret
