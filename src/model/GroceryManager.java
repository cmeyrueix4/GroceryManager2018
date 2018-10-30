package model;

import model.exceptions.CategoryException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class GroceryManager implements Loadable, Saveable {
    private Map<Food, Integer> needbuy;
    private Refrigerator r;
    private Freezer fr;
    private Cupboard c;

    public GroceryManager() {
        needbuy = new HashMap<>();
        r = new Refrigerator(0);
        fr = new Freezer(0);
        c = new Cupboard(0);
    }

    public void loadBuy() throws IOException, CategoryException {
        Path path = Paths.get("needbuy.txt");
        List<String> lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, category);
            needbuy.put(f, amount);
        }

        path = Paths.get("freezer.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, category);
            fr.addToStorage(f, amount);
        }

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("fridge.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, category);
            r.addToStorage(f, amount);
        }

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("cupboard.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, category);
            c.addToStorage(f, amount);
        }
    }


    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public void saveBuy() throws IOException {
        PrintWriter writer = new PrintWriter("needbuy.txt", "UTF-8");
        for (Map.Entry<Food, Integer> f : needbuy.entrySet()) {
            writer.println(f.getKey().getName() + " " + f.getValue() + " " + f.getKey().getCategory() + " ");
        }
        writer.close();

        writer = new PrintWriter("freezer.txt", "UTF-8");
        for (Food f : fr.getHave().keySet()) {
            writer.println(f.getName() + " " + f.getAmount() + " " + f.getCategory());
        }
        writer.close();

        writer = new PrintWriter("fridge.txt", "UTF-8");
        for (Food f : r.getHave().keySet()) {
            writer.println(f.getName() + " " + f.getAmount() + " " + f.getCategory());
        }
        writer.close();

        writer = new PrintWriter("cupboard.txt", "UTF-8");
        for (Food f : c.getHave().keySet()) {
            writer.println(f.getName() + " " + f.getAmount() + " " + f.getCategory());
        }
        writer.close();
    }


    //MODIFIES: needbuy list
    //EFFECTS: adds a food item to the needbuy list
    public void addFoodBuy(Food food, int amount) {
        int n = amount;
        if(needbuy.containsKey(food)) {
            if(amount == 0) {
                return;
            }
            n = needbuy.get(food)+n;
        }
        needbuy.put(food, n);
    }

    //EFFECTS: creates a new Food item
    public Food createFoodItem(String name, String category) throws CategoryException {
        FoodCategory cat = FoodCategory.parseCategory(category);
        return new Food(name, cat);
    }

    //MODIFIES: have list in either a freezer, fridge or cupboard
    //EFFECTS: adds a food item to the have list in either a freezer, cupboard, or fridge
    //         if item is already in the list then the amount is increased
    public void addFoodBought(String stored, Food item, int amount) {
        switch (stored.toLowerCase()) {
            case "cupboard":
                c.addToStorage(item, amount);
                break;
            case "fridge":
                r.addToStorage(item, amount);
                break;
            case "freezer":
                fr.addToStorage(item, amount);
                break;
        }
    }

    //EFFECTS: prints out the needbuy list
    public void printToBuy() {
        System.out.println("You need to buy ");
        for (Map.Entry<Food, Integer> f : needbuy.entrySet()) {
            System.out.println(f.getValue() + " " + f.getKey().getName());
        }
    }

    //EFFECTS: prints out the bought list with a specific statement for each storage type
    public void printWhereStored() {
        printInFridge();
        System.out.println(" is in your fridge");
        printInCupboard();
        System.out.println(" is in your cupboard");
        printInFreezer();
        System.out.println(" is in your freezer");
    }

    //EFFECTS: prints out the list of things in storage
    public void printPurchased() {
        System.out.println("You currently have:");

        fr.print();

        r.print();

        c.print();
    }

    public void printInFridge(){
        r.print();
    }

    public void printInFreezer(){
        fr.print();
    }

    public void printInCupboard(){
        c.print();
    }

    public Map<Food, Integer> getNeedbuy() {
        return needbuy;
    }

    private void setNeedbuy(Map<Food, Integer> needbuy) {
        this.needbuy = needbuy;
    }
}

     //TODO goShopping function goes through needbuy and adds it to fridge
    //for each item food.getName()
    //
//    public void boughtShopping(String name) {
//        int index= -1;
//        for (int i = 0; i < needbuy.size(); i++) {
//            if needbuy.get(i).getName() = name
//                    index = i;
//        }
//        if (index !=-1){
//            needbuy.remove(index);
//        }
//    }
//        if(needbuy.contains(food.getName()) && food.getName().equals(name)){
//            needbuy.remove(food);
//        }




//        for (Food f: needbuy, c.getHave(), r.getHave(), fr.getHave())
//            if( )