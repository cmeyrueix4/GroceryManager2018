package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Food;
import model.FoodCategory;
import model.GroceryManager;
import model.exceptions.CategoryException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class NeedToBuyPanel {
    ObservableList<String> categories = FXCollections.observableArrayList("DAIRY", "MEAT", "VEGETABLE", "FRUIT", "SWEETS", "GRAIN", "OTHER");

    private GroceryPanel groceryPanel = new GroceryPanel();

    @FXML
    private TextField needFoodInput;
    @FXML
    private TextField needAmountInput;
    @FXML
    private TextField needOutput;
    @FXML
    private ChoiceBox foodCategory;



    @FXML
    private void initialize(){
        foodCategory.setItems(categories);
        try {
            Scanner s = new Scanner(new File("needbuy.txt"));
            while (s.hasNext()) {
                needOutput.appendText("\n" + s.nextLine() + "\n");
            }
        }catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    @FXML
    private void createNewOutputItem() throws CategoryException, IOException {
        Food item = groceryPanel.list.createFoodItem(needFoodInput.getText(), foodCategory.getValue().toString());
        int amount = Integer.parseInt(needAmountInput.getText());
        groceryPanel.list.addFoodBuy(item, amount);
        groceryPanel.list.save();
        needOutput.appendText(needFoodInput.getText() + ", " + needAmountInput.getText() + ", " + foodCategory.getValue().toString() + ", " + "\n");
        needFoodInput.clear();
        needAmountInput.clear();
    }

}


