package Model;

import java.util.ArrayList;


public class GroceryManager {
    ArrayList<Food> needbuy = new ArrayList<>();
    ArrayList<Food> bought = new ArrayList<>();


    //MODIFIES: needbuy list
    //EFFECTS: adds a food item to the needbuy list
    public void addFoodBuy(Food food){
        needbuy.add(food);
    }


    //MODIFIES: bought list
    //EFFECTS: adds a food item to the bought list
    public void addFoodFridge(Food food) {
        bought.add(food);
    }

    //EFFECTS: prints out the needbuy list
    public void printToBuy(){
        System.out.println("You need to buy ");
        for (Food f : needbuy) {
            System.out.println(f.getAmount() + " " + f.getName());
        }
    }

    //EFFECTS: prints out the bought list
    public void printPurchased (){
        System.out.println("The following are now in your fridge: ");
        for (Food f : bought) {
            System.out.println(f.getAmount() + " " + f.getName());
        }
    }

    public ArrayList<Food> getNeedbuy() {
        return needbuy;
    }

    public void setNeedbuy(ArrayList<Food> needbuy) {
        this.needbuy = needbuy;
    }

    public ArrayList<Food> getBought() {
        return bought;
    }

    public void setBought(ArrayList<Food> bought) {
        this.bought = bought;
    }

    //    @Override
//    public String toString() {
//        for (Food f : needbuy) {
//            System.out.println("You need to buy " + f.amount + " " + f.name);
//    }
//
}
