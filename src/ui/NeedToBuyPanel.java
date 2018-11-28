package ui;

import javafx.beans.property.SimpleObjectProperty;
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
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class NeedToBuyPanel {
    ObservableList<String> categories = FXCollections.observableArrayList("DAIRY", "MEAT", "VEGETABLE", "FRUIT", "SWEETS", "GRAIN", "OTHER");

    private GroceryPanel groceryPanel = new GroceryPanel();
    private AtomicBoolean first = new AtomicBoolean(true);

    @FXML
    private TextField needFoodInput;
    @FXML
    private TextField needAmountInput;
    @FXML
    private TextField removeInput;
    @FXML
    private TextArea needOutput;
    @FXML
    private ChoiceBox foodCategory;
    @FXML
    private TableView<Map.Entry<Food, Integer>> needTable = new TableView<>();



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

        //populate the table
        populateNeed("Name", "Amount", "Category");

        //populate the list
//        ObservableMap<Food, Integer> needbuys = FXCollections.observableMap(groceryPanel.list.getNeedbuy());
//        ObservableList<Food> neeeeedbuys = FXCollections.observableArrayList

//        List<Food> foodList = new ListView<Food>();

    }

    @FXML
    private void createNewOutputItem() throws CategoryException, IOException {
        Food item = groceryPanel.list.createFoodItem(needFoodInput.getText(), foodCategory.getValue().toString());
        int amount = Integer.parseInt(needAmountInput.getText());
        groceryPanel.list.addFoodBuy(item, amount);
        groceryPanel.list.save();
        populateNeed("Name", "Amount", "Category");
//        needOutput.appendText(needFoodInput.getText() + ", " + needAmountInput.getText() + ", " + foodCategory.getValue().toString() + ", " + "\n");
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

    @FXML
    public void removeItemFromNeedBuy() throws CategoryException {
        if(removeInput.getText().isEmpty()) {
            return;
        }
        int i = 0;
        for(Map.Entry<Food, Integer> item : needTable.getItems()) {
            if(!needTable.getSelectionModel().isSelected(i++)) {
                continue;
            }
            System.out.println("Item selected for removal: "+item.getKey().getName());
            int quantity = Integer.parseInt(removeInput.getText());
            groceryPanel.list.removeFromNeedBuy(item.getKey(), quantity);
        }
        populateNeed("Name", "Amount", "Category");
    }

    public void populateNeed(String name, String amount, String cat){
        TableColumn<Map.Entry<Food, Integer>, String> Name = new TableColumn<>(name);
        Name.setCellValueFactory(param -> new SimpleObjectProperty<String>(param.getValue().getKey().getName()));

        TableColumn<Map.Entry<Food, Integer>, Integer> Amount = new TableColumn<>(amount);
        Amount.setCellValueFactory(param -> new SimpleObjectProperty<Integer>(param.getValue().getValue()));

        TableColumn<Map.Entry<Food, Integer>, FoodCategory> Cat = new TableColumn<>(cat);
        Cat.setCellValueFactory(param -> new SimpleObjectProperty<FoodCategory>(param.getValue().getKey().getCategory()));

        ObservableList<Map.Entry<Food, Integer>> items = FXCollections.observableArrayList(groceryPanel.list.getNeedbuy().entrySet());
        needTable.setItems(items);
        needTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        needTable.getColumns().setAll(Name, Amount, Cat);
        needTable.setEditable(false);
    }

}


