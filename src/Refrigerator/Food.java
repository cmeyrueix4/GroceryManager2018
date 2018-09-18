package Refrigerator;

public class Food {

    public String name;
    public int amount;

    public Food (String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    public static void foodName() {
        System.out.println("Food");
    }

    public static void foodType() {
        System.out.println("Dairy");
    }
}

