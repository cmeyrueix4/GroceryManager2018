package ui;

import Refrigerator.Food;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Food> buy = new ArrayList<>();
        ArrayList<Food> fridge = new ArrayList<>();
        String operation = "";
        while (true) {
            System.out.println("Please type an option: buy, bought, or quit");
            operation = scanner.nextLine();
            System.out.println("you selected: " + operation);

            if (operation.equals("buy")) {
                buy.add(toBuy());
            }

            if (operation.equals("bought")) {
                fridge.add(addFridge());
            }

            else if (operation.equals("quit")) {
                break;
            }
        }
        for (Food f : buy) {
            System.out.println("You need to buy " + f.amount + " " + f.name);
        }

        for (Food f : fridge) {
            System.out.println(f.amount + " " + f.name + " is now in your fridge");
        }


    }

    private static Food toBuy() {
        System.out.println("Please enter what you need to buy: ");
        String name = scanner.next();
        System.out.println("Please enter how many you would like to buy: ");
        int amount = scanner.nextInt();
        scanner.nextLine();


        System.out.println("You need to buy " + name + " in the quantity of: " + amount);
        return new Food(name, amount);
    }

    private static Food addFridge() {
        System.out.println("Please enter what you bought: ");
        String name = scanner.next();
        System.out.println("Please enter how many you bought: ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println(amount + " " + name + " has been added to your fridge");
        return new Food(name, amount);
    }

}



