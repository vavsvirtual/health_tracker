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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Point;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


public class WeeklySummary extends Application {

    public static Stage stage;
    private JFrame frame;
    private static DatePicker inPicker;
    private static DatePicker fmPicker;
    private static DatePicker toPicker;

    private static final String pattern = "dd-MMM-yy";

    enum DateParameterType {FROM_DATE, TO_DATE}

    ;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Groups");
        stage = primaryStage;
        Scene scene = summaryScene(stage);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static Scene summaryScene(Stage primaryStage) {
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

        JFXButton summary = new JFXButton("History");
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
        goals.setFocusTraversable(false);
        groups.setFocusTraversable(false);
        logout.setFocusTraversable(false);

        vBox.getChildren().addAll(profile, goals, summary, groups, logout);
        vBox.setPadding(new Insets(0, 0, 0, 0));


        GridPane gridPaneSummary = new GridPane();

        Label headerLabel = new Label("History");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gridPaneSummary.add(headerLabel, 0, 0, 2, 1);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setTranslateX(-300);
        headerLabel.setTranslateY(0);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Title
        Label title1 = new Label("Enter a range of date to check");
        Label title2 = new Label(" the history of the selected date range.");
        VBox titleVb = new VBox();
        titleVb.setAlignment(Pos.CENTER);
        titleVb.getChildren().addAll(title1, title2);

        // Input date picker
        Label inPickLabel = new Label("Input date:");
        DatePicker inPicker = new DatePicker();
        inPicker.setPromptText(pattern);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        StringConverter<LocalDate> converter =
                new LocalDateStringConverter(formatter, null);
        inPicker.setConverter(converter);
        gridPaneSummary.add(inPickLabel, 0, 0);
        gridPaneSummary.add(inPicker, 1, 0);

        // From and to date pickers

        Label pickLabel1 = new Label("From date:");
        DatePicker fmPicker = new DatePicker(LocalDate.now());
        fmPicker.setEditable(false);
        gridPaneSummary.add(pickLabel1, 0, 1);
        gridPaneSummary.add(fmPicker, 1, 1);

        Label pickLabel2 = new Label("To date:");
        DatePicker toPicker = new DatePicker(LocalDate.now());
        toPicker.setEditable(false);
        gridPaneSummary.add(pickLabel2, 0, 2);
        gridPaneSummary.add(toPicker, 1, 2);

// Cell factory for picker date validation
        inPicker.setDayCellFactory(getCustomDateCellFactory(null));
        fmPicker.setDayCellFactory(getCustomDateCellFactory(DateParameterType.FROM_DATE));
        toPicker.setDayCellFactory(getCustomDateCellFactory(DateParameterType.TO_DATE));

        // Button
        Button btn = new Button("view History");
        btn.setTooltip(new Tooltip("Find if the input date is within the from and to dates."));
        btn.setOnAction(event -> {

            buttonActionListenerRoutine();

        });

        BorderPane menu = new BorderPane();
        menu.setLeft(vBox);
        menu.setRight(gridPaneSummary);

        gridPaneSummary.getChildren().addAll(btn);

        return new Scene(menu, 1200, 700);

    }

    private void initAndShowGUI() {

        frame = new JFrame("View History");
        JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(new Point(400, 200));

        Platform.runLater(() -> {
            fxPanel.setScene(summaryScene(stage));
        });

        frame.requestFocus();
    }

    private static Callback<DatePicker, DateCell> getCustomDateCellFactory(DateParameterType dateParamType) {

        Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {

                    @Override
                    public DateCell call(DatePicker datePicker) {

                        return new DateCell() {

                            @Override
                            public void updateItem(LocalDate select, boolean empty) {

                                super.updateItem(select, empty);


                                if (select.isAfter(LocalDate.now())) {

                                    setDisable(true);
                                    return;
                                }


                                if ((dateParamType == DateParameterType.FROM_DATE) &&
                                        (select.isAfter(toPicker.getValue()))) {

                                    setDisable(true);
                                }


                                if (dateParamType == DateParameterType.TO_DATE) {

                                    if ((select.isBefore(fmPicker.getValue())) ||
                                            (select.isAfter(LocalDate.now()))) {

                                        setDisable(true);
                                    }
                                }
                            }
                        };
                    }
                };

        return dayCellFactory;
    }

    private static void buttonActionListenerRoutine() {

        if (inPicker.getValue() == null) {

            showAlert(Alert.AlertType.ERROR,
                    "The input date must be in dd-MMM-yy format (for example, 02-Mar-16).");
            inPicker.requestFocus();
            return;
        }

        if (validDate(fmPicker.getValue(), toPicker.getValue(), inPicker.getValue())) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Valid date");
            alert.setHeaderText("Success!");
            alert.setContentText("The input date is within the selected date range.");
            alert.showAndWait();
        } else {
            showAlert(Alert.AlertType.ERROR,
                    "The input date is not within the selected date range!");
        }
    }

    private static void showAlert(Alert.AlertType alertType, String content) {

        Alert alert = new Alert(alertType);
        alert.setTitle("Invalid date");
        alert.setContentText(content);
        alert.showAndWait();
    }


    private static boolean validDate(LocalDate start, LocalDate end, LocalDate input) {

        if ((input.isEqual(start)) || (input.isEqual(end))) {

            return true;
        } else if ((input.isAfter(start)) && (input.isBefore(end))) {

            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        launch(args);
    }

}
