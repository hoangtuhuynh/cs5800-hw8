package notification;

public enum OnlineMethod {
    CREDIT_CARD("credit card"),
    PAYPAL("PayPal");

    private final String label;

    OnlineMethod(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
