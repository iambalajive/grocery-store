package com.balaji.billing.payment;

import com.balaji.customer.Customer;
import com.balaji.discount.DiscountStrategy;
import com.balaji.exception.GroceryStoreException;

import java.util.List;

/**
 *
 */
public abstract class PaymentApi {

    /**
     * List of all the discount strategies
     */
    protected List<DiscountStrategy> discountStrategies;

    /**
     * Generates the bill based on the customer and cart details
     * @param customer
     * @return customer
     * @throws GroceryStoreException
     */
    public abstract Customer generateBill(Customer customer) throws GroceryStoreException;
}
