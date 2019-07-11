package cs1302.exceptions;

import java.util.Scanner;
import java.util.InputMismatchException;

private static Scanner input;

/**
 *
 * Does not work with scientific notation.
 *
 *
 */
public class DoubleSum {
    public static void main(String[] args) {
        input = new Scanner(System.in);

        loopValidate();
        
    } // main


    public static void validate(String s) {
        // Create a Scanner to parse each token of the user's input
/*        Scanner inputParser = new Scanner(s);

        while(inputParser.hasNext()) {
            Double.parseDouble(inputParser.next());
        } // while
                while(!valid) {
            try{
                validate(userInput);
                valid = true;
            } catch(InputMismatchException ime) {
                System.out.println("Invalid input. Try again");
                userInput = input.nextLine();
            } // try
        } // while
*/
    } // validate
    
    /**
     * Preemptively checking for invalid input. This is error-prone and it's
     * the same work that the nextDouble() method would do internally.
     * Why do the same work twice and then potentially do it incorrectly when
     * we could just call nextDouble and catch any thrown exceptions.
     *
     * Don't be afraid of exceptions - know how to handle them when they occur.
     */
    public static double loopValidate() {
        double sum = 0.0;
        // Create a Scanner to parse each token of the user's input
        Scanner inputParser = new Scanner(s);
        boolean valid = true;

        System.out.println("Enter positive floating point numbers separated by a space");
        String userInput = input.nextLine();

        do {            
            //Validate the user input
            while(inputParser.hasNext()) {
                // Store the characters up to the next space in variable token
                String token = inputParser.next();

                int decimalCount = 0;
                for(int i = 0; i < token.length(); i++) {
                    char cur = token.charAt(i);
                    if(!Character.isDigit(cur)) {
                        if((cur == '.')) {
                            if(decimalCount == 0) {
                                decimalCount++;
                            } else {
                                valid = false;
                            } // if
                        } else {
                            valid = false;
                        } // if
                    } // if
                } // for
                if(valid) {
                    //The token is valid. We can safely turn it into a double.
                    sum += Double.parseDouble(token);
                } else {
                    sum = 0.0;
                    System.out.println("Input error. Try again");
                    userInput = input.nextLine();
                } // if
            } // while
        } while(!valid);
        
        //All tokens were found to be valid
        return sum;

    } // doubleSum
    
} // DoubleSum
