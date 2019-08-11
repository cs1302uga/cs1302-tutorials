package cs1302.interfaces;

import cs1302.interfaces.contract.Styleable;
import cs1302.interfaces.impl.Fancy;
import cs1302.interfaces.impl.SuperFancy;
import cs1302.interfaces.impl.BasicFancy;

/** 
 * This class contains some test code for the {@link Styleable}
 * interface and some of its implementing classes.
 */
public class StyleDriver {

    /**
     * Runs a {@code style()} and {@code unstyle()} test on the 
     * given {@link Styleable} object.
     * @param testName the name of the test
     * @param s        the given {@link Styleable}
     */
    public static void test(String testName, Styleable s) {
        System.out.printf("# %s Test\n", testName);
        System.out.println(s);
        s.style();
        System.out.println(s);
        s.unstyle();
        System.out.println(s);
    } // test

    /**
     * The entry point into the test application.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        Styleable message;

        message = new Fancy("important message");
        test("Fancy", message);

        message = new SuperFancy("important message");
        test("Super Fancy", message);

        message = new BasicFancy("important message");
        test("Basic Fancy", message);
	
    } // main

} // SecretDriver

