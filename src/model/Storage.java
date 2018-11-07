package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Storage {

    protected int capacity;
    protected Map<Food, Integer> have;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.have = new HashMap<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a food to have list
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

    public void print() {
        label();
        for (Map.Entry<Food, Integer> e : have.entrySet()) {
            System.out.println(e.getKey().getName() + " " + e.getKey().getCategory() + ": " + e.getValue());
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

//
//
//    //EFFECTS returns the index number of a food in have, if not in list returns -1
//    public int getFoodIndex(String name) {
//        for (int i = 0; i < have.size(); i++) {
//            if (have.get(i).getName().equals(name)) {
//                return i;
//            }
//        }
//        return -1;
//    }