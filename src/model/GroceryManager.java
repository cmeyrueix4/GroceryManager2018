package model;

import model.exceptions.CategoryException;
import ui.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class GroceryManager extends Observable implements Loadable, Saveable {
    private Map<Food, Integer> needbuy;
    private Refrigerator r;
    private Freezer fr;
    private Cupboard c;

    public GroceryManager(){
        needbuy = new HashMap<>();
        r = new Refrigerator(100);
        fr = new Freezer(100);
        c = new Cupboard(100);

        addObserver(r);
        addObserver(fr);
        addObserver(c);
    }

    //MODIFIES: needbuy list
    //EFFECTS: adds a food item to the needbuy list
    public void addFoodBuy(Food food, int amount) {
        int n = amount;
        if (needbuy.containsKey(food)) {
            if (amount == 0) {
                return;
            }
            n = needbuy.get(food) + n;
        }
        needbuy.put(food, n);
    }

    //EFFECTS: creates a new Food item
    public Food createFoodItem(String name, String category) throws CategoryException {
        FoodCategory cat = FoodCategory.parseCategory(category);
        return new Food(name, cat);
    }

    //class called logging which would be an observer, addobserver

    //MODIFIES: have list in either a freezer, fridge or cupboard
    //EFFECTS: adds a food item to the have list in either a freezer, cupboard, or fridge
    //         if item is already in the list then the amount is increased
    public void addFoodBought(String stored, Food item, int amount) {
        switch (stored.toLowerCase()) {
            case "cupboard":
                c.addToStorage(item, amount);
                setChanged();
                notifyObservers("cupboard");
                break;
            case "fridge":
                r.addToStorage(item, amount);
                setChanged();
                notifyObservers("fridge");
                break;
            case "freezer":
                fr.addToStorage(item, amount);
                setChanged();
                notifyObservers("freezer");
                break;

        }
    }

    //EFFECTS: prints out the needbuy list
    public void printToBuy() {
        System.out.println("You need to buy ");
        for (Map.Entry<Food, Integer> f : needbuy.entrySet()) {
            System.out.println("\t" +f.getValue() + " " + f.getKey().getName());
        }
    }

    //EFFECTS: prints out the list of things in storage
    public void printPurchased() {
        r.print();
        fr.print();
        c.print();
    }

    public Map<Food, Integer> getNeedbuy() {
        return needbuy;
    }

    private void setNeedbuy(Map<Food, Integer> needbuy) {
        this.needbuy = needbuy;
    }


    //EFFECTS: checks the capacity of a storage
    public void checkCapacity(String stored){
        switch (stored.toLowerCase()) {
            case "cupboard":
                System.out.println(c.getCapacity()+"% storage left");
                break;
            case "fridge":
                System.out.println(r.getCapacity()+"% storage left");
                break;
            case "freezer":
                System.out.println(fr.getCapacity()+"% storage left");
                break;
        }
    }

    //REQUIRES: needbuy to not be empty
    //MODIFIES: this
    //EFFECTS: removes items from needbuy list and stores them in the proper storage
    public void goShopping(){
        for (Map.Entry<Food, Integer> n : needbuy.entrySet()) {
            System.out.println("Please enter where " + n.getKey().getName() + " should be stored: fridge, freezer, or cupboard");
            String stored = Main.scanner.nextLine();
            addFoodBought(stored, n.getKey(), n.getValue());
        }
        needbuy.clear();
    }

    public void load() throws IOException, CategoryException {
        Path path = Paths.get("needbuy.txt");
        loadNeedBuy(path);

        path = Paths.get("freezer.txt");
        loadEachStorageList(path, fr);

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("fridge.txt");
        loadEachStorageList(path, r);

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("cupboard.txt");
        loadEachStorageList(path, c);
    }

    private void loadNeedBuy(Path path) throws IOException, CategoryException {
        List<String> lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnComma(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, category);
            needbuy.put(f, amount);
        }
    }

    private void loadEachStorageList(Path path, Storage st) throws IOException, CategoryException {
        List<String> lines;
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnComma(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
            Food f = new Food(name, category);
            st.addToStorage(f, amount);
        }
    }


    private static ArrayList<String> splitOnComma(String line) {
        String[] splits = line.split(", ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public void save() throws IOException {
        PrintWriter writer = new PrintWriter("needbuy.txt", "UTF-8");
        for (Map.Entry<Food, Integer> f : needbuy.entrySet()) {
            writer.println(f.getKey().getName() + ", " + f.getValue() + ", " + f.getKey().getCategory() + ", ");
        }
        writer.close();

        saveStorageList("freezer.txt", fr.getHave());

        saveStorageList("fridge.txt", r.getHave());

        saveStorageList("cupboard.txt", c.getHave());
    }

    private void saveStorageList(String filename, Map<Food, Integer> have) throws IOException {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for (Food f : have.keySet()) {
            writer.println(f.getName() + ", " + f.getAmount() + ", " + f.getCategory()+ ", ");
        }
        writer.close();
    }


    public void printInFridge() {
        r.print();
        System.out.println(" is in your fridge");
    }

    public void printInFreezer() {
        fr.print();
        System.out.println(" is in your freezer");
    }

    public void printInCupboard() {
        c.print();
        System.out.println(" is in your cupboard");
    }

    public Refrigerator getR() {
        return r;
    }

    public Freezer getFr() {
        return fr;
    }

    public Cupboard getC() {
        return c;
    }
}