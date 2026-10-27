package notification;

public class OnlinePaymentNotification extends PaymentNotification {
    private final OnlineMethod method;

    public OnlinePaymentNotification(NotificationChannel channel, OnlineMethod method) {
        super(channel);
        if (method == null) {
            throw new IllegalArgumentException("Online payment method is required");
        }
        this.method = method;
    }

    public OnlineMethod getMethod() {
        return method;
    }

    @Override
    public String getPaymentType() {
        return "Online (" + method + ")";
    }

    @Override
    protected String buildMessage(double amount) {
        return "Your " + method + " payment of " + formatAmount(amount)
                + " was received. Your order is being processed.";
    }
}
