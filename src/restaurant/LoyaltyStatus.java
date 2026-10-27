package restaurant;

public enum LoyaltyStatus {
    NONE("None", 0.00),
    SILVER("Silver", 0.05),
    GOLD("Gold", 0.10),
    PLATINUM("Platinum", 0.15);

    private final String label;
    private final double discountRate;

    LoyaltyStatus(String label, double discountRate) {
        this.label = label;
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    // Rounded to the cent so the receipt lines always add up.
    public double getDiscount(double total) {
        if (total < 0) {
            throw new IllegalArgumentException("Order total cannot be negative");
        }
        return Math.round(total * discountRate * 100) / 100.0;
    }

    public double applyDiscount(double total) {
        return total - getDiscount(total);
    }

    @Override
    public String toString() {
        return label;
    }
}
