package restaurant;

public class HotDog implements FoodItem {
    @Override
    public String getDescription() {
        return "Hot Dog";
    }

    @Override
    public double getCost() {
        return 3.49;
    }
}
