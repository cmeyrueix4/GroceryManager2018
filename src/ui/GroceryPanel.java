package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.GroceryManager;
import model.exceptions.CategoryException;
import java.io.IOException;


public class GroceryPanel extends Application {

    public ImageView title, background;

    public static GroceryManager list = new GroceryManager();

    public static void main(String[] args) throws IOException, CategoryException {
        list.load();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("À Table");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    //Change scene
    @FXML
    public void changeScreenNeedToBuy(ActionEvent event) throws IOException {
        Parent myParent = FXMLLoader.load(getClass().getResource("NeedToBuyScreen.fxml"));
        Scene newScene = new Scene(myParent);
        Stage appStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        appStage.setScene(newScene);
        appStage.show();
    }

    @FXML
    public void changeScreenBought(ActionEvent event) throws IOException {
        Parent myParent = FXMLLoader.load(getClass().getResource("Store.fxml"));
        Scene newScene = new Scene(myParent);
        Stage appStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        appStage.setScene(newScene);
        appStage.show();
    }

    @FXML
    public void quit(ActionEvent event) throws IOException {
        try {
            list.save();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Stage appStage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        appStage.close();
    }

    @FXML
    public void initialize() {

    }
}