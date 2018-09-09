package com.balaji.discount.product;

import com.balaji.customer.Customer;
import com.balaji.discount.DiscountStrategy;
import com.balaji.billing.ItemizedBill;

/**
 * Discount that is applied to induvidual items
 * eg:Induvidual chips varieties
 */
public class ItemSpecificDiscount implements DiscountStrategy {

    private int discountPercentage;
    private String itemCode;
    public ItemSpecificDiscount(int discountPercentage,String itemCode){
        this.discountPercentage = discountPercentage;
        this.itemCode = itemCode;
    }


    public void calculateDiscount(Customer customer) {
        ItemizedBill itemizedBill = customer.getBill().getItemizedBillByItemCode(this.itemCode);

        if(itemizedBill!=null){
            itemizedBill.addDiscount(this.discountPercentage);
        }
    }
}
