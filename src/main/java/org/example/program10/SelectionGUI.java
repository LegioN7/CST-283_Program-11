package org.example.program10;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the GUI for selecting the operation to be performed on the patient data.
 * It provides a user interface with two buttons: "Search Patient" and "Query Data".
 * The "Search Patient" button loads the patient data and opens the HealthGUI.
 * The "Query Data" button loads the patient data and opens the QueryGUI.
 */
public class SelectionGUI {
    private final Stage stage;
    private final GridPane gridPane;

    private Button searchPatientButton;
    private Button queryDataButton;

    /**
     * Constructor for the SelectionGUI class.
     * Initializes the GUI components and sets up the event handlers.
     */
    public SelectionGUI() {
        stage = new Stage();
        stage.setTitle("Patient Search and Management System");

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        addSearchPatientButton();
        addQueryDataButton();

        VBox vBox = new VBox(gridPane);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Adds the "Search Patient" button to the GUI.
     * When this button is clicked, it loads the patient data and opens the HealthGUI.
     */
    private void addSearchPatientButton() {
        searchPatientButton = new Button("Search Patient");
        searchPatientButton.setOnAction(e -> {
            PatientBST patientBST = new PatientBST();
            try {
                patientBST.loadPatientData();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                HealthGUI healthGUI = new HealthGUI(new VBox());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.close();
        });
        gridPane.add(searchPatientButton, 0, 0);
    }

    /**
     * Adds the "Query Data" button to the GUI.
     * When this button is clicked, it loads the patient data and opens the QueryGUI.
     */
    private void addQueryDataButton() {
        queryDataButton = new Button("Query Data");
        queryDataButton.setOnAction(e -> {
            PatientBST patientBST = new PatientBST();
            try {
                patientBST.loadPatientData();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            QueryGUI queryGUI = new QueryGUI(patientBST);
            stage.close();
        });
        gridPane.add(queryDataButton, 1, 0);
    }
}
