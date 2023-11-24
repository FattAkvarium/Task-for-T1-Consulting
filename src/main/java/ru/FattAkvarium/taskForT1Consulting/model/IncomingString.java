package ru.FattAkvarium.taskForT1Consulting.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class IncomingString implements Serializable {

    @NotEmpty(message = "Incoming string should not be empty!")
    @Size(min = 2, max = 1000, message = "Incoming string should be between 2 and 1000 characters!")
    private String incomingString;

    public IncomingString() {
    }

    public IncomingString(String incomingString) {
        this.incomingString = incomingString;
    }

    public String getIncomingString() {
        return incomingString;
    }

    public void setIncomingString(String incomingString) {
        this.incomingString = incomingString;
    }
}
