package Tests;


import Model.Food;
import Model.GroceryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroceryManagerTest {

    GroceryManager testGroceryManager;
    Food egg;

    @BeforeEach
    public void runBefore(){
        testGroceryManager = new GroceryManager();
        egg = new Food("Egg", 5, "meat");
    }

    @Test
    public void testAddFoodBuy(){
        testGroceryManager.addFoodBuy(egg);
        assertEquals(testGroceryManager.getNeedbuy().size(), 1);
    }

    @Test
    public void testAddFoodFridge(){
        testGroceryManager.addFoodFridge(egg);
        assertEquals(testGroceryManager.getBought().size(), 1);
    }

}
