package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Home extends Application {
    private static Stage stage;
    double x, y = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.setTitle("Health Tracker");
        stage = primaryStage;
        Scene scene = homeScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static Scene homeScene() {

        GridPane gridPaneHome = new GridPane();

        gridPaneHome.setStyle("-fx-background-color: white;");


        Label headerLabel = new Label("Hello");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPaneHome.add(headerLabel, 0, 0, 2, 1);
        headerLabel.setTranslateX(200);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));


        Button logOutButton = new Button("Logout");
        logOutButton.setPrefHeight(40);
        logOutButton.setDefaultButton(true);
        logOutButton.setPrefWidth(100);
        logOutButton.setTranslateX(1050);
        logOutButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(logOutButton, new Insets(20, 0, 20, 0));

        logOutButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                //stage.setScene(Main.logInScene());
                System.exit(1);
            }
        });

        gridPaneHome.getChildren().addAll(logOutButton);

        return new Scene(gridPaneHome, 1200, 700);
    }


    public static void main(String[] args) {

        launch(args);
    }

}










