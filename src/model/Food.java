package model;


public class Food {

    private FoodCategory category;
    private String name;
    private int amount;

    public Food(String name, int amount, FoodCategory category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public Food(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}