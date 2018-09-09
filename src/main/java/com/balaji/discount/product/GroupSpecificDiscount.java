package com.balaji.discount.product;

import com.balaji.billing.ItemizedBill;
import com.balaji.customer.Customer;
import com.balaji.discount.DiscountStrategy;

import java.util.List;

/**
 * Discount applied at item group level
 * eg: Apply discounts to all category of chips
 */
public class GroupSpecificDiscount implements DiscountStrategy {

    private int discountPercentage;
    private String parentCode;

    public GroupSpecificDiscount(int discountPercentage, String parentCode) {
        this.parentCode = parentCode;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void calculateDiscount(Customer customer) {

        List<ItemizedBill> itemizedBills = customer.getBill().getItemizedBillByParentCode(parentCode);

        for (ItemizedBill itemizedBill : itemizedBills) {
            itemizedBill.addDiscount(this.discountPercentage);
        }
    }
}
