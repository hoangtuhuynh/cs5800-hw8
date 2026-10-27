package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class OrderTest {
    private static final double CENT = 0.001;

    // Burger with Cheese, Bacon = 7.99 and Fries with Ketchup = 2.74, so the subtotal is 10.73.
    private static Order goldOrder() {
        Order order = new Order(new Customer("Carol", LoyaltyStatus.GOLD));
        order.addItem(new Bacon(new Cheese(new Burger())));
        order.addItem(new Ketchup(new Fries()));
        return order;
    }

    @Test
    void customerIsRequired() {
        assertThrows(IllegalArgumentException.class, () -> new Order(null));
    }

    @Test
    void getCustomer() {
        Customer customer = new Customer("Alice", LoyaltyStatus.NONE);
        assertSame(customer, new Order(customer).getCustomer());
    }

    @Test
    void addItemKeepsItemsInOrder() {
        Order order = new Order(new Customer("Alice", LoyaltyStatus.NONE));
        FoodItem burger = new Burger();
        FoodItem fries = new Fries();
        order.addItem(burger);
        order.addItem(fries);

        assertEquals(List.of(burger, fries), order.getItems());
    }

    @Test
    void addItemRejectsNull() {
        Order order = new Order(new Customer("Alice", LoyaltyStatus.NONE));
        assertThrows(IllegalArgumentException.class, () -> order.addItem(null));
    }

    @Test
    void getItemsCannotBeModified() {
        Order order = goldOrder();
        assertThrows(UnsupportedOperationException.class, () -> order.getItems().add(new HotDog()));
    }

    @Test
    void getSubtotalSumsItemsIncludingToppings() {
        assertEquals(10.73, goldOrder().getSubtotal(), CENT);
    }

    @Test
    void emptyOrderCostsNothing() {
        Order order = new Order(new Customer("Alice", LoyaltyStatus.PLATINUM));
        assertEquals(0.00, order.getSubtotal(), CENT);
        assertEquals(0.00, order.getTotal(), CENT);
    }

    @Test
    void getDiscountUsesTheCustomersLoyaltyStatus() {
        assertEquals(1.07, goldOrder().getDiscount(), CENT);
    }

    @Test
    void getTotalAppliesTheDiscount() {
        assertEquals(9.66, goldOrder().getTotal(), CENT);
    }

    @Test
    void noLoyaltyStatusPaysFullPrice() {
        Order order = new Order(new Customer("Alice", LoyaltyStatus.NONE));
        order.addItem(new Burger());
        assertEquals(order.getSubtotal(), order.getTotal(), CENT);
    }

    @Test
    void getReceiptListsItemsAndTotals() {
        String receipt = goldOrder().getReceipt();

        assertTrue(receipt.contains("Order for Carol (loyalty: Gold)"));
        assertTrue(receipt.contains("Burger with Cheese, Bacon"));
        assertTrue(receipt.contains("$7.99"));
        assertTrue(receipt.contains("Fries with Ketchup"));
        assertTrue(receipt.contains("$2.74"));
        assertTrue(receipt.contains("$10.73"));
        assertTrue(receipt.contains("Gold discount (10%)"));
        assertTrue(receipt.contains("-$1.07"));
        assertTrue(receipt.contains("$9.66"));
    }
}
