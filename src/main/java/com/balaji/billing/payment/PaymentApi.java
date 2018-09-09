package com.balaji.billing.payment;

import com.balaji.customer.Customer;
import com.balaji.discount.DiscountStrategy;
import com.balaji.exception.GroceryStoreException;

import java.util.List;

public abstract class PaymentApi {

    protected List<DiscountStrategy> discountStrategies;

    public abstract Customer generateBill(Customer customer) throws GroceryStoreException;
}
