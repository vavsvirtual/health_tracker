package frontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;

public class GoalSettingPane extends BorderPane implements EventHandler<ActionEvent> {
    // photo
    ImageView ivPhoto;
    File photoFile;

    // general
    TextField tfUsername;
    TextField tfEmail;

    // current goal
    DatePicker dpStartingDate;
    DatePicker dpDeadLine;
    TextField tfTargetWeight;
    ComboBox<String> cbExercise;
    TextField tfDuration;

    // save button
    Button btnSave;

    public GoalSettingPane() {
        setPrefWidth(600);
        setPrefHeight(600);

        VBox rootBox = new VBox(10);
        rootBox.setPadding(new Insets(10));

        rootBox.getChildren().add(createPhotoPart());
        rootBox.getChildren().add(createGeneralPart());
        rootBox.getChildren().add(createGoalPart());

        rootBox.getChildren().add(createSaveButtonPart());
        setCenter(rootBox);
    }

    public String getTitle() {
        return "Goal Setting";
    }

    @Override
    public void handle(ActionEvent event) {
        double targetWeight;
        double duration;

        try {
            targetWeight = Double.parseDouble(tfTargetWeight.getText());
        } catch (NumberFormatException e) {
            showMsg("Invalid Target Weight");
            return;
        }

        try {
            duration = Double.parseDouble(tfDuration.getText());
        } catch (NumberFormatException e) {
            showMsg("Invalid Duration");
            return;
        }

        LocalDate startDate = dpStartingDate.getValue();
        LocalDate deadlineDate = dpDeadLine.getValue();
        String exercise = cbExercise.getValue();
        String username = tfUsername.getText();

        Stage stage = new Stage();
        GoalStartPane root = new GoalStartPane(stage);
        root.targetWeight = targetWeight;
        root.duration = duration;
        root.startDate = startDate;
        root.deadlineDate = deadlineDate;
        root.exercise = exercise;
        root.username = username;
        root.photoFile = photoFile;

        root.createLayout();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle(root.getTitle());
        stage.showAndWait();
    }

    private Node createGoalPart() {
        LocalDate nowDate = LocalDate.now();

        dpStartingDate = new DatePicker(nowDate);
        dpDeadLine = new DatePicker(nowDate.plusDays(7));

        tfTargetWeight = new TextField();

        cbExercise = new ComboBox<String>();
        cbExercise.setEditable(true);
        cbExercise.getItems().addAll("Swimming", "Jogging");

        tfDuration = new TextField();

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Starting Date"), 0, 0);
        gridPane.add(createLabel("Deadline"), 0, 1);
        gridPane.add(createLabel("Target Weight"), 0, 2);
        gridPane.add(createLabel("Exercise"), 0, 3);
        gridPane.add(createLabel("Duration"), 0, 4);

        gridPane.add(dpStartingDate, 1, 0);
        gridPane.add(dpDeadLine, 1, 1);
        gridPane.add(tfTargetWeight, 1, 2);
        gridPane.add(cbExercise, 1, 3);
        gridPane.add(tfDuration, 1, 4);

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(createTitleLabel("Diet"), hBox);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox createSaveButtonPart() {
        btnSave = new Button("  Set the Goal  ");
        btnSave.setOnAction(this);

        VBox vBox = new VBox(btnSave);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));
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
        tfEmail = new TextField();

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Username"), 0, 0);
        gridPane.add(createLabel("Email Address"), 0, 1);
        gridPane.add(tfUsername, 1, 0);
        gridPane.add(tfEmail, 1, 1);

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(createTitleLabel("General"), hBox);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 0, 0, 5));
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
