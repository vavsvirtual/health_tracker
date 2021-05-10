package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GoalStartPane extends Application {

    public static Stage stage;

    double targetWeight;
    double duration;
    LocalDate startDate;
    LocalDate deadlineDate;
    String exercise;
    String username;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Goal Start");
        stage = primaryStage;
        Scene scene = goalStartScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static Scene goalStartScene() {

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


        GridPane gridPaneStart = new GridPane();


        Label headerLabel = new Label("Goals");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gridPaneStart.add(headerLabel, 0, 0, 2, 1);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setTranslateX(-400);
        headerLabel.setTranslateY(0);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));


//        public void run () {
//            secondsLeft--;
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    labelTimeLeft.setText(getTimeLeft());
//                }
//            });
//
//            if (secondsLeft == 0) {
//                timer.cancel();
//
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        timeUp();
//                    }
//                });
//
//            }
//        }
//
//
//        int secondsLeft = (int) (duration * 60);
//
//        Timer timer = new Timer();
//        timer.schedule(new TimerJob(), 0, 1000);
//
//
//        ButtonType buttonType = new ButtonType("Set Next Goal");
//        Alert alert = new Alert(AlertType.CONFIRMATION, "Time up!\nMeet Goal?", ButtonType.YES,
//                ButtonType.NO, buttonType);
//        alert.setWidth(500);
//
//        alert.showAndWait().ifPresent(response -> {
//            if (response == ButtonType.YES) {
//                showMsg("Thanks for using this!");
//                stage.close();
//            }
//
//            if (response == ButtonType.NO) {
//                startTimer();
//            }
//
//            if (response == buttonType) {
//                stage.close();
//            }
//        });
//
//
//
//        startTimer();
//
//
//        labelTimeLeft = createLabel(getTimeLeft());

        Label startDate = new Label("Starting Date");
        startDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneStart.add(startDate, 0, 0, 2, 1);
        startDate.setAlignment(Pos.CENTER);
        startDate.setTranslateX(-600);
        startDate.setTranslateY(150);
        GridPane.setHalignment(startDate, HPos.CENTER);
        GridPane.setMargin(startDate, new Insets(20, 0, 20, 0));

        Label deadlineDate = new Label("Deadline");
        deadlineDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneStart.add(deadlineDate, 0, 0, 2, 1);
        deadlineDate.setAlignment(Pos.CENTER);
        deadlineDate.setTranslateX(-600);
        deadlineDate.setTranslateY(150);
        GridPane.setHalignment(deadlineDate, HPos.CENTER);
        GridPane.setMargin(deadlineDate, new Insets(20, 0, 20, 0));

        Label targetWeight = new Label("Target Weight");
        targetWeight.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneStart.add(targetWeight, 0, 0, 2, 1);
        targetWeight.setAlignment(Pos.CENTER);
        targetWeight.setTranslateX(-600);
        targetWeight.setTranslateY(150);
        GridPane.setHalignment(targetWeight, HPos.CENTER);
        GridPane.setMargin(targetWeight, new Insets(20, 0, 20, 0));

        Label exercise = new Label("Exercise");
        exercise.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneStart.add(exercise, 0, 0, 2, 1);
        exercise.setAlignment(Pos.CENTER);
        exercise.setTranslateX(-600);
        exercise.setTranslateY(150);
        GridPane.setHalignment(exercise, HPos.CENTER);
        GridPane.setMargin(exercise, new Insets(20, 0, 20, 0));


        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

//            gridPane.add(createLabel(startDate.format(formatter)), 1, 0);
//            gridPane.add(createLabel(deadlineDate.format(formatter)), 1, 1);
//            gridPane.add(createLabel(String.format("%.2f kg", targetWeight)), 1, 2);
//            gridPane.add(createLabel(exercise), 1, 3);
//            gridPane.add(labelTimeLeft, 1, 4);


//        return String.format("%02d:%02d", secondsLeft / 60, secondsLeft % 60);

        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneStart);

        gridPaneStart.getChildren().addAll();

        return new Scene(menu, 1200, 700);
    }



    private static void showMsg(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void handle(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Information Saved");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}