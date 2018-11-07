package model;


import java.util.ArrayList;

public class Refrigerator extends Storage{

    public Refrigerator(int capacity){
        super(capacity);
    }

    @Override
    public void label() {
        System.out.println("In your fridge you have: ");
    }

    //add to have do inFridge, if not add

}

//    private boolean inFridge(String name){
//        for (Food h: have) {
//            if (h.getName().equals(name)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public int howMany(String name) {
//        for(Food h: have){
//            if(h.getName().equals(name)){
//                return h.getAmount();
//            }
//        }
//        return 0;
//    }
//
//    //REQUIRES inFridge to be true
//    //MODIFIES object in list
//    //EFFECTS: adds num to the amount of a given Food
//    public int addMore(int num, String name) {
//        if(inFridge(name)){
//            have.set(getFoodIndex(name), new Food(name, num+have.get(getFoodIndex(name)).getAmount()));
//        }
//        return 0;
//    }
//
//    //REQUIRES inFridge to be true
//    //MODIFIES object in list
//    //EFFECTS subtracts n to the amount of given Food
//    public int addLess(int num, String name) {
//        if(inFridge(name)){
//            have.set(getFoodIndex(name), new Food(name, have.get(getFoodIndex(name)).getAmount()-num));
//        }
//        return 0;
//    }
//
//
//    //EFFECTS returns the index number of a food in have, if not in list returns -1
//    public int getFoodIndex(String name){
//        for(int i=0; i<have.size(); i++) {
//            if (have.get(i).getName().equals(name)) {
//                return i;
//            }
//        }
//        return -1;
//    }





