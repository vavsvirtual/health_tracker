package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Main extends Application{
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Health Tracker");
        stage = primaryStage;
        Scene scene = logInScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene logInScene(){

        GridPane gridPaneLogin = new GridPane();

        gridPaneLogin.setAlignment(Pos.CENTER);

        gridPaneLogin.setPadding(new Insets(40, 40, 40, 40));

        gridPaneLogin.setHgap(10);

        gridPaneLogin.setVgap(10);

        Label headerLabel = new Label("User Login");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPaneLogin.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        Label userLabel = new Label("Username :");
        gridPaneLogin.add(userLabel, 0,2);

        TextField userField = new TextField();
        userField.setPromptText("Username");
        userField.setFocusTraversable(false);
        userField.setPrefHeight(40);
        userField.setPrefWidth(300);
        gridPaneLogin.add(userField, 1,2);

        Label passwordLabel = new Label("Password : ");
        gridPaneLogin.add(passwordLabel, 0, 4);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setFocusTraversable(false);
        passwordField.setPrefHeight(40);
        passwordField.setPrefWidth(300);
        gridPaneLogin.add(passwordField, 1, 4);


        Button createLoginButton = new Button("Login");
        createLoginButton.setPrefHeight(40);
        createLoginButton.setDefaultButton(true);
        createLoginButton.setPrefWidth(200);
        createLoginButton.setTranslateY(200);
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

                showAlert(Alert.AlertType.CONFIRMATION, gridPaneLogin.getScene().getWindow(), "Login Successful!", "Welcome " + userField.getText());

                System.out.println("User " + userField.getText() + " has logged in");

            }
        });

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setPrefHeight(40);
        createAccountButton.setDefaultButton(true);
        createAccountButton.setPrefWidth(200);
        createAccountButton.setTranslateX(310);
        createAccountButton.setTranslateY(200);
        createAccountButton.setStyle("-fx-background-color: #3D405B; -fx-text-fill: #F4F1DE; -fx-font-weight: bold;");
        GridPane.setMargin(createAccountButton, new Insets(20, 0,20,0));

        createAccountButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                stage.setScene(CreateAccountScene());
            }
        });
        gridPaneLogin.getChildren().addAll(createLoginButton, createAccountButton);
        return new Scene(gridPaneLogin, 1200, 700);

    }


    private Scene CreateAccountScene() {

        GridPane gridPaneRegister = new GridPane();

        gridPaneRegister.setAlignment(Pos.CENTER);

        gridPaneRegister.setPadding(new Insets(40, 40, 40, 40));

        gridPaneRegister.setHgap(10);

        gridPaneRegister.setVgap(10);


        Label headerLabel = new Label("User Registration");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPaneRegister.add(headerLabel, 0,0,2,1);
        headerLabel.setTranslateX(50);
        headerLabel.setTranslateY(-150);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Label nameLabel = new Label("Full Name : ");
        nameLabel.setTranslateX(-80);
        nameLabel.setTranslateY(-50);

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        nameField.setFocusTraversable(false);
        nameField.setPrefHeight(40);
        nameField.setTranslateY(-50);


        Label userLabel = new Label("Username :");
        userLabel.setTranslateX(-80);

        TextField userField = new TextField();
        userField.setPromptText("Username");
        userField.setFocusTraversable(false);
        userField.setPrefHeight(40);


        Label emailLabel = new Label("Email : ");
        emailLabel.setTranslateX(-80);
        emailLabel.setTranslateY(50);


        TextField emailField = new TextField();
        emailField.setPromptText("example@hotmail.co.uk");
        emailField.setFocusTraversable(false);
        emailField.setPrefHeight(40);
        emailField.setTranslateY(50);


        Label passwordLabel = new Label("Password : ");
        passwordLabel.setTranslateX(-80);
        passwordLabel.setTranslateY(100);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setFocusTraversable(false);
        passwordField.setPrefHeight(40);
        passwordField.setTranslateY(100);

        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(300);
        submitButton.setTranslateX(-50);
        submitButton.setTranslateY(170);
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
                    showAlert(Alert.AlertType.ERROR, gridPaneRegister.getScene().getWindow(), "Form Error!", "Please enter your email address");
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
        gridPaneRegister.getChildren().addAll(nameLabel, nameField, userLabel, userField, emailLabel, emailField,
                passwordLabel,passwordField, submitButton);
        return new Scene(gridPaneRegister,1200, 700);

    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
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