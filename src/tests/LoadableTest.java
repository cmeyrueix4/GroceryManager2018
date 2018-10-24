package tests;

import model.Food;
import model.FoodCategory;
import model.GroceryManager;
import model.exceptions.CategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadableTest {

    public GroceryManager g;
    public GroceryManager s;

    @BeforeEach
    public void runBefore(){
        g = new GroceryManager();
        s = new GroceryManager();
    }

    @Test
    public void testLoadBuy() throws IOException, CategoryException {
        g.addFoodBuy(new Food("Egg", 6, FoodCategory.MEAT));

        s.loadBuy();

        for (int i = 0; i < g.getNeedbuy().size(); i++) {
            assertTrue(s.getNeedbuy().get(i).getName().equals(g.getNeedbuy().get(i).getName()));
            assertTrue(s.getNeedbuy().get(i).getAmount() == g.getNeedbuy().get(i).getAmount());
        }
    }

}
