package com.balaji.billing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ItemizedBillTest {

    private ItemizedBill itemizedBill;

    @Before
    public void setUp() {
        itemizedBill = new ItemizedBill(120, "1", 2, "2");
    }

    @Test
    public void calculateItemizedBill() {
        itemizedBill.addDiscount(10);
        Assert.assertEquals(itemizedBill.getBundlePrice(), 216.0, 0.001);
    }

}
