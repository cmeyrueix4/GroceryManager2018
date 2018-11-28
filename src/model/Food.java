package model;


import java.util.Objects;

public class Food {

    private FoodCategory category;
    private String name;
    private Storage s;
    private GroceryManager gm;

    public Food(String name, FoodCategory category) {
        this.name = name;
        this.category = category;
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

    public int getAmountS() {
        return s.howMany(name);
    }

    public int getAmountN(){
        return gm.howMany(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return category == food.category &&
                Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(category, name);
    }

    public void setStorage(Storage s) {
        this.s = s;
        this.s.addToStorage(this, 0);
    }

    public void setGm(GroceryManager gm) {
        this.gm = gm;
    }

}