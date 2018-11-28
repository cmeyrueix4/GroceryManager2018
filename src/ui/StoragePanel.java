package ui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TextField fridgeRemoveInput;
    @FXML
    private TextField freezerRemoveInput;
    @FXML
    private TextField cupboardRemoveInput;
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
        freezerChoiceInput.setItems(categories);
        cupboardChoiceInput.setItems(categories);

        //populate tables
        populateR("Name", "Amount", "Category");
        populateFr("Name", "Amount", "Category");
        populateC("Name", "Amount", "Category");

        //initialize progress bars
        fridgeCapacityProgress();
        freezerCapacityProgress();
        cupboardCapacityProgress();

    }

    @FXML
    private void fridgeCapacityProgress(){
        for (int i = 0; i < gp.list.getR().getCapacity(); i++) {
            fridgeCapacityBar.setProgress(i/100.0);
        }
    }

    @FXML
    private void freezerCapacityProgress(){
        for (int i = 0; i < gp.list.getFr().getCapacity(); i++) {
            freezerCapacityBar.setProgress(i/100.0);
        }
    }

    @FXML
    private void cupboardCapacityProgress(){
        for (int i = 0; i < gp.list.getC().getCapacity(); i++) {
            cupboardCapacityBar.setProgress(i/100.0);
        }
    }

    @FXML
    private void createNewOutputFridgeItem() throws CategoryException, IOException {
        Food item = gp.list.createFoodItem(fridgeNameInput.getText(), fridgeChoiceInput.getValue().toString());
        int amount = Integer.parseInt(fridgeAmountInput.getText());
        gp.list.getR().addToStorage(item, amount);
        gp.list.save();
        populateR("Name", "Amount", "Category");

        fridgeNameInput.clear();
        fridgeAmountInput.clear();
        fridgeCapacityProgress();
    }

    @FXML
    private void createNewOutputFreezerItem() throws CategoryException, IOException {
        Food item = gp.list.createFoodItem(freezerNameInput.getText(), freezerChoiceInput.getValue().toString());
        int amount = Integer.parseInt(freezerAmountInput.getText());
        gp.list.addFoodBought("freezer", item, amount);
        gp.list.save();
        gp.list.save();
        populateFr("Name", "Amount", "Category");

        freezerNameInput.clear();
        freezerAmountInput.clear();
        freezerCapacityProgress();
    }

    @FXML
    private void createNewOutputCupboardItem() throws CategoryException, IOException {
        Food item = gp.list.createFoodItem(cupboardNameInput.getText(), cupboardChoiceInput.getValue().toString());
        int amount = Integer.parseInt(cupboardAmountInput.getText());
        gp.list.addFoodBought("cupboard", item, amount);
        gp.list.save();
        populateC("Name", "Amount", "Category");

        cupboardNameInput.clear();
        cupboardAmountInput.clear();
        cupboardCapacityProgress();
    }

    @FXML
    private void removeItemFridge() throws IOException {
        if(fridgeRemoveInput.getText().isEmpty()) {
            return;
        }
        int i = 0;
        for(Map.Entry<Food, Integer> item : fridgeTable.getItems()) {
            if(!fridgeTable.getSelectionModel().isSelected(i++)) {
                continue;
            }
            System.out.println("Item selected for removal: "+item.getKey().getName());
            int quantity = Integer.parseInt(fridgeRemoveInput.getText());
            gp.list.getR().removeFromStorage(item.getKey(), quantity);
        }
        populateR("Name", "Amount", "Category");
        fridgeCapacityProgress();
        fridgeRemoveInput.clear();
        gp.list.save();
    }

    @FXML
    private void removeItemFreezer() throws IOException {
        if(freezerRemoveInput.getText().isEmpty()) {
            return;
        }
        int i = 0;
        for(Map.Entry<Food, Integer> item : freezerTable.getItems()) {
            if(!freezerTable.getSelectionModel().isSelected(i++)) {
                continue;
            }
            System.out.println("Item selected for removal: "+item.getKey().getName());
            int quantity = Integer.parseInt(freezerRemoveInput.getText());
            gp.list.getFr().removeFromStorage(item.getKey(), quantity);
        }
        populateFr("Name", "Amount", "Category");
        freezerCapacityProgress();
        freezerRemoveInput.clear();
        gp.list.save();
    }

    @FXML
    private void removeItemCupboard() throws IOException {
        if(cupboardRemoveInput.getText().isEmpty()) {
            return;
        }
        int i = 0;
        for(Map.Entry<Food, Integer> item : cupboardTable.getItems()) {
            if(!cupboardTable.getSelectionModel().isSelected(i++)) {
                continue;
            }
            System.out.println("Item selected for removal: "+item.getKey().getName());
            int quantity = Integer.parseInt(cupboardRemoveInput.getText());
            gp.list.getC().removeFromStorage(item.getKey(), quantity);
        }
        populateC("Name", "Amount", "Category");
        cupboardCapacityProgress();
        cupboardRemoveInput.clear();
        gp.list.save();
    }


    public void populateR(String name, String amount, String cat){
        TableColumn<Map.Entry<Food, Integer>, String> Name = new TableColumn<>(name);
        Name.setCellValueFactory(param -> {
            return new SimpleObjectProperty<>(param.getValue().getKey().getName());
        });

        TableColumn<Map.Entry<Food, Integer>, Integer> Amount = new TableColumn<>(amount);
        Amount.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getValue()));

        TableColumn<Map.Entry<Food, Integer>, FoodCategory> Cat = new TableColumn<>(cat);
        Cat.setCellValueFactory(param -> {
            return new SimpleObjectProperty<>(param.getValue().getKey().getCategory());
        });

        ObservableList<Map.Entry<Food, Integer>> items = FXCollections.observableArrayList(gp.list.getR().getHave().entrySet());
        fridgeTable.setItems(items);
        fridgeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fridgeTable.getColumns().setAll(Name, Amount, Cat);
        fridgeTable.setEditable(false);
    }

    public void populateFr(String name, String amount, String cat){
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

        ObservableList<Map.Entry<Food, Integer>> freezerItems = FXCollections.observableArrayList(gp.list.getFr().getHave().entrySet());
        freezerTable.setItems(freezerItems);
        freezerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        freezerTable.getColumns().setAll(Name, Amount, Cat);
        freezerTable.setEditable(false);
    }

    public void populateC(String name, String amount, String cat){
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

        ObservableList<Map.Entry<Food, Integer>> cupboardItems = FXCollections.observableArrayList(gp.list.getC().getHave().entrySet());
        cupboardTable.setItems(cupboardItems);
        cupboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cupboardTable.getColumns().setAll(Name, Amount, Cat);
        cupboardTable.setEditable(false);

//        Cat.setCellFactory(TextFieldTableCell<Food>forTableColumn());
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



