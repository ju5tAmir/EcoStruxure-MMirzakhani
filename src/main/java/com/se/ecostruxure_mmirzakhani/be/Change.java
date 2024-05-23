package com.se.ecostruxure_mmirzakhani.be;

public class Change {
    private String              object;
    private String              property;
    private Object              previousState;
    private Object              currentState;
    private ChangeState         changeState;


    // ******************** Constructors *********************************
    public Change(){

    }


    // ******************** Methods **************************************
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getPreviousState() {
        return previousState;
    }

    public void setPreviousState(Object previousState) {
        this.previousState = previousState;
    }

    public Object getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Object currentState) {
        this.currentState = currentState;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public ChangeState getChangeState() {
        return changeState;
    }

    public void setChangeState(ChangeState changeState) {
        this.changeState = changeState;
    }

    @Override
    public String toString() {
        return "Change{" +
                "object='" + object + '\'' +
                ", property='" + property + '\'' +
                ", previousState=" + previousState +
                ", currentState=" + currentState +
                ", changeState=" + changeState +
                '}';
    }
}
