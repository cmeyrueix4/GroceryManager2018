package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import model.exceptions.CategoryException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    private Stage primaryStage;
    public Pane mainLayout;

    public static void main(String[] args) throws IOException, CategoryException {
       // createAndShowGUI();
        //GroceryPanel showScreen = new GroceryPanel();
        //showScreen.start();
//        launch(args);
        GroceryManager groceryList = new GroceryManager();
        String operation;
        groceryList.load();

        while (true) {

            System.out.println("Please type an option: buy, bought, view, capacity, went shopping, or quit");
            operation = scanner.nextLine();
            System.out.println("you selected: " + operation);

            if (operation.equals("buy")) {
                boolean properFoodItem = false;
                Food newFoodItem = null;
                int amt = 0;
                String name = null;
                String category = null;
                while (!properFoodItem) {
                    System.out.println("Please enter what you need to buy: ");
                    name = scanner.nextLine();
                    System.out.println("Please enter how many you would like to buy: ");
                    String amount = scanner.nextLine();
                    System.out.println("Please enter the category (Dairy, Meat, Vegetable, Fruit, Grain, Sweets, Other): ");
                    category = scanner.nextLine();
                    try {
                        groceryList.createFoodItem(name, category);
                        amt = Integer.parseInt(amount);
                        properFoodItem = true;
                    } catch (CategoryException ce) {
                        properFoodItem = false;
                        System.out.println("Invalid category. Please enter a valid category.");;
                    } catch (NumberFormatException nfe) {
                        properFoodItem = false;
                        System.out.println("Invalid entry. Please enter a number.");
                    }
                }

               newFoodItem = groceryList.createFoodItem(name, category);

                groceryList.addFoodBuy(newFoodItem, amt);
                System.out.println("You need to buy " + newFoodItem.getName() + " in the quantity of: " + amt);
                groceryList.save();


            } else if (operation.equals("bought")) {
                boolean isGoodUserInput = false;
                Food newFoodItem = null;
                int amt = 0;
                while (!isGoodUserInput) {
                    System.out.println("Please enter what you bought: ");
                    String name = scanner.nextLine();
                    System.out.println("Please enter how many you bought: ");
                    String amount = scanner.nextLine();
                    System.out.println("Please enter the category (Dairy, Meat, Vegetable, Fruit, Grain, Sweets, Other): ");
                    String category = scanner.nextLine();
                    try {
                        newFoodItem = groceryList.createFoodItem(name, category);
                        amt = Integer.parseInt(amount);
                        isGoodUserInput = true;
                    } catch (CategoryException ce) {
                        isGoodUserInput = false;
                        System.out.println("Invalid entry. Please enter a valid category.");
                    } catch (NumberFormatException nfe) {
                        isGoodUserInput = false;
                        System.out.println("Invalid entry. Please enter a number.");
                    }

                }
                System.out.println("Please enter where it should be stored: fridge, freezer, or cupboard");
                String stored = scanner.nextLine();
                groceryList.addFoodBought(stored, newFoodItem, amt);
                groceryList.save();
            }

            else if (operation.equals("view")) {
                groceryList.printToBuy();
                groceryList.printPurchased();
            }
            else if(operation.equals("capacity")){
                System.out.println("Please enter what storage you would like to check: fridge, freezer, or cupboard");
                String stored = scanner.nextLine();
                groceryList.checkCapacity(stored);
            }

            else if(operation.equals("went shopping")){
                groceryList.goShopping();
            }

            else if (operation.equals("quit")) {
                groceryList.save();
                break;
            }
        }
        groceryList.printToBuy();
        groceryList.printPurchased();
    }

}




