package Model;

import java.util.ArrayList;

public class Cupboard extends Storage{

    private int capacity;

    public Cupboard(int capacity) {
        super(capacity);
    }

    @Override
    public void label(Food food) {
        System.out.println(food.getAmount() + " " + food.getName() + " " + "has now been added to your cupboard!");
    }

    public ArrayList<Food> getHave() {
        return have;
    }

    private void setHave(ArrayList<Food> have) {
        this.have = have;
    }

    private int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

