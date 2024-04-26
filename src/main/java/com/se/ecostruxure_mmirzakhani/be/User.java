package com.se.ecostruxure_mmirzakhani.be;

/**
 * Represent a user in the application.
 * Open end to extend more users as application grows.
 * like admin, guest or etc.
 */
public class User {
    private int id;

    /**
     * Constructor
     */
    public User(){

    }

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
