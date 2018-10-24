package ui;

import model.*;
import model.exceptions.CategoryException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, CategoryException {
        GroceryManager grocerylist = new GroceryManager();
        String operation;
        grocerylist.loadBuy();

        while (true) {

            System.out.println("Please type an option: buy, bought, view, or quit");
            operation = scanner.nextLine();
            System.out.println("you selected: " + operation);

            if (operation.equals("buy")) {
                boolean properFoodItem = false;
                Food newFoodItem = null;
                while (!properFoodItem) {
                    System.out.println("Please enter what you need to buy: ");
                    String name = scanner.nextLine();
                    System.out.println("Please enter how many you would like to buy: ");
                    String amount = scanner.nextLine();
                    System.out.println("Please enter the category (Dairy, Meat, Vegetable, Fruit, Grain, Sweets, Other): ");
                    String category = scanner.nextLine();
                    try {
                        grocerylist.createFoodItem(name, amount, category);
                        properFoodItem = true;
                    } catch (CategoryException ce) {
                        properFoodItem = false;
                        System.out.println("Invalid category. Please enter a valid category.");;
                    } catch (NumberFormatException nfe) {
                        properFoodItem = false;
                        System.out.println("Invalid entry. Please enter a number.");
                    }
                }

                grocerylist.addFoodBuy(newFoodItem);

                System.out.println("You need to buy " + newFoodItem.getName() + " in the quantity of: " + newFoodItem.getAmount());


            } else if (operation.equals("bought")) {
                boolean isGoodUserInput = false;
                Food newFoodItem = null;
                while (!isGoodUserInput) {
                    System.out.println("Please enter what you bought: ");
                    String name = scanner.nextLine();
                    System.out.println("Please enter how many you bought: ");
                    String amount = scanner.nextLine();
                    System.out.println("Please enter the category (Dairy, Meat, Vegetable, Fruit, Grain, Sweets, Other): ");
                    String category = scanner.nextLine();
                    try {
                        newFoodItem = grocerylist.createFoodItem(name, amount, category);
                        isGoodUserInput = true;
                    } catch (CategoryException ce) {
                        isGoodUserInput = false;
                        System.out.println("Invalid entry. Please enter a valid category.");
                    } catch (NumberFormatException nfe) {
                        isGoodUserInput = false;
                        System.out.println("Invalid entry. Please enter a number.");
                    }finally {
                        System.out.println("Bon appetit!!");
                    }

                }
                System.out.println("Please enter where it should be stored: fridge, freezer, or cupboard");
                String stored = scanner.nextLine();
                grocerylist.addFoodBought(stored, newFoodItem);
            }

            //add storage todo
            else if (operation.equals("view")) {
                grocerylist.printToBuy();
                grocerylist.printPurchased();
            } else if (operation.equals("quit")) {
                grocerylist.saveBuy();
                break;
            }
        }
        grocerylist.printToBuy();
        grocerylist.printWhereStored();
    }

}




