package restaurant;

public abstract class ToppingDecorator implements FoodItem {
    private final FoodItem item;
    private final String name;
    private final double price;

    protected ToppingDecorator(FoodItem item, String name, double price) {
        if (item == null) {
            throw new IllegalArgumentException("A topping needs a food item to go on");
        }
        this.item = item;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getDescription() {
        String separator = item instanceof ToppingDecorator ? ", " : " with ";
        return item.getDescription() + separator + name;
    }

    @Override
    public double getCost() {
        return item.getCost() + price;
    }
}
