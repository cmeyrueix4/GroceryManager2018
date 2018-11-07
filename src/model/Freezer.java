package model;

public class Freezer extends Storage {

    public Freezer(int capacity) {
        super(capacity);
    }

    @Override
    public void label() {
        System.out.println("In your freezer you have: ");
    }


}

