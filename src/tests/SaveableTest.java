package tests;

import model.Food;
import model.FoodCategory;
import model.GroceryManager;
import model.exceptions.CategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        s.addFoodBuy(new Food("Egg", 6, FoodCategory.MEAT));
//        ArrayList<String> inFile = new ArrayList<>();
//
//        inFile = s.toBuy();
        s.saveBuy();

        s2.loadBuy();

//        List<String> y = new ArrayList<>();
//        Path path = Paths.get("C:\\Users\\Cyrielle\\IdeaProjects\\projectw1_team311").resolve("needbuy.txt");

        assertEquals(s.getNeedbuy().size(), s2.getNeedbuy().size());

// y = Files.readAllLines(path);
//
//        for (int i=0; i<inFile.size(); i++){
//            assertTrue(inFile.get(i).equals(y.get(i)));
//        }
    }

}