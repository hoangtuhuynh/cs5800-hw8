package restaurant;

public class Customer {
    private final String name;
    private final LoyaltyStatus loyaltyStatus;

    public Customer(String name, LoyaltyStatus loyaltyStatus) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        if (loyaltyStatus == null) {
            throw new IllegalArgumentException("Loyalty status is required");
        }
        this.name = name;
        this.loyaltyStatus = loyaltyStatus;
    }

    public String getName() {
        return name;
    }

    public LoyaltyStatus getLoyaltyStatus() {
        return loyaltyStatus;
    }
}
