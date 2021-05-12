package sample;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class ViewHistory {

    private JFrame frame;
    private DatePicker inPicker;
    private DatePicker fmPicker;
    private DatePicker toPicker;
    private static final String pattern = "dd-MMM-yy";
    enum DateParameterType { FROM_DATE, TO_DATE };

    public static void main(String... args) {

        SwingUtilities.invokeLater(() -> {

            new ViewHistory().initAndShowGUI();
        });
    }

    /*
     * Builds and displays the JFrame with the JFXPanel.
     * This method is invoked on the Swing Event Dispatch Thread.
     */
    private void initAndShowGUI() {

        frame = new JFrame("View History");
        JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(new Point(400, 200));

        Platform.runLater(() -> {

            fxPanel.setScene(createScene());
        });

        frame.requestFocus();
    }

    private Scene createScene() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
//side menu

        // Title
        Label title1 = new Label("Enter a range of date to check");
        Label title2 = new Label(" the history of the selected date range.");
        VBox titleVb = new VBox();
        titleVb.setAlignment(Pos.CENTER);
        titleVb.getChildren().addAll(title1, title2);

       // Input date picker
        Label inPickLabel = new Label("Input date:");
        inPicker = new DatePicker();
        inPicker.setPromptText(pattern);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        StringConverter<LocalDate> converter =
                new LocalDateStringConverter(formatter, null);
        inPicker.setConverter(converter);
        grid.add(inPickLabel, 0, 0);
        grid.add(inPicker, 1, 0);

        // From and to date pickers

        Label pickLabel1 = new Label("From date:");
        fmPicker = new DatePicker(LocalDate.now());
        fmPicker.setEditable(false);
        grid.add(pickLabel1, 0, 1);
        grid.add(fmPicker, 1, 1);

        Label pickLabel2 = new Label("To date:");
        toPicker = new DatePicker(LocalDate.now());
        toPicker.setEditable(false);
        grid.add(pickLabel2, 0, 2);
        grid.add(toPicker, 1, 2);

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
        HBox btnHb = new HBox();
        btnHb.setAlignment(Pos.CENTER);
        btnHb.getChildren().add(btn);

        // Vbox and scene
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(15, 15, 15, 15));
        vbox.getChildren().addAll(titleVb, grid, btnHb);

        return new Scene(vbox);
    }

    /*
     * Returns a Callback with DateCell as return value for the DatePicker. The
     * DateCell determines that the DatePicker date values are disabled. This applies
     * for the three date picker widgets.
     * This allows the following validation:
     * * The selected date cannot be greater than today.
     * * From-date cannot be greater than to-date.
     */
    private Callback<DatePicker, DateCell> getCustomDateCellFactory(DateParameterType dateParamType) {

        Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {

                    @Override
                    public DateCell call(DatePicker datePicker) {

                        return new DateCell() {

                            @Override
                            public void updateItem(LocalDate select, boolean empty) {

                                super.updateItem(select, empty);

                                // Date cannot be after today.
                                // This applies for all the date pickers.
                                if (select.isAfter(LocalDate.now())) {

                                    setDisable(true);
                                    return;
                                }

                                // From-date cannot be greater than to-date.
                                if ((dateParamType == DateParameterType.FROM_DATE) &&
                                        (select.isAfter(toPicker.getValue()))) {

                                    setDisable(true);
                                }

                                // To-date cannot be less than from-date,
                                // and cannot be greater than today.
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

    // Validate button's action listener routine.
    private void buttonActionListenerRoutine() {

        if (inPicker.getValue() == null) {

            showAlert(AlertType.ERROR,
                    "The input date must be in dd-MMM-yy format (for example, 02-Mar-16).");
            inPicker.requestFocus();
            return;
        }

        if (validDate(fmPicker.getValue(), toPicker.getValue(), inPicker.getValue())) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Valid date");
            alert.setHeaderText("Success!");
            alert.setContentText("The input date is within the selected date range.");
            alert.showAndWait();
        }
        else {
            showAlert(AlertType.ERROR,
                    "The input date is not within the selected date range!");
        }
    }

    private void showAlert(AlertType alertType, String content) {

        Alert alert = new Alert(alertType);
        alert.setTitle("Invalid date");
        alert.setContentText(content);
        alert.showAndWait();
    }

    /*
     * Returns true if the given input date is between the start and
     * end dates (including).
     */
    private boolean validDate(LocalDate start, LocalDate end, LocalDate input) {

        if ((input.isEqual(start)) || (input.isEqual(end))) {

            return true;
        }
        else if ((input.isAfter(start)) && (input.isBefore(end))) {

            return true;
        }
        else {
            return false;
        }
    }
}