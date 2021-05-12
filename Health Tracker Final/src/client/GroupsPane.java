package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.util.Pair;


public class GroupsPane extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Groups");
        stage = primaryStage;
        Scene scene = groupScene(stage);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static Scene groupScene(Stage primaryStage) {
        stage = primaryStage;

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
                    stage.setScene(ProfilePane.profileScene(stage));
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
                    stage.setScene(GoalSettingPane.goalSettingScene(stage));
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
                    //stage.setScene();
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
                    stage.setScene(GroupsPane.groupScene(stage));
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

        //Setting focus to just this pane
        profile.setFocusTraversable(false);
        goals.setFocusTraversable(false);
        summary.setFocusTraversable(false);
        logout.setFocusTraversable(false);
        vBox.getChildren().addAll(profile, goals, summary, groups, logout);

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
        secondLabel.setFont(Font.font("Arial", 20));
        gridPaneGroup.add(secondLabel, 0, 0, 2, 1);
        secondLabel.setAlignment(Pos.CENTER);
        secondLabel.setTranslateX(-465);
        secondLabel.setTranslateY(110);
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
        nameField.setTranslateY(85);
        gridPaneGroup.add(nameField, 1, 2);

        Label inviteFriendLabel = new Label("Invite Friend");
        inviteFriendLabel.setFont(Font.font("Arial", 20));
        gridPaneGroup.add(inviteFriendLabel, 0, 0, 2, 1);
        inviteFriendLabel.setAlignment(Pos.CENTER);
        inviteFriendLabel.setTranslateX(-473);
        inviteFriendLabel.setTranslateY(250);
        GridPane.setHalignment(inviteFriendLabel, HPos.CENTER);
        GridPane.setMargin(inviteFriendLabel, new Insets(20, 0, 20, 0));

        Label addFriend = new Label("Friends Username");
        addFriend.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneGroup.add(addFriend, 0, 0, 2, 1);
        addFriend.setAlignment(Pos.CENTER);
        addFriend.setTranslateX(-600);
        addFriend.setTranslateY(290);
        GridPane.setHalignment(addFriend, HPos.CENTER);
        GridPane.setMargin(addFriend, new Insets(20, 0, 20, 0));

        TextField friendField = new TextField();
        friendField.setPrefHeight(40);
        friendField.setPrefWidth(300);
        friendField.setTranslateX(-530);
        friendField.setTranslateY(190);
        gridPaneGroup.add(friendField, 1, 4);

        Label groupToInviteToLabel = new Label("Group To Invite To");
        groupToInviteToLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneGroup.add(groupToInviteToLabel, 0, 0, 2, 1);
        groupToInviteToLabel.setAlignment(Pos.CENTER);
        groupToInviteToLabel.setTranslateX(-600);
        groupToInviteToLabel.setTranslateY(354);
        GridPane.setHalignment(groupToInviteToLabel, HPos.CENTER);
        GridPane.setMargin(groupToInviteToLabel, new Insets(20, 0, 20, 0));

        ComboBox groupToInviteTo = new ComboBox();
        if(Main.userData.getGroupInfo() != null){
            groupToInviteTo.getItems().addAll(Main.userData.getGroupInfo());
        }
        groupToInviteTo.setEditable(false);
        groupToInviteTo.setPrefHeight(40);
        groupToInviteTo.setPrefWidth(300);
        groupToInviteTo.setTranslateX(-530);
        groupToInviteTo.setTranslateY(255);
        gridPaneGroup.add(groupToInviteTo, 1, 4);

        Button addFriendButton = new Button("Invite Friend");
        addFriendButton.setPrefHeight(40);
        addFriendButton.setDefaultButton(true);
        addFriendButton.setPrefWidth(300);
        addFriendButton.setTranslateX(-230);
        addFriendButton.setTranslateY(420);
        addFriendButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(addFriendButton, new Insets(20, 0, 20, 0));

        addFriendButton.setOnAction(t -> {
            if(groupToInviteTo.getValue() == null){
                showAlert(Alert.AlertType.WARNING, "Invite to Group", "Please select a valid group");
            }else if(friendField.getText().isEmpty()){
                showAlert(Alert.AlertType.WARNING, "Invite to Group", "Please enter your friends username");
            }else{
                String groupName = groupToInviteTo.getValue().toString().toLowerCase();
                String userName = friendField.getText().toLowerCase();
                Pair<Boolean, String> pair = Main.connection.inviteToGroup(userName, groupName);
                if(pair.getKey()){
                    showAlert(Alert.AlertType.CONFIRMATION, "Invite to Group", pair.getValue());
                }else{
                    showAlert(Alert.AlertType.ERROR, "Invite to Group", pair.getValue());
                }
            }
        });



        Button createGroupButton = new Button("Create New Group");
        createGroupButton.setPrefHeight(40);
        createGroupButton.setDefaultButton(true);
        createGroupButton.setPrefWidth(300);
        createGroupButton.setTranslateX(-230);
        createGroupButton.setTranslateY(212);
        createGroupButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(createGroupButton, new Insets(20, 0, 20, 0));


        createGroupButton.setOnAction(t -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Create Group", "Please enter a group name");
            }else{
                String groupName = nameField.getText().toLowerCase();
                Pair<Boolean, String> pair = Main.connection.createGroup(groupName);
                //Failed to make group
                if(!pair.getKey()){
                    showAlert(Alert.AlertType.ERROR, "Create Group", pair.getValue());
                }else{
                    showAlert(Alert.AlertType.CONFIRMATION, "Create Group", pair.getValue());
                    //Refresh group list
                    groupToInviteTo.getItems().removeAll(Main.userData.getGroupInfo());
                    groupToInviteTo.getItems().addAll(Main.userData.getGroupInfo());
                }
            }
        });


        Label thirdLabel = new Label("Join Group");
        thirdLabel.setFont(Font.font("Arial", 20));
        gridPaneGroup.add(thirdLabel, 0, 0, 2, 1);
        thirdLabel.setAlignment(Pos.CENTER);
        thirdLabel.setTranslateX(-20);
        thirdLabel.setTranslateY(105);
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

        TextField tfGroupNameJoin = new TextField();
        tfGroupNameJoin.setPrefHeight(40);
        tfGroupNameJoin.setPrefWidth(300);
        tfGroupNameJoin.setTranslateX(-70);
        tfGroupNameJoin.setTranslateY(45);
        gridPaneGroup.add(tfGroupNameJoin, 1, 4);

        Label joinCodeLabel = new Label("Join Code");
        joinCodeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneGroup.add(joinCodeLabel, 0, 0, 2, 1);
        joinCodeLabel.setAlignment(Pos.CENTER);
        joinCodeLabel.setTranslateX(-120);
        joinCodeLabel.setTranslateY(215);
        GridPane.setHalignment(joinCodeLabel, HPos.CENTER);
        GridPane.setMargin(joinCodeLabel, new Insets(20, 0, 20, 0));

        TextField tfJoinCode = new TextField();
        tfJoinCode.setPrefHeight(40);
        tfJoinCode.setPrefWidth(300);
        tfJoinCode.setTranslateX(-70);
        tfJoinCode.setTranslateY(110);
        gridPaneGroup.add(tfJoinCode, 1, 4);

        Button joinGroupButton = new Button("Join a Group");
        joinGroupButton.setPrefHeight(40);
        joinGroupButton.setDefaultButton(true);
        joinGroupButton.setPrefWidth(300);
        joinGroupButton.setTranslateX(230);
        joinGroupButton.setTranslateY(275);
        joinGroupButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(joinGroupButton, new Insets(20, 0, 20, 0));

        joinGroupButton.setOnAction(t -> {
            if(tfGroupNameJoin.getText().isEmpty()){
                showAlert(Alert.AlertType.WARNING, "Join Group", "Please enter a group name");
            }else if(tfJoinCode.getText().isEmpty()){
                showAlert(Alert.AlertType.WARNING, "Join Group", "Please enter a join code");
            }else{
                try{
                    int joinCode = Integer.parseInt(tfJoinCode.getText());
                    String groupName = tfGroupNameJoin.getText();
                    //Attempt to join
                    Pair<Boolean, String> pair = Main.connection.joinGroup(groupName, joinCode);
                    //Success!
                    if(pair.getKey()){
                        showAlert(Alert.AlertType.CONFIRMATION, "Join Group", pair.getValue());
                        //Refresh group list
                        groupToInviteTo.getItems().removeAll(Main.userData.getGroupInfo());
                        groupToInviteTo.getItems().addAll(Main.userData.getGroupInfo());
                    //Failure
                    }else{
                        showAlert(Alert.AlertType.ERROR, "Join Group", pair.getValue());
                    }
                }catch(NumberFormatException exception){
                    showAlert(Alert.AlertType.WARNING, "Join Group", "Join code must be a whole number");
                }
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