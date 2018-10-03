package Model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GroceryManager implements Loadable, Saveable{
    ArrayList<Food> needbuy;
    ArrayList<Food> bought;

    public GroceryManager(){
        needbuy = new ArrayList<>();
        bought = new ArrayList<>();
    }

    public void loadBuy() throws IOException {
        Path path = Paths.get("C://Users/Cyrielle/Desktop").resolve("needbuy.txt");
        List<String> lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            Food f = new Food(name, amount);
            needbuy.add(f);
        }
    }


    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void saveBuy() throws IOException {
        Path path = Paths.get("C://Users/Cyrielle/Desktop").resolve("needbuy.txt");

        Files.write(path, toBuy(), Charset.defaultCharset());
    }

    //MODIFIES: needbuy list
    //EFFECTS: adds a food item to the needbuy list
    public void addFoodBuy(Food food) {
        needbuy.add(food);
    }


    //MODIFIES: bought list
    //EFFECTS: adds a food item to the bought list
    public void addFoodFridge(Food food) {
        bought.add(food);
    }

    //EFFECTS: prints out the needbuy list
    public void printToBuy() {
        System.out.println("You need to buy ");
        for (Food f : needbuy) {
            System.out.println(f.getAmount() + " " + f.getName());
        }
    }

    //EFFECTS: prints out the bought list
    public void printPurchased() {
        System.out.println("The following are now in your fridge: ");
        for (Food f : bought) {
            System.out.println(f.getAmount() + " " + f.getName());
        }
    }

    //EFFECTS: returns lines of list
    public ArrayList<String> toBuy(){
        ArrayList<String> saveBuy = new ArrayList<>();

        for(Food f: needbuy){
            saveBuy.add(f.getName() +" "+f.getAmount());
        }

        return saveBuy;
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

}

//    public void loadBought() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("bought.txt"));//create two input files one bought and needbuy
//        for (String s : lines) {
//            ArrayList<String> partsofLine = splitOnSpace(s);
//            String name = partsofLine.get(0);
//            int amount = Integer.parseInt(partsofLine.get(1));
//            Food f = new Food(name, amount);
//            bought.add(f);
//        }
//    }

//        PrintWriter writer = new PrintWriter("needbuy.txt","UTF-8");
//        for(Food f:needbuy){
//            writer.println(f.getName()+" "+f.getAmount()+" ");
//         }
//
//        writer.close();

//
//    public void saveBought() throws IOException {
//        PrintWriter writer = new PrintWriter("bought.txt","UTF-8");
//        for(Food f:needbuy){
//            writer.println(f.getName()+" "+f.getAmount());
//        }
//
//        writer.close();
//    }