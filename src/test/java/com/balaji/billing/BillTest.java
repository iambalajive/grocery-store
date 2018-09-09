package com.balaji.billing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BillTest {

    Bill bill = new Bill();
    @Before
    public void setUp() {
        bill.addItemizedBill(new ItemizedBill(100,"1",1,"2"));
        bill.addItemizedBill(new ItemizedBill(200,"2",2,"2"));
    }
    @Test
    public void getCorrectBillAmount() {
        bill.addDiscount(10);

        Assert.assertEquals(bill.getBillAmt(),450.0f,0.0001);
    }

    @Test
    public void handleMultipleDiscounts() {
        bill.addDiscount(10);
        bill.addDiscount(10);

        Assert.assertEquals(bill.getBillAmt(),400.0f,0.0001);
    }

    @Test
    public void handleDiscountOnItemizedBill() {

        ItemizedBill itemizedBill = new ItemizedBill(100,"3333",3,"1231");
        itemizedBill.addDiscount(10);
        bill.addItemizedBill(itemizedBill);

        bill.addDiscount(20);

        Assert.assertEquals(bill.getBillAmt(),616.0f,0.0001);
    }

}
