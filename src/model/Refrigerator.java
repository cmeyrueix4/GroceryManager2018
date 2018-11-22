package model;

public class Refrigerator extends Storage{

    public Refrigerator(int capacity){
        super(capacity);
    }

    @Override
    public void label() {
        System.out.println("In your fridge you have: ");
    }
}