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
    
    /**
     * Computes and returns the mean or average of the elements in {@code nums}.
     *
     * @param nums  numbers to average
     * @return mean of {@code nums}
     */
    public static double computeMean(double[] nums) {
        int count = nums.length;
        double sum = computeSum(nums);
        double mean = sum / count;
        return mean;
    } // computeMean
    
    public static void main(String[] args) {    
        double[] myNums = new double[] { 5.0, 5.0, 5.0 };
        double mean = computeMean(myNums);
        System.out.printf("mean = %f\n", mean);
    } // main

} // Driver
