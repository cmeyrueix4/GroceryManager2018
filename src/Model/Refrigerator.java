package Model;


public class Refrigerator {

    private int capacity;
    private Food have;

    public Refrigerator(int capacity, Food have){
        this.capacity = capacity;
        this.have = have;
    }

    public Food getHave() {
        return have;
    }

    public void setHave(Food have) {
        this.have = have;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}




