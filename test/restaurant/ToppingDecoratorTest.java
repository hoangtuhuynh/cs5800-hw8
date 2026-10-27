package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ToppingDecoratorTest {
    private static final double CENT = 0.001;

    @Test
    void ketchupAddsNameAndPrice() {
        FoodItem item = new Ketchup(new Fries());
        assertEquals("Fries with Ketchup", item.getDescription());
        assertEquals(2.49 + 0.25, item.getCost(), CENT);
    }

    @Test
    void mustardAddsNameAndPrice() {
        FoodItem item = new Mustard(new HotDog());
        assertEquals("Hot Dog with Mustard", item.getDescription());
        assertEquals(3.49 + 0.25, item.getCost(), CENT);
    }

    @Test
    void cheeseAddsNameAndPrice() {
        FoodItem item = new Cheese(new Burger());
        assertEquals("Burger with Cheese", item.getDescription());
        assertEquals(5.99 + 0.75, item.getCost(), CENT);
    }

    @Test
    void onionsAddNameAndPrice() {
        FoodItem item = new Onions(new Burger());
        assertEquals("Burger with Onions", item.getDescription());
        assertEquals(5.99 + 0.50, item.getCost(), CENT);
    }

    @Test
    void baconAddsNameAndPrice() {
        FoodItem item = new Bacon(new Burger());
        assertEquals("Burger with Bacon", item.getDescription());
        assertEquals(5.99 + 1.25, item.getCost(), CENT);
    }

    @Test
    void toppingsStackInTheOrderTheyWereAdded() {
        FoodItem item = new Onions(new Bacon(new Cheese(new Burger())));
        assertEquals("Burger with Cheese, Bacon, Onions", item.getDescription());
        assertEquals(5.99 + 0.75 + 1.25 + 0.50, item.getCost(), CENT);
    }

    @Test
    void sameToppingCanBeAddedTwice() {
        FoodItem item = new Cheese(new Cheese(new Burger()));
        assertEquals("Burger with Cheese, Cheese", item.getDescription());
        assertEquals(5.99 + 0.75 + 0.75, item.getCost(), CENT);
    }

    @Test
    void toppingNeedsAFoodItem() {
        assertThrows(IllegalArgumentException.class, () -> new Ketchup(null));
        assertThrows(IllegalArgumentException.class, () -> new Mustard(null));
        assertThrows(IllegalArgumentException.class, () -> new Cheese(null));
        assertThrows(IllegalArgumentException.class, () -> new Onions(null));
        assertThrows(IllegalArgumentException.class, () -> new Bacon(null));
    }
}
