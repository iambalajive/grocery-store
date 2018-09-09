package com.balaji.discount;

import com.balaji.customer.Customer;

/**
 * Generic Discount Strategy
 */
public interface DiscountStrategy {
    /**
     * Calculates the discount on case by case basis
     * and updates the bill amount
     * @param customer
     */
    void calculateDiscount(Customer customer);

}
