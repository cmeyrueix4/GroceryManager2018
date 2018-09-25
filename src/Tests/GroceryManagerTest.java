package Tests;


import Model.Food;
import Model.FoodCategory;
import Model.GroceryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroceryManagerTest {


    GroceryManager testGroceryManager;

    @BeforeEach
    public void runBefore(){
        GroceryManager testGroceryManager = new GroceryManager();
    }

    @Test
    public void testAddFoodBuy(){
        Food egg = new Food("Egg", 5, FoodCategory.MEAT);
        testGroceryManager.addFoodBuy(egg);
        assertEquals(testGroceryManager.getNeedbuy().size(), 1);
    }

    @Test
    public void testAddBought(){

    }

}
