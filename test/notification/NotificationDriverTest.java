package notification;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class NotificationDriverTest {

    @Test
    void mainShowsEveryRequiredScenario() {
        String output = captureOutput(() -> NotificationDriver.main(new String[0]));

        assertTrue(output.contains("Part 1"));
        assertTrue(output.contains("Online (credit card) + Email:"));
        assertTrue(output.contains("Online (PayPal) + SMS:"));
        assertTrue(output.contains("Cash on delivery + Email:"));
        assertTrue(output.contains("Cash on delivery + SMS:"));

        assertTrue(output.contains("Part 2"));
        assertTrue(output.contains("Bitcoin + Email:"));
        assertTrue(output.contains("Bitcoin + SMS:"));

        assertTrue(output.contains("Part 3"));
        assertTrue(output.contains("Online (PayPal) + Push:"));
        assertTrue(output.contains("Cash on delivery + Push:"));
    }

    private static String captureOutput(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }
}
