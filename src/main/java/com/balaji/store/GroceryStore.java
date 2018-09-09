package com.balaji.store;

import com.balaji.billing.Bill;
import com.balaji.billing.payment.PaymentApi;
import com.balaji.customer.Customer;
import com.balaji.exception.GroceryStoreException;
import com.balaji.inventory.InventoryApi;
import com.balaji.store.register.IRegister;
import com.balaji.store.register.Register;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The Grocery Store class that contains registers(counters) and inventory
 * The customer is tagged to a vacant register
 */
public class GroceryStore {

    private Queue<IRegister> vacantRegisters;
    private InventoryApi inventoryApi;
    private PaymentApi paymentApi;
    private List<Bill> billCopy; // Stores the copy of the bill for future reference

    public GroceryStore(int noOfRegisters, InventoryApi inventoryApi, PaymentApi paymentApi) {
        this.vacantRegisters = new LinkedBlockingQueue<>();
        this.inventoryApi = inventoryApi;
        this.paymentApi = paymentApi;
        this.billCopy = new ArrayList<>();
        this.initializeRegisters(noOfRegisters);
    }

    /**
     * Initialize the registers and add them to vancant Queue
     *
     * @param noOfRegisters
     */
    private void initializeRegisters(int noOfRegisters) {
        for (int i = 0; i < noOfRegisters; i++) {
            vacantRegisters.offer(new Register(i, this.inventoryApi, this.paymentApi));
        }
    }

    /**
     * Picks a vacant Register
     *
     * @return Register
     */
    private IRegister getRegister() {
        while (true) {
            if (!vacantRegisters.isEmpty()) {
                return vacantRegisters.poll();
            }
        }
    }

    /**
     * Once the customer is done the register is released
     *
     * @param register
     */
    private void releaseRegister(IRegister register) {
        vacantRegisters.offer(register);
    }

    /**
     * Method that assigns register ,buys and generates bill
     *
     * @param customer
     * @return customer
     */
    public Customer buyItems(Customer customer) throws GroceryStoreException {
        IRegister register = getRegister();
        register.checkout(customer);
        this.releaseRegister(register);
        billCopy.add(customer.getBill());
        return customer;
    }

    /**
     * Sum total of all the bills/transactions so far
     * @return total sales amount
     */

    public float getTotalSales() {
        float totalSales = 0;
        for (Bill bill : billCopy) {
            totalSales += bill.getBillAmt();
        }
        return totalSales;
    }


}
