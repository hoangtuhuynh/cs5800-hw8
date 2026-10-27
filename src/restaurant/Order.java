package restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Order {
    private final Customer customer;
    private final List<FoodItem> items = new ArrayList<>();

    public Order(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }
        this.customer = customer;
    }

    public void addItem(FoodItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Food item is required");
        }
        items.add(item);
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<FoodItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (FoodItem item : items) {
            subtotal += item.getCost();
        }
        return Math.round(subtotal * 100) / 100.0;
    }

    public double getDiscount() {
        return customer.getLoyaltyStatus().getDiscount(getSubtotal());
    }

    public double getTotal() {
        return customer.getLoyaltyStatus().applyDiscount(getSubtotal());
    }

    public String getReceipt() {
        LoyaltyStatus status = customer.getLoyaltyStatus();
        StringBuilder receipt = new StringBuilder();
        receipt.append("Order for ").append(customer.getName())
                .append(" (loyalty: ").append(status).append(")\n");
        for (FoodItem item : items) {
            receipt.append(line(item.getDescription(), money(item.getCost())));
        }
        receipt.append(line("Subtotal", money(getSubtotal())));
        long percent = Math.round(status.getDiscountRate() * 100);
        receipt.append(line(status + " discount (" + percent + "%)", "-" + money(getDiscount())));
        receipt.append(line("Total", money(getTotal())));
        return receipt.toString();
    }

    private static String line(String label, String amount) {
        return String.format(Locale.US, "  %-52s %9s%n", label, amount);
    }

    private static String money(double amount) {
        return String.format(Locale.US, "$%.2f", amount);
    }
}
