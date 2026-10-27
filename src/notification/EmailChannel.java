package notification;

public class EmailChannel implements NotificationChannel {
    @Override
    public String getName() {
        return "Email";
    }

    @Override
    public String send(String recipient, String message) {
        return "[Email] To: " + recipient + " | Subject: Payment update | " + message;
    }
}
