package client;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                try {
//                    stage.setScene(ProfilePane.profileScene());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                ProfilePane.profileScene();

            }
        });

        JFXButton goals = new JFXButton("Goals");
        goals.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        goals.setPrefWidth(230);

        goals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                try {
//                    stage.setScene(GoalSettingPane.goalSettingScene());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                GoalSettingPane.goalSettingScene();

            }
        });

        JFXButton summary = new JFXButton("Weekly Summary");
        summary.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        summary.setPrefWidth(230);

        summary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                try {
//                    stage.setScene(WeeklySummary.summaryScene());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                WeeklySummary.summaryScene();

            }
        });

        JFXButton groups = new JFXButton("Groups");
        groups.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        groups.setPrefWidth(230);

        groups.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                try {
//                    stage.setScene(GroupsPane.groupScene());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                GroupsPane.groupScene();

            }
        });

        JFXButton logout = new JFXButton("Logout");
        logout.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        logout.setPrefWidth(230);

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                try {
//                    stage.setScene(Main.logInScene());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Main.logInScene();

            }
        });

        vBox.setAlignment(Pos.BASELINE_LEFT);

        vBox.getChildren().addAll(profile, goals, summary, groups, logout);
        vBox.setPadding(new Insets(0, 0, 0, 0));


        GridPane gridPaneSummary = new GridPane();

        Label headerLabel = new Label("Weekly Summary");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gridPaneSummary.add(headerLabel, 0, 0, 2, 1);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setTranslateX(-250);
        headerLabel.setTranslateY(0);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));


        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneSummary);

        gridPaneSummary.getChildren().addAll();

        return new Scene(menu, 1200, 700);

    }

    public static void main(String[] args) {

        launch(args);
    }

}
