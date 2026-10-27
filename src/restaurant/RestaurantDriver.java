package restaurant;

import java.util.List;
import java.util.Locale;

public class RestaurantDriver {
    public static void main(String[] args) {
        System.out.println("===== Food items with toppings =====\n");

        FoodItem plainBurger = new Burger();
        FoodItem cheeseBaconBurger = new Bacon(new Cheese(new Burger()));
        FoodItem loadedBurger = new Onions(new Ketchup(new Bacon(new Cheese(new Cheese(new Burger())))));
        FoodItem ketchupFries = new Ketchup(new Fries());
        FoodItem cheeseFries = new Cheese(new Fries());
        FoodItem classicHotDog = new Onions(new Mustard(new Ketchup(new HotDog())));
        FoodItem plainHotDog = new HotDog();

        for (FoodItem item : List.of(plainBurger, cheeseBaconBurger, loadedBurger,
                ketchupFries, cheeseFries, classicHotDog, plainHotDog)) {
            System.out.printf(Locale.US, "  %-52s $%.2f%n", item.getDescription(), item.getCost());
        }
        System.out.println();

        System.out.println("===== Orders with loyalty discounts =====\n");

        Order regular = new Order(new Customer("Alice", LoyaltyStatus.NONE));
        regular.addItem(plainBurger);
        regular.addItem(ketchupFries);

        Order silver = new Order(new Customer("Bob", LoyaltyStatus.SILVER));
        silver.addItem(classicHotDog);
        silver.addItem(plainHotDog);
        silver.addItem(cheeseFries);

        Order gold = new Order(new Customer("Carol", LoyaltyStatus.GOLD));
        gold.addItem(cheeseBaconBurger);
        gold.addItem(ketchupFries);
        gold.addItem(classicHotDog);

        Order platinum = new Order(new Customer("Dave", LoyaltyStatus.PLATINUM));
        platinum.addItem(loadedBurger);
        platinum.addItem(loadedBurger);
        platinum.addItem(cheeseFries);
        platinum.addItem(classicHotDog);

        for (Order order : List.of(regular, silver, gold, platinum)) {
            System.out.println(order.getReceipt());
        }
    }
}
