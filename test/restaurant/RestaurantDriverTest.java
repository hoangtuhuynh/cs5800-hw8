package restaurant;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class RestaurantDriverTest {

    @Test
    void mainShowsFoodItemsAndAReceiptPerLoyaltyStatus() {
        String output = captureOutput(() -> RestaurantDriver.main(new String[0]));

        assertTrue(output.contains("Burger with Cheese, Bacon"));
        assertTrue(output.contains("Hot Dog with Ketchup, Mustard, Onions"));
        assertTrue(output.contains("None discount (0%)"));
        assertTrue(output.contains("Silver discount (5%)"));
        assertTrue(output.contains("Gold discount (10%)"));
        assertTrue(output.contains("Platinum discount (15%)"));
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
