package notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PaymentNotificationTest {

    @Test
    void channelIsRequired() {
        assertThrows(IllegalArgumentException.class,
                () -> new OnlinePaymentNotification(null, OnlineMethod.PAYPAL));
        assertThrows(IllegalArgumentException.class, () -> new CashOnDeliveryPayment(null));
        assertThrows(IllegalArgumentException.class, () -> new BitcoinPayment(null));
    }

    @Test
    void getChannelReturnsTheChannelItWasBuiltWith() {
        NotificationChannel sms = new SmsChannel();
        assertSame(sms, new CashOnDeliveryPayment(sms).getChannel());
    }

    @Test
    void sendNotificationRejectsMissingRecipient() {
        PaymentNotification notification = new BitcoinPayment(new EmailChannel());
        assertThrows(IllegalArgumentException.class, () -> notification.sendNotification(null, 10));
        assertThrows(IllegalArgumentException.class, () -> notification.sendNotification("  ", 10));
    }

    @Test
    void sendNotificationRejectsNonPositiveAmount() {
        PaymentNotification notification = new BitcoinPayment(new EmailChannel());
        assertThrows(IllegalArgumentException.class, () -> notification.sendNotification("a@b.com", 0));
        assertThrows(IllegalArgumentException.class, () -> notification.sendNotification("a@b.com", -5));
    }

    @Test
    void sendNotificationPassesThePaymentMessageToTheChannel() {
        PaymentNotification notification = new CashOnDeliveryPayment(new SmsChannel());
        assertEquals("[SMS] To: +1-555-0100 | " + notification.buildMessage(18.75),
                notification.sendNotification("+1-555-0100", 18.75));
    }

    @Test
    void formatAmountUsesTwoDecimals() {
        assertEquals("$5.00", PaymentNotification.formatAmount(5));
        assertEquals("$1234.57", PaymentNotification.formatAmount(1234.567));
    }

    @Test
    void onlineMethodLabels() {
        assertEquals("credit card", OnlineMethod.CREDIT_CARD.toString());
        assertEquals("PayPal", OnlineMethod.PAYPAL.toString());
    }

    @Test
    void onlineMethodIsRequired() {
        assertThrows(IllegalArgumentException.class,
                () -> new OnlinePaymentNotification(new EmailChannel(), null));
    }

    @Test
    void onlineGetMethod() {
        assertEquals(OnlineMethod.CREDIT_CARD,
                new OnlinePaymentNotification(new EmailChannel(), OnlineMethod.CREDIT_CARD).getMethod());
    }

    @Test
    void onlinePaymentType() {
        assertEquals("Online (PayPal)",
                new OnlinePaymentNotification(new EmailChannel(), OnlineMethod.PAYPAL).getPaymentType());
    }

    @Test
    void onlineMessageMentionsMethodAndAmount() {
        String message = new OnlinePaymentNotification(new EmailChannel(), OnlineMethod.CREDIT_CARD)
                .buildMessage(59.99);
        assertEquals("Your credit card payment of $59.99 was received. Your order is being processed.", message);
    }

    @Test
    void cashPaymentType() {
        assertEquals("Cash on delivery", new CashOnDeliveryPayment(new EmailChannel()).getPaymentType());
    }

    @Test
    void cashMessageAsksForCashOnArrival() {
        String message = new CashOnDeliveryPayment(new EmailChannel()).buildMessage(35);
        assertEquals("Your order is confirmed. Please have $35.00 in cash ready when the driver arrives.", message);
    }

    @Test
    void bitcoinPaymentType() {
        assertEquals("Bitcoin", new BitcoinPayment(new EmailChannel()).getPaymentType());
    }

    @Test
    void bitcoinMessageMentionsBlockchainConfirmation() {
        String message = new BitcoinPayment(new EmailChannel()).buildMessage(120);
        assertTrue(message.startsWith("Your Bitcoin payment of $120.00 was detected."));
        assertTrue(message.contains("blockchain"));
    }

    @Test
    void anyPaymentTypeWorksWithAnyChannel() {
        NotificationChannel[] channels = {new EmailChannel(), new SmsChannel(), new PushChannel()};
        for (NotificationChannel channel : channels) {
            PaymentNotification[] payments = {
                    new OnlinePaymentNotification(channel, OnlineMethod.PAYPAL),
                    new CashOnDeliveryPayment(channel),
                    new BitcoinPayment(channel)
            };
            for (PaymentNotification payment : payments) {
                String sent = payment.sendNotification("someone", 10);
                assertEquals(channel.send("someone", payment.buildMessage(10)), sent);
            }
        }
    }
}
