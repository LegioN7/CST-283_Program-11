package org.example.program10;

import java.io.*;
import java.util.List;

/**
 * This class represents a Binary Search Tree (BST) specifically for Patient objects.
 * It provides methods for loading and saving patient data from/to a file, adding, deleting, updating, and searching for patients in the BST.
 * It also provides methods for getting all patients, checking the size and emptiness of the BST, getting the depth of the BST, and performing different types of traversals on the BST.
 */
public class PatientBST {

    private final String FILENAME = "patients.txt";

    /**
     * The Binary Search Tree (BST) for storing Patient objects.
     */
    private final BinarySearchTree<Patient> bst;

    /**
     * Constructor for the PatientBST class.
     * Initializes the Binary Search Tree (BST).
     */
    public PatientBST() {
        bst = new BinarySearchTree<>();
    }

    /**
     * Loads patient data from a file and adds them to the BST.
     */
    public void loadPatientData() throws IOException {
        // Load patient data from file
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by comma and create a new patient object
                String[] data = line.split(",");
                Patient patient = new Patient(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]);
                // Add the patient to the BST
                bst.add(patient);
                // Debug Statement
                // System.out.println("Loaded patient: " + patient);
            }
        }
    }

    /**
     * Saves patient data from the BST to a file.
     *
     * @param filename The name of the file to save the patient data to.
     */
    public void savePatientData(String filename) throws IOException {
        // Save patient data to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // Get all patients in the BST
            List<Patient> allPatients = bst.inorderTraversal();
            // Loop through all patients and write them to the file
            for (Patient patient : allPatients) {
                bw.write(patient.toDataString());
                bw.newLine();
            }
        }
    }

    /**
     * Adds a new patient to the BST.
     *
     * @param firstName, lastName, address, city, state, zip, phone, email, date1, date2 The details of the patient to be added.
     */
    public void addPatient(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email, String date1, String date2) {
        bst.add(new Patient(firstName, lastName, address, city, state, zip, phone, email, date1, date2));
    }

    /**
     * Deletes a patient from the BST.
     *
     * @param email The email of the patient to be deleted.
     */
    public void deletePatient(String email) {
        bst.remove(new Patient(email));
    }

    /**
     * Adds a new patient to the BST.
     *
     * @param patient The patient to be added.
     */
    public void addPatient(Patient patient) {
        bst.add(patient);
    }

    /**
     * Updates the details of a patient in the BST.
     *
     * @param updatedPatient The patient with updated details.
     */
    public void updatePatient(Patient updatedPatient) {
        if (isValidPatient(updatedPatient)) {
            Patient existingPatient = bst.search(new Patient(updatedPatient.getEmail()));

            if (existingPatient != null) {
                existingPatient.setFirstName(updatedPatient.getFirstName());
                existingPatient.setLastName(updatedPatient.getLastName());
                existingPatient.setAddress(updatedPatient.getAddress());
                existingPatient.setCity(updatedPatient.getCity());
                existingPatient.setState(updatedPatient.getState());
                existingPatient.setZip(updatedPatient.getZip());
                existingPatient.setPhone(updatedPatient.getPhone());
                existingPatient.setEmail(updatedPatient.getEmail());
                existingPatient.setDate1(updatedPatient.getDate1());
                existingPatient.setDate2(updatedPatient.getDate2());
            }
        }
    }

    private boolean isValidPatient(Patient patient) {
        // Validate the patient email
        // Reference:
        // https://www.baeldung.com/java-email-validation-regex
        return patient.getEmail() != null && patient.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    /**
     * Searches for a patient in the BST.
     *
     * @param email The email of the patient to be searched for.
     * @return The patient if found, null otherwise.
     */
    public Patient searchPatient(String email) {
        return bst.search(new Patient(email));
    }

    /**
     * Gets all patients in the BST.
     *
     * @return A list of all patients in the BST.
     */
    public List<Patient> getAllPatients() {
        return bst.inorderTraversal();
    }

    /**
     * Gets the size of the BST.
     *
     * @return The number of nodes (patients) in the BST.
     */
    public int size() {
        return bst.size();
    }

    /**
     * Checks if the BST is empty.
     *
     * @return true if the BST is empty, false otherwise.
     */
    public boolean isEmpty() {
        return bst.getRoot() == null;
    }

    /**
     * Gets the depth of the BST.
     *
     * @return The depth of the BST.
     */
    public int treeDepth() {
        return bst.treeDepth();
    }

    /**
     * Performs an in-order traversal of the BST.
     */
    public void traverseInOrder() {
        bst.traverseInOrder();
    }

    /**
     * Performs a pre-order traversal of the BST.
     */
    public void traversePreOrder() {
        bst.traversePreOrder();
    }

    /**
     * Performs a post-order traversal of the BST.
     */
    public void traversePostOrder() {
        bst.traversePostOrder();
    }
}
