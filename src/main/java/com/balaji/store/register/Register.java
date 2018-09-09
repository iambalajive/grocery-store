package com.balaji.store.register;

import com.balaji.billing.payment.PaymentApi;
import com.balaji.customer.Customer;
import com.balaji.customer.Order;
import com.balaji.exception.GroceryStoreException;
import com.balaji.inventory.InventoryApi;
import com.balaji.items.Item;
import com.balaji.items.ItemBundle;
import com.balaji.store.Cart;

import java.util.List;

/**
 * Register or Checkout Counter class that takes the customer
 * Fulfills the order based on the customer needs
 * Invokes the customer
 */

public class Register extends IRegister {

    private int registerNo;
    private InventoryApi inventoryApi;
    private PaymentApi paymentApi;
    private Customer customer;

    public Register(int registerNo, InventoryApi inventoryApi, PaymentApi paymentApi) {
        this.registerNo = registerNo;
        this.inventoryApi = inventoryApi;
        this.paymentApi = paymentApi;
    }


    /**
     * Pick items from the inventory
     * Creates a cart with item bundles and attach it to the customer
     * @param customer
     * @throws GroceryStoreException
     */
    @Override
    protected void procureItemFromInventory(Customer customer) throws GroceryStoreException {

        Cart cart = new Cart();
        for (Order order : customer.getOrders()) {
            List<Item> items = inventoryApi.getItem(order);
            cart.addItemBundle(new ItemBundle(items));
        }
        customer.setCart(cart);

    }


    /**
     * Talks to payment Api and attaches the bill to the customer
     * @param customer
     * @throws GroceryStoreException
     */
    @Override
    protected void makePayment(Customer customer) throws GroceryStoreException {
        this.paymentApi.generateBill(customer);
    }

    /**
     * Assign customer to the Register
     * @param customer
     */
    @Override
    protected void assignCustomer(Customer customer) {
        this.customer = customer;
    }
}
