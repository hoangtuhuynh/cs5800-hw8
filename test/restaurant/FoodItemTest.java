package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FoodItemTest {
    private static final double CENT = 0.001;

    @Test
    void burgerDescription() {
        assertEquals("Burger", new Burger().getDescription());
    }

    @Test
    void burgerCost() {
        assertEquals(5.99, new Burger().getCost(), CENT);
    }

    @Test
    void friesDescription() {
        assertEquals("Fries", new Fries().getDescription());
    }

    @Test
    void friesCost() {
        assertEquals(2.49, new Fries().getCost(), CENT);
    }

    @Test
    void hotDogDescription() {
        assertEquals("Hot Dog", new HotDog().getDescription());
    }

    @Test
    void hotDogCost() {
        assertEquals(3.49, new HotDog().getCost(), CENT);
    }
}
