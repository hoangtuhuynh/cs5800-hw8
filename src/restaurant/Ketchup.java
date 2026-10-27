package restaurant;

public class Ketchup extends ToppingDecorator {
    public Ketchup(FoodItem item) {
        super(item, "Ketchup", 0.25);
    }
}
