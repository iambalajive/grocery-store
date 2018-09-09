package com.balaji.billing;

import com.balaji.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The bill class that is shared to the customer
 * Has the list of itemized bill (Bill per item code)
 * Discount applied on the entire Bill
 */
public class Bill {

    private HashMap<String, ItemizedBill> itemizedBills;

    private float discountPercentage;

    public Bill() {
        itemizedBills = new HashMap<>();
    }


    /**
     * Add Itemized bill to the bill
     *
     * @param itemizedBill
     */
    public void addItemizedBill(ItemizedBill itemizedBill) {
        itemizedBills.put(itemizedBill.getItemCode(), itemizedBill);
    }

    /**
     * Get itemized bill by given item code
     *
     * @param itemCode
     * @return Itemized Bill
     */
    public ItemizedBill getItemizedBillByItemCode(String itemCode) {
        return itemizedBills.get(itemCode);
    }

    /**
     * Get Itemized bill for the given parentItemCode
     *
     * @param parentItemCode
     * @return List of matched itemized bills
     */
    public List<ItemizedBill> getItemizedBillByParentCode(String parentItemCode) {
        List<ItemizedBill> matchedItems = new ArrayList<>();

        for (ItemizedBill itemizedBill : itemizedBills.values()) {
            if (itemizedBill.getParentItemCode().equals(parentItemCode)) {
                matchedItems.add(itemizedBill);
            }
        }
        return matchedItems;
    }

    /**
     * Append discount to the bill at customer level
     *
     * @param discountPercentage
     */
    public void addDiscount(float discountPercentage) {
        this.discountPercentage += discountPercentage;
    }

    /**
     * Calculates the final bill amount for a purchase
     * includes product discount and customer discount
     *
     * @return bill amount
     */
    public float getBillAmt() {
        float price = 0.0f;
        for (ItemizedBill itemizedBill : itemizedBills.values()) {
            price += itemizedBill.getBundlePrice();
        }
        return Utils.calculateDiscountedPrice(price, discountPercentage);
    }


    @Override
    public String toString() {
        return "Bill{" +
                "itemizedBills=" + itemizedBills +
                ", discountPercentage=" + discountPercentage +
                ", Net bill Amount=" + this.getBillAmt() +
                '}';
    }

}
