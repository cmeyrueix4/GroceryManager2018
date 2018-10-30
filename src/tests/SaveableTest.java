package tests;

import model.Food;
import model.FoodCategory;
import model.GroceryManager;
import model.exceptions.CategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SaveableTest {

    public GroceryManager s;
    public GroceryManager s2;

    @BeforeEach
    public void runBefore(){
        s = new GroceryManager();
        s2 = new GroceryManager();
    }

    @Test
    public void testSaveBuy() throws IOException, CategoryException {
        s.addFoodBuy(new Food("Egg", FoodCategory.MEAT), 6);
        s.saveBuy();


        List<String> y = Files.readAllLines(Paths.get("needbuy.txt"));
        assertEquals("Egg 6 MEAT", y.get(0));
    }

}
