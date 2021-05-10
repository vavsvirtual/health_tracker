package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class GroupsPane extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Groups");
        stage = primaryStage;
        Scene scene = groupScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static Scene groupScene() {

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

        Label headerLabel = new Label("Groups");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gridPaneGroup.add(headerLabel, 0, 0, 2, 1);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setTranslateX(-200);
        headerLabel.setTranslateY(0);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));


        Label secondLabel = new Label("Create Group");
        secondLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        gridPaneGroup.add(secondLabel, 0, 0, 2, 1);
        secondLabel.setAlignment(Pos.CENTER);
        secondLabel.setTranslateX(-430);
        secondLabel.setTranslateY(70);
        GridPane.setHalignment(secondLabel, HPos.CENTER);
        GridPane.setMargin(secondLabel, new Insets(20, 0, 20, 0));

        Label groupLabel = new Label("Group Name");
        groupLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneGroup.add(groupLabel, 0, 0, 2, 1);
        groupLabel.setAlignment(Pos.CENTER);
        groupLabel.setTranslateX(-600);
        groupLabel.setTranslateY(150);
        GridPane.setHalignment(groupLabel, HPos.CENTER);
        GridPane.setMargin(groupLabel, new Insets(20, 0, 20, 0));

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        nameField.setMaxWidth(400);
        nameField.setTranslateX(-530);
        nameField.setTranslateY(90);
        gridPaneGroup.add(nameField, 1, 2);


        Label goalLabel = new Label("Group Goal");
        goalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneGroup.add(goalLabel, 0, 0, 2, 1);
        goalLabel.setAlignment(Pos.CENTER);
        goalLabel.setTranslateX(-600);
        goalLabel.setTranslateY(210);
        GridPane.setHalignment(goalLabel, HPos.CENTER);
        GridPane.setMargin(goalLabel, new Insets(20, 0, 20, 0));

        ComboBox cbGoal = new ComboBox();
        cbGoal.getItems().addAll(
                "Exercise", "Calories", "Custom"
        );
        cbGoal.setPrefHeight(40);
        cbGoal.setPrefWidth(300);
        cbGoal.setTranslateX(-530);
        cbGoal.setTranslateY(150);
        gridPaneGroup.add(cbGoal, 1, 2);

        Label addFriend = new Label("Add Friend");
        addFriend.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneGroup.add(addFriend, 0, 0, 2, 1);
        addFriend.setAlignment(Pos.CENTER);
        addFriend.setTranslateX(-600);
        addFriend.setTranslateY(270);
        GridPane.setHalignment(addFriend, HPos.CENTER);
        GridPane.setMargin(addFriend, new Insets(20, 0, 20, 0));

        TextField friendField = new TextField();
        friendField.setPrefHeight(40);
        friendField.setPrefWidth(300);
        friendField.setTranslateX(-530);
        friendField.setTranslateY(170);
        gridPaneGroup.add(friendField, 1, 4);

        Button addFriendButton = new Button("Add Friend");
        addFriendButton.setPrefHeight(40);
        addFriendButton.setDefaultButton(true);
        addFriendButton.setPrefWidth(100);
        addFriendButton.setTranslateX(-130);
        addFriendButton.setTranslateY(330);
        addFriendButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(addFriendButton, new Insets(20, 0, 20, 0));

        addFriendButton.setOnAction(t -> {
            if (cbGoal.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error!", "Please add a friend");
            }
        });


        Button createGroupButton = new Button("Create New Group");
        createGroupButton.setPrefHeight(40);
        createGroupButton.setDefaultButton(true);
        createGroupButton.setPrefWidth(300);
        createGroupButton.setTranslateX(-230);
        createGroupButton.setTranslateY(400);
        createGroupButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(createGroupButton, new Insets(20, 0, 20, 0));

        createGroupButton.setOnAction(t -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a unique group name");
                return;
            }
            if (cbGoal.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error!", "Please select a goal for the group");
                return;
            }

            showAlert(Alert.AlertType.CONFIRMATION, "Created Group Successful!", "Group " + nameField.getText());

            System.out.println("Created group " + nameField.getText() + " successfully");
        });


        Label thirdLabel = new Label("Join Group");
        thirdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        gridPaneGroup.add(thirdLabel, 0, 0, 2, 1);
        thirdLabel.setAlignment(Pos.CENTER);
        thirdLabel.setTranslateX(30);
        thirdLabel.setTranslateY(70);
        GridPane.setHalignment(thirdLabel, HPos.CENTER);
        GridPane.setMargin(thirdLabel, new Insets(20, 0, 20, 0));

        Label goalLabel_one = new Label("Group Name");
        goalLabel_one.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneGroup.add(goalLabel_one, 0, 0, 2, 1);
        goalLabel_one.setAlignment(Pos.CENTER);
        goalLabel_one.setTranslateX(-120);
        goalLabel_one.setTranslateY(150);
        GridPane.setHalignment(goalLabel_one, HPos.CENTER);
        GridPane.setMargin(goalLabel_one, new Insets(20, 0, 20, 0));

        ComboBox cbJoin = new ComboBox();
        cbJoin.getItems().addAll(
                "Group 10", "Group 11", "Group 12", "Group 13", "Group 14"
        );
        cbJoin.setPrefHeight(40);
        cbJoin.setPrefWidth(300);
        cbJoin.setTranslateX(-50);
        cbJoin.setTranslateY(90);
        gridPaneGroup.add(cbJoin, 1, 2);

        Button joinGroupButton = new Button("Join a Group");
        joinGroupButton.setPrefHeight(40);
        joinGroupButton.setDefaultButton(true);
        joinGroupButton.setPrefWidth(300);
        joinGroupButton.setTranslateX(230);
        joinGroupButton.setTranslateY(400);
        joinGroupButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(joinGroupButton, new Insets(20, 0, 20, 0));

        joinGroupButton.setOnAction(t -> {

            if (cbJoin.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error!", "Please select a group to join");
            }

        });

        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneGroup);

        gridPaneGroup.getChildren().addAll(addFriendButton, createGroupButton, joinGroupButton);

        return new Scene(menu, 1200, 700);
    }


    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}