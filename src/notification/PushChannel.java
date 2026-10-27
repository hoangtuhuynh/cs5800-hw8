package notification;

public class PushChannel implements NotificationChannel {
    @Override
    public String getName() {
        return "Push";
    }

    @Override
    public String send(String recipient, String message) {
        return "[Push] Device: " + recipient + " | " + message;
    }
}
