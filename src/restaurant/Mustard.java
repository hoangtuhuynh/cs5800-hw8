package restaurant;

public class Mustard extends ToppingDecorator {
    public Mustard(FoodItem item) {
        super(item, "Mustard", 0.25);
    }
}
