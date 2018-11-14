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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadableTest {

    public GroceryManager g;
    public GroceryManager s;

    @BeforeEach
    public void runBefore() {
        g = new GroceryManager();
        s = new GroceryManager();
    }

    @Test
    public void testLoadBuy() throws IOException, CategoryException {
        List<String> lines = new ArrayList<>();
        lines.add("Egg 6 MEAT");
        lines.add("bacon 6 MEAT");
        lines.add("oranges 5 FRUIT");
        lines.add("wine 1 OTHER");
        Files.write(Paths.get("needbuy.txt"), lines);
        s.load();
        assertEquals((Integer)6, s.getNeedbuy().get(new Food("Egg", FoodCategory.MEAT)));

    }

}
