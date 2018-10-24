package model;

import java.util.ArrayList;

public abstract class Storage {

    protected int capacity;
    protected ArrayList<Food> have;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.have = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a food to have list
    public void addToStorage(Food food) {
        this.have.add(food);
    }

    public abstract void label(Food food);

    //EFFECTS: checks if list have is empty
    public boolean isEmpty() {
        return have.isEmpty();
    }


    //EFFECTS: checks if a food name is already in list
    public boolean inStorage(String name) {
        for (Food h : have) {
            if (h.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

//
    //EFFECTS: returns the amount of a given food
    public int howMany(String name) {
        for (Food h : have) {
            if (h.getName().equals(name)) {
                return h.getAmount();
            }
        }
        return 0;
    }

    //REQUIRES inStorage to be true
    //MODIFIES object in list
    //EFFECTS: adds num to the amount of a given Food
    public int addMore(int num, String name, FoodCategory category) {
        if (inStorage(name)) {
            have.set(getFoodIndex(name), new Food(name, num + have.get(getFoodIndex(name)).getAmount(), category));
        }
        return 0;
    }

    //REQUIRES inFridge to be true
    //MODIFIES object in list
    //EFFECTS subtracts n to the amount of given Food
    public int addLess(int num, String name) {
        if (inStorage(name)) {
            have.set(getFoodIndex(name), new Food(name, have.get(getFoodIndex(name)).getAmount() - num));
        }
        return 0;
    }


    //EFFECTS returns the index number of a food in have, if not in list returns -1
    public int getFoodIndex(String name) {
        for (int i = 0; i < have.size(); i++) {
            if (have.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Food> getHave() {
        return have;
    }

    public void setHave(ArrayList<Food> have) {
        this.have = have;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}



//    //REQUIRES: a non-empty list
//    //MODIFIES: this
//    //EFFECTS: checks to see if food is already in list, if true then increases the amount by increase
//    public void addAmountToStorage (Food food, Integer increase){
//        if (have.contains(food.getName()) && !have.isEmpty()){
//            food.setAmount(food.getAmount() + increase);
//        }
//    }