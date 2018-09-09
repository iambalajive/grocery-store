package com.balaji.inventory;

import com.balaji.customer.Order;
import com.balaji.exception.GroceryStoreException;
import com.balaji.items.Item;

import java.util.List;

/**
 * The inventory Api , Adds, removes and retrieves items
 * Storage of the item is left to the implementor
 */
public interface InventoryApi {

    /**
     * Add the item to the item store
     *
     * @param item
     */
    void addItem(Item item);

    /**
     * Remove the item from the item store
     *
     * @param itemCode
     */
    void removeItem(String itemCode);

    /**
     * Retrieves the items for the given order
     *
     * @param order
     * @return
     */
    List<Item> getItem(Order order) throws GroceryStoreException;

    /**
     * Prints the current state of the inventory
     */
    void currentState();

    /**
     * Lists all the items left in the inventory
     * @return Items
     */
    List<Item> listItems();
}
