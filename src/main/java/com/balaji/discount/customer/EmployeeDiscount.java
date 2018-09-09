package com.balaji.discount.customer;

import com.balaji.customer.Customer;
import com.balaji.discount.DiscountStrategy;

/**
 * Special Discount for employees
 */
public class EmployeeDiscount extends PersonDiscountStrategy implements DiscountStrategy {

    public EmployeeDiscount(int discountPercentage) {
        super(discountPercentage);
    }

    public void calculateDiscount(Customer customer) {
        if (customer.isEmployee()) {
            super.setDiscount(customer);
        }
    }
}
