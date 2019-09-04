package cs1302.jdb;

/**
 * Test class for JDB tutorial.
 */
public class Driver {

    /**
     * Computes and returns the sum of the elements in {@code nums}.
     *
     * @param nums  numbers to sum
     * @return sum of {@code nums}
     */
    public static double computeSum(double[] nums) {
        double sum = 0.0;
        for (int i = 0; i < nums.length - 1; i++) {
            sum += nums[i];
        } // for
        return sum;
    } // computeSum
    
    public static void main(String[] args) {
    
    
    } // main

} // Driver
