package client;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import shared.Exercise;
import shared.Goal;
import shared.Weight;

public class GoalSettingPane extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Goal Settings");
        stage = primaryStage;
        Scene scene = goalSettingScene(stage);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static Scene goalSettingScene(Stage primaryStage) {
        stage = primaryStage;

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
                    stage.setScene(WeeklySummary.summaryScene(stage));
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
        summary.setFocusTraversable(false);
        groups.setFocusTraversable(false);
        logout.setFocusTraversable(false);

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

        LocalDate nowDate = LocalDate.now();

        // Exercise Goal

        Label thirdLabel = new Label("Exercise");
        thirdLabel.setFont(Font.font("Arial", 20));
        gridPaneSetting.add(thirdLabel, 0, 0, 2, 1);
        thirdLabel.setAlignment(Pos.CENTER);
        thirdLabel.setTranslateX(-490);
        thirdLabel.setTranslateY(290);
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
        cbExercise.getItems().addAll(Exercise.ExerciseType.values());
        cbExercise.setPrefHeight(40);
        cbExercise.setPrefWidth(300);
        cbExercise.setTranslateX(-530);
        cbExercise.setTranslateY(320);
        gridPaneSetting.add(cbExercise, 1, 2);

        Label duration = new Label("Duration (Mins)");
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
        btnExercise.setTranslateX(-230);
        btnExercise.setTranslateY(500);
        btnExercise.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(btnExercise, new Insets(20, 0, 20, 0));

        btnExercise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                int durationMins = -1;
                if(cbExercise.getValue() == null){
                    showAlert(AlertType.WARNING, "Exercise Goal","Please select an exercise type");
                }else if(!LocalDate.now().isBefore(dpDeadLine.getValue())){
                    showAlert(AlertType.WARNING, "Exercise Goal","Please enter a date in the future");
                }else{
                    try{
                        durationMins = Integer.parseInt(tfDuration.getText());
                        if(durationMins > 0 && (LocalDate.now().until(dpDeadLine.getValue(), ChronoUnit.DAYS))*1440 > durationMins){
                            Exercise exercise = new Exercise((Exercise.ExerciseType) cbExercise.getValue(), durationMins);
                            Goal goal = new Goal(exercise);
                            if(Main.userData.addGoal(goal, dpDeadLine.getValue()) && Main.saveUserData()){
                                showAlert(AlertType.CONFIRMATION, "Exercise Goal", "Your exercise goal has been saved");
                            }else{
                                showAlert(AlertType.ERROR, "Exercise Goal", "Sorry we couldn't add that, double check" +
                                        " & try again later");
                            }
                        }else{
                            throw new NumberFormatException();
                        }
                    }catch(NumberFormatException exception){
                        showAlert(AlertType.WARNING, "Exercise Goal","Please enter a duration above 0 and " +
                                "no longer than the minutes until your deadline");
                    }
                }
            }
        });


        // Weight Goal

        Label forthLabel = new Label("Weight");
        forthLabel.setFont(Font.font("Arial", 20));
        gridPaneSetting.add(forthLabel, 0, 0, 2, 1);
        forthLabel.setAlignment(Pos.CENTER);
        forthLabel.setTranslateX(-20);
        forthLabel.setTranslateY(290);
        GridPane.setHalignment(forthLabel, HPos.CENTER);
        GridPane.setMargin(forthLabel, new Insets(20, 0, 20, 0));

        Label deadlineDateWeight = new Label("Goal Deadline");
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


        Label targetWeight = new Label("Target Weight (Kg)");
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

        Label gainingOrLoosing = new Label("Tick if you wish to gain weight:");
        gainingOrLoosing.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneSetting.add(gainingOrLoosing, 0, 0, 2, 1);
        gainingOrLoosing.setAlignment(Pos.CENTER);
        gainingOrLoosing.setTranslateX(70);
        gainingOrLoosing.setTranslateY(433);
        GridPane.setHalignment(gainingOrLoosing, HPos.CENTER);
        GridPane.setMargin(gainingOrLoosing, new Insets(20, 0, 20, 0));


        CheckBox cbGainingWeight = new CheckBox();
        cbGainingWeight.setTranslateX(210);
        cbGainingWeight.setTranslateY(370);
        gridPaneSetting.add(cbGainingWeight, 1, 2);


        Button btnWeight = new Button("Set Weight Goal");
        btnWeight.setPrefHeight(40);
        btnWeight.setDefaultButton(true);
        btnWeight.setPrefWidth(300);
        btnWeight.setTranslateX(250);
        btnWeight.setTranslateY(500);
        btnWeight.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(btnWeight, new Insets(20, 0, 20, 0));


        btnWeight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double goalWeight;
                boolean gainingWeight = cbGainingWeight.isSelected();
                try{
                    //Parse weight
                    goalWeight = Double.parseDouble(tfTargetWeight.getText());
                    if(goalWeight <= 0){
                        throw new NumberFormatException();
                    }
                    //Check if you have already met your goal
                    double currentWeight = Main.userData.getCurrentWeight().getWeightKg();
                    double goalBmi = Weight.calculateBMI(goalWeight, Main.userData.getHeightCm());
                    if((gainingWeight && goalWeight <= currentWeight) || (!gainingWeight && goalWeight >= currentWeight)){
                        showAlert(AlertType.WARNING, "Weight Goal", "Your current weight has already met this goal, set a different goal");
                    //Check if your goal could be harmful
                    }else if(((goalBmi < Weight.BMIRank.NORMAL.getLowerBound()) && !gainingWeight) || ((goalBmi > Weight.BMIRank.OBESE.getLowerBound())&&gainingWeight)){
                        String gainLoose = gainingWeight ? "gaining" : "loosing";
                        showAlert(AlertType.WARNING, "Weight Goal", "Your current height suggests that purposely "
                                + gainLoose + " that much weight could harm your health.\nPlease enter a different goal");
                    //Check your goal is in the future
                    }else if(!LocalDate.now().isBefore(dpDeadLineWeight.getValue())){
                        showAlert(AlertType.WARNING, "Weight Goal", "Please enter a date in the future");
                    //Create goal
                    }else{
                        Weight weight = new Weight(goalWeight);
                        Goal goal = new Goal(weight, gainingWeight);
                        if(Main.userData.addGoal(goal, dpDeadLineWeight.getValue()) && Main.saveUserData()){
                            showAlert(AlertType.CONFIRMATION, "Weight Goal", "Your weight goal has been saved");
                        }else{
                            showAlert(AlertType.ERROR, "Weight Goal", "Sorry we couldn't add that, double check" +
                                " & try again later");
                            }
                    }
                }catch(NumberFormatException exception){
                    showAlert(AlertType.WARNING, "Weight Goal", "Please enter a valid number");
                }
            }
        });
        /*btnWeight.setOnAction(new EventHandler<ActionEvent>() {
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
                //String username = tfUsername.getText();

                Stage stage = new Stage();
                GoalStartPane root = new GoalStartPane(stage);
                root.targetWeight = targetWeight;
                root.deadlineDate = deadlineDate;
                //root.username = username;

                root.createLayout();

                Scene scene = new Scene(root);

                stage.setScene(scene);

                stage.setTitle(root.getTitle());
                stage.showAndWait();

            }
        });*/

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