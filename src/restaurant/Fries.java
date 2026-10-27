package restaurant;

public class Fries implements FoodItem {
    @Override
    public String getDescription() {
        return "Fries";
    }

    @Override
    public double getCost() {
        return 2.49;
    }
}
