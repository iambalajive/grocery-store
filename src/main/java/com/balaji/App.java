package com.balaji;

import com.balaji.billing.payment.PaymentApi;
import com.balaji.billing.payment.PaymentImpl;
import com.balaji.customer.Customer;
import com.balaji.customer.Order;
import com.balaji.discount.DiscountStrategy;
import com.balaji.discount.customer.EmployeeDiscount;
import com.balaji.discount.customer.SeniorCitizenDiscount;
import com.balaji.discount.product.ItemSpecificDiscount;
import com.balaji.exception.GroceryStoreException;
import com.balaji.inventory.InventoryApi;
import com.balaji.inventory.StoreInventoryImpl;
import com.balaji.store.GroceryStore;
import com.balaji.utils.InventoryInitializer;

import java.util.ArrayList;

/**
 * Driver program
 */
public class App {
    public static void main(String[] args) throws GroceryStoreException {


        InventoryApi inventoryApi = new StoreInventoryImpl(InventoryInitializer.buildInventory());
        PaymentApi paymentApi = new PaymentImpl(new ArrayList<DiscountStrategy>() {{
            add(new ItemSpecificDiscount(10,"101"));
            add(new SeniorCitizenDiscount(60,10));
            add(new EmployeeDiscount(10));
        }});

        GroceryStore groceryStore = new GroceryStore(5,inventoryApi,paymentApi);

        Customer customer = new Customer("Balaji", true, 21);

        customer.setOrders(new ArrayList<Order>() {{
            add(new Order(2,"11"));
            add(new Order(2,"21"));
        }});


        Customer seniorCitizen = new Customer("Balaji", true, 90);

        seniorCitizen.setOrders(new ArrayList<Order>() {{
            add(new Order(2,"11"));
            add(new Order(2,"21"));
        }});


        groceryStore.buyItems(seniorCitizen);

        groceryStore.buyItems(customer);
        //Bill amount for normal customer
        System.out.println(customer.getBill());

        // Bill amount for senior
        System.out.println(seniorCitizen.getBill());

        //Total sales
        System.out.println("Sales " + groceryStore.getTotalSales());

        inventoryApi.currentState();
    }
}
