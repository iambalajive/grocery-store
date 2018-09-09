package com.balaji.billing;

import com.balaji.utils.Utils;

/**
 * Itemized Bill has the bill details for a particular item code
 * with discounts for that particular product
 */
public class ItemizedBill {

    private float pricePerItem;
    private String itemCode;
    private int quantity;
    private int discountPercentage;
    private String parentItemCode;


    public ItemizedBill(float price, String itemCode, int quantity, String parentItemCode) {
        this.pricePerItem = price;
        this.quantity = quantity;
        this.itemCode = itemCode;
        this.discountPercentage = 0;
        this.parentItemCode = parentItemCode;
    }

    /**
     * Appending discount to the item
     *
     * @param discountPercentage
     */
    public void addDiscount(int discountPercentage) {
        this.discountPercentage += discountPercentage;
    }

    /**
     * Calculate the price of the bundle after applying discount
     *
     * @return bundle price
     */
    public float getBundlePrice() {
        return Utils.calculateDiscountedPrice(this.pricePerItem * this.quantity, discountPercentage);
    }


    public String getItemCode() {
        return itemCode;
    }

    public String getParentItemCode() {
        return parentItemCode;
    }

}
