package com.balaji.customer;

import com.balaji.billing.Bill;
import com.balaji.store.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer class that contains all the customer details
 * After checkout the customer is given the cart and the generated bill
 */

public class Customer {

    private String name;
    private boolean isEmployee;
    private int age;
    private List<Order> orders;
    private Bill bill;
    private Cart cart;

    public Customer(String name, boolean isEmployee, int age) {
        this.name = name;
        this.isEmployee = isEmployee;
        this.age = age;
        this.orders = new ArrayList<Order>();
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Bill getBill() {
        return bill;
    }

    public Cart getCart() {
        return cart;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getAge() {
        return age;
    }

}
