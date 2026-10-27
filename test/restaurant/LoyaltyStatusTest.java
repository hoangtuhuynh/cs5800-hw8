package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LoyaltyStatusTest {
    private static final double CENT = 0.001;

    @Test
    void discountRates() {
        assertEquals(0.00, LoyaltyStatus.NONE.getDiscountRate(), CENT);
        assertEquals(0.05, LoyaltyStatus.SILVER.getDiscountRate(), CENT);
        assertEquals(0.10, LoyaltyStatus.GOLD.getDiscountRate(), CENT);
        assertEquals(0.15, LoyaltyStatus.PLATINUM.getDiscountRate(), CENT);
    }

    @Test
    void getDiscountIsRoundedToTheCent() {
        assertEquals(0.00, LoyaltyStatus.NONE.getDiscount(20.00), CENT);
        assertEquals(1.00, LoyaltyStatus.SILVER.getDiscount(20.00), CENT);
        assertEquals(1.15, LoyaltyStatus.GOLD.getDiscount(11.48), CENT);
        assertEquals(3.00, LoyaltyStatus.PLATINUM.getDiscount(20.00), CENT);
    }

    @Test
    void applyDiscountSubtractsTheDiscount() {
        assertEquals(20.00, LoyaltyStatus.NONE.applyDiscount(20.00), CENT);
        assertEquals(19.00, LoyaltyStatus.SILVER.applyDiscount(20.00), CENT);
        assertEquals(10.33, LoyaltyStatus.GOLD.applyDiscount(11.48), CENT);
        assertEquals(17.00, LoyaltyStatus.PLATINUM.applyDiscount(20.00), CENT);
    }

    @Test
    void negativeTotalIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> LoyaltyStatus.GOLD.getDiscount(-1));
        assertThrows(IllegalArgumentException.class, () -> LoyaltyStatus.GOLD.applyDiscount(-1));
    }

    @Test
    void labels() {
        assertEquals("None", LoyaltyStatus.NONE.toString());
        assertEquals("Silver", LoyaltyStatus.SILVER.toString());
        assertEquals("Gold", LoyaltyStatus.GOLD.toString());
        assertEquals("Platinum", LoyaltyStatus.PLATINUM.toString());
    }
}
