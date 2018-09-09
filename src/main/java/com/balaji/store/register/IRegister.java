package com.balaji.store.register;

import com.balaji.customer.Customer;
import com.balaji.exception.GroceryStoreException;

/**
 * Abstract register that defines all the operations of a register
 */
public abstract class IRegister {

    /**
     * Pick item from the inventory
     * @param customer
     * @throws Exception
     */
    protected abstract void procureItemFromInventory(Customer customer) throws GroceryStoreException;

    /**
     * Integrate with payment module
     * @param customer
     * @throws Exception
     */
    protected abstract void makePayment(Customer customer) throws GroceryStoreException;

    /**
     * Assign the customer to the register
     * @param customer
     */
    protected abstract void assignCustomer(Customer customer);

    /**
     * Template method that fulfills the order
     * completes the workflow
     * @param customer
     * @return
     * @throws Exception
     */
    public final Customer checkout(Customer customer) throws GroceryStoreException {
        assignCustomer(customer);
        procureItemFromInventory(customer);
        makePayment(customer);
        return customer;
    }

}
