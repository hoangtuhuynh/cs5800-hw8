package restaurant;

public class Cheese extends ToppingDecorator {
    public Cheese(FoodItem item) {
        super(item, "Cheese", 0.75);
    }
}
