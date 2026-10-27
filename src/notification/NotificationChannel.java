package notification;

public interface NotificationChannel {
    String getName();

    String send(String recipient, String message);
}
