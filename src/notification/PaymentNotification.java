package notification;

import java.util.Locale;

public abstract class PaymentNotification {
    private final NotificationChannel channel;

    protected PaymentNotification(NotificationChannel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Notification channel is required");
        }
        this.channel = channel;
    }

    public abstract String getPaymentType();

    protected abstract String buildMessage(double amount);

    public NotificationChannel getChannel() {
        return channel;
    }

    public String sendNotification(String recipient, double amount) {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException("Recipient is required");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
        return channel.send(recipient, buildMessage(amount));
    }

    protected static String formatAmount(double amount) {
        return String.format(Locale.US, "$%.2f", amount);
    }
}
