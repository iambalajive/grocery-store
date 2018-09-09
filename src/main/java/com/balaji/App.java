package com.balaji;

import com.balaji.billing.payment.PaymentApi;
import com.balaji.billing.payment.PaymentImpl;
import com.balaji.customer.Customer;
import com.balaji.customer.Order;
import com.balaji.inventory.InventoryApi;
import com.balaji.inventory.StoreInventoryImpl;
import com.balaji.store.GroceryStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver program
 */
public class App {
    public static void main(String[] args) {

//        InventoryApi inventoryApi = new StoreInventoryImpl(null);
//        PaymentApi paymentApi = new PaymentImpl(new ArrayList<>());
//        GroceryStore groceryStore = new GroceryStore(5,inventoryApi,paymentApi);
//
//        Customer customer = new Customer("Balaji", true, 21);
//        Order order1 = new Order(1,"11");
//
//        List<Order> orders = new ArrayList<>();
//        orders.add(order1);
//
//        customer.setOrders(orders);
//
//        groceryStore.buyItems(customer);
    }
}
