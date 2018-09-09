package com.balaji.inventory;


import com.balaji.customer.Order;
import com.balaji.exception.GroceryStoreException;
import com.balaji.items.Item;
import com.balaji.utils.InventoryInitializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StoreInventoryImplTest {

    InventoryApi inventoryApi;

    @Before
    public void init() {
        inventoryApi = new StoreInventoryImpl(InventoryInitializer.buildInventory());
    }

    @Test
    public void addItemToInventory() {
        Item item = new Item("34", 9, "name", "pp");
        inventoryApi.addItem(item);
        Assert.assertEquals(inventoryApi.listItems().contains(item), true);
    }

    @Test
    public void getItemBasedOnOrder() throws Exception {
        Order order = new Order(2, "11");
        Assert.assertEquals(inventoryApi.getItem(order).size(), 2);
    }

    @Test(expected = GroceryStoreException.class)
    public void ordersGreaterThanStockCount() throws Exception {
        Order order = new Order(222, "11");
        Assert.assertEquals(inventoryApi.getItem(order).size(), 222);
    }

    @Test(expected = GroceryStoreException.class)
    public void handleInvalidItemCode() throws Exception {
        Order order = new Order(222, "99");
        Assert.assertEquals(inventoryApi.getItem(order).size(), 222);
    }

    @Test
    public void removeItem() {
        inventoryApi.removeItem("11");
        Assert.assertEquals(inventoryApi.listItems().size(), 6);
    }
}
