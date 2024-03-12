package org.example.program10;

/**
 * This class represents a Patient object.
 * It contains fields for the first name, last name, address, city, state, zip, phone, email, date1, and date2.
 * It also contains a constructor to initialize the fields, getters and setters for the fields, and toString and toDataString methods.
 */
public class Patient implements Comparable<Patient> {

    /**
     * The first name of the patient.
     */
    private String firstName;

    /**
     * The last name of the patient.
     */
    private String lastName;

    /**
     * The street address of the patient.
     */
    private String address;

    /**
     * The city of the patient.
     */
    private String city;

    /**
     * The state of the patient.
     */
    private String state;

    /**
     * The zip code of the patient.
     */
    private String zip;

    /**
     * The phone number of the patient.
     */
    private String phone;

    /**
     * The email of the patient.
     */
    private String email;

    /**
     * The date of the first covid-19 vaccine shot.
     */
    private String date1;

    /**
     * The date of the second covid-19 vaccine shot.
     */
    private String date2;

    /**
     * Constructs a Patient object with the given fields.
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param phone
     * @param email
     * @param date1
     * @param date2
     */
    public Patient(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email, String date1, String date2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * Constructs a Patient object with the given email.
     * @param email
     */
    public Patient(String email) {
        this.email = email;
    }

    /**
     * Gets the first name of the patient.
     * @return The first name of the patient.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the patient.
     * @param firstName The first name of the patient.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the patient.
     * @return The last name of the patient.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the patient.
     * @param lastName The last name of the patient.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the address of the patient.
     * @return The address of the patient.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the patient.
     * @param address The address of the patient.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the city of the patient.
     * @return The city of the patient.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the patient.
     * @param city The city of the patient.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state of the patient.
     * @return The state of the patient.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the patient.
     * @param state The state of the patient.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the zip code of the patient.
     * @return The zip code of the patient.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the zip code of the patient.
     * @param zip The zip code of the patient.
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the phone number of the patient.
     * @return The phone number of the patient.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the patient.
     * @param phone The phone number of the patient.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email of the patient.
     * @return The email of the patient.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the patient.
     * @param email The email of the patient.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the date of the first covid-19 vaccine shot.
     * @return The date of the first covid-19 vaccine shot.
     */
    public String getDate1() {
        return date1;
    }

    /**
     * Sets the date of the first covid-19 vaccine shot.
     * @param date1 The date of the first covid-19 vaccine shot.
     */
    public void setDate1(String date1) {
        this.date1 = date1;
    }

    /**
     * Gets the date of the second covid-19 vaccine shot.
     * @return The date of the second covid-19 vaccine shot.
     */
    public String getDate2() {
        return date2;
    }

    /**
     * Sets the date of the second covid-19 vaccine shot.
     * @param date2 The date of the second covid-19 vaccine shot.
     */
    public void setDate2(String date2) {
        this.date2 = date2;
    }

    /**
     * Returns a string representation of the Patient object.
     * @return A string representation of the Patient object.
     */
    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", date1='" + date1 + '\'' +
                ", date2='" + date2 + '\'' +
                '}';
    }

    /**
     * Returns a string representation of the Patient object in a format that can be written to a file.
     * @return A string representation of the Patient object in a format that can be written to a file.
     */
    public String toDataString() {
        return firstName + "," + lastName + "," + address + "," + city + "," + state + "," + zip + "," + phone + "," + email + "," + date1 + "," + date2;
    }

    /**
     * Compares this Patient object with the specified Patient object for order.
     * @param other The Patient object to be compared.
     * @return A negative integer, zero, or a positive integer as this Patient object is less than, equal to, or greater than the specified Patient object.
     */
    @Override
    public int compareTo(Patient other) {
        return this.email.compareTo(other.email);
    }
}
