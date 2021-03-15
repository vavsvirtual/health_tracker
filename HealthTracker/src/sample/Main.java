package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application{
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Scene scene = logInScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene logInScene(){
        GridPane gridPaneLogin = new GridPane();

        gridPaneLogin.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPaneLogin.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPaneLogin.setHgap(10);

        // Set the vertical gap between rows
        gridPaneLogin.setVgap(10);

        Label headerLabel = new Label("Login Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPaneLogin.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
//        headerLabel.setTranslateX(10);
//        headerLabel.setTranslateY(90);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        Label userLabel = new Label("Username:");
        gridPaneLogin.add(userLabel, 0,2);

        TextField userField = new TextField();
        userField.setPrefHeight(40);
        userField.setPrefWidth(300);
        gridPaneLogin.add(userField, 1,2);

        Label passwordLabel = new Label("Password : ");
        gridPaneLogin.add(passwordLabel, 0, 4);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setPrefWidth(300);
        gridPaneLogin.add(passwordField, 1, 4);


        Button createLoginButton = new Button("Login");
        createLoginButton.setPrefHeight(40);
        createLoginButton.setDefaultButton(true);
        createLoginButton.setPrefWidth(100);
        createLoginButton.setTranslateY(200);
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
            }
        });

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setPrefHeight(40);
        createAccountButton.setDefaultButton(true);
        createAccountButton.setPrefWidth(100);
        createAccountButton.setTranslateX(310);
        createAccountButton.setTranslateY(200);
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

        // Set a padding of 20px on each side
        gridPaneRegister.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPaneRegister.setHgap(10);

        // Set the vertical gap between rows
        gridPaneRegister.setVgap(10);

        Label headerLabel = new Label("User Registration");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPaneRegister.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        nameLabel.setTranslateX(-80);
        nameLabel.setTranslateY(50);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        nameField.setPrefWidth(300);
        nameField.setTranslateY(50);

        Label userLabel = new Label("Username :");
        userLabel.setTranslateX(-80);
        userLabel.setTranslateY(100);

        TextField userField = new TextField();
        userField.setPrefHeight(40);
        userField.setPrefWidth(300);
        userField.setTranslateY(100);

        // Add Email Label
        Label emailLabel = new Label("Email : ");
        emailLabel.setTranslateX(-80);
        emailLabel.setTranslateY(150);

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        emailField.setPrefWidth(300);
        emailField.setTranslateY(150);

        Label passwordLabel = new Label("Password : ");
        passwordLabel.setTranslateX(-80);
        passwordLabel.setTranslateY(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setPrefWidth(300);
        passwordField.setTranslateY(200);

//        // Confirm Password Label
//        Label confirmPasswordLabel = new Label("Confirm Password : ");
//        confirmPasswordLabel.setTranslateX(-80);
//        confirmPasswordLabel.setTranslateY(250);
//
//        // Confirm Password Field
//        PasswordField confirmPasswordField = new PasswordField();
//        confirmPasswordField.setPrefHeight(40);
//        confirmPasswordField.setPrefWidth(300);
//        confirmPasswordField.setTranslateY(250);


        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        submitButton.setTranslateY(300);
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
//                if(confirmPasswordField.getText().isEmpty()) {
//                    showAlert(Alert.AlertType.ERROR, gridPaneRegister.getScene().getWindow(), "Form Error!", "Check Password");
//                    return;
//                }

                showAlert(Alert.AlertType.CONFIRMATION, gridPaneRegister.getScene().getWindow(), "Registration Successful!", "Welcome " + userField.getText());

                //System.out.println("Account for user " + userField.getText() + " was created successfully");
            }
        });
        gridPaneRegister.getChildren().addAll(
                nameLabel, nameField,
                userLabel, userField,
                emailLabel, emailField,
                passwordLabel,passwordField,
                //confirmPasswordLabel, confirmPasswordField,
                submitButton);
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