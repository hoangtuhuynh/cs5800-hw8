package restaurant;

public class Burger implements FoodItem {
    @Override
    public String getDescription() {
        return "Burger";
    }

    @Override
    public double getCost() {
        return 5.99;
    }
}
