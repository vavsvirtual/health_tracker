package client;

import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GoalSettingPane extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Goal Settings");
        stage = primaryStage;
        Scene scene = goalSettingScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static Scene goalSettingScene() {

        //Side Menu

        VBox vBox = new VBox(50);

        vBox.setStyle("-fx-background-color: #3D405B;");

        vBox.setPrefWidth(230);

        JFXButton profile = new JFXButton("Profile");
        profile.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        profile.setPrefSize(230, 100);

        profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage.setScene(ProfilePane.profileScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton goals = new JFXButton("Goals");
        goals.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        goals.setPrefWidth(230);

        goals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage.setScene(GoalSettingPane.goalSettingScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton summary = new JFXButton("Weekly Summary");
        summary.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        summary.setPrefWidth(230);

        summary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage.setScene(WeeklySummary.summaryScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton groups = new JFXButton("Groups");
        groups.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        groups.setPrefWidth(230);

        groups.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage.setScene(GroupsPane.groupScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JFXButton logout = new JFXButton("Logout");
        logout.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        logout.setPrefWidth(230);

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage.setScene(Main.logInScene());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        vBox.setAlignment(Pos.BASELINE_LEFT);

        vBox.getChildren().addAll(profile, goals, summary, groups, logout);

        // Goal Setting

        GridPane gridPaneSetting = new GridPane();

        Label headerLabel = new Label("Goals");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gridPaneSetting.add(headerLabel, 0, 0, 2, 1);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setTranslateX(-170);
        headerLabel.setTranslateY(0);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        Label secondLabel = new Label("General");
        secondLabel.setFont(Font.font("Arial", 20));
        gridPaneSetting.add(secondLabel, 0, 0, 2, 1);
        secondLabel.setAlignment(Pos.CENTER);
        secondLabel.setTranslateX(-170);
        secondLabel.setTranslateY(70);
        GridPane.setHalignment(secondLabel, HPos.CENTER);
        GridPane.setMargin(secondLabel, new Insets(20, 0, 20, 0));

        Label username = new Label("Username");
        username.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(username, 0, 0, 2, 1);
        username.setAlignment(Pos.CENTER);
        username.setTranslateX(-370);
        username.setTranslateY(140);
        GridPane.setHalignment(username, HPos.CENTER);
        GridPane.setMargin(username, new Insets(20, 0, 20, 0));

        TextField tfUsername = new TextField();
        tfUsername.setPrefHeight(40);
        tfUsername.setPrefWidth(300);
        tfUsername.setTranslateX(-300);
        tfUsername.setTranslateY(40);
        gridPaneSetting.add(tfUsername, 1, 4);

        Label email = new Label("Email Address");
        email.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(email, 0, 0, 2, 1);
        email.setAlignment(Pos.CENTER);
        email.setTranslateX(-370);
        email.setTranslateY(190);
        GridPane.setHalignment(email, HPos.CENTER);
        GridPane.setMargin(email, new Insets(20, 0, 20, 0));

        TextField tfEmail = new TextField();
        tfEmail.setPrefHeight(40);
        tfEmail.setPrefWidth(300);
        tfEmail.setTranslateX(-300);
        tfEmail.setTranslateY(90);
        gridPaneSetting.add(tfEmail, 1, 4);

        LocalDate nowDate = LocalDate.now();

        // Exercise Goal

        Label thirdLabel = new Label("Exercise");
        thirdLabel.setFont(Font.font("Arial", 20));
        gridPaneSetting.add(thirdLabel, 0, 0, 2, 1);
        thirdLabel.setAlignment(Pos.CENTER);
        thirdLabel.setTranslateX(-500);
        thirdLabel.setTranslateY(250);
        GridPane.setHalignment(thirdLabel, HPos.CENTER);
        GridPane.setMargin(thirdLabel, new Insets(20, 0, 20, 0));


        Label deadlineDate = new Label("Deadline");
        deadlineDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(deadlineDate, 0, 0, 2, 1);
        deadlineDate.setAlignment(Pos.CENTER);
        deadlineDate.setTranslateX(-600);
        deadlineDate.setTranslateY(330);
        GridPane.setHalignment(deadlineDate, HPos.CENTER);
        GridPane.setMargin(deadlineDate, new Insets(20, 0, 20, 0));

        DatePicker dpDeadLine = new DatePicker(nowDate.plusDays(7));
        dpDeadLine.setPrefHeight(40);
        dpDeadLine.setPrefWidth(300);
        dpDeadLine.setTranslateX(-530);
        dpDeadLine.setTranslateY(270);
        gridPaneSetting.add(dpDeadLine, 1, 2);

        Label exercise = new Label("Exercise");
        exercise.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(exercise, 0, 0, 2, 1);
        exercise.setAlignment(Pos.CENTER);
        exercise.setTranslateX(-600);
        exercise.setTranslateY(380);
        GridPane.setHalignment(exercise, HPos.CENTER);
        GridPane.setMargin(exercise, new Insets(20, 0, 20, 0));

        ComboBox cbExercise = new ComboBox<String>();
        cbExercise.setEditable(true);
        cbExercise.getItems().addAll("Swimming", "Jogging");
        cbExercise.setPrefHeight(40);
        cbExercise.setPrefWidth(300);
        cbExercise.setTranslateX(-530);
        cbExercise.setTranslateY(320);
        gridPaneSetting.add(cbExercise, 1, 2);

        Label duration = new Label("Duration");
        duration.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(duration, 0, 0, 2, 1);
        duration.setAlignment(Pos.CENTER);
        duration.setTranslateX(-600);
        duration.setTranslateY(430);
        GridPane.setHalignment(duration, HPos.CENTER);
        GridPane.setMargin(duration, new Insets(20, 0, 20, 0));

        TextField tfDuration = new TextField();
        tfDuration.setPrefHeight(40);
        tfDuration.setMaxWidth(400);
        tfDuration.setTranslateX(-530);
        tfDuration.setTranslateY(370);
        gridPaneSetting.add(tfDuration, 1, 2);

        Button btnExercise = new Button("Set Exercise Goal");
        btnExercise.setPrefHeight(40);
        btnExercise.setDefaultButton(true);
        btnExercise.setPrefWidth(300);
        btnExercise.setTranslateX(-250);
        btnExercise.setTranslateY(600);
        btnExercise.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(btnExercise, new Insets(20, 0, 20, 0));

        btnExercise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                double duration;

                try {
                    duration = Double.parseDouble(tfDuration.getText());
                } catch (NumberFormatException e) {
                    showMsg("Invalid Duration");
                    return;
                }


                LocalDate deadlineDate = dpDeadLine.getValue();
                String exercise = (String) cbExercise.getValue();
                String username = tfUsername.getText();

                Stage stage = new Stage();
                GoalStartPane root = new GoalStartPane(stage);
                root.duration = duration;
                root.deadlineDate = deadlineDate;
                root.exercise = exercise;
                root.username = username;

                root.createLayout();

                Scene scene = new Scene(root);

                stage.setScene(scene);

                stage.setTitle(root.getTitle());
                stage.showAndWait();

            }
        });


        // Weight Goal

        Label forthLabel = new Label("Weight");
        forthLabel.setFont(Font.font("Arial", 20));
        gridPaneSetting.add(forthLabel, 0, 0, 2, 1);
        forthLabel.setAlignment(Pos.CENTER);
        forthLabel.setTranslateX(0);
        forthLabel.setTranslateY(250);
        GridPane.setHalignment(forthLabel, HPos.CENTER);
        GridPane.setMargin(forthLabel, new Insets(20, 0, 20, 0));

        Label deadlineDateWeight = new Label("Deadline");
        deadlineDateWeight.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(deadlineDateWeight, 0, 0, 2, 1);
        deadlineDateWeight.setAlignment(Pos.CENTER);
        deadlineDateWeight.setTranslateX(-120);
        deadlineDateWeight.setTranslateY(330);
        GridPane.setHalignment(deadlineDateWeight, HPos.CENTER);
        GridPane.setMargin(deadlineDateWeight, new Insets(20, 0, 20, 0));

        DatePicker dpDeadLineWeight = new DatePicker(nowDate.plusDays(7));
        dpDeadLineWeight.setPrefHeight(40);
        dpDeadLineWeight.setPrefWidth(300);
        dpDeadLineWeight.setTranslateX(-50);
        dpDeadLineWeight.setTranslateY(270);
        gridPaneSetting.add(dpDeadLineWeight, 1, 2);


        Label targetWeight = new Label("Target Weight");
        targetWeight.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(targetWeight, 0, 0, 2, 1);
        targetWeight.setAlignment(Pos.CENTER);
        targetWeight.setTranslateX(-120);
        targetWeight.setTranslateY(380);
        GridPane.setHalignment(targetWeight, HPos.CENTER);
        GridPane.setMargin(targetWeight, new Insets(20, 0, 20, 0));

        TextField tfTargetWeight = new TextField();
        tfTargetWeight.setPrefHeight(40);
        tfTargetWeight.setMaxWidth(400);
        tfTargetWeight.setTranslateX(-50);
        tfTargetWeight.setTranslateY(320);
        gridPaneSetting.add(tfTargetWeight, 1, 2);


        Button btnWeight = new Button("Set Weight Goal");
        btnWeight.setPrefHeight(40);
        btnWeight.setDefaultButton(true);
        btnWeight.setPrefWidth(300);
        btnWeight.setTranslateX(200);
        btnWeight.setTranslateY(600);
        btnWeight.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(btnWeight, new Insets(20, 0, 20, 0));

        btnWeight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                double targetWeight;

                try {
                    targetWeight = Double.parseDouble(tfTargetWeight.getText());
                } catch (NumberFormatException e) {
                    showMsg("Invalid Target Weight");
                    return;
                }

                LocalDate deadlineDate = dpDeadLine.getValue();
                String username = tfUsername.getText();

                Stage stage = new Stage();
                GoalStartPane root = new GoalStartPane(stage);
                root.targetWeight = targetWeight;
                root.deadlineDate = deadlineDate;
                root.username = username;

                root.createLayout();

                Scene scene = new Scene(root);

                stage.setScene(scene);

                stage.setTitle(root.getTitle());
                stage.showAndWait();

            }
        });

        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneSetting);

        gridPaneSetting.getChildren().addAll(btnExercise, btnWeight);

        return new Scene(menu, 1200, 700);

    }

    private static void showMsg(String text) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}