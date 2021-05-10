package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GoalSettingPane extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Goal Settings");
        stage = primaryStage;
        Scene scene = goalSettingScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static Scene goalSettingScene() {

        VBox vBox = new VBox(50);

        vBox.setStyle("-fx-background-color: #3D405B;");

        vBox.setPrefWidth(230);

        JFXButton profile = new JFXButton("Profile");
        profile.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        profile.setPrefSize(230, 100);

        profile.setOnAction(event -> {
            stage.setScene(ProfilePane.profileScene());
        });

        JFXButton goals = new JFXButton("Goals");
        goals.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        goals.setPrefWidth(230);

        JFXButton summary = new JFXButton("Weekly Summary");
        summary.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        summary.setPrefWidth(230);

        JFXButton groups = new JFXButton("Groups");
        groups.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        groups.setPrefWidth(230);

        groups.setOnAction(event -> {
            stage.setScene(GroupsPane.groupScene());
        });

        JFXButton logout = new JFXButton("Logout");
        logout.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        logout.setPrefWidth(230);

        logout.setOnAction(event -> {
            stage.setScene(Main.logInScene());
        });

        vBox.setAlignment(Pos.BASELINE_LEFT);

        vBox.getChildren().addAll(profile, goals, summary, groups, logout);
        vBox.setPadding(new Insets(0, 0, 0, 0));


        GridPane gridPaneSetting = new GridPane();

        Label headerLabel = new Label("Goals");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gridPaneSetting.add(headerLabel, 0, 0, 2, 1);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setTranslateX(-300);
        headerLabel.setTranslateY(0);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

//        ImageView ivPhoto = new ImageView();
//        Image image = new Image(GoalSettingPane.class.getResourceAsStream("photo_default.jpg"));
//        ivPhoto.fitWidthProperty().set(150);
//        ivPhoto.fitHeightProperty().set(150);
//        ivPhoto.setImage(image);
//        ivPhoto.setTranslateX(-130);
//        ivPhoto.setTranslateY(500);
//
//        ivPhoto.setOnMouseClicked(event -> {
//
//            JFileChooser chooser = new JFileChooser(new File("."));
//            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//            chooser.setFileFilter(
//                    new FileNameExtensionFilter("image files", "jpg", "png", "bmp"));
//
//            int status = chooser.showOpenDialog(null);
//            if (status == JFileChooser.APPROVE_OPTION) {
//                File selectedFile = chooser.getSelectedFile();
//                if (selectedFile != null) {
//                    try {
//                        Image image1 = new Image(new FileInputStream(selectedFile));
//                        ivPhoto.setImage(image1);
//
//                        File photoFile = selectedFile;
//                    } catch (FileNotFoundException e) {
//                        showMsg("failed to read image");
//                    }
//                }
//            }
//        });

        Label secondLabel = new Label("General");
        secondLabel.setFont(Font.font("Arial", 20));
        gridPaneSetting.add(secondLabel, 0, 0, 2, 1);
        secondLabel.setAlignment(Pos.CENTER);
        secondLabel.setTranslateX(-300);
        secondLabel.setTranslateY(250);
        GridPane.setHalignment(secondLabel, HPos.CENTER);
        GridPane.setMargin(secondLabel, new Insets(20, 0, 20, 0));

        Label username = new Label("Username");
        username.setFont(Font.font("Arial", 15));
        gridPaneSetting.add(username, 0, 0, 2, 1);
        username.setAlignment(Pos.CENTER);
        username.setTranslateX(-00);
        username.setTranslateY(150);
        GridPane.setHalignment(username, HPos.CENTER);
        GridPane.setMargin(username, new Insets(20, 0, 20, 0));

        TextField tfUsername = new TextField();
        tfUsername.setPrefHeight(40);
        tfUsername.setPrefWidth(300);
        tfUsername.setTranslateX(-300);
        tfUsername.setTranslateY(170);
        gridPaneSetting.add(tfUsername, 1, 4);

        Label email = new Label("Email Address");
        email.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(email, 0, 0, 2, 1);
        email.setAlignment(Pos.CENTER);
        email.setTranslateX(-600);
        email.setTranslateY(150);
        GridPane.setHalignment(email, HPos.CENTER);
        GridPane.setMargin(email, new Insets(20, 0, 20, 0));

        TextField tfEmail = new TextField();
        tfEmail.setPrefHeight(40);
        tfEmail.setPrefWidth(300);
        tfEmail.setTranslateX(-530);
        tfEmail.setTranslateY(170);
        gridPaneSetting.add(tfEmail, 1, 4);


//        public void handle (ActionEvent event){
//            double targetWeight;
//            double duration;
//
//            try {
//                targetWeight = Double.parseDouble(tfTargetWeight.getText());
//            } catch (NumberFormatException e) {
//                showMsg("Invalid Target Weight");
//                return;
//            }
//
//            try {
//                duration = Double.parseDouble(tfDuration.getText());
//            } catch (NumberFormatException e) {
//                showMsg("Invalid Duration");
//                return;
//            }
//
//            LocalDate startDate = dpStartingDate.getValue();
//            LocalDate deadlineDate = dpDeadLine.getValue();
//            String exercise = cbExercise.getValue();
//            String username = tfUsername.getText();
//
//            Stage stage = new Stage();
//            GoalStartScene root = new GoalStartScene(stage);
//            root.targetWeight = targetWeight;
//            root.duration = duration;
//            root.startDate = startDate;
//            root.deadlineDate = deadlineDate;
//            root.exercise = exercise;
//            root.username = username;
//            root.photoFile = photoFile;
//
//            root.createLayout();
//
//            Scene scene = new Scene(root);
//
//            stage.setScene(scene);
//
//            stage.showAndWait();
//        }

        LocalDate nowDate = LocalDate.now();

        Label thirdLabel = new Label("Diet");
        thirdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        gridPaneSetting.add(thirdLabel, 0, 0, 2, 1);
        thirdLabel.setAlignment(Pos.CENTER);
        thirdLabel.setTranslateX(30);
        thirdLabel.setTranslateY(70);
        GridPane.setHalignment(thirdLabel, HPos.CENTER);
        GridPane.setMargin(thirdLabel, new Insets(20, 0, 20, 0));

        Label startDate = new Label("Starting Date");
        startDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(startDate, 0, 0, 2, 1);
        startDate.setAlignment(Pos.CENTER);
        startDate.setTranslateX(-600);
        startDate.setTranslateY(150);
        GridPane.setHalignment(startDate, HPos.CENTER);
        GridPane.setMargin(startDate, new Insets(20, 0, 20, 0));

        DatePicker dpStartingDate = new DatePicker(nowDate);
        dpStartingDate.setPrefHeight(40);
        dpStartingDate.setPrefWidth(300);
        dpStartingDate.setTranslateX(-530);
        dpStartingDate.setTranslateY(150);
        gridPaneSetting.add(dpStartingDate, 1, 2);

        Label deadlineDate = new Label("Deadline");
        deadlineDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(deadlineDate, 0, 0, 2, 1);
        deadlineDate.setAlignment(Pos.CENTER);
        deadlineDate.setTranslateX(-600);
        deadlineDate.setTranslateY(150);
        GridPane.setHalignment(deadlineDate, HPos.CENTER);
        GridPane.setMargin(deadlineDate, new Insets(20, 0, 20, 0));

        DatePicker dpDeadLine = new DatePicker(nowDate.plusDays(7));
        dpDeadLine.setPrefHeight(40);
        dpDeadLine.setPrefWidth(300);
        dpDeadLine.setTranslateX(-530);
        dpDeadLine.setTranslateY(150);
        gridPaneSetting.add(dpDeadLine, 1, 2);

        Label targetWeight = new Label("Target Weight");
        targetWeight.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(targetWeight, 0, 0, 2, 1);
        targetWeight.setAlignment(Pos.CENTER);
        targetWeight.setTranslateX(-600);
        targetWeight.setTranslateY(150);
        GridPane.setHalignment(targetWeight, HPos.CENTER);
        GridPane.setMargin(targetWeight, new Insets(20, 0, 20, 0));

        TextField tfTargetWeight = new TextField();
        tfTargetWeight.setPrefHeight(40);
        tfTargetWeight.setMaxWidth(400);
        tfTargetWeight.setTranslateX(-530);
        tfTargetWeight.setTranslateY(90);
        gridPaneSetting.add(tfTargetWeight, 1, 2);

        Label exercise = new Label("Exercise");
        exercise.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(exercise, 0, 0, 2, 1);
        exercise.setAlignment(Pos.CENTER);
        exercise.setTranslateX(-600);
        exercise.setTranslateY(150);
        GridPane.setHalignment(exercise, HPos.CENTER);
        GridPane.setMargin(exercise, new Insets(20, 0, 20, 0));

        ComboBox cbExercise = new ComboBox<String>();
        cbExercise.setEditable(true);
        cbExercise.getItems().addAll("Swimming", "Jogging");
        cbExercise.setPrefHeight(40);
        cbExercise.setPrefWidth(300);
        cbExercise.setTranslateX(-530);
        cbExercise.setTranslateY(150);
        gridPaneSetting.add(cbExercise, 1, 2);

        Label duration = new Label("Duration");
        duration.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(duration, 0, 0, 2, 1);
        duration.setAlignment(Pos.CENTER);
        duration.setTranslateX(-600);
        duration.setTranslateY(150);
        GridPane.setHalignment(duration, HPos.CENTER);
        GridPane.setMargin(duration, new Insets(20, 0, 20, 0));

        TextField tfDuration = new TextField();
        tfDuration.setPrefHeight(40);
        tfDuration.setMaxWidth(400);
        tfDuration.setTranslateX(-530);
        tfDuration.setTranslateY(90);
        gridPaneSetting.add(tfDuration, 1, 2);


        Button btnSave = new Button("Set the Goal");
        btnSave.setPrefHeight(40);
        btnSave.setDefaultButton(true);
        btnSave.setPrefWidth(100);
        btnSave.setTranslateX(-130);
        btnSave.setTranslateY(500);
        btnSave.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(btnSave, new Insets(20, 0, 20, 0));

        btnSave.setOnAction(event -> {
            stage.setScene(GoalStartPane.goalStartScene());
        });

        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneSetting);

        gridPaneSetting.getChildren().addAll(btnSave);

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