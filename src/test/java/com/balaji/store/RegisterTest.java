package com.balaji.store;

import com.balaji.billing.payment.PaymentApi;
import com.balaji.billing.payment.PaymentImpl;
import com.balaji.customer.Customer;
import com.balaji.customer.Order;
import com.balaji.discount.DiscountStrategy;
import com.balaji.discount.customer.EmployeeDiscount;
import com.balaji.discount.customer.SeniorCitizenDiscount;
import com.balaji.inventory.InventoryApi;
import com.balaji.inventory.StoreInventoryImpl;
import com.balaji.items.Item;
import com.balaji.store.register.IRegister;
import com.balaji.store.register.Register;
import com.balaji.utils.InventoryInitializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RegisterTest {

    private IRegister register;
    private Customer customer;
    @Before
    public void setUp() {
        List<Item> items = InventoryInitializer.buildInventory();
        InventoryApi inventoryApi = new StoreInventoryImpl(items);

        List<DiscountStrategy> discountStrategies = new ArrayList<DiscountStrategy>() {{
            add(new SeniorCitizenDiscount(79,10));
            add(new EmployeeDiscount(10));
        }};

        PaymentApi paymentApi = new PaymentImpl(discountStrategies);

        register  = new Register(1,inventoryApi,paymentApi);

        customer = new Customer("Balaji",false,13);
    }

    @Test
    public void testCheckout() throws Exception {
        Order order = new Order(2,"11");
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        customer.setOrders(orders);
        register.checkout(customer);

        Assert.assertEquals(customer.getBill().getBillAmt(),40.0,0.001);
        Assert.assertNotNull(customer.getCart().getCheckedOutProducts());
    }
}
