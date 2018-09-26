package ui;

import Model.Food;
import Model.FoodCategory;
import Model.GroceryManager;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GroceryManager grocerylist = new GroceryManager();
        Food item;
        String operation;

        while (true) {
            System.out.println("Please type an option: buy, bought, or quit");
            operation = scanner.nextLine();
            System.out.println("you selected: " + operation);

            if (operation.equals("buy")) {
                System.out.println("Please enter what you need to buy: ");
                String name = scanner.nextLine();
                System.out.println("Please enter how many you would like to buy: ");
                int amount = scanner.nextInt();
                System.out.println("Please enter the category (Dairy, Meat, Vegetable, Fruit, Grain, Sweets, Other): ");
                String category = scanner.nextLine();
                scanner.nextLine();
                item = new Food(name, amount, category);

                grocerylist.addFoodBuy(item);

                System.out.println("You need to buy " + name + " in the quantity of: " + amount);

            } else if (operation.equals("bought")) {
                System.out.println("Please enter what you bought: ");
                String name = scanner.nextLine();
                System.out.println("Please enter how many you bought: ");
                int amount = scanner.nextInt();
                System.out.println("Please enter the category (Dairy, Meat, Vegetable, Fruit, Grain, Sweets, Other): ");
                String category = scanner.nextLine();
                scanner.nextLine();

                System.out.println(amount + " " + name + " has been added to your fridge");
                item = new Food(name, amount, category);

                grocerylist.addFoodFridge(item);
            } else if (operation.equals("quit")) {
                break;
            }
        }
        grocerylist.printToBuy();
        grocerylist.printPurchased();

    }
}



