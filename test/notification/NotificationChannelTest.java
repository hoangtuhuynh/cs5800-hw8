package notification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NotificationChannelTest {

    @Test
    void emailChannelName() {
        assertEquals("Email", new EmailChannel().getName());
    }

    @Test
    void emailChannelSendFormatsAnEmail() {
        assertEquals("[Email] To: a@b.com | Subject: Payment update | Hello",
                new EmailChannel().send("a@b.com", "Hello"));
    }

    @Test
    void smsChannelName() {
        assertEquals("SMS", new SmsChannel().getName());
    }

    @Test
    void smsChannelSendFormatsAText() {
        assertEquals("[SMS] To: +1-555-0100 | Hello", new SmsChannel().send("+1-555-0100", "Hello"));
    }

    @Test
    void pushChannelName() {
        assertEquals("Push", new PushChannel().getName());
    }

    @Test
    void pushChannelSendFormatsAPushMessage() {
        assertEquals("[Push] Device: phone-1 | Hello", new PushChannel().send("phone-1", "Hello"));
    }
}
