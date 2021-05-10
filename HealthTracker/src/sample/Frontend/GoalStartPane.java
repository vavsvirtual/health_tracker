package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.Stage;

public class GoalStartPane extends BorderPane implements EventHandler<ActionEvent> {
    double targetWeight;
    double duration;
    LocalDate startDate;
    LocalDate deadlineDate;
    String exercise;
    String username;
    File photoFile;
    private ImageView ivPhoto;

    int secondsLeft;
    Label labelTimeLeft;

    Timer timer;

    Stage stage;

    public GoalStartPane(Stage stage) {
        this.stage = stage;

        setPrefWidth(400);
        setPrefHeight(500);
    }

    private class TimerJob extends TimerTask {
        @Override
        public void run() {
            secondsLeft--;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelTimeLeft.setText(getTimeLeft());
                }
            });

            if (secondsLeft == 0) {
                timer.cancel();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timeUp();
                    }
                });

            }
        }
    }

    private void startTimer() {
        secondsLeft = (int) (duration * 60);

        timer = new Timer();
        timer.schedule(new TimerJob(), 0, 1000);
    }

    public void timeUp() {
        ButtonType buttonType = new ButtonType("Set Next Goal");
        Alert alert = new Alert(AlertType.CONFIRMATION, "Time up!\nMeet Goal?", ButtonType.YES,
                ButtonType.NO, buttonType);
        alert.setWidth(500);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                showMsg("Thanks for using this!");
                stage.close();
            }

            if (response == ButtonType.NO) {
                startTimer();
            }

            if (response == buttonType) {
                stage.close();
            }
        });
    }

    public void createLayout() {
        VBox rootBox = new VBox(10);
        rootBox.setPadding(new Insets(10));

        rootBox.getChildren().add(createPhotoPart());
        rootBox.getChildren().add(createMainPart());

        setCenter(rootBox);

        startTimer();
    }

    public String getTitle() {
        return "Goal Start";
    }

    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Information Saved");
        alert.showAndWait();
    }

    private Node createMainPart() {
        secondsLeft = (int) (duration * 60);

        labelTimeLeft = createLabel(getTimeLeft());

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Starting Date"), 0, 0);
        gridPane.add(createLabel("Deadline"), 0, 1);
        gridPane.add(createLabel("Target Weight"), 0, 2);
        gridPane.add(createLabel("Exercise"), 0, 3);
        gridPane.add(createLabel("Time Left"), 0, 4);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        gridPane.add(createLabel(startDate.format(formatter)), 1, 0);
        gridPane.add(createLabel(deadlineDate.format(formatter)), 1, 1);
        gridPane.add(createLabel(String.format("%.2f kg", targetWeight)), 1, 2);
        gridPane.add(createLabel(exercise), 1, 3);
        gridPane.add(labelTimeLeft, 1, 4);

        VBox vBox = new VBox(gridPane);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private String getTimeLeft() {
        return String.format("%02d:%02d", secondsLeft / 60, secondsLeft % 60);
    }

    private VBox createPhotoPart() {
        ivPhoto = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("photo_default.jpg"));
        ivPhoto.fitWidthProperty().set(150);
        ivPhoto.fitHeightProperty().set(150);
        ivPhoto.setImage(image);
        VBox vBox = new VBox(ivPhoto);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        if (photoFile != null) {
            try {
                image = new Image(new FileInputStream(photoFile));
                ivPhoto.setImage(image);
            } catch (FileNotFoundException e) {
                System.out.println(e.toString());
            }
        }
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

    private void showMsg(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(text);
        alert.showAndWait();
    }
}