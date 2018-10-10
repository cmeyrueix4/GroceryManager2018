package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GroceryManager implements Loadable, Saveable {
    ArrayList<Food> needbuy;
    private Refrigerator r;
    private Freezer fr;
    private Cupboard c;

    public GroceryManager() {
        needbuy = new ArrayList<>();
        r = new Refrigerator(0);
        fr = new Freezer(0);
        c = new Cupboard(0);
    }

    public void loadBuy() throws IOException {
        Path path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("needbuy.txt");
        List<String> lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.convert(partsofLine.get(2));
            Food f = new Food(name, amount, category);
            needbuy.add(f);
        }

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("freezer.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.convert(partsofLine.get(2));
            Food f = new Food(name, amount, category);
            fr.addToStorage(f);
        }

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("fridge.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.convert(partsofLine.get(2));
            Food f = new Food(name, amount, category);
            r.addToStorage(f);
        }

        path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("cupboard.txt");
        lines = Files.readAllLines(path);//create two input files one bought and needbuy
        for (String s : lines) {
            ArrayList<String> partsofLine = splitOnSpace(s);
            String name = partsofLine.get(0);
            int amount = Integer.parseInt(partsofLine.get(1));
            FoodCategory category = FoodCategory.convert(partsofLine.get(2));
            Food f = new Food(name, amount, category);
            c.addToStorage(f);
        }
    }


    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public void saveBuy() throws IOException {
        PrintWriter writer = new PrintWriter("needbuy.txt","UTF-8");
        for(Food f:needbuy){
            writer.println(f.getName()+" "+f.getAmount()+" " + f.getCategory() + " ");
         }
        writer.close();

        writer = new PrintWriter("freezer.txt","UTF-8");
        for(Food f: fr.getHave()){
            writer.println(f.getName()+" "+f.getAmount()+" " + f.getCategory());
        }
        writer.close();

        writer = new PrintWriter("fridge.txt","UTF-8");
        for(Food f: r.getHave()){
            writer.println(f.getName()+" "+f.getAmount()+" " + f.getCategory());
        }
        writer.close();

        writer = new PrintWriter("cupboard.txt","UTF-8");
        for(Food f: c.getHave()){
            writer.println(f.getName()+" "+f.getAmount()+" " + f.getCategory());
        }
        writer.close();
    }


    //MODIFIES: needbuy list
    //EFFECTS: adds a food item to the needbuy list
    public void addFoodBuy(Food food) {
        needbuy.add(food);
    }

    //MODIFIES: have list in either a freezer, fridge or cupboard
    //EFFECTS: adds a food item to the have list in either a freezer, cupboard, or fridge
    public void addFoodBought(String stored, Food item){
        if(stored.equals("cupboard")){
            c.addToStorage(item);

        }

        else if (stored.equals("fridge")){
            r.addToStorage(item);
        }

        else if (stored.equals("freezer")){
            fr.addToStorage(item);
        }
    }

//    //MODIFIES: bought list SHOULD CALL FRIDGE
//    //EFFECTS: adds a food item to the bought list
//    public void addFoodFridge(Food food) {
//        bought.add(food);
//    }

    //EFFECTS: prints out the needbuy list
    public void printToBuy() {
        System.out.println("You need to buy ");
        for (Food f : needbuy) {
            System.out.println(f.getAmount() + " " + f.getName());
        }
    }

    //EFFECTS: prints out the bought list with a specific statement for each storage type
    public void printWhereStored() {
        for(Food f: fr.getHave()){
            fr.label(f);
        }

        for (Food f : r.getHave()) {
            r.label(f);
        }

        for (Food f: c.getHave()){
            c.label(f);
        }
    }

    //EFFECTS: prints out the list of things in storage
    public void printPurchased(){
        System.out.println("You currently have:");

        for(Food f: fr.getHave()){
            System.out.println(f.getAmount() + " " + f.getName());
        }

        for (Food f : r.getHave()) {
            System.out.println(f.getAmount() + " " + f.getName());
        }

        for (Food f: c.getHave()){
            System.out.println(f.getAmount() + " " + f.getName());
        }
    }











//    //EFFECTS: returns lines of list
//    public ArrayList<String> toBuy() {
//        ArrayList<String> saveBuy = new ArrayList<>();
//
//        for (Food f : needbuy) {
//            saveBuy.add(f.getName() + " " + f.getAmount());
//        }
//
//        return saveBuy;
//    }

    // goShopping function goes through needbuy and adds it to fridge
//    public void goShopping(){
//        for (Food f: needbuy, c.getHave(), r.getHave(), fr.getHave())
//            if( )
//    }

    public ArrayList<Food> getNeedbuy() {
        return needbuy;
    }

    private void setNeedbuy(ArrayList<Food> needbuy) {
        this.needbuy = needbuy;
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

//        Path path = Paths.get("C://Users/Cyrielle/Desktop").resolve("needbuy.txt");
//
//        Files.write(path, toBuy(), Charset.defaultCharset());
//    }