package cs1302.exceptions;

import java.util.Scanner;

/**
 * A class containing two methods to read a series of floating point numbers
 * from the user and sum up the values. The two methods handle invalid input
 * in different ways. The first method attempts to avoid exceptions
 * altogether and the second handles exceptions when they occur.
 *
 */
public class DoubleSum {

    private static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        System.out.println(loopInputSum());
    } // main

    /**
     * Preemptively checking for invalid input. This is error-prone and it forces
     * the computer to duplicate the work as parseDouble() is validating the input
     * internally.
     *
     * @return the sum of the values
     */
    public static double loopInputSum() {

        double sum = 0.0;
        // Create a Scanner to parse each token of the user's input
        Scanner inputParser;// = new Scanner(s);
        boolean valid = true;

        System.out.println("Enter positive floating point numbers separated by a space");
        String userInput = input.nextLine();

        do {
            // The inputParser gets its values from the userInput String - not the keyboard.
            inputParser = new Scanner(userInput);
            valid = true;
            //Validate the user input
            while (inputParser.hasNext()) {
                // Store the characters up to the next space in variable token
                String token = inputParser.next();

                int decimalCount = 0;
                for (int i = 0; i < token.length(); i++) {
                    char cur = token.charAt(i);
                    if (!Character.isDigit(cur)) {
                        if ((cur == '.')) {
                            if (decimalCount == 0) {
                                decimalCount++;
                            } else {
                                valid = false;
                            } // if
                        } else {
                            valid = false;
                        } // if
                    } // if
                } // for
                if (valid) {
                    //The token is valid. We can safely turn it into a double.
                    sum += Double.parseDouble(token);
                } else {
                    sum = 0.0;
                    System.out.println("Input error. Try again");
                    userInput = input.nextLine();
                } // if
            } // while
        } while (!valid);

        // All tokens were found to be valid
        return sum;

    } // doubleSum

    /**
     * Using exception handing to check for invalid input. This approach
     * is cleaner and handles more input errors. Also, the computer doesn't
     * have to duplicate work.
     *
     * @return the sum of the values
     */
    public static double exceptionInputSum() {

        double sum = 0.0;
        boolean valid = true;
        System.out.println("Enter positive floating point numbers separated by a space");
        String userInput = input.nextLine();

        // Create a Scanner to parse each token of the user's input
        Scanner inputParser;

        do {
            valid = true;
            inputParser = new Scanner(userInput);

            try {
                while (inputParser.hasNext()) {
                    String curToken = inputParser.next();
                    double curVal = Double.parseDouble(curToken);
                    sum += curVal;
                } // while
            } catch (NumberFormatException nfe) {
                System.out.println("Input error. Try again");
                userInput = input.nextLine();
                valid = false;
                sum = 0.0;
            } // try

        } while (!valid);

        // Implement me with exception handling!
        return sum;

    } // validate

} // DoubleSum
