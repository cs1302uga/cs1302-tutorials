package cs1302.interfaces;

import cs1302.interfaces.contract.Encryptable;
import cs1302.interfaces.impl.Secret;
import cs1302.interfaces.impl.BasicSecret;
import cs1302.interfaces.impl.SuperSecret;

/** 
 * This class contains some test code for the {@link Encryptable}
 * interface and some of its implementing classes.
 */
public class SecretDriver {

    /**
     * Runs an {@code encrypt()} and {@code decrypt()} test on the 
     * given {@link Encryptable} object.
     * @param testName the name of the test
     * @param e        the given {@link Encryptable}
     */
    public static void test(String testName, Encryptable e) {
	System.out.printf("# %s Test\n", testName);
	System.out.println(e);
	e.encrypt();
	System.out.println(e);
	e.decrypt();
	System.out.println(e);
    } // test

    /**
     * The entry point into the test application.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

	Encryptable secret;

	secret = new Secret("Hello, world...");
	test("Secret", secret);

	secret = new SuperSecret("Hello, world!!!");
	test("SuperSecret", secret);

	secret = new BasicSecret("Hello, world?");
	test("BasicSecret", secret);
    } // main

} // SecretDriver

