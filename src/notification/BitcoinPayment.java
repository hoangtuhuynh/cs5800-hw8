package notification;

public class BitcoinPayment extends PaymentNotification {
    public BitcoinPayment(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public String getPaymentType() {
        return "Bitcoin";
    }

    @Override
    protected String buildMessage(double amount) {
        return "Your Bitcoin payment of " + formatAmount(amount)
                + " was detected. Your order ships once the transaction is confirmed on the blockchain.";
    }
}
