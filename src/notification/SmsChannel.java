package notification;

public class SmsChannel implements NotificationChannel {
    @Override
    public String getName() {
        return "SMS";
    }

    @Override
    public String send(String recipient, String message) {
        return "[SMS] To: " + recipient + " | " + message;
    }
}
