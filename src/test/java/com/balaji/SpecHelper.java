package com.balaji;

import com.balaji.billing.payment.PaymentApi;
import com.balaji.billing.payment.PaymentImpl;
import com.balaji.discount.DiscountStrategy;
import com.balaji.discount.customer.EmployeeDiscount;
import com.balaji.discount.customer.SeniorCitizenDiscount;
import com.balaji.discount.product.GroupSpecificDiscount;
import com.balaji.discount.product.ItemSpecificDiscount;
import com.balaji.inventory.InventoryApi;
import com.balaji.inventory.StoreInventoryImpl;
import com.balaji.items.Item;
import com.balaji.utils.InventoryInitializer;

import java.util.ArrayList;
import java.util.List;

public class SpecHelper {

    private SpecHelper() {

    }

    public static InventoryApi buildInventory() {
        List<Item> items = InventoryInitializer.buildInventory("src//test/resources/test-inventory.json");
        return new StoreInventoryImpl(items);
    }

    public static PaymentApi buildPaymentApi() {
        List<DiscountStrategy> discountStrategies = new ArrayList<DiscountStrategy>() {{
            add(new SeniorCitizenDiscount(60, 10));
            add(new EmployeeDiscount(10));
        }};

        return new PaymentImpl(discountStrategies);
    }

    public static PaymentApi groupItemDiscountStub() {
        List<DiscountStrategy> discountStrategies = new ArrayList<DiscountStrategy>() {{
            add(new SeniorCitizenDiscount(60, 10));
            add(new EmployeeDiscount(10));
            add(new GroupSpecificDiscount(10,"1"));
        }};

        return new PaymentImpl(discountStrategies);
    }

    public static PaymentApi indivudualItemDiscountStub() {
        List<DiscountStrategy> discountStrategies = new ArrayList<DiscountStrategy>() {{
            add(new SeniorCitizenDiscount(60, 10));
            add(new EmployeeDiscount(10));
            add(new ItemSpecificDiscount(10,"21"));
        }};

        return new PaymentImpl(discountStrategies);
    }
}
