package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.image.Image;

import static javafx.scene.Node.*;


public class Main extends Application{
    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Health Tracker");
        stage = primaryStage;
        Scene scene = logInScene();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static Scene logInScene(){

        HBox root = new HBox();

        root.setStyle("-fx-background-color: white;");

        String imageUrl = "https://images.unsplash.com/photo-1514995428455-447d4443fa7f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80";

        Image image = new Image(imageUrl,550,700,false,true);

        ImageView imageView = new ImageView(image);

        root.getChildren().add(imageView);


        GridPane gridPaneLogin = new GridPane();

        gridPaneLogin.setTranslateX(-310);
        gridPaneLogin.setTranslateY(200);

        gridPaneLogin.setPadding(new Insets(40, 40, 40, 40));

        gridPaneLogin.setHgap(10);
        gridPaneLogin.setVgap(10);


        Label headerLabel = new Label("User Login");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPaneLogin.add(headerLabel, 0,0,2,1);
        headerLabel.setTranslateX(200);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        Label userLabel = new Label("Username :");
        userLabel.setTranslateX(-80);
        gridPaneLogin.add(userLabel, 1,2);

        TextField userField = new TextField();
        userField.setPromptText("Username");
        userField.setFocusTraversable(false);
        userField.setPrefHeight(40);
        userField.setPrefWidth(400);
        gridPaneLogin.add(userField, 1,2);


        Label passwordLabel = new Label("Password : ");
        passwordLabel.setTranslateX(-80);
        gridPaneLogin.add(passwordLabel, 1, 4);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setFocusTraversable(false);
        passwordField.setPrefHeight(40);
        passwordField.setPrefWidth(400);
        gridPaneLogin.add(passwordField, 1, 4);


        Button createLoginButton = new Button("Login");
        createLoginButton.setPrefHeight(40);
        createLoginButton.setDefaultButton(true);
        createLoginButton.setPrefWidth(400);
        createLoginButton.setTranslateX(410);
        createLoginButton.setTranslateY(210);
        createLoginButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(createLoginButton, new Insets(20, 0,20,0));


        createLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(userField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPaneLogin.getScene().getWindow(), "Form Error!", "Please enter your username");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPaneLogin.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }

                System.out.println("User " + userField.getText() + " has logged in");

//                try {
//                    stage.setScene(ProfilePane.profilePane());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }

        });


        Button createAccountButton = new Button("Create Account");
        createAccountButton.setPrefHeight(40);
        createAccountButton.setDefaultButton(true);
        createAccountButton.setPrefWidth(400);
        createAccountButton.setTranslateX(410);
        createAccountButton.setTranslateY(260);
        createAccountButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(createAccountButton, new Insets(20, 0,20,0));

        createAccountButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                stage.setScene(CreateAccountScene());
            }
        });

        BorderPane pane = new BorderPane();
        pane.setLeft(root);
        pane.setRight(gridPaneLogin);

        gridPaneLogin.getChildren().addAll(createLoginButton, createAccountButton);
        return new Scene(pane, 1200, 700);

    }


    private static Scene CreateAccountScene() {

        HBox root = new HBox();

        root.setStyle("-fx-background-color: white;");

        String imageUrl = "https://images.unsplash.com/photo-1511690656952-34342bb7c2f2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80";

        Image image = new Image(imageUrl,550,700,false,true);

        ImageView imageView = new ImageView(image);

        root.getChildren().add(imageView);


        GridPane gridPaneRegister = new GridPane();

        gridPaneRegister.setTranslateX(110);
        gridPaneRegister.setTranslateY(200);

        gridPaneRegister.setPadding(new Insets(40, 40, 40, 40));

        gridPaneRegister.setHgap(10);
        gridPaneRegister.setVgap(10);


        Label headerLabel = new Label("User Registration");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPaneRegister.add(headerLabel, 0,0,2,1);
        headerLabel.setTranslateX(100);
        headerLabel.setTranslateY(-30);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        Label nameLabel = new Label("Full Name : ");
        nameLabel.setTranslateX(-80);
        nameLabel.setTranslateY(50);

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        nameField.setFocusTraversable(false);
        nameField.setPrefWidth(400);
        nameField.setPrefHeight(40);
        nameField.setTranslateY(50);


        Label userLabel = new Label("Username :");
        userLabel.setTranslateX(-80);
        userLabel.setTranslateY(100);

        TextField userField = new TextField();
        userField.setPromptText("Username");
        userField.setFocusTraversable(false);
        userField.setPrefWidth(400);
        userField.setPrefHeight(40);
        userField.setTranslateY(100);


        Label emailLabel = new Label("Email : ");
        emailLabel.setTranslateX(-80);
        emailLabel.setTranslateY(150);

        TextField emailField = new TextField();
        emailField.setPromptText("example@hotmail.co.uk");
        emailField.setFocusTraversable(false);
        emailField.setPrefWidth(400);
        emailField.setPrefHeight(40);
        emailField.setTranslateY(150);


        Label passwordLabel = new Label("Password : ");
        passwordLabel.setTranslateX(-80);
        passwordLabel.setTranslateY(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setFocusTraversable(false);
        passwordField.setPrefWidth(400);
        passwordField.setPrefHeight(40);
        passwordField.setTranslateY(200);


        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(400);
        submitButton.setTranslateX(-40);
        submitButton.setTranslateY(270);
        submitButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold; ");
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,50));

        submitButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPaneRegister.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }
                if(userField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPaneRegister.getScene().getWindow(), "Form Error!", "Please enter your username");
                    return;
                }
                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPaneRegister.getScene().getWindow(), "Form Error!", "Please enter your email id");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPaneRegister.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, gridPaneRegister.getScene().getWindow(), "Registration Successful!", "Welcome " + userField.getText());

                System.out.println("Account for user " + userField.getText() + " was created successfully");
            }
        });

        Button backButton = new Button("Back");
        backButton.setPrefHeight(40);
        backButton.setDefaultButton(true);
        backButton.setPrefWidth(400);
        backButton.setTranslateX(-40);
        backButton.setTranslateY(320);
        backButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold; ");
        GridPane.setHalignment(backButton, HPos.CENTER);
        GridPane.setMargin(backButton, new Insets(20, 0,20,50));

        backButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                stage.setScene(logInScene());
            }
        });

        BorderPane pane = new BorderPane();
        pane.setLeft(gridPaneRegister);
        pane.setRight(root);

        gridPaneRegister.getChildren().addAll(nameLabel, nameField, userLabel, userField, emailLabel, emailField,
                passwordLabel,passwordField, submitButton, backButton);
        return new Scene(pane,1200, 700);

    }



    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}