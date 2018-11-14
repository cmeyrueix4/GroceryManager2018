package model;

import model.exceptions.CategoryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class Storage implements Observer {

    protected int capacity;
    protected Map<Food, Integer> have;
    protected String textFile;

    public Storage(int capacity) {
//        this.textFile = textFile;
        this.capacity = capacity;
        this.have = new HashMap<>();
//        load();
    }


//    private void load(Path path) throws IOException, CategoryException {
//        Path path = Paths.get(textFile);
//        List<String> lines;
//        lines = Files.readAllLines(path);//create two input files one bought and needbuy
//        for (String s : lines) {
//            ArrayList<String> partsofLine = splitOnSpace(s);
//            String name = partsofLine.get(0);
//            int amount = Integer.parseInt(partsofLine.get(1));
//            FoodCategory category = FoodCategory.parseCategory(partsofLine.get(2));
//            Food f = new Food(name, category);
//            st.addToStorage(f, amount);
//        }
//    }

//    private static ArrayList<String> splitOnSpace(String line) {
//        String[] splits = line.split(" ");
//        return new ArrayList<>(Arrays.asList(splits));
//    }

    @Override
    public void update(Observable o, Object arg){
        System.out.println("An item has been added to" + arg);
    }

    //MODIFIES: this
    //EFFECTS: adds a food to have list and decreases capacity in storage
    public void addToStorage(Food food, int quantity) {
        int n = quantity;
        if (n == 0) {
            return;
        }
        if (this.have.containsKey(food)) {
            n = this.have.get(food) + n;
        }
        this.have.put(food, n);
        food.setStorage(this);

        decreaseCapacity(food);
    }

    public abstract void label();

    //EFFECTS: checks if list have is empty
    public boolean isEmpty() {
        return have.isEmpty();
    }


    //EFFECTS: checks if a food name is already in list
    public boolean inStorage(String name) {
        for (Food h : have.keySet()) {
            if (h.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: checks to see if amount of food is null and returns 0, if not null then returns amount i
    public int howMany(Food food) {
        Integer i = have.get(food);
        if (i == null) {
            return 0;
        }
        return i;
    }

    //EFFECTS: returns the amount of a given food
    public int howMany(String name) {
        for (Food h : have.keySet()) {
            if (h.getName().equals(name)) {
                return howMany(h);
            }
        }
        return 0;
    }

    //REQUIRES inStorage to be true
    //MODIFIES object in list
    //EFFECTS: adds num to the amount of a given Food
    public void addMore(int num, String name, FoodCategory category) {
        if (inStorage(name)) {
            this.addToStorage(new Food(name, category), num);
        }
    }

    //REQUIRES inFridge to be true
    //MODIFIES object in list
    //EFFECTS subtracts n to the amount of given Food
    public void remove(int num, String name, FoodCategory category) {
        if (inStorage(name)) {
            this.addToStorage(new Food(name, category), -num);
        }
    }

    //REQUIRES: an item to be in storage
    //MODIFIES: this
    //EFFECTS: when an item is added to storage, capacity is increased based on amount
    public void decreaseCapacity(Food food) {
//        for (Map.Entry<Food, Integer> e : have.entrySet()) {
//        for (int i = 0; i <= food.getAmount(); i++) {
            capacity = capacity - food.getAmount();

    }




    //EFFECTS: prints the items in the storage
    public void print() {
        label();
        for (Map.Entry<Food, Integer> e : have.entrySet()) {
            System.out.println("\t" +e.getKey().getName() + " " + e.getKey().getCategory() + ": " + e.getValue());
        }
    }


    public Map<Food,Integer> getHave() {
        return have;
    }

    public void setHave(Map<Food, Integer> have) {
        this.have = have;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}