package com.se.ecostruxure_mmirzakhani.be;

public class Change {
    private String property;
    private Object previousState;
    private Object currentState;
    private ChangeState changeState;

    public Change(){

    }


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
}
