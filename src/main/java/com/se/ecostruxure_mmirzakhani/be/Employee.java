package com.se.ecostruxure_mmirzakhani.be;

public class Employee extends User {
    // Personal attributes
    private String firstName;
    private String lastName;
    private Contract contract;

    // Constructor
    public Employee() {}

    public Employee(int employeeID, String firstName, String lastName) {
        super(employeeID); // Call the constructor of the User class to set the employeeID
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Methods
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contract=" + contract +
                '}';
    }
}