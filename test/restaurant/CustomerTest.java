package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void getName() {
        assertEquals("Alice", new Customer("Alice", LoyaltyStatus.GOLD).getName());
    }

    @Test
    void getLoyaltyStatus() {
        assertEquals(LoyaltyStatus.GOLD, new Customer("Alice", LoyaltyStatus.GOLD).getLoyaltyStatus());
    }

    @Test
    void nameIsRequired() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, LoyaltyStatus.NONE));
        assertThrows(IllegalArgumentException.class, () -> new Customer(" ", LoyaltyStatus.NONE));
    }

    @Test
    void loyaltyStatusIsRequired() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("Alice", null));
    }
}
