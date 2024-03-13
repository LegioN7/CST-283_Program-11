package org.example.program10;

// CST-283
// Aaron Pelto
// Winter 2024

/*
    Write a software application to manage a very large data set of health patients your organization is
    responsible for.
    The format of the provided data file is:
        firstName,lastName,address,city,state,zip,phone,email,date1,date2

    The Data includes the following:
        General contact information.
        The unique key for an entry is their email.
        COVID-19 virus inoculation (two shots are required).
            If one or both date files are 0000-00-00, then they have not received it yet.
            Date1 will be populated before date2.
*/

/*
    Your back-end data container should include:
        A class to store and manage one Patient object including all fields from each data line in the file
        A binary search tree of Patient objects to manage all the data (utilizing the generic BST
    developed in class)
        Strongly consider defining a class to "wrap around" the provided binary search tree of Patient
    objects.
            This will likely give more flexibility with methods called in the driver class.
    You will need to include the following methods:
        Add a patient
        Delete an entry
        Change patient info
        Search for a given patient record.
*/

/*
    Build a front-end set of graphical user interface that includes an interface to enable these features:
        Create a login window view that includes only login ID and password text fields
            The password field must be hidden.
        The main interface should include the following UI elements:
            Add a patient,
            Delete a patient,
                 Use the patient email as the key for deletions.
            Change any field in the patient record
            Search for a patient.
             Include the ability to search (via the email key) to populate all the interface fields with current data.
             Quit Button
              This should write the binary search tree back to a file and quit the application.

     User Interface Requirements:
        Drop-down list for the state selection.
        Devise an approach to include two date fields in the interface for the respective vaccines.
        Do not use the text field to store both dates
        Include a basic error checking feature to be sure the first date is always less than the second date.

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

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class represents the main entry point for the Health Application.
 * The application is responsible for managing a large data set of health patients.
 * The data includes general contact information and COVID-19 inoculation dates.
 * The application provides a GUI for user interaction, which includes features to add, delete, change, and search for patient records.
 * It also includes features to list and count patients based on their inoculation status and location.
 */
public class HealthApplication extends Application {

    /**
     * It creates an instance of the LoginGUI class, which provides the login interface for the application.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        // Create the login GUI
        // This will be the entry point for the application
        // The user will need to log in before accessing the main application
        // The username and password are admin
        LoginGUI loginGUI = new LoginGUI();
    }

    /**
     * The main method for the HealthApplication class.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}