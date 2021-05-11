package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;


public class ProfilePane extends Application {

    public static Stage stage;
    // photo
    private static ImageView ivPhoto;
    private static File photoFile;

    // general
    private static TextField tfUsername;
    private static TextField tfRealName;
    private static TextField tfEmail;

    // personal information
    private static TextField tfHeight;
    private static TextField tfWeight;
    private static TextField tfBMI;
    private static TextField tfTargetWeight;
    private static TextField tfHealthOverview;
    private static Button btnGenerate;

    // exercise
    private static TextField tfDuration;
    private static TextField tfDistance;
    private static ComboBox<String> cbTypeOfSports;

    // diet
    private static ComboBox<String> cbFood;
    private static ComboBox<String> cbDrink;
    private static ComboBox<String> cbMealType;
    private static TextField tfCalorieCount;

    // save button
    private static Button btnSave;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Profile");
        stage = primaryStage;
        Scene scene = profileScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }



    public static Scene profileScene() {

        VBox vBox = new VBox(50);

        vBox.setStyle("-fx-background-color: #3D405B;");

        vBox.setPrefWidth(230);

        JFXButton profile = new JFXButton("Profile");
        profile.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        profile.setPrefSize(230, 100);

        profile.setOnAction(event -> {
            try {
                stage.setScene(ProfilePane.profileScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            try {
                stage.setScene(GroupsPane.groupScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        JFXButton logout = new JFXButton("Logout");
        logout.setStyle("-fx-text-fill: #F4F1DE; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center");
        logout.setPrefWidth(230);
        logout.setOnAction(event -> {
            try {
                stage.setScene(Main.logInScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        vBox.setAlignment(Pos.BASELINE_LEFT);

        vBox.getChildren().addAll(profile, goals, summary, groups, logout);
        vBox.setPadding(new Insets(0, 0, 0, 0));

        GridPane gridPaneProfile = new GridPane();

        cbFood = new ComboBox<String>();
        cbFood.setEditable(true);
        cbFood.getItems().addAll("Hamburger", "Salad", "Ice Cream");

        cbDrink = new ComboBox<String>();
        cbDrink.setEditable(true);
        cbDrink.getItems().addAll("Water", "Cola", "Sprite");

        cbMealType = new ComboBox<String>();
        cbMealType.setEditable(true);
        cbMealType.getItems().addAll("Vegan", "Fruit", "Starch");

        tfCalorieCount = new TextField();

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Food"), 0, 0);
        gridPane.add(createLabel("Drink"), 0, 1);
        gridPane.add(createLabel("Meal Type"), 0, 2);
        gridPane.add(createLabel("Calorie Count"), 0, 3);

        gridPane.add(cbFood, 1, 0);
        gridPane.add(cbDrink, 1, 1);
        gridPane.add(cbMealType, 1, 2);
        gridPane.add(tfCalorieCount, 1, 3);

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setPadding(new Insets(5, -300, 0, 50));
        VBox vBox1 = new VBox(createTitleLabel("Diet"), hBox);
        vBox1.setAlignment(Pos.BOTTOM_RIGHT);
        vBox1.setPadding(new Insets(-500, 500, 50, -100));


        tfDuration = new TextField();
        tfDistance = new TextField();
        cbTypeOfSports = new ComboBox<String>();
        cbTypeOfSports.setEditable(true);
        cbTypeOfSports.getItems().addAll("Swimming", "Togging", "Yoga");

        GridPane gridPane1 = createGridPane();
        gridPane1.add(createLabel("Duration"), 0, 0);
        gridPane1.add(createLabel("Distance"), 0, 1);
        gridPane1.add(createLabel("Type of Sports"), 0, 2);
        gridPane1.add(tfDuration, 1, 0);
        gridPane1.add(tfDistance, 1, 1);
        gridPane1.add(cbTypeOfSports, 1, 2);

        HBox hBox1 = new HBox(gridPane);
        hBox1.setAlignment(Pos.TOP_RIGHT);
        hBox1.setPadding(new Insets(5, -300, 0, 50));
        VBox vBox2 = new VBox(createTitleLabel("Exercise"), hBox);
        vBox2.setAlignment(Pos.TOP_RIGHT);
        vBox2.setPadding(new Insets(-380, 500, 0, -100));


        tfHeight = new TextField();
        tfWeight = new TextField();
        tfBMI = new TextField();
        tfTargetWeight = new TextField();
        tfHealthOverview = new TextField();

        btnGenerate = new Button("Generate");

        btnGenerate.setPrefSize(100, 20);
        btnGenerate.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");


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
        });

        GridPane gridPane2 = createGridPane();
        gridPane2.add(createLabel("Height(m)"), 0, 0);
        gridPane2.add(createLabel("Weight(kg)"), 0, 1);
        gridPane2.add(createLabel("Current BMI"), 0, 2);
        gridPane2.add(createLabel("Target Weight"), 0, 3);
        gridPane2.add(createLabel("Health Overview"), 0, 4);

        gridPane.add(tfHeight, 1, 0);
        gridPane.add(tfWeight, 1, 1);
        gridPane.add(tfBMI, 1, 2);
        gridPane.add(tfTargetWeight, 1, 3);
        gridPane.add(tfHealthOverview, 1, 4);
        gridPane.add(btnGenerate, 3, 2);

        HBox hBox3 = new HBox(gridPane);
        hBox3.setAlignment(Pos.BOTTOM_LEFT);
        hBox3.setPadding(new Insets(5, 0, 0, 50));
        VBox vBox3 = new VBox(createTitleLabel("Personal Information"), hBox);
        vBox3.setAlignment(Pos.BOTTOM_LEFT);
        vBox3.setPadding(new Insets(5, 0, 0, 50));


        btnSave = new Button("  Save  ");
        btnSave.setPrefSize(250, 40);
        btnSave.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");

        //btnSave.setOnAction(this);

        VBox vBox4 = new VBox(btnSave);
        vBox4.setPadding(new Insets(10, 10, 10, 10));
        vBox4.setAlignment(Pos.CENTER);



//        ivPhoto = new ImageView();
//        Image image = new Image(getClass().getResourceAsStream("photo_default.jpg"));
//        ivPhoto.fitWidthProperty().set(150);
//        ivPhoto.fitHeightProperty().set(150);
//        ivPhoto.setImage(image);
//        VBox vBox5 = new VBox(ivPhoto);
//        vBox5.setPadding(new Insets(10, 10, 10, 10));
//
//        ivPhoto.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//
//                JFileChooser chooser = new JFileChooser(new File("."));
//                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                chooser.setFileFilter(
//                        new FileNameExtensionFilter("image files", "jpg", "png", "bmp"));
//
//                int status = chooser.showOpenDialog(null);
//                if (status == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = chooser.getSelectedFile();
//                    if (selectedFile != null) {
//                        try {
//                            Image image = new Image(new FileInputStream(selectedFile));
//                            ivPhoto.setImage(image);
//
//                            photoFile = selectedFile;
//                        } catch (FileNotFoundException e) {
//                            showMsg("failed to read image");
//                        }
//                    }
//                }
//            }
//        });
//        vBox5.setAlignment(Pos.CENTER);
//        vBox5.setPadding(new Insets(50, 0, 0, 0));



        tfUsername = new TextField();
        tfRealName = new TextField();
        tfEmail = new TextField();

        GridPane gridPane5 = createGridPane();
        gridPane5.add(createLabel("Username"), 0, 0);
        gridPane5.add(createLabel("Real Name"), 0, 1);
        gridPane5.add(createLabel("Email Address"), 0, 2);
        gridPane5.add(tfUsername, 1, 0);
        gridPane5.add(tfRealName, 1, 1);
        gridPane5.add(tfEmail, 1, 2);

        HBox hBox4 = new HBox(gridPane);
        hBox4.setAlignment(Pos.TOP_LEFT);
        hBox4.setPadding(new Insets(5, 50, 0, 50));
        VBox vBox6 = new VBox(createTitleLabel("General"), hBox);
        vBox6.setAlignment(Pos.TOP_LEFT);
        vBox6.setPadding(new Insets(5, 50, 0, 50));


        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneProfile);

        gridPaneProfile.getChildren().addAll();

        return new Scene(menu, 1200, 700);

    }




    private static GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 0, 0, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        return gridPane;
    }

    private static Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(20));
        return label;
    }

    private static Label createTitleLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(25));
        return label;
    }

    private static void showMsg(String text) {
        Alert alert = new Alert(AlertType.WARNING);
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



/*
package frontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProfilePane extends BorderPane implements EventHandler<ActionEvent> {
    // photo
    ImageView ivPhoto;
    File photoFile;

    // general
    TextField tfUsername;
    TextField tfRealName;
    TextField tfEmail;

    // personal information
    TextField tfHeight;
    TextField tfWeight;
    TextField tfBMI;
    TextField tfTargetWeight;
    TextField tfHealthOverview;
    Button btnGenerate;

    // exercise
    TextField tfDuration;
    TextField tfDistance;
    ComboBox<String> cbTypeOfSports;

    // diet
    ComboBox<String> cbFood;
    ComboBox<String> cbDrink;
    ComboBox<String> cbMealType;
    TextField tfCalorieCount;

    // save button
    Button btnSave;

    public ProfilePane() {
        setPrefWidth(1000);
        setPrefHeight(600);

        VBox rootBox = new VBox(10);
        rootBox.setPadding(new Insets(10));

        rootBox.getChildren().add(createPhotoPart());

        VBox vBox1 = new VBox(10);
        vBox1.getChildren().add(createGeneralPart());
        vBox1.getChildren().add(createPersonalPart());
        VBox vBox2 = new VBox(10);
        vBox2.getChildren().add(createExercisePart());
        vBox2.getChildren().add(createDietPart());

        HBox hBox = new HBox(30, vBox1, vBox2);
        rootBox.getChildren().add(hBox);
        rootBox.getChildren().add(createSaveButtonPart());

        setCenter(rootBox);
    }

    public String getTitle() {
        return "Profile";
    }

    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Information Saved");
        alert.showAndWait();
    }

    private Node createDietPart() {
        cbFood = new ComboBox<String>();
        cbFood.setEditable(true);
        cbFood.getItems().addAll("Hamburger", "Salad", "Ice Cream");

        cbDrink = new ComboBox<String>();
        cbDrink.setEditable(true);
        cbDrink.getItems().addAll("Water", "Cola", "Sprite");

        cbMealType = new ComboBox<String>();

        cbMealType.getItems().addAll("Vegan", "Fruit", "Starch");

        tfCalorieCount = new TextField();

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Food"), 0, 0);
        gridPane.add(createLabel("Drink"), 0, 1);
        gridPane.add(createLabel("Meal Type"), 0, 2);
        gridPane.add(createLabel("Calorie Count"), 0, 3);

        gridPane.add(cbFood, 1, 0);
        gridPane.add(cbDrink, 1, 1);
        gridPane.add(cbMealType, 1, 2);
        gridPane.add(tfCalorieCount, 1, 3);

        //date picker for Diet
        LocalDate nowDate = LocalDate.now();
        Label DietDate = new Label("Date");
        DietDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(DietDate, 0, 0, 2, 1);
        DietDate.setAlignment(Pos.CENTER);
        DietDate.setTranslateX(-600);
        DietDate.setTranslateY(330);
        GridPane.setHalignment(DietDate, HPos.CENTER);
        GridPane.setMargin(DietDate, new Insets(20, 0, 20, 0));
        DatePicker dpDietDate = new DatePicker(nowDate.plusDays(7));
        dpDietDate.setPrefHeight(40);
        dpDietDate.setPrefWidth(300);
        dpDietDate.setTranslateX(-530);
        dpDietDate.setTranslateY(270);
        gridPaneProfile.add(dpDietDate, 1, 2);



        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(createTitleLabel("Diet"), hBox);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private Node createExercisePart() {
        LocalDate nowDate = LocalDate.now();
        tfDuration = new TextField();
        tfDistance = new TextField();
        cbTypeOfSports = new ComboBox<String>();

        cbTypeOfSports.getItems().addAll("Swimming", "Togging", "Yoga");

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Duration"), 0, 0);
        gridPane.add(createLabel("Distance"), 0, 1);
        gridPane.add(createLabel("Type of Sports"), 0, 2);
        gridPane.add(tfDuration, 1, 0);
        gridPane.add(tfDistance, 1, 1);
        gridPane.add(cbTypeOfSports, 1, 2);

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(createTitleLabel("Exercise"), hBox);
        vBox.setAlignment(Pos.CENTER);
        return vBox;

        //date picker for Exercise

        Label ExerciseDate = new Label("Exercise Date");
        ExerciseDate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gridPaneProfile.add(ExerciseDate, 0, 0, 2, 1);
        ExerciseDate.setAlignment(Pos.CENTER);
        ExerciseDate.setTranslateX(-600);
        ExerciseDate.setTranslateY(330);
        GridPane.setHalignment(ExerciseDate, HPos.CENTER);
        GridPane.setMargin(ExerciseDate, new Insets(20, 0, 20, 0));
        DatePicker dpExerciseDate = new DatePicker(nowDate.plusDays(7));
        dpExerciseDate.setPrefHeight(40);
        dpExerciseDate.setPrefWidth(300);
        dpExerciseDate.setTranslateX(-530);
        dpExerciseDate.setTranslateY(270);
        gridPaneProfile.add(dpExerciseDate, 1, 2);

    }

    private Node createPersonalPart() {
        tfHeight = new TextField();
        tfWeight = new TextField();
        tfBMI = new TextField();
        tfTargetWeight = new TextField();
        tfHealthOverview = new TextField();
        btnGenerate = new Button("Generate");

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
        });

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Height(m)"), 0, 0);
        gridPane.add(createLabel("Weight(kg)"), 0, 1);
        gridPane.add(createLabel("Current BMI"), 0, 2);
        gridPane.add(createLabel("Target Weight"), 0, 3);
        gridPane.add(createLabel("Health Overview"), 0, 4);

        gridPane.add(tfHeight, 1, 0);
        gridPane.add(tfWeight, 1, 1);
        gridPane.add(tfBMI, 1, 2);
        gridPane.add(tfTargetWeight, 1, 3);
        gridPane.add(tfHealthOverview, 1, 4);
        gridPane.add(btnGenerate, 3, 2);

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(createTitleLabel("Personal Information"), hBox);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox createSaveButtonPart() {
        btnSave = new Button("  Save  ");
        btnSave.setOnAction(this);

        VBox vBox = new VBox(btnSave);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox createPhotoPart() {
        ivPhoto = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("photo_default.jpg"));
        ivPhoto.fitWidthProperty().set(150);
        ivPhoto.fitHeightProperty().set(150);
        ivPhoto.setImage(image);
        VBox vBox = new VBox(ivPhoto);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        ivPhoto.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                JFileChooser chooser = new JFileChooser(new File("."));
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setFileFilter(
                        new FileNameExtensionFilter("image files", "jpg", "png", "bmp"));

                int status = chooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    if (selectedFile != null) {
                        try {
                            Image image = new Image(new FileInputStream(selectedFile));
                            ivPhoto.setImage(image);

                            photoFile = selectedFile;
                        } catch (FileNotFoundException e) {
                            showMsg("failed to read image");
                        }
                    }
                }
            }
        });
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox createGeneralPart() {
        tfUsername = new TextField();
        tfRealName = new TextField();
        tfEmail = new TextField();

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Username"), 0, 0);
        gridPane.add(createLabel("Real Name"), 0, 1);
        gridPane.add(createLabel("Email Address"), 0, 2);
        gridPane.add(tfUsername, 1, 0);
        gridPane.add(tfRealName, 1, 1);
        gridPane.add(tfEmail, 1, 2);

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(createTitleLabel("General"), hBox);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 0, 0, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        return gridPane;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(20));
        return label;
    }

    private Label createTitleLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(25));
        return label;
    }

    private void showMsg(String text) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
*/
