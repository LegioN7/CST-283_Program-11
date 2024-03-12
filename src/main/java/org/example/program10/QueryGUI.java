package org.example.program10;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

// Query Requirements
/*
    Search Requirements:
        For a selected state, list names for (and count #)
            The number having received the first shot but not the second.
            The number not receiving either shot,
            The number receiving both shots
        For a selected zip code, list names for (and count #)
            The number having received the first shot but not the second.
            The number not receiving either shot,
            The number receiving both shots
 */

/**
 * This class represents the GUI for querying patient data.
 * It provides a user interface for searching patients by state or zip code and displays the search results in a table.
 * It also displays the count of patients who have received the first shot but not the second, who have not received either shot, and who have received both shots.
 */
public class QueryGUI{

    /**
     * The primary stage for the application, onto which the application scene can be set.
     */
    private final Stage stage;

    /**
     * The grid pane for the GUI layout.
     */
    private final GridPane gridPane;

    /**
     * The text area for displaying the search results.
     */
    private final TextArea resultArea;

    /**
     * The table view for displaying the search results.
     */
    private final TableView<Patient> patientTable;

    /**
     * The PatientBST object to be queried.
     */
    private final PatientBST patientBST;

    /**
     * Constructor for the QueryGUI class.
     * Initializes the GUI components and sets up the event handlers.
     * @param patientBST The PatientBST object to be queried.
     */
    public QueryGUI(PatientBST patientBST) {
        // Initialize the patientBST
        this.patientBST = patientBST;

        // Create the stage
        stage = new Stage();
        stage.setTitle("Patient Search and Management System");

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Add the search type combo box
        // Easier than adding a radio button for each search type
        ComboBox<String> searchTypeComboBox = new ComboBox<>();
        searchTypeComboBox.getItems().addAll("State", "Zip");
        gridPane.add(searchTypeComboBox, 0, 0);

        // Add the search field
        TextField searchField = new TextField();
        gridPane.add(searchField, 1, 0);

        // Add the search button
        Button searchButton = new Button("Search");
        gridPane.add(searchButton, 2, 0);

        resultArea = new TextArea();
        resultArea.setEditable(false);
        gridPane.add(resultArea, 0, 1, 3, 1);

        // Add the patient table
        patientTable = new TableView<>();
        setupPatientTable();
        gridPane.add(patientTable, 0, 2, 3, 1);

        // Set up the event handler for the search button
        searchButton.setOnAction(e -> {
            // Perform the search
            String searchType = searchTypeComboBox.getValue();
            // Get the text from the search field
            String searchValue = searchField.getText();
            // Check if the search type and value are valid
            if (searchType != null && !searchValue.isEmpty()) {
                // Use regex to validate the search value
                if ((searchType.equals("State") && searchValue.matches("[A-Za-z]{2}")) ||
                        // 5-digit zip code regex
                        (searchType.equals("Zip") && searchValue.matches("\\d{5}"))) {
                    performSearch(searchType, searchValue);
                } else {
                    // Display an error message if the search value is invalid
                    resultArea.setText("Invalid input. Please enter a 2-letter state or a 5-digit zip code.");
                }
            }
        });

        VBox vBox = new VBox(gridPane);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 900, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets up the patient table with the appropriate columns.
     */
    private void setupPatientTable() {
        TableColumn<Patient, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Patient, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Patient, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Patient, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Patient, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Patient, String> zipColumn = new TableColumn<>("Zip");
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zip"));

        TableColumn<Patient, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Patient, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Patient, String> date1Column = new TableColumn<>("Date 1");
        date1Column.setCellValueFactory(new PropertyValueFactory<>("date1"));

        TableColumn<Patient, String> date2Column = new TableColumn<>("Date 2");
        date2Column.setCellValueFactory(new PropertyValueFactory<>("date2"));

        // IntelliJ IDEA suggested that this line resolves a warning
        // I'd have to override this method in the Patient class to resolve the warning
        patientTable.getColumns().addAll(firstNameColumn, lastNameColumn, addressColumn, cityColumn, stateColumn, zipColumn, phoneColumn, emailColumn, date1Column, date2Column);
    }

    /**
     * Performs the search based on the selected search type and search value.
     * Updates the result area and patient table with the search results.
     * @param searchType The type of search to be performed ("State" or "Zip").
     * @param searchValue The value to be searched for.
     */
    private void performSearch(String searchType, String searchValue) {
        // Check if the BST is empty
        if (patientBST.isEmpty()) {
            // Update the resultArea with a message if the BST is empty
            resultArea.setText("No patients in the database.");
            return;
        }
        // Retrieve all patients from the BST
        List<Patient> allPatients = patientBST.getAllPatients();

        // Initialize the list for filtered patients
        List<Patient> patients = new ArrayList<>();

        // Initialize the lists for each category
        List<Patient> firstShotOnly = new ArrayList<>();
        List<Patient> noShots = new ArrayList<>();
        List<Patient> bothShots = new ArrayList<>();

        // Iterate over all patients and add them to the filtered list if they match the search criteria
        for (Patient patient : allPatients) {
            // Check if the patient matches the search criteria
            if ((searchType.equals("State") && patient.getState().equals(searchValue)) ||
                    (searchType.equals("Zip") && patient.getZip().equals(searchValue))) {
                patients.add(patient);

                // Add the patient to the appropriate category list
                // based on their inoculation status
                if (patient.getDate1() != null && !patient.getDate1().equals("0000-00-00") && (patient.getDate2() == null || patient.getDate2().equals("0000-00-00"))) {
                    firstShotOnly.add(patient);
                } else if ((patient.getDate1() == null || patient.getDate1().equals("0000-00-00")) && (patient.getDate2() == null || patient.getDate2().equals("0000-00-00"))) {
                    noShots.add(patient);
                } else if (patient.getDate1() != null && !patient.getDate1().equals("0000-00-00") && patient.getDate2() != null && !patient.getDate2().equals("0000-00-00")) {
                    bothShots.add(patient);
                }
            }
        }

        // Update the resultArea with the count of patients in each category
        resultArea.setText("Number having received the first shot but not the second: " + firstShotOnly.size() +
                "\nNumber not receiving either shot: " + noShots.size() +
                "\nNumber receiving both shots: " + bothShots.size());

        // Update the patientTable with all the patients who are part of the search
        ObservableList<Patient> data = FXCollections.observableArrayList(patients);
        // Update the patientTable with the search results
        patientTable.setItems(data);
    }

}
