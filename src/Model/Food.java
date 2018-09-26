package Model;


public class Food {

    private String category;
    private String name;
    private int amount;

    public Food(String name, int amount, String category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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