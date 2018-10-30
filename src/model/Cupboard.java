package model;

import java.util.ArrayList;

public class Cupboard extends Storage{

    public Cupboard(int capacity) {
        super(capacity);
    }

    @Override
    public void label(Food food) {
        System.out.println(food.getAmount() + " " + food.getName() + " " + "is now in your cupboard!");
    }

}

