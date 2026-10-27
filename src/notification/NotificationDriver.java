package notification;

public class NotificationDriver {
    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel push = new PushChannel();

        System.out.println("===== Part 1: Online and Cash on Delivery over Email and SMS =====\n");
        show(new OnlinePaymentNotification(email, OnlineMethod.CREDIT_CARD), "alice@example.com", 59.99);
        show(new OnlinePaymentNotification(sms, OnlineMethod.PAYPAL), "+1-555-0101", 24.50);
        show(new CashOnDeliveryPayment(email), "bob@example.com", 35.00);
        show(new CashOnDeliveryPayment(sms), "+1-555-0102", 18.75);

        System.out.println("===== Part 2: New payment type - Bitcoin =====\n");
        show(new BitcoinPayment(email), "carol@example.com", 120.00);
        show(new BitcoinPayment(sms), "+1-555-0103", 75.25);

        System.out.println("===== Part 3: New channel - Push =====\n");
        show(new OnlinePaymentNotification(push, OnlineMethod.PAYPAL), "dave-iphone", 42.10);
        show(new CashOnDeliveryPayment(push), "erin-pixel", 15.00);
    }

    private static void show(PaymentNotification notification, String recipient, double amount) {
        System.out.println(notification.getPaymentType() + " + " + notification.getChannel().getName() + ":");
        System.out.println("  " + notification.sendNotification(recipient, amount));
        System.out.println();
    }
}
