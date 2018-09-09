package com.balaji.inventory;

import com.balaji.customer.Order;
import com.balaji.exception.GroceryStoreException;
import com.balaji.items.Item;

import java.util.*;

/**
 * The inventory Implementation
 */

public class StoreInventoryImpl implements InventoryApi {


    //The item store . Map of item code to list of items
    private Map<String, Queue<Item>> itemStore;

    /**
     * Init method that builds the item store for the inventory
     *
     * @param items
     */
    private void init(List<Item> items) {
        for (Item item : items) {
            this.addItem(item);
        }
    }

    public StoreInventoryImpl(List<Item> items) {
        itemStore = new HashMap<>();
        this.init(items);
    }


    /**
     * Add the items to the store .
     * If the item is already available then appends to the existing list else create a new list
     *
     * @param item
     */
    @Override
    public void addItem(Item item) {
        Queue<Item> items =
                itemStore.containsKey(item.getItemCode()) ? itemStore.get(item.getItemCode()) : new LinkedList<>();

        items.add(item);

        itemStore.put(item.getItemCode(), items);
    }


    /**
     * Remove an item code from the item store
     *
     * @param itemCode
     */
    @Override
    public void removeItem(String itemCode) {
        itemStore.remove(itemCode);
    }

    /**
     * Translates the order to a List of items
     * throws Exception if code is invalid or the inventory does not have enough items to meet the order
     *
     * @param order
     * @return
     * @throws GroceryStoreException
     */
    @Override
    public List<Item> getItem(Order order) throws GroceryStoreException {
        Queue<Item> items = itemStore.get(order.getItemCode());

        if (items == null) {
            throw new GroceryStoreException("Invalid item Code " + order.getItemCode());
        }

        if (items.size() < order.getQuantity()) {
            throw new GroceryStoreException("Insufficient number of items in the inventory " + order.getItemCode());
        }

        //Takes items from item store
        int i = 0;
        List<Item> orderedItems = new ArrayList<Item>();
        while (i < order.getQuantity()) {
            orderedItems.add(items.poll());
            i++;
        }
        return orderedItems;
    }

    /**
     * Prints the current state of the inventory
     */
    @Override
    public void currentState() {
        System.out.println(itemStore);
    }

    /**
     * Returns the list of items present in the inventory
     *
     * @return List of items
     */

    @Override
    public List<Item> listItems() {
        List<Item> items = new ArrayList<>();
        for (Queue<Item> itemQ : itemStore.values()) {
            items.addAll(itemQ);
        }
        return items;
    }


}
