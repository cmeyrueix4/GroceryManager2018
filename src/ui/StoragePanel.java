package ui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import model.Food;
import model.FoodCategory;
import model.exceptions.CategoryException;

import java.io.IOException;
import java.util.Map;

public class StoragePanel extends TableView<Map.Entry<Food, Integer>> {
    private ObservableList<String> categories = FXCollections.observableArrayList("DAIRY", "MEAT", "VEGETABLE", "FRUIT", "SWEETS", "GRAIN", "OTHER");

    @FXML
    private TableView<Map.Entry<Food, Integer>> fridgeTable = new TableView<>();
    @FXML
    private TableView<Map.Entry<Food, Integer>> freezerTable = new TableView<>();
    @FXML
    private TableView<Map.Entry<Food, Integer>> cupboardTable = new TableView<>();

    @FXML
    private GroceryPanel gp = new GroceryPanel();

    @FXML
    private TextField fridgeNameInput;
    @FXML
    private TextField freezerNameInput;
    @FXML
    private TextField cupboardNameInput;
    @FXML
    private TextField fridgeAmountInput;
    @FXML
    private TextField freezerAmountInput;
    @FXML
    private TextField cupboardAmountInput;
    @FXML
    private ChoiceBox fridgeChoiceInput;
    @FXML
    private ChoiceBox freezerChoiceInput;
    @FXML
    private ChoiceBox cupboardChoiceInput;
    @FXML
    private ProgressBar fridgeCapacityBar;
    @FXML
    private ProgressBar freezerCapacityBar;
    @FXML
    private ProgressBar cupboardCapacityBar;

    @FXML
    public void initialize(){
        //create drop down menu
        fridgeChoiceInput.setItems(categories);

        //populate tables
        populateR("Name", "Amount", "Category");

        //initialize progress bars
        fridgeCapacityProgress();
    }

    @FXML
    private void fridgeCapacityProgress(){
        for (int i = 0; i < gp.list.getR().getCapacity(); i++) {
            fridgeCapacityBar.setProgress(i/100.0);
        }
    }

    @FXML
    private void createNewOutputItem() throws CategoryException, IOException {
        Food item = gp.list.createFoodItem(fridgeNameInput.getText(), fridgeChoiceInput.getValue().toString());
        int amount = Integer.parseInt(fridgeAmountInput.getText());
        gp.list.addFoodBought("fridge", item, amount);
        gp.list.save();
        gp.list.load();

        fridgeNameInput.clear();
        fridgeAmountInput.clear();
        populateR("Name", "Amount", "Category");

    }

    public void populateR(String name, String amount, String cat){
        TableColumn<Map.Entry<Food, Integer>, String> Name = new TableColumn<>(name);
        Name.setCellValueFactory(param -> {
            return new SimpleObjectProperty<String>(param.getValue().getKey().getName());
        });

        TableColumn<Map.Entry<Food, Integer>, Integer> Amount = new TableColumn<>(amount);
        Amount.setCellValueFactory(param -> new SimpleObjectProperty<Integer>(param.getValue().getValue()));

        TableColumn<Map.Entry<Food, Integer>, FoodCategory> Cat = new TableColumn<>(cat);
        Cat.setCellValueFactory(param -> {
            return new SimpleObjectProperty<FoodCategory>(param.getValue().getKey().getCategory());
        });

        ObservableList<Map.Entry<Food, Integer>> items = FXCollections.observableArrayList(gp.list.getR().getHave().entrySet());
        fridgeTable.setItems(items);
        fridgeTable.getColumns().setAll(Name, Amount, Cat);
        fridgeTable.setEditable(true);
    }
}



