package com.balaji.billing.payment;

import com.balaji.billing.Bill;
import com.balaji.billing.ItemizedBill;
import com.balaji.customer.Customer;
import com.balaji.discount.DiscountStrategy;
import com.balaji.exception.GroceryStoreException;
import com.balaji.items.ItemBundle;

import java.util.List;

public class PaymentImpl extends PaymentApi {

    public PaymentImpl(List<DiscountStrategy> strategies) {
        this.discountStrategies = strategies;
    }

    private Bill constructBill(List<ItemBundle> itemBundles) {
        Bill bill = new Bill();
        for (ItemBundle itemBundle : itemBundles) {
            bill.addItemizedBill(new ItemizedBill(itemBundle.getPricePerItem(),
                    itemBundle.getItemCode(), itemBundle.getNoOfItems(),
                    itemBundle.getParentItemCode()));
        }

        return bill;
    }

    @Override
    public Customer generateBill(Customer customer) throws GroceryStoreException {

        if (customer.getCart() == null) {
            throw new GroceryStoreException("No Cart initialized");
        }

        Bill bill = constructBill(customer.getCart().getCheckedOutProducts());
        customer.setBill(bill);
        for (DiscountStrategy discountStrategy : discountStrategies) {
            discountStrategy.calculateDiscount(customer);
        }

        return customer;
    }
}
