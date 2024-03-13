package org.example.program10;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the GUI for managing patient data.
 * It provides a user interface for adding, removing, and editing patient details, as well as searching for patients by email.
 */
public class HealthGUI {

    /**
     * The primary stage for the application, onto which the application scene can be set.
     */
    private final Stage stage;

    /**
     * The grid pane for the GUI layout.
     */
    private final GridPane gridPane;

    /**
     * The tab pane for the GUI layout.
     */
    private final TabPane tabPane;

    /**
     * The PatientBST object for managing patient data.
     */
    private final PatientBST patientBST;

    /**
     * The text field for entering the patient's first name.
     */
    private TextField firstNameField;

    /**
     * The text field for entering the patient's last name.
     */
    private TextField lastNameField;

    /**
     * The text field for entering the patient's address.
     */
    private TextField addressField;

    /**
     * The text field for entering the patient's city.
     */
    private TextField cityField;

    /**
     * The combo box for selecting the patient's state.
     */
    private ComboBox<String> stateField;

    /**
     * The text field for entering the patient's zip code.
     */
    private TextField zipField;

    /**
     * The text field for entering the patient's phone number.
     */
    private TextField phoneField;

    /**
     * The text field for entering the patient's email.
     */
    private TextField emailField;


    /**
     * The date picker for selecting the date of the flu inoculation.
     */
    private DatePicker fluInoculationDate1;

    /**
     * The date picker for selecting the date of the flu inoculation.
     */
    private DatePicker fluInoculationDate2;

    /**
     * The date picker for selecting the date of the first covid-19 inoculation.
     */
    private DatePicker covidInoculationDate1;

    /**
     * The date picker for selecting the date of the second covid-19 inoculation.
     */
    private DatePicker covidInoculationDate2;


    /**
     * The "Add Patient" button for adding a new patient.
     */
    private Button addPatientButton;

    /**
     * The "Remove Patient" button for removing a patient.
     */
    private Button removePatientButton;

    /**
     * The "Help" button for displaying instructions on how to use the application.
     */
    private Button helpButton;

    /**
     * The "Search Patient" button for searching for a patient by email.
     */
    private Button searchButton;

    /**
     * The "Edit Patient" button for editing a patient's details.
     */
    private Button editPatientButton;

    /**
     * The "Reset" button for clearing all the text fields and date pickers.
     */
    private Button resetButton;

    /**
     * The "Quit" button for saving the patient data to a file and closing the application.
     */
    private Button quitButton;

