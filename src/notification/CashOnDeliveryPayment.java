package notification;

public class CashOnDeliveryPayment extends PaymentNotification {
    public CashOnDeliveryPayment(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public String getPaymentType() {
        return "Cash on delivery";
    }

    @Override
    protected String buildMessage(double amount) {
        return "Your order is confirmed. Please have " + formatAmount(amount)
                + " in cash ready when the driver arrives.";
    }
}
