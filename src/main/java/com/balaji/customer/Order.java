package com.balaji.customer;

/**
 * Order placed by the customer . Denotes the item code of the item
 * they want to buy and the quantity
 */
public class Order {
    private int quantity;
    private String itemCode;

    public Order(int quantity, String itemCode) {
        this.quantity = quantity;
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemCode() {
        return itemCode;
    }

}
