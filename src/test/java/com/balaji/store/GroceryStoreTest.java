package com.balaji.store;

import com.balaji.SpecHelper;
import com.balaji.billing.payment.PaymentApi;
import com.balaji.customer.Customer;
import com.balaji.customer.Order;
import com.balaji.exception.GroceryStoreException;
import com.balaji.inventory.InventoryApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GroceryStoreTest {

    private GroceryStore groceryStore;

    @Before
    public void setUp() {
        InventoryApi inventoryApi = SpecHelper.buildInventory();
        PaymentApi paymentApi = SpecHelper.buildPaymentApi();
        groceryStore = new GroceryStore(3, inventoryApi, paymentApi);
    }

    @Test
    public void nonEmployeeNonSeniorCitizen() throws GroceryStoreException {
        Customer customer = new Customer("Balaji", false, 40);
        List<Order> orders = new ArrayList<Order>() {{
            add(new Order(3, "11"));
            add(new Order(2, "21"));
        }};
        customer.setOrders(orders);

        Assert.assertEquals(groceryStore.buyItems(customer).getBill().getBillAmt(), 300.0, 0.001);
        Assert.assertEquals(groceryStore.getTotalSales(), 300.00, 0.001);
    }


    @Test
    public void multipleCustomers() throws GroceryStoreException {
        //10  percent off
        Customer seniorCitizen = new Customer("Balaji", false, 80);
        List<Order> orders1 = new ArrayList<Order>() {{
            add(new Order(3, "11"));
            add(new Order(2, "21"));
        }};

        seniorCitizen.setOrders(orders1);


        Customer normalCustomer = new Customer("Customer", false, 40);
        List<Order> orders2 = new ArrayList<Order>() {{
            add(new Order(2, "11"));
            add(new Order(2, "21"));
        }};
        normalCustomer.setOrders(orders2);


        Assert.assertEquals(groceryStore.buyItems(seniorCitizen).getBill().getBillAmt(), 270.0, 0.001);
        Assert.assertEquals(groceryStore.buyItems(normalCustomer).getBill().getBillAmt(), 280.0, 0.001);

        Assert.assertEquals(groceryStore.getTotalSales(), 550.0, 0.001);
    }

    @Test
    public void groupDiscount() throws GroceryStoreException {
        PaymentApi paymentApi = SpecHelper.groupItemDiscountStub();
        InventoryApi inventoryApi = SpecHelper.buildInventory();

        GroceryStore groceryStore = new GroceryStore(2, inventoryApi, paymentApi);

        Customer normalCustomer = new Customer("Customer", false, 40);
        normalCustomer.setOrders(new ArrayList<Order>() {{
            add(new Order(2, "11"));
            add(new Order(2, "12"));
            add(new Order(1,"21"));
        }});

        Assert.assertEquals(groceryStore.buyItems(normalCustomer).getBill().getBillAmt(), 228.0, 0.001);
    }


    @Test
    public void individualItemDiscount() throws GroceryStoreException {
        PaymentApi paymentApi = SpecHelper.indivudualItemDiscountStub();
        InventoryApi inventoryApi = SpecHelper.buildInventory();

        GroceryStore groceryStore = new GroceryStore(2, inventoryApi, paymentApi);

        Customer instoreEmployee = new Customer("Customer", true, 40);
        instoreEmployee.setOrders(new ArrayList<Order>() {{
            add(new Order(2, "11"));
            add(new Order(2, "12"));
            add(new Order(2,"21"));
        }});

        Assert.assertEquals(groceryStore.buyItems(instoreEmployee).getBill().getBillAmt(), 302.4, 0.001);
        Assert.assertEquals(groceryStore.getTotalSales(),302.4,0.001);
    }


}
