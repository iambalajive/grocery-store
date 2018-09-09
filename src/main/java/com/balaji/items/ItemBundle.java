package com.balaji.items;

import com.balaji.exception.GroceryStoreException;

import java.util.List;

/**
 * Group of items having the same item codes
 * Contains the number of items with price per item and parentItemCode
 */
public class ItemBundle {

    private int noOfItems;
    private String itemCode;
    private String parentItemCode;
    private float pricePerItem;
    private List<Item> items;

    public ItemBundle(List<Item> items) throws GroceryStoreException {
        if (items == null || items.isEmpty()) {
            throw new GroceryStoreException("Cannot initialize item bundle with no items");
        }
        this.items = items;
        Item item = items.get(0);
        this.noOfItems = items.size();
        this.pricePerItem = item.getPrice();
        this.itemCode = item.getItemCode();
        this.parentItemCode = item.getParentItemCode();
        this.itemValidator();
    }

    /**
     * Validates the list of items that are clubbed together
     * Cannot have items of two different items codes clubbed together
     *
     * @throws GroceryStoreException
     */
    private void itemValidator() throws GroceryStoreException {
        String itemCode = this.items.get(0).getItemCode();

        for (Item item : items) {
            if (!item.getItemCode().equals(itemCode)) {
                throw new GroceryStoreException("Dissimilar items present in the bundle");
            }
        }
    }

    public String getParentItemCode() {
        return parentItemCode;
    }

    public float getPricePerItem() {
        return pricePerItem;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public String getItemCode() {
        return itemCode;
    }

}
