package client;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WeeklySummary extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Groups");
        stage = primaryStage;
        Scene scene = summaryScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static Scene summaryScene() {

        VBox vBox = new VBox(50);

        vBox.setStyle("-fx-background-color: #3D405B;");

        vBox.setPrefWidth(230);

        JFXButton profile = new JFXButton("Profile");
        profile.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        profile.setPrefSize(230, 100);

        profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(ProfilePane.profileScene());
            }
        });

        JFXButton goals = new JFXButton("Goals");
        goals.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        goals.setPrefWidth(230);

        goals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(GoalSettingPane.goalSettingScene());
            }
        });

        JFXButton summary = new JFXButton("Weekly Summary");
        summary.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        summary.setPrefWidth(230);

        summary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(WeeklySummary.summaryScene());
            }
        });

        JFXButton groups = new JFXButton("Groups");
        groups.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        groups.setPrefWidth(230);

        groups.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(GroupsPane.groupScene());
            }
        });

        JFXButton logout = new JFXButton("Logout");
        logout.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        logout.setPrefWidth(230);

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(Main.logInScene());
            }
        });

        vBox.setAlignment(Pos.BASELINE_LEFT);

        vBox.getChildren().addAll(profile, goals, summary, groups, logout);
        vBox.setPadding(new Insets(0, 0, 0, 0));


        GridPane gridPaneGroup = new GridPane();

        //code here


        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneGroup);

        gridPaneGroup.getChildren().addAll();

        return new Scene(menu, 1200, 700);

    }

    public static void main(String[] args) {

        launch(args);
    }

}
