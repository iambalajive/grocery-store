package com.balaji.discount.customer;

import com.balaji.customer.Customer;
import com.balaji.discount.DiscountStrategy;

/**
 * Special discount for senior citizens
 */
public class SeniorCitizenDiscount extends PersonDiscountStrategy implements DiscountStrategy {

    private int seniorCitizenAgeLimit;

    public SeniorCitizenDiscount(int seniorCitizenAgeLimit, int discountPercentage) {
        super(discountPercentage);
        this.seniorCitizenAgeLimit = seniorCitizenAgeLimit;
    }

    public void calculateDiscount(Customer customer) {
        if (customer.getAge() > seniorCitizenAgeLimit) {
            super.setDiscount(customer);
        }
    }
}
