package ru.FattAkvarium.taskForT1Consulting.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class IncomingString {

    @NotEmpty(message = "Incoming string should not be empty!")
    @Size(min = 1, max = 1000, message = "Incoming string should be between 1 and 1000 characters!")
    private String incomingString;

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