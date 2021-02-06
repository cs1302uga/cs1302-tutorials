package cs1302.models;

import java.util.List;

/**
 * Represents a catalog of items.
 */
public class Catalog {

    /**
     * Return a value in {@code prices} that is less than all other distinct
     * values found in the array.
     * @param prices  an array of price values
     * @return a minimal value in {@code prices}
     * @throws NullPointerException when {@code prices} is {@code null}
     * @throws IllegalArgumentException when {@prices.length == 0}
     */
    public double findMin(double[] prices) {
        if (prices.length == 0) {
            throw new IllegalArgumentException("prices.length cannot be 0");
        } else {
            double minSoFar = prices[0];
            for (int i = 1; i < prices.length; i++) {
                minSoFar = Utility.min(minSoFar, prices[i]);
            } // for
            return minSoFar;
        } // min
    } // findMin

} // Catalog
