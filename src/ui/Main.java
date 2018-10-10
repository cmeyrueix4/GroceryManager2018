package ui;

import Model.*;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        GroceryManager grocerylist = new GroceryManager();
        Food item;
        String operation;
        grocerylist.loadBuy();

        while (true) {

            System.out.println("Please type an option: buy, bought, view, or quit");
            operation = scanner.nextLine();
            System.out.println("you selected: " + operation);

            if (operation.equals("buy")) {
                System.out.println("Please enter what you need to buy: ");
                String name = scanner.nextLine();
                System.out.println("Please enter how many you would like to buy: ");
                int amount = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the category (Dairy, Meat, Vegetable, Fruit, Grain, Sweets, Other): ");
                String category = scanner.nextLine();
                item = new Food(name, amount, FoodCategory.convert(category));

                grocerylist.addFoodBuy(item);

                System.out.println("You need to buy " + name + " in the quantity of: " + amount);

            } else if (operation.equals("bought")) {
                System.out.println("Please enter what you bought: ");
                String name = scanner.nextLine();
                System.out.println("Please enter how many you bought: ");
                int amount = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the category (Dairy, Meat, Vegetable, Fruit, Grain, Sweets, Other): ");
                String category = scanner.nextLine();
                item = new Food(name, amount, FoodCategory.convert(category));

                System.out.println("Please enter where it should be stored: fridge, freezer, or cupboard");
                String stored = scanner.nextLine();
                grocerylist.addFoodBought(stored, item);

          //      System.out.println(amount + " " + name + " has been added to your fridge");
//                item = new Food(name, amount, FoodCategory.convert(category));
//
//                grocerylist.addFoodFridge(item);

            }

            else if (operation.equals("view")){
                grocerylist.printToBuy();
                grocerylist.printPurchased();
            }

            else if (operation.equals("quit")) {
                //viewState
                grocerylist.saveBuy();
                break;
            }
        }
        grocerylist.printToBuy();
        grocerylist.printWhereStored();
    }

}




