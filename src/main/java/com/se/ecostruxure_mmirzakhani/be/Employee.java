package com.se.ecostruxure_mmirzakhani.be;


import java.util.Objects;

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

    // ******************** Equals and HashCode ********************************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        if (!super.equals(o)) return false;
        return getId() == employee.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
