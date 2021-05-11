package client;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shared.Exercise;
import shared.Meal;


public class ProfilePane extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Profile");
        stage = primaryStage;
        Scene scene = profileScene(stage);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static Scene profileScene(Stage primaryStage) {
        stage = primaryStage;
        // SideMenu
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
        vBox.getChildren().addAll(profile, goals, summary, groups, logout);
        vBox.setPadding(new Insets(0, 0, 0, 0));

        // Profile
        GridPane gridPaneProfile = new GridPane();


        Label headerLabel = new Label("Profile");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gridPaneProfile.add(headerLabel, 0, 0, 2, 1);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setTranslateX(-170);
        headerLabel.setTranslateY(0);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // General
        Label secondLabel = new Label("General");
        secondLabel.setFont(Font.font("Arial", 20));
        gridPaneProfile.add(secondLabel, 0, 0, 2, 1);
        secondLabel.setAlignment(Pos.CENTER_LEFT);
        secondLabel.setTranslateX(-485);
        secondLabel.setTranslateY(70);
        GridPane.setHalignment(secondLabel, HPos.CENTER);
        GridPane.setMargin(secondLabel, new Insets(20, 0, 20, 0));


        Label username = new Label("Username");
        username.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(username, 0, 0, 2, 1);
        username.setAlignment(Pos.CENTER);
        username.setTranslateX(-600);
        username.setTranslateY(120);
        GridPane.setHalignment(username, HPos.CENTER);
        GridPane.setMargin(username, new Insets(20, 0, 20, 0));

        TextField tfUsername = new TextField(Main.userData.getUserName());
        tfUsername.setPrefHeight(40);
        tfUsername.setMaxWidth(400);
        tfUsername.setTranslateX(-520);
        tfUsername.setTranslateY(60);
        tfUsername.setEditable(false);
        gridPaneProfile.add(tfUsername, 1, 2);

        Label name = new Label("Name");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(name, 0, 0, 2, 1);
        name.setAlignment(Pos.CENTER);
        name.setTranslateX(-600);
        name.setTranslateY(170);
        GridPane.setHalignment(name, HPos.CENTER);
        GridPane.setMargin(name, new Insets(20, 0, 20, 0));

        TextField tfRealName = new TextField(Main.userData.getFullName());
        tfRealName.setPrefHeight(40);
        tfRealName.setMaxWidth(400);
        tfRealName.setTranslateX(-520);
        tfRealName.setTranslateY(110);
        tfRealName.setEditable(false);
        gridPaneProfile.add(tfRealName, 1, 2);

        Label email = new Label("Email Address");
        email.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(email, 0, 0, 2, 1);
        email.setAlignment(Pos.CENTER);
        email.setTranslateX(-600);
        email.setTranslateY(220);
        GridPane.setHalignment(email, HPos.CENTER);
        GridPane.setMargin(email, new Insets(20, 0, 20, 0));

        TextField tfEmail = new TextField(Main.userData.getEmail());
        tfEmail.setPrefHeight(40);
        tfEmail.setMaxWidth(400);
        tfEmail.setTranslateX(-520);
        tfEmail.setTranslateY(160);
        tfEmail.setEditable(false);
        gridPaneProfile.add(tfEmail, 1, 2);


        //Personal Information

        Label personalInformation = new Label("Personal Information");
        personalInformation.setFont(Font.font("Arial", 20));
        gridPaneProfile.add(personalInformation, 0, 0, 2, 1);
        personalInformation.setAlignment(Pos.CENTER);
        personalInformation.setTranslateX(-425);
        personalInformation.setTranslateY(280);
        GridPane.setHalignment(personalInformation, HPos.CENTER);
        GridPane.setMargin(personalInformation, new Insets(20, 0, 20, 0));

        Label height = new Label("Height");
        height.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(height, 0, 0, 2, 1);
        height.setAlignment(Pos.CENTER);
        height.setTranslateX(-600);
        height.setTranslateY(320);
        GridPane.setHalignment(height, HPos.CENTER);
        GridPane.setMargin(height, new Insets(20, 0, 20, 0));

        TextField tfHeight = new TextField();
        tfHeight.setPrefHeight(40);
        tfHeight.setMaxWidth(400);
        tfHeight.setTranslateX(-520);
        tfHeight.setTranslateY(260);
        gridPaneProfile.add(tfHeight, 1, 2);

        Label weight = new Label("Weight");
        weight.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(weight, 0, 0, 2, 1);
        weight.setAlignment(Pos.CENTER);
        weight.setTranslateX(-600);
        weight.setTranslateY(370);
        GridPane.setHalignment(weight, HPos.CENTER);
        GridPane.setMargin(weight, new Insets(20, 0, 20, 0));

        TextField tfWeight = new TextField();
        tfWeight.setPrefHeight(40);
        tfWeight.setMaxWidth(400);
        tfWeight.setTranslateX(-520);
        tfWeight.setTranslateY(310);
        gridPaneProfile.add(tfWeight, 1, 2);

        Label bMI = new Label("Current BMI");
        bMI.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(bMI, 0, 0, 2, 1);
        bMI.setAlignment(Pos.CENTER);
        bMI.setTranslateX(-600);
        bMI.setTranslateY(420);
        GridPane.setHalignment(bMI, HPos.CENTER);
        GridPane.setMargin(bMI, new Insets(20, 0, 20, 0));

        TextField tfBMI = new TextField();
        tfBMI.setPrefHeight(40);
        tfBMI.setMaxWidth(400);
        tfBMI.setTranslateX(-520);
        tfBMI.setTranslateY(360);
        gridPaneProfile.add(tfBMI, 1, 2);

        Label targetWeight = new Label("Target Weight");
        targetWeight.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(targetWeight, 0, 0, 2, 1);
        targetWeight.setAlignment(Pos.CENTER);
        targetWeight.setTranslateX(-600);
        targetWeight.setTranslateY(470);
        GridPane.setHalignment(targetWeight, HPos.CENTER);
        GridPane.setMargin(targetWeight, new Insets(20, 0, 20, 0));

        TextField tfTargetWeight = new TextField();
        tfTargetWeight.setPrefHeight(40);
        tfTargetWeight.setMaxWidth(400);
        tfTargetWeight.setTranslateX(-520);
        tfTargetWeight.setTranslateY(410);
        gridPaneProfile.add(tfTargetWeight, 1, 2);

        Label overView = new Label("Health Overview");
        overView.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(overView, 0, 0, 2, 1);
        overView.setAlignment(Pos.CENTER);
        overView.setTranslateX(-600);
        overView.setTranslateY(520);
        GridPane.setHalignment(overView, HPos.CENTER);
        GridPane.setMargin(overView, new Insets(20, 0, 20, 0));

        TextField tfHealthOverview = new TextField();
        tfHealthOverview.setPrefHeight(40);
        tfHealthOverview.setMaxWidth(400);
        tfHealthOverview.setTranslateX(-520);
        tfHealthOverview.setTranslateY(460);
        gridPaneProfile.add(tfHealthOverview, 1, 2);

        /*Button btnGenerate = new Button("Generate");
        btnGenerate.setPrefHeight(40);
        btnGenerate.setDefaultButton(true);
        btnGenerate.setPrefWidth(100);
        btnGenerate.setTranslateX(-130);
        btnGenerate.setTranslateY(580);
        btnGenerate.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(btnGenerate, new Insets(20, 0, 20, 0));

        btnGenerate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                double weight, height;
                try {
                    height = Double.parseDouble(tfHeight.getText());
                } catch (NumberFormatException e) {
                    showMsg("Invalid Height");
                    return;
                }
                try {
                    weight = Double.parseDouble(tfWeight.getText());
                } catch (NumberFormatException e) {
                    showMsg("Invalid Weight");
                    return;
                }
                double bmi = weight / (height * height);
                tfBMI.setText(String.format("%.2f", bmi));
            }
        });*/


        Label exercise1 = new Label("Exercise");
        exercise1.setFont(Font.font("Arial", 20));
        gridPaneProfile.add(exercise1, 0, 0, 2, 1);
        exercise1.setAlignment(Pos.CENTER);
        exercise1.setTranslateX(20);
        exercise1.setTranslateY(70);
        GridPane.setHalignment(exercise1, HPos.CENTER);
        GridPane.setMargin(exercise1, new Insets(20, 0, 20, 0));

        Label duration = new Label("Duration (Mins)");
        duration.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(duration, 0, 0, 2, 1);
        duration.setAlignment(Pos.CENTER);
        duration.setTranslateX(-100);
        duration.setTranslateY(120);
        GridPane.setHalignment(duration, HPos.CENTER);
        GridPane.setMargin(duration, new Insets(20, 0, 20, 0));

        TextField tfDuration = new TextField();
        tfDuration.setPrefHeight(40);
        tfDuration.setMaxWidth(400);
        tfDuration.setTranslateX(-20);
        tfDuration.setTranslateY(60);
        gridPaneProfile.add(tfDuration, 1, 2);

        Label distance = new Label("Distance (Km)");
        distance.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(distance, 0, 0, 2, 1);
        distance.setAlignment(Pos.CENTER);
        distance.setTranslateX(-100);
        distance.setTranslateY(170);
        GridPane.setHalignment(distance, HPos.CENTER);
        GridPane.setMargin(distance, new Insets(20, 0, 20, 0));

        TextField tfDistance = new TextField();
        tfDistance.setPrefHeight(40);
        tfDistance.setMaxWidth(400);
        tfDistance.setTranslateX(-20);
        tfDistance.setTranslateY(110);
        gridPaneProfile.add(tfDistance, 1, 2);

        Label exerciseType = new Label("Exercise Type");
        exerciseType.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(exerciseType, 0, 0, 2, 1);
        exerciseType.setAlignment(Pos.CENTER);
        exerciseType.setTranslateX(-100);
        exerciseType.setTranslateY(220);
        GridPane.setHalignment(exerciseType, HPos.CENTER);
        GridPane.setMargin(exerciseType, new Insets(20, 0, 20, 0));

        //ExerciseTypes (pulled from the enums in the exercise class)
        ComboBox cbTypeOfSports = new ComboBox<String>();
        cbTypeOfSports.getItems().addAll(Exercise.ExerciseType.values());
        cbTypeOfSports.setPrefHeight(40);
        cbTypeOfSports.setPrefWidth(300);
        cbTypeOfSports.setTranslateX(-20);
        cbTypeOfSports.setTranslateY(160);
        gridPaneProfile.add(cbTypeOfSports, 1, 2);

        //date picker for Exercise
        Label ExerciseDate = new Label("Exercise Date");
        ExerciseDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(ExerciseDate, 0, 0, 2, 1);
        ExerciseDate.setAlignment(Pos.CENTER);
        ExerciseDate.setTranslateX(-100);
        ExerciseDate.setTranslateY(270);
        GridPane.setHalignment(ExerciseDate,HPos.CENTER);
        GridPane.setMargin(ExerciseDate, new Insets(20, 0, 20, 0));

        DatePicker dpExerciseDate = new DatePicker(LocalDate.now());
        dpExerciseDate.setPrefHeight(40);
        dpExerciseDate.setPrefWidth(300);
        dpExerciseDate.setTranslateX(-20);
        dpExerciseDate.setTranslateY(210);
        gridPaneProfile.add(dpExerciseDate, 1, 2);


        //Add Exercise Button
        Button btnAddExercise = new Button("Add Exercise");
        btnAddExercise.setPrefHeight(40);
        btnAddExercise.setDefaultButton(true);
        btnAddExercise.setPrefWidth(150);
        btnAddExercise.setTranslateX(355);
        btnAddExercise.setTranslateY(70);
        btnAddExercise.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setHalignment(btnAddExercise, HPos.CENTER);

        btnAddExercise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                double distanceKm = -1;
                int durationMins = -1;
                if(cbTypeOfSports.getValue() == null){
                    showAlert(AlertType.WARNING, "Exercise Log","Please select an exercise type");
                }else if(dpExerciseDate.getValue() == null || dpExerciseDate.getValue().isAfter(LocalDate.now())){
                    showAlert(AlertType.WARNING, "Exercise Log","Please select a valid day, one not in the future");
                }else{
                    try{
                        distanceKm = Double.parseDouble(tfDistance.getText());
                    }catch(NumberFormatException exception){
                        //Do nothing
                    }
                    try{
                        durationMins = Integer.parseInt(tfDuration.getText());
                    }catch(NumberFormatException exception){
                        //Do nothing
                    }
                    Exercise exercise = null;
                    if(distanceKm > 0 && durationMins > 0){
                        exercise = new Exercise((Exercise.ExerciseType) cbTypeOfSports.getValue(), durationMins, distanceKm);
                    }else if(distanceKm > 0){
                        exercise = new Exercise((Exercise.ExerciseType) cbTypeOfSports.getValue(), distanceKm);
                    }else if(durationMins > 0){
                        exercise = new Exercise((Exercise.ExerciseType) cbTypeOfSports.getValue(), durationMins);
                    }
                    //If exercise still null then values weren't input correctly
                    if(exercise == null){
                        showAlert(AlertType.WARNING, "Exercise Log", "Please enter a valid Distance or Duration" +
                                "\nRemember, Duration must be a whole number");
                    }else {
                        boolean success = Main.userData.addExercise(exercise, dpExerciseDate.getValue());
                        if(success && Main.saveUserData()){
                            showAlert(AlertType.CONFIRMATION, "Exercise Log", "Exercise logged");
                        }else{
                            showAlert(AlertType.ERROR, "Exercise Log", "Sorry we couldn't add that, double check" +
                                    " & try again later");
                        }
                    }
                }
            }
        });



        // Diet

        Label diet = new Label("Diet");
        diet.setFont(Font.font("Arial", 20));
        gridPaneProfile.add(diet, 0, 0, 2, 1);
        diet.setAlignment(Pos.CENTER);
        diet.setTranslateX(0);
        diet.setTranslateY(320);
        GridPane.setHalignment(diet, HPos.CENTER);
        GridPane.setMargin(diet, new Insets(20, 0, 20, 0));

        Label food = new Label("Food");
        food.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(food, 0, 0, 2, 1);
        food.setAlignment(Pos.CENTER);
        food.setTranslateX(-100);
        food.setTranslateY(370);
        GridPane.setHalignment(food, HPos.CENTER);
        GridPane.setMargin(food, new Insets(20, 0, 20, 0));

        ComboBox cbFood = new ComboBox<String>();
        cbFood.setEditable(true);
        cbFood.getItems().addAll(Main.userData.getFoodSet());
        cbFood.setPrefHeight(40);
        cbFood.setPrefWidth(300);
        cbFood.setTranslateX(-20);
        cbFood.setTranslateY(310);
        gridPaneProfile.add(cbFood, 1, 2);

        Label drink = new Label("Drink");
        drink.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(drink, 0, 0, 2, 1);
        drink.setAlignment(Pos.CENTER);
        drink.setTranslateX(-100);
        drink.setTranslateY(420);
        GridPane.setHalignment(drink, HPos.CENTER);
        GridPane.setMargin(drink, new Insets(20, 0, 20, 0));

        ComboBox cbDrink = new ComboBox<String>();
        cbDrink.setEditable(true);
        cbDrink.getItems().addAll(Main.userData.getDrinkSet());
        cbDrink.setPrefHeight(40);
        cbDrink.setPrefWidth(300);
        cbDrink.setTranslateX(-20);
        cbDrink.setTranslateY(360);
        gridPaneProfile.add(cbDrink, 1, 2);

        Label meal = new Label("Meal Type");
        meal.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(meal, 0, 0, 2, 1);
        meal.setAlignment(Pos.CENTER);
        meal.setTranslateX(-100);
        meal.setTranslateY(470);
        GridPane.setHalignment(meal, HPos.CENTER);
        GridPane.setMargin(meal, new Insets(20, 0, 20, 0));

        ComboBox cbMealType = new ComboBox<String>();
        cbMealType.getItems().addAll(Meal.MealType.values());
        cbMealType.setPrefHeight(40);
        cbMealType.setPrefWidth(300);
        cbMealType.setTranslateX(-20);
        cbMealType.setTranslateY(410);
        gridPaneProfile.add(cbMealType, 1, 2);

        Label calorieCount = new Label("Calorie Count");
        calorieCount.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(calorieCount, 0, 0, 2, 1);
        calorieCount.setAlignment(Pos.CENTER);
        calorieCount.setTranslateX(-100);
        calorieCount.setTranslateY(520);
        GridPane.setHalignment(calorieCount, HPos.CENTER);
        GridPane.setMargin(calorieCount, new Insets(20, 0, 20, 0));

        TextField tfCalorieCount = new TextField();
        tfCalorieCount.setPrefHeight(40);
        tfCalorieCount.setMaxWidth(400);
        tfCalorieCount.setTranslateX(-20);
        tfCalorieCount.setTranslateY(460);
        gridPaneProfile.add(tfCalorieCount, 1, 2);


        //date picker for Diet

        Label DietDate = new Label("Date");
        DietDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(DietDate, 0, 0, 2, 1);
        DietDate.setAlignment(Pos.CENTER);
        DietDate.setTranslateX(-100);
        DietDate.setTranslateY(570);
        GridPane.setHalignment(DietDate, HPos.CENTER);
        GridPane.setMargin(DietDate, new Insets(20, 0, 20, 0));

        DatePicker dpDietDate = new DatePicker(LocalDate.now());
        dpDietDate.setPrefHeight(40);
        dpDietDate.setPrefWidth(300);
        dpDietDate.setTranslateX(-20);
        dpDietDate.setTranslateY(510);
        gridPaneProfile.add(dpDietDate, 1, 2);

        //Add Diet Button
        Button btnAddDiet = new Button("Add Diet");
        btnAddDiet.setPrefHeight(40);
        btnAddDiet.setDefaultButton(true);
        btnAddDiet.setPrefWidth(150);
        btnAddDiet.setTranslateX(355);
        btnAddDiet.setTranslateY(320);
        btnAddDiet.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setHalignment(btnAddDiet, HPos.CENTER);

        btnAddDiet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                //local arguments
                String food = cbFood.getValue().toString();
                String drink = cbDrink.getValue().toString();
                //Check is meal type has been selected
                if (cbMealType.getValue() == null) {
                    showAlert(AlertType.WARNING, "Diet Log", "Please select an meal type");
                } else if (dpDietDate.getValue() == null || dpDietDate.getValue().isAfter(LocalDate.now())) {
                    showAlert(AlertType.WARNING, "Diet Log", "Please select a valid day, one not in the future");
                } else if (food == null || drink == null || food.length() == 0 || drink.length() == 0) {
                    showAlert(AlertType.WARNING, "Diet Log", "Please enter or select both a food and drink");
                } else {
                    Double calorieCount = -1d;
                    try {
                        calorieCount = Double.parseDouble(tfCalorieCount.getText());
                    }catch (NumberFormatException exception) {
                        //do nothing
                    }
                    if(calorieCount > 0){
                        //If food & drinks are new add to set
                        Main.userData.addDrink(drink);
                        Main.userData.addFood(food);
                        //Reissue food and drink sets
                        cbDrink.getItems().removeAll(Main.userData.getDrinkSet());
                        cbDrink.getItems().addAll(Main.userData.getDrinkSet());
                        cbFood.getItems().removeAll(Main.userData.getFoodSet());
                        cbFood.getItems().addAll(Main.userData.getFoodSet());

                        Meal meal = new Meal(food, drink,(Meal.MealType)cbMealType.getValue());
                        boolean success = Main.userData.addMeal(meal, dpDietDate.getValue());
                        if(success && Main.saveUserData()){
                            System.out.println("Logged Meal: " + meal.getFoodName() + " & " + meal.getDrinkName());
                            showAlert(AlertType.CONFIRMATION, "Diet Log", "Success! Your meal has been logged");
                        }else{
                            showAlert(AlertType.ERROR, "Diet Log", "Sorry we couldn't add that, double check" +
                                    " & try again later");
                        }
                    }else{
                        showAlert(AlertType.WARNING, "Diet Log", "Please enter a valid calorie count");
                    }
                }
            }

        });






        Button btnSave = new Button("Save User Info");
        btnSave.setPrefHeight(40);
        btnSave.setDefaultButton(true);
        btnSave.setPrefWidth(300);
        btnSave.setTranslateX(-220);
        btnSave.setTranslateY(575);
        btnSave.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(btnSave, new Insets(20, 0, 20, 0));

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (username.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error!", "Please enter username");
                }
                if (name.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error!", "Please enter name");
                }
                if (email.getText().isEmpty()) {
                    showMsg("Please enter email address");
                }

                if (height.getText().isEmpty()) {
                    showMsg("Please enter height");
                }
                if (weight.getText().isEmpty()) {
                    showMsg("Please enter weight");
                }
                if (bMI.getText().isEmpty()) {
                    showMsg("Please enter BMI");
                }
                if (targetWeight.getText().isEmpty()) {
                    showMsg("Please enter target weight");
                }
                if (overView.getText().isEmpty()) {
                    showMsg("Please enter overview");
                }
                if (email.getText().isEmpty()) {
                    showMsg("Please enter email address");
                }

            }
        });


        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneProfile);

        gridPaneProfile.getChildren().addAll(btnSave,btnAddExercise, btnAddDiet);

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
    public void handle(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Information Saved");
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
