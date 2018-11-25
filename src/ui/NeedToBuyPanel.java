package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Food;
import model.FoodCategory;
import model.GroceryManager;
import model.exceptions.CategoryException;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class NeedToBuyPanel {
    ObservableList<String> categories = FXCollections.observableArrayList("DAIRY", "MEAT", "VEGETABLE", "FRUIT", "SWEETS", "GRAIN", "OTHER");

    private GroceryPanel groceryPanel = new GroceryPanel();

    @FXML
    private TextField needFoodInput;
    @FXML
    private TextField needAmountInput;
    @FXML
    private TextArea needOutput;
    @FXML
    private ChoiceBox foodCategory;



    @FXML
    private void initialize(){
        // create the drop down menu
        foodCategory.setItems(categories);

        //populate the textarea
        try {
            Scanner s = new Scanner(new File("needbuy.txt"));
            while (s.hasNext()) {
                needOutput.appendText(s.nextLine() + "\n");
            }
        }catch (FileNotFoundException e) {
            System.err.println(e);
        }

        //populate the list
        ObservableMap<Food, Integer> needbuys = FXCollections.observableMap(groceryPanel.list.getNeedbuy());
//        ObservableList<Food> neeeeedbuys = FXCollections.observableArrayList

//        List<Food> foodList = new ListView<Food>();

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

    @FXML
    public void backToMainScreen(MouseEvent event) throws IOException {
        Parent myParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene newScene = new Scene(myParent);
        Stage appStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        appStage.setScene(newScene);
        appStage.show();
    }

}


