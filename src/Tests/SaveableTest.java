package Tests;

import Model.Food;
import Model.FoodCategory;
import Model.GroceryManager;
import Model.Saveable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SaveableTest {

    public GroceryManager s;

    @BeforeEach
    public void runBefore(){
        s = new GroceryManager();
    }

    @Test
    public void testSaveBuy() throws IOException{
        s.addFoodBuy(new Food("Egg", 6, FoodCategory.MEAT));
        ArrayList<String> inFile = new ArrayList<>();

        inFile = s.toBuy();
        s.saveBuy();

        List<String> y = new ArrayList<>();
        Path path = Paths.get("C://Users/Cyrielle/Desktop").resolve("needbuy.txt");
        y = Files.readAllLines(path);

        for (int i=0; i<inFile.size(); i++){
            assertTrue(inFile.get(i).equals(y.get(i)));
        }
    }

}