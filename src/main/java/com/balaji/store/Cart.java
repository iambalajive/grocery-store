package com.balaji.store;

import com.balaji.items.ItemBundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Shopping that is given to the customer after checkout
 * It contains the list of item bundles
 */
public class Cart {

    private List<ItemBundle> checkedOutProducts;

    public Cart() {
        this.checkedOutProducts = new ArrayList<ItemBundle>();
    }

    public void addItemBundle(ItemBundle itemBundle) {
        this.checkedOutProducts.add(itemBundle);
    }

    public List<ItemBundle> getCheckedOutProducts() {
        return checkedOutProducts;
    }

}
