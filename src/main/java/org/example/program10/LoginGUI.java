package org.example.program10;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class represents the Login GUI for the application.
 * It contains fields for the stage, gridPane, usernameField, passwordField, loginButton, and quitButton.
 * It also contains methods to add login details, add login button, perform login function, display login alert,
 * add quit button, and perform quit function.
 */
public class LoginGUI {
    private final Stage stage;
    private final GridPane gridPane;

    private TextField usernameField;
    private PasswordField passwordField;

    private Button loginButton;
    private Button quitButton;

    /**
     * Constructor for the LoginGUI class.
     * Initializes the stage, gridPane, usernameField, passwordField, loginButton, and quitButton.
     * Also sets up the scene and shows the stage.
     */
    public LoginGUI() {
        stage = new Stage();
        stage.setTitle("Login");

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        addLoginDetails();
        addLoginButton();
        addQuitButton();

        VBox vBox = new VBox(gridPane);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Adds login details to the grid pane.
     */
    private void addLoginDetails() {
        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 0);

        usernameField = new TextField();
        gridPane.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 1);

        passwordField = new PasswordField();
        // Allow the user to press Enter to log in
        passwordField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                loginFunction();
            }
        });
        gridPane.add(passwordField, 1, 1);
    }

    /**
     * Adds a login button to the grid pane.
     */
    private void addLoginButton() {
        loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            // Perform the login function
            loginFunction();
        });
        gridPane.add(loginButton, 0, 2);
    }

    /**
     * Performs the login function.
     * Checks if the entered username and password are correct.
     * If they are correct, it closes the stage and opens the SelectionGUI.
     * If they are not correct, it displays a login alert.
     */
    private void loginFunction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if the username and password are correct
        // Temporarily hardcoding the username and password
        if ("admin".equals(username) && "admin".equals(password)) {
            stage.close();
            new SelectionGUI();
        } else {
            // Display an alert if the username or password is incorrect
            loginAlert();
        }
    }

    /**
     * Displays a login alert.
     * The alert is displayed when the entered username or password are incorrect.
     */
    private void loginAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid username or password");
        alert.setContentText("Please try again");
        alert.showAndWait();
    }

    /**
     * Adds a quit button to the grid pane.
     */
    private void addQuitButton() {
        // Because the user has not logged in, I'm not adjusting the BST
        // Especially because nothing should have been loaded, or edited.
        quitButton = new Button("Quit");
        quitButton.setOnAction(e -> quitFunction());
        gridPane.add(quitButton, 1, 2);
    }

    /**
     * Performs the quit function.
     * Closes the stage when the quit button is clicked.
     */
    private void quitFunction() {
        stage.close();
    }
}