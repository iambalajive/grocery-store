package com.balaji.utils;

/**
 * Utility class that contains all helper methods
 */
public class Utils {

    private Utils() {

    }

    /**
     * Calculate the discounted price for given price and discount percentage
     * @param price
     * @param discountPercentage
     * @return discountedPrice
     */
    public static float calculateDiscountedPrice(float price,float discountPercentage) {
        if(discountPercentage > 100) {
            return 0.0f;
        }
        return (price - ((discountPercentage/100) * price));
    }
}
