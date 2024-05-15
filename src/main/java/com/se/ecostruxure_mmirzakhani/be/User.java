package com.se.ecostruxure_mmirzakhani.be;

/**
 * Represent a user in the application.
 * Open end to extend more users as application grows.
 * like admin, guest or etc.
 */
public class User extends Person{
    private int id;

    // ******************** Constructors **************************************
    public User(){
    }

    public User(int id){
        this.id = id;
    }


    // ******************** Methods **************************************
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
