package restaurant;

public class Bacon extends ToppingDecorator {
    public Bacon(FoodItem item) {
        super(item, "Bacon", 1.25);
    }
}
