package com.se.ecostruxure_mmirzakhani.be;

public class Employee extends User {
    private Contract        contract;


    // ******************** Constructors **************************************
    public Employee(){
        this.contract =     new Contract();
    }
    public Employee(int id) {
        super(id);
    }


    // ******************** Methods *******************************************
    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public String getLastName() {
        return super.getLastName();
    }

    public void setLastName(String lastName) {
        super.setLastName(lastName);
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
                "contract=" + contract +
                '}';
    }
}
