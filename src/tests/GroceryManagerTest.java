package tests;


import model.*;
import model.exceptions.CategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GroceryManagerTest {

    GroceryManager testGroceryManager;
    Food egg;
    Food egg2;
    Refrigerator r;
    Freezer f;
    Cupboard c;

    @BeforeEach
    public void runBefore(){
        testGroceryManager = new GroceryManager();
    }

    @Test
    public void testAddFoodBuy(){
        egg = new Food("Egg",FoodCategory.MEAT);
        testGroceryManager.addFoodBuy(egg,5);
        assertEquals(testGroceryManager.getNeedbuy().size(), 1);
    }

    @Test
    public void testCreateNewFoodItem() {
        String name = "Rice";
        String category = "Grain";
        try {
            testGroceryManager.createFoodItem(name, category);
        } catch (CategoryException e) {
            fail("");
        }
    }


    @Test
    public void testCreateNewFoodItemThrowCategoryException(){
        String name = "Rice";
        String category = "3";
        try {
            testGroceryManager.createFoodItem(name, category);
        } catch (CategoryException ignored) {
        }
    }

    @Test
    public void testAddFoodBoughtNotAlreadyInFridge(){
        egg = new Food("Egg",  FoodCategory.MEAT);
        testGroceryManager.addFoodBought("fridge",egg,5);
    }

//    @Test
//    public void testAddFoodBoughtAlreadyInFridge(){
//        egg = new Food("Egg", 5, FoodCategory.MEAT);
//        testGroceryManager.addFoodBought("fridge", egg);
//
//
//
//        egg2 = new Food("Egg", 5, FoodCategory.MEAT);
//        testGroceryManager.addFoodBought("fridge", egg2);
//    }
//
//    @Test
//    public void testAddFoodBoughtNotAlreadyInFreezer(){
//
//    }
//
//    @Test
//    public void testAddFoodBoughtAlreadyInFreezer(){
//
//    }
//
//    @Test
//    public void testAddFoodBoughtNotAlreadyInCupboard(){
//
//    }
//
//    @Test
//    public void testAddFoodBoughtAlreadyInCupboard(){
//
//    }

//    @Test
//    public void testPrintToBuy(){
//        testGroceryManager.addFoodBuy(egg);
// .equals with a string we are expecting
//
//    }

    @Test
    public void testPrintWhereStored(){

    }

    @Test
    public void testPrintPurchased(){

    }


//    @Test
//    public void testAddFoodFridge(){
//        testGroceryManager.addFoodFridge(egg);
//        assertEquals(testGroceryManager.getBought().size(), 1);
//    }

}