    /**
     * Constructor for the HealthGUI class.
     * Initializes the stage, gridPane, tabPane, and patientBST.
     * Also sets up the scene and shows the stage.
     *
     * @param vBox The VBox to add the tabPane and gridPane to.
     */
    public HealthGUI(VBox vBox) throws IOException {
        stage = new Stage();
        stage.setTitle("Patient Search and Management System");

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        tabPane = new TabPane();

        patientBST = new PatientBST();
        patientBST.loadPatientData();

        // Add tabs to the tab pane
        addPatientDetailsTab();
        addFluTab();
        addCovid19Tab();

        // Disable these buttons on launch
        removePatientButton.setDisable(true);
        editPatientButton.setDisable(true);
        resetButton.setDisable(true);

        vBox.getChildren().add(tabPane);
        vBox.getChildren().add(gridPane);

        Scene scene = new Scene(vBox, 720, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Adds the "Patient Details" tab to the tab pane.
     * This tab contains fields for patient's name, address, and contact information.
     * It also contains buttons for help, search, add, remove, edit, reset, and quit operations.
     */
    private void addPatientDetailsTab() {
        Tab patientDetailsTab = new Tab("Patient Details");

        GridPane patientDetailsPane = new GridPane();
        patientDetailsPane.setPadding(new Insets(20, 20, 20, 20));
        patientDetailsPane.setVgap(10);
        patientDetailsPane.setHgap(10);

        // Add patient details fields
        addPatientNameFields(patientDetailsPane);
        // Add patient address fields
        addPatientAddressFields(patientDetailsPane);
        // Add patient contact fields
        addPatientContactFields(patientDetailsPane);

        // Group the buttons in HBox and add to the grid pane
        HBox buttonBox1 = new HBox(5);
        addHelpButton();
        buttonBox1.getChildren().add(helpButton);
        addSearchPatientButton();
        buttonBox1.getChildren().add(searchButton);
        patientDetailsPane.add(buttonBox1, 0, 6, 2, 1);

        HBox buttonBox2 = new HBox(5);
        addPatientButton();
        buttonBox2.getChildren().add(addPatientButton);
        removePatientButton();
        buttonBox2.getChildren().add(removePatientButton);
        addEditPatientButton();
        buttonBox2.getChildren().add(editPatientButton);
        patientDetailsPane.add(buttonBox2, 0, 7, 2, 1);

        HBox buttonBox3 = new HBox(5);
        addResetButton();
        buttonBox3.getChildren().add(resetButton);
        patientDetailsPane.add(buttonBox3, 0, 8, 2, 1);

        HBox buttonBox4 = new HBox(5);
        addQuitButton();
        buttonBox4.getChildren().add(quitButton);
        patientDetailsPane.add(buttonBox4, 0, 9, 2, 1);

        patientDetailsTab.setContent(patientDetailsPane);
        tabPane.getTabs().add(patientDetailsTab);
    }

    /**
     * Adds the patient name fields to the grid pane.
     *
     * @param pane The grid pane to add the fields to.
     */
    private void addPatientNameFields(GridPane pane) {
        Label nameLabel = new Label("Patient Name");
        pane.add(nameLabel, 0, 0);

        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.setEditable(false);
        pane.add(firstNameField, 0, 1);

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.setEditable(false);
        pane.add(lastNameField, 1, 1);
    }

    /**
     * Adds the patient address fields to the grid pane.
     *
     * @param pane The grid pane to add the fields to.
     */
    private void addPatientAddressFields(GridPane pane) {
        Label addressLabel = new Label("Patient Address");
        pane.add(addressLabel, 0, 2);

        HBox addressBox = new HBox(10);
        addressField = new TextField();
        addressField.setPromptText("Street Address");
        addressField.setEditable(false);
        addressBox.getChildren().add(addressField);

        cityField = new TextField();
        cityField.setPromptText("City");
        cityField.setEditable(false);
        addressBox.getChildren().add(cityField);

        stateField = new ComboBox<>();
        // There has to be a better way to do this?
        stateField.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY", "DC", "PR");
        stateField.setPromptText("State");
        stateField.setEditable(false);
        stateField.setDisable(true);
        addressBox.getChildren().add(stateField);

        zipField = new TextField();
        zipField.setPromptText("Zip");
        zipField.setEditable(false);
        addressBox.getChildren().add(zipField);

        pane.add(addressBox, 0, 3, 2, 1);
    }

    /**
     * Adds the patient contact fields to the grid pane.
     *
     * @param pane The grid pane to add the fields to.
     */
    private void addPatientContactFields(GridPane pane) {
        Label contactLabel = new Label("Patient Contact Information");
        pane.add(contactLabel, 0, 4);

        phoneField = new TextField();
        phoneField.setPromptText("Phone");
        phoneField.setEditable(false);
        pane.add(phoneField, 0, 5);

        emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                searchFunction();
            }
        });
        pane.add(emailField, 1, 5);
    }

    /**
     * Adds the "Flu Inoculation" tab to the tab pane.
     * This tab contains fields for the dates of flu inoculation in 2023 and 2024.
     */
    private void addFluTab() {
        Tab inoculationTab = new Tab("Flu Inoculation");

        GridPane inoculationPane = new GridPane();
        inoculationPane.setPadding(new Insets(20, 20, 20, 20));
        inoculationPane.setVgap(10);
        inoculationPane.setHgap(10);

        Label inoculationDetailsLabel = new Label("Flu Inoculation Details");
        inoculationPane.add(inoculationDetailsLabel, 0, 0);

        Label date1Label = new Label("Flu Inoculation 2023");
        inoculationPane.add(date1Label, 0, 1);
        fluInoculationDate1 = new DatePicker();
        inoculationPane.add(fluInoculationDate1, 0, 2);

        Label date2Label = new Label("Flu Inoculation 2024");
        inoculationPane.add(date2Label, 0, 3);
        fluInoculationDate2 = new DatePicker();
        inoculationPane.add(fluInoculationDate2, 0, 4);

        inoculationTab.setContent(inoculationPane);
        tabPane.getTabs().add(inoculationTab);
    }

    /**
     * Adds the "Covid-19 Inoculation" tab to the tab pane.
     * This tab contains fields for the dates of Covid-19 inoculation and booster shots.
     */
    private void addCovid19Tab() {
        Tab inoculationTab = new Tab("Covid-19 Inoculation");

        GridPane inoculationPane = new GridPane();
        inoculationPane.setPadding(new Insets(20, 20, 20, 20));
        inoculationPane.setVgap(10);
        inoculationPane.setHgap(10);

        Label inoculationDetailsLabel = new Label("Covid-19 Inoculation Details");
        inoculationPane.add(inoculationDetailsLabel, 0, 0);

        Label date1Label = new Label("Inoculation Date 1");
        inoculationPane.add(date1Label, 0, 1);
        covidInoculationDate1 = new DatePicker();
        inoculationPane.add(covidInoculationDate1, 0, 2);

        Label date2Label = new Label("Inoculation Date 2");
        inoculationPane.add(date2Label, 1, 1);
        covidInoculationDate2 = new DatePicker();
        inoculationPane.add(covidInoculationDate2, 1, 2);

        Label boosterLabel1 = new Label("Covid-19 Booster 1 Date");
        inoculationPane.add(boosterLabel1, 0, 3);
        DatePicker boosterDate1 = new DatePicker();
        inoculationPane.add(boosterDate1, 0, 4);

        Label boosterLabel2 = new Label("Covid-19 Booster 2 Date");
        inoculationPane.add(boosterLabel2, 0, 5);
        DatePicker boosterDate2 = new DatePicker();
        inoculationPane.add(boosterDate2, 0, 6);

        inoculationTab.setContent(inoculationPane);
        tabPane.getTabs().add(inoculationTab);
    }

    private Patient getPatientFromFields() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getValue();
        String zip = zipField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date1 = covidInoculationDate1.getValue() != null ? covidInoculationDate1.getValue().format(formatter) : "0000-00-00";
        String date2 = covidInoculationDate2.getValue() != null ? covidInoculationDate2.getValue().format(formatter) : "0000-00-00";

        return new Patient(firstName, lastName, address, city, state, zip, phone, email, date1, date2);
    }

    /**
     * Creates and sets the action for the "Add Patient" button.
     * The action triggers the addPatientFunction method.
     */
    public void addPatientButton() {
        addPatientButton = new Button("Add Patient");
        addPatientButton.setOnAction(e -> addPatientFunction());
    }

    /**
     * Adds a new patient to the patient list.
     * The patient details are retrieved from the text fields and date pickers.
     * The patient is then added to the patient list using the PatientBST class's method.
     */
    public void addPatientFunction() {
        // Use a method to get the patient from the fields
        Patient newPatient = getPatientFromFields();

        // Add the patient to the BST
        patientBST.addPatient(newPatient);
    }

    /**
     * Creates and sets the action for the "Remove Patient" button.
     * The action triggers the removePatientFunction method.
     */
    public void removePatientButton() {
        removePatientButton = new Button("Remove Patient");
        removePatientButton.setOnAction(e -> removePatientFunction());
    }

    /**
     * Removes a patient from the patient list.
     * The patient to be removed is identified by their email.
     * If the patient exists, they are removed from the patient list using the PatientBST class's method.
     */
    public void removePatientFunction() {
        String email = emailField.getText();

        Patient patient = patientBST.searchPatient(email);
        if (patient == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Patient Email");
            alert.setContentText("No patient found with the provided email.\n" + "Please try again");
            alert.showAndWait();
            return;
        }

        // Create and show a confirmation alert for removing the patient
        Alert alert = removePatientAlert();

        // If the user confirms the removal, delete the patient
        if (alert.getResult() == ButtonType.OK) {
            patientBST.deletePatient(email);
            resetFields();
            removePatientConfirmation();
        }
    }

    /**
     * Creates and shows a confirmation alert for removing a patient.
     * The alert asks the user to confirm the removal of the patient.
     *
     * @return The alert, so the result (Ok/Cancel) can be checked in the calling method.
     */
    public Alert removePatientAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove Patient");
        alert.setHeaderText("Do you want to remove the patient?");
        alert.setContentText("Click OK to confirm");
        alert.showAndWait();
        return alert;
    }

    /**
     * Shows a confirmation alert indicating that the patient has been successfully removed.
     */
    public void removePatientConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Remove Patient");
        alert.setHeaderText(null);
        alert.setContentText("Patient has been removed successfully");
        alert.showAndWait();
    }

    /**
     * Creates and sets the action for the "Search Patient" button.
     * The action triggers the searchFunction method.
     */
    public void addSearchPatientButton() {
        searchButton = new Button("Search Patient");
        searchButton.setOnAction(e -> searchFunction());
    }

    /**
     * Searches for a patient in the patient list by their email.
     * If the patient is found, their details are displayed in the text fields and date pickers.
     * If the patient is not found, an error message is displayed and all fields except the email field are disabled.
     */
    private void searchFunction() {
        // Get the email from the email field
        String email = emailField.getText();

        // Search for the patient in the patient list via the email field
        Patient patient = patientBST.searchPatient(email);

        // If the patient is found, display their details in the text fields and date pickers
        // Unlock the fields for editing
        if (patient != null) {
            firstNameField.setText(patient.getFirstName());
            lastNameField.setText(patient.getLastName());
            addressField.setText(patient.getAddress());
            cityField.setText(patient.getCity());
            stateField.setValue(patient.getState());
            zipField.setText(patient.getZip());
            phoneField.setText(patient.getPhone());
            emailField.setText(patient.getEmail());

            firstNameField.setEditable(true);
            lastNameField.setEditable(true);
            addressField.setEditable(true);
            cityField.setEditable(true);
            stateField.setDisable(false);
            zipField.setEditable(true);
            phoneField.setEditable(true);
            emailField.setEditable(true);
            covidInoculationDate1.setDisable(false);
            covidInoculationDate2.setDisable(false);
            
            removePatientButton.setDisable(false);
            editPatientButton.setDisable(false);
            resetButton.setDisable(false);

            // Set the date pickers to the patient's inoculation dates
            // Because the date pickers are setup as 0000-00-00 if no date is provided, we need to check for that
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (patient.getDate1() != null && !patient.getDate1().isEmpty() && !patient.getDate1().equals("0000-00-00")) {
                LocalDate date1 = LocalDate.parse(patient.getDate1(), formatter);
                covidInoculationDate1.setValue(date1);
            } else {
                covidInoculationDate1.setValue(null);
            }

            if (patient.getDate2() != null && !patient.getDate2().isEmpty() && !patient.getDate2().equals("0000-00-00")) {
                LocalDate date2 = LocalDate.parse(patient.getDate2(), formatter);
                covidInoculationDate2.setValue(date2);
            } else {
                covidInoculationDate2.setValue(null);
            }
        } else {
            // If the patient is not found, display an error message and disable all fields except the email field
            searchAlert();

            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            addressField.setEditable(false);
            cityField.setEditable(false);
            stateField.setDisable(true);
            zipField.setEditable(false);
            phoneField.setEditable(false);
            emailField.setEditable(true);
            covidInoculationDate1.setDisable(true);
            covidInoculationDate2.setDisable(true);

            removePatientButton.setDisable(true);
            editPatientButton.setDisable(true);
        }
    }

    /**
     * Creates and shows an error alert for an invalid patient email.
     * The alert informs the user that no patient was found with the provided email and asks them to try again.
     */
    private void searchAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Patient Email");
        alert.setContentText("No patient found with the provided email.\n" + "Please try again");
        alert.showAndWait();
    }

    /**
     * Edits a patient's details in the patient list.
     * The updated patient details are retrieved from the text fields and date pickers.
     * If the second inoculation date is before the first, an error message is displayed.
     * Otherwise, a confirmation alert is shown to the user.
     * If the user confirms the edit operation, the patient's details are updated in the patient list using the PatientBST class's method.
     */
    private void editPatientFunction() {
        // Check if the second inoculation date is before the first
        LocalDate date1Local = covidInoculationDate1.getValue();
        LocalDate date2Local = covidInoculationDate2.getValue();

        if (date1Local != null && date2Local != null && date2Local.isBefore(date1Local)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Inoculation Dates");
            alert.setContentText("The second inoculation date cannot be before the first inoculation date.");
            alert.showAndWait();
            return;
        }

        // Get the patient from the fields
        Patient updatedPatient = getPatientFromFields();

        // Create and show a confirmation alert for editing the patient's details
        Alert alert = editPatientAlert();

        // If the user confirms the edit operation, update the patient's details
        if (alert.getResult() == ButtonType.OK) {
            patientBST.updatePatient(updatedPatient);

            // Show a confirmation alert indicating that the patient's details have been successfully updated
            editPatientAlertConfirmation();
        }
    }

    /**
     * Creates and shows a confirmation alert for editing a patient's details.
     * The alert asks the user to confirm the edit operation.
     *
     * @return The alert, so the result (Ok/Cancel) can be checked in the calling method.
     */
    private Alert editPatientAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edit Patient");
        alert.setHeaderText("Do you want to edit the patient's details?");
        alert.setContentText("Click OK to confirm");
        alert.showAndWait();

        return alert;
    }

    /**
     * Shows a confirmation alert indicating that the patient's details have been successfully updated.
     */
    private void editPatientAlertConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Edit Patient");
        alert.setHeaderText(null);
        alert.setContentText("Patient details have been updated successfully");
        alert.showAndWait();
    }

    /**
     * Adds a "Help" button to the GUI.
     * When this button is clicked, it shows an alert with instructions on how to use the application.
     */
    private void addHelpButton() {
        helpButton = new Button("Help");
        helpButton.setOnAction(e -> {
            helpButtonFunction();
        });
    }

    private void helpButtonFunction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);

        String helpContent = "To search a Patient - Enter their email and click Search Patient.\n" +
                "To Edit a Patient - Search the Patient, update the fields, and click Edit Patient.\n" +
                "To Reset the Fields - Click the Reset button\n" +
                "To quit the application - Click the Quit button\n";

        alert.setContentText(helpContent);
        alert.showAndWait();
    }

    /**
     * Adds an "Edit Patient" button to the GUI.
     * When this button is clicked, it triggers the editPatientFunction method to edit a patient's details.
     */
    public void addEditPatientButton() {
        editPatientButton = new Button("Edit Patient");
        editPatientButton.setOnAction(e -> {
            editPatientFunction();
        });
    }

    /**
     * Adds a "Reset" button to the GUI.
     * When this button is clicked, it clears all the text fields and date pickers.
     */
    public void addResetButton() {
        resetButton = new Button("Reset");
        resetButton.setOnAction(e -> {
            resetFields();
        });
    }

    private void resetFields() {
        // Clear the name fields
        firstNameField.clear();
        lastNameField.clear();

        // Clear the address fields
        addressField.clear();
        cityField.clear();
        stateField.setValue(null);
        zipField.clear();

        // Clear the phone and email fields
        phoneField.clear();
        emailField.clear();

        // Clear the flu date pickers
        fluInoculationDate1.setValue(null);
        fluInoculationDate2.setValue(null);

        // Clear the covid-19 date pickers
        covidInoculationDate1.setValue(null);
        covidInoculationDate2.setValue(null);

        // Disable the buttons after reset
        removePatientButton.setDisable(true);
        editPatientButton.setDisable(true);
        resetButton.setDisable(true);
    }

    /**
     * Adds a "Quit" button to the GUI.
     * When this button is clicked, it saves the patient data to a file and closes the stage.
     */
    private void addQuitButton() {
        quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            // Save patient data to file and close the stage
            try {
                patientBST.savePatientData("patients_bst.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.close();
        });
    }
}