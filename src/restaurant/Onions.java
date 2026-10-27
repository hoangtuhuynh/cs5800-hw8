package restaurant;

public class Onions extends ToppingDecorator {
    public Onions(FoodItem item) {
        super(item, "Onions", 0.50);
    }
}
