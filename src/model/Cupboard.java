package model;

public class Cupboard extends Storage{

    public Cupboard(int capacity) {
        super(capacity);
    }

    @Override
    public void label() {
        System.out.println("In your cupboard you have: ");
    }

}

