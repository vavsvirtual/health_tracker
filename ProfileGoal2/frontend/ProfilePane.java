package frontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
        setPrefWidth(600);
        setPrefHeight(600);

        VBox rootBox = new VBox(10);
        rootBox.setPadding(new Insets(10));

        rootBox.getChildren().add(createPhotoPart());
        rootBox.getChildren().add(createGeneralPart());
        rootBox.getChildren().add(createPersonalPart());
        rootBox.getChildren().add(createExercisePart());
        rootBox.getChildren().add(createDietPart());

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
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(createTitleLabel("Diet"), hBox);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private Node createExercisePart() {
        tfDuration = new TextField();
        tfDistance = new TextField();
        cbTypeOfSports = new ComboBox<String>();
        cbTypeOfSports.setEditable(true);
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
