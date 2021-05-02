package Frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;


public class GroupsPane extends BorderPane implements EventHandler<ActionEvent> {

    Label headerLabel;

    // Existing Group Button
    Button btnGroupOne;
    Button btnGroupTwo;
    Button btnGroupThree;
    Button btnGroupFour;

    // Create Group
    TextField nameField;
    ComboBox<String> cbGoal;
    TextField friendField;
    Button btnAddFriend;
    Button btnCreateGroup;

    // Join Group
    ComboBox<String> cbJoin;
    Button btnJoinGroup;


    public GroupsPane() {

        VBox rootBox = new VBox(10);
        rootBox.setPadding(new Insets(10));

        rootBox.getChildren().add(title());
        rootBox.getChildren().addAll(existingGroup());
        rootBox.getChildren().addAll(createGroup());
        rootBox.getChildren().addAll(joinGroup());

        ScrollPane scrollPane = new ScrollPane(rootBox);
        setCenter(scrollPane);

    }

    public String getTitle() {
        return "Groups";
    }


    public void handle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setContentText("Information Saved");
        alert.showAndWait();
    }

    public Node title(){
        headerLabel = new Label("Groups");

        GridPane gridPane = createGridPane();
        gridPane.add(headerLabel, 1, 0);

        VBox vBox = new VBox(headerLabel);
        vBox.setPadding(new Insets(20, 0, 0, 400));

        return new VBox(gridPane);
    }

    public Node existingGroup(){
        btnGroupOne = new Button("Group 1");
        btnGroupTwo = new Button("Group 2");
        btnGroupThree = new Button("Group 3");
        btnGroupFour = new Button("Group 4");

        GridPane gridPane = createGridPane();
        gridPane.add(btnGroupOne, 1, 6);
        gridPane.add(btnGroupTwo, 2, 6);
        gridPane.add(btnGroupThree, 3, 6);
        gridPane.add(btnGroupFour, 4, 6);

        btnGroupOne.setPrefSize(100, 60);
        btnGroupTwo.setPrefSize(100, 60);
        btnGroupThree.setPrefSize(100, 60);
        btnGroupFour.setPrefSize(100, 60);

        btnGroupOne.setStyle("-fx-background-color: #EAB69F; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        btnGroupTwo.setStyle("-fx-background-color: #E07A5F; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        btnGroupThree.setStyle("-fx-background-color: #81B29A; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        btnGroupFour.setStyle("-fx-background-color: #F2CC8F; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");

        return new VBox(gridPane);
    }

    public Node createGroup(){

        nameField = new TextField();

        cbGoal = new ComboBox<String>();
        cbGoal.setEditable(true);
        cbGoal.getItems().addAll("Exercise", "Calories", "Custom");

        friendField = new TextField();

        btnAddFriend = new Button("Add Friend");

        btnAddFriend.setPrefSize(100, 40);
        btnAddFriend.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        //btnAddFriend.setPadding(new Insets(5, 0, 0, 40));

        btnCreateGroup = new Button("Create Group");

        btnCreateGroup.setPrefSize(300, 40);
        btnCreateGroup.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");

        btnCreateGroup.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error!", "Please enter a unique group name");
                    return;
                }
                if(cbGoal.getSelectionModel().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error!", "Please enter your username");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, "Created Group Successful!", "Group " + nameField.getText());

                System.out.println("Created group " + nameField.getText() + " successfully");
            }
        });


        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Group Name"), 0, 0);
        gridPane.add(createLabel("Group Goal"), 0, 1);
        gridPane.add(createLabel("Add Friend"), 0, 2);

        gridPane.add(nameField, 1, 0);
        gridPane.add(cbGoal, 1, 1);
        gridPane.add(friendField, 1, 2);
        gridPane.add(btnAddFriend, 1, 3);
        gridPane.add(btnCreateGroup, 1, 4);

        return new VBox(createTitleLabel("Create Group"), gridPane);

    }

    private Node joinGroup(){

        cbJoin = new ComboBox<String>();
        cbJoin.setEditable(true);
        cbJoin.getItems().addAll("Group 10", "Group 11", "Group 12", "Group 13", "Group 14");

        btnJoinGroup = new Button("Join Group");

        btnJoinGroup.setPrefSize(300, 40);
        btnJoinGroup.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");

        btnJoinGroup.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){

                if(cbJoin.getSelectionModel().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error!", "Please select a group to join");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, "Created Group Successful!", "Group " + nameField.getText());

                System.out.println("Joined group " + cbJoin.getSelectionModel() + " successfully");
            }
        });

        GridPane gridPane = createGridPane();
        gridPane.add(createLabel("Group Name"), 0, 0);


        gridPane.add(cbJoin, 1, 0);
        gridPane.add(btnJoinGroup, 1, 1);

        return new VBox(createTitleLabel("Join Group"), gridPane);
    }


    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 0, 0, 400));
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
        label.setPadding(new Insets(5, 0, 0, 400));
        return label;
    }

    private void showMsg(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
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


}