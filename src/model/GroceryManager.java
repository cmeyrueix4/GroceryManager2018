package model;

import model.exceptions.CategoryException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GroceryManager implements Loadable, Saveable {
    ArrayList<Food> needbuy;
    private Refrigerator r;
    private Freezer fr;
    private Cupboard c;

    public GroceryManager() {
        needbuy = new ArrayList<>();
        r = new Refrigerator(0);
        fr = new Freezer(0);
        c = new Cupboard(0);
    }

    public void loadBuy() throws IOException, CategoryException {
        Path path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("needbuy.txt");
        List<String> lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, amount, category);
            needbuy.add(f);
        }

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("freezer.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, amount, category);
            fr.addToStorage(f);
        }

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("fridge.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, amount, category);
            r.addToStorage(f);
        }

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("cupboard.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, amount, category);
            c.addToStorage(f);
        }
    }


    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public void saveBuy() throws IOException {
        PrintWriter writer = new PrintWriter("needbuy.txt", "UTF-8");
        for (Food f : needbuy) {
            writer.println(f.getName() + " " + f.getAmount() + " " + f.getCategory() + " ");
        }
        writer.close();

        writer = new PrintWriter("freezer.txt", "UTF-8");
        for (Food f : fr.getHave()) {
            writer.println(f.getName() + " " + f.getAmount() + " " + f.getCategory());
        }
        writer.close();

        writer = new PrintWriter("fridge.txt", "UTF-8");
        for (Food f : r.getHave()) {
            writer.println(f.getName() + " " + f.getAmount() + " " + f.getCategory());
        }
        writer.close();

        writer = new PrintWriter("cupboard.txt", "UTF-8");
        for (Food f : c.getHave()) {
            writer.println(f.getName() + " " + f.getAmount() + " " + f.getCategory());
        }
        writer.close();
    }


    //MODIFIES: needbuy list
    //EFFECTS: adds a food item to the needbuy list
    public void addFoodBuy(Food food) {
        needbuy.add(food);
    }

    //EFFECTS: creates a new Food item
    public Food createFoodItem(String name, String amount, String category) throws CategoryException, NumberFormatException {
        int amountInt = Integer.parseInt(amount);
        FoodCategory cat = FoodCategory.parseCategory(category);
        return new Food(name, amountInt, cat);
    }

    //MODIFIES: have list in either a freezer, fridge or cupboard
    //EFFECTS: adds a food item to the have list in either a freezer, cupboard, or fridge
    //         if item is already in the list then the amount is increased
    public void addFoodBought(String stored, Food item) {
        if (stored.equals("cupboard") && !c.inStorage(item.getName())) {
            c.addToStorage(item);
        } else if (stored.equals("cupboard") && c.inStorage(item.getName())) {
            c.addMore(item.getAmount(), item.getName(), item.getCategory());
        } else if (stored.equals("fridge") && !r.inStorage(item.getName())) {
            r.addToStorage(item);
        } else if (stored.equals("fridge") && r.inStorage(item.getName())) {
            r.addMore(item.getAmount(), item.getName(), item.getCategory());
        } else if (stored.equals("freezer") && !fr.inStorage(item.getName())) {
            fr.addToStorage(item);
        } else if (stored.equals("freezer") && fr.inStorage(item.getName())) {
            fr.addMore(item.getAmount(), item.getName(), item.getCategory());
        }
    }

    //EFFECTS: prints out the needbuy list
    public void printToBuy() {
        System.out.println("You need to buy ");
        for (Food f : needbuy) {
            System.out.println(f.getAmount() + " " + f.getName());
        }
    }

    //EFFECTS: prints out the bought list with a specific statement for each storage type
    public void printWhereStored() {
        for (Food f : fr.getHave()) {
            fr.label(f);
        }

        for (Food f : r.getHave()) {
            r.label(f);
        }

        for (Food f : c.getHave()) {
            c.label(f);
        }
    }

    //EFFECTS: prints out the list of things in storage
    public void printPurchased() {
        System.out.println("You currently have:");

        for (Food f : fr.getHave()) {
            System.out.println(f.getAmount() + " " + f.getName());
        }

        for (Food f : r.getHave()) {
            System.out.println(f.getAmount() + " " + f.getName());
        }

        for (Food f : c.getHave()) {
            System.out.println(f.getAmount() + " " + f.getName());
        }
    }

    public ArrayList<Food> getNeedbuy() {
        return needbuy;
    }

    private void setNeedbuy(ArrayList<Food> needbuy) {
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