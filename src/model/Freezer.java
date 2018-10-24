package model;

import java.util.ArrayList;

public class Freezer extends Storage {

    public Freezer(int capacity) {
        super(capacity);
    }

    @Override
    public void label(Food food) {
        System.out.println(food.getAmount() + " " + food.getName() + " " + "is now in your freezer!");
    }

    public ArrayList<Food> getHave() {
        return this.have;
    }

}

