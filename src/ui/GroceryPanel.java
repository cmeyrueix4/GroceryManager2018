package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GroceryManager;

import java.io.IOException;

public class GroceryPanel {

    ImageView title, background;

    GroceryManager list;
    public Stage stage;

    @FXML
    public void onBuy() {
        //stage.setScene(new Scene(buyLayout));
    }


    @FXML
    public void quit() {
        // end app
        try {
            list.save();
        } catch (IOException e) {
        }
        stage.close();
    }

    @FXML
    public void initialize() {
        
    }

//    private GroceryManager groceryManager = new GroceryManager();

//    public static void main(String[] args) {
//        launch(GroceryPanel.class, args);
//    }

//    @Override
//    public void start(Stage primaryStage){
//        BorderPane border = new BorderPane();
//        HBox hbox = addHBox();
//        border.setTop(hbox);
//        border.setLeft(addVBox());
//        addStackPane(hbox);         // Add stack to HBox in top region
//
//        Scene scene = new Scene(border);
//
//        primaryStage.setTitle("Let's Eat!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

//    public HBox addHBox() {
//        HBox hbox = new HBox();
//        hbox.setPadding(new Insets(15, 12, 15, 12));
//        hbox.setSpacing(10);
//        hbox.setStyle("-fx-background-color: #336699;");
//
//        Button buttonCurrent = new Button("Need to buy");
//        buttonCurrent.setPrefSize(100, 20);
//
//        Button buttonProjected = new Button("Bought");
//        buttonProjected.setPrefSize(100, 20);
//        hbox.getChildren().addAll(buttonCurrent, buttonProjected);
//
//        return hbox;
//    }
//
//    public VBox addVBox() {
//        VBox vbox = new VBox();
//        vbox.setPadding(new Insets(10));
//        vbox.setSpacing(8);
//
//        Text title = new Text("Choose your storage");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        vbox.getChildren().add(title);
//
//        Hyperlink options[] = new Hyperlink[] {
//                new Hyperlink("Fridge"),
//                new Hyperlink("Freezer"),
//                new Hyperlink("Cupboard")};
//
//        for (int i=0; i<3; i++) {
//            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
//            vbox.getChildren().add(options[i]);
//        }
//
//        return vbox;
//    }
//
//    public void addStackPane(HBox hb) {
//        StackPane stack = new StackPane();
//        Rectangle helpIcon = new Rectangle(30.0, 25.0);
//        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
//                new Stop[]{
//                        new Stop(0, Color.web("#4977A3")),
//                        new Stop(0.5, Color.web("#B0C6DA")),
//                        new Stop(1,Color.web("#9CB6CF")),}));
//        helpIcon.setStroke(Color.web("#D0E6FA"));
//        helpIcon.setArcHeight(3.5);
//        helpIcon.setArcWidth(3.5);
//
//        Text helpText = new Text("?");
//        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
//        helpText.setFill(Color.WHITE);
//        helpText.setStroke(Color.web("#7080A0"));
//
//        stack.getChildren().addAll(helpIcon, helpText);
//        stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
//        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"
//
//        hb.getChildren().add(stack);            // Add stack pane to HBox object
//        HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
//    }
}
