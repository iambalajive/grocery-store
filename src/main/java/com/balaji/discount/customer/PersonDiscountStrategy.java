package com.balaji.discount.customer;

import com.balaji.billing.Bill;
import com.balaji.customer.Customer;

/**
 * Discount that updates the bill amount based on the given discount
 * percent if the condition is satisfied . These discounts are based
 * on the customer details and not products
 */
public abstract class PersonDiscountStrategy {

    private int discountPercentage;

    public PersonDiscountStrategy(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setDiscount(Customer customer) {
        Bill bill = customer.getBill();
        bill.addDiscount(this.discountPercentage);
        customer.setBill(bill);
    }
}
