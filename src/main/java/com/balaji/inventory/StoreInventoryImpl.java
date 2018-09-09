package com.balaji.inventory;

import com.balaji.customer.Order;
import com.balaji.exception.GroceryStoreException;
import com.balaji.items.Item;

import java.util.*;

/**
 * The inventory Implementation
 */

public class StoreInventoryImpl implements InventoryApi {


    private Map<String, Queue<Item>> itemStore;

    private void init(List<Item> items) {
        for (Item item : items) {
            this.addItem(item);
        }
    }

    public StoreInventoryImpl(List<Item> items) {
        itemStore = new HashMap<>();
        this.init(items);
    }


    @Override
    public void addItem(Item item) {
        Queue<Item> items =
                itemStore.containsKey(item.getItemCode()) ? itemStore.get(item.getItemCode()) : new LinkedList<>();

        items.add(item);

        itemStore.put(item.getItemCode(), items);
    }


    @Override
    public void removeItem(String itemCode) {
        itemStore.remove(itemCode);
    }

    @Override
    public List<Item> getItem(Order order) throws GroceryStoreException {
        Queue<Item> items = itemStore.get(order.getItemCode());

        if (items == null) {
            throw new GroceryStoreException("Invalid item Code " + order.getItemCode());
        }

        if (items.size() < order.getQuantity()) {
            throw new GroceryStoreException("Insufficient number of items in the inventory "+order.getItemCode());
        }

        int i = 0;
        List<Item> orderedItems = new ArrayList<Item>();
        while (i < order.getQuantity()) {
            orderedItems.add(items.poll());
            i++;
        }
        return orderedItems;
    }

    @Override
    public void currentState() {
        System.out.println(itemStore);
    }

    @Override
    public List<Item> listItems() {
        List<Item> items = new ArrayList<>();
        for (Queue<Item> itemQ : itemStore.values()) {
            items.addAll(itemQ);
        }
        return items;
    }


}
