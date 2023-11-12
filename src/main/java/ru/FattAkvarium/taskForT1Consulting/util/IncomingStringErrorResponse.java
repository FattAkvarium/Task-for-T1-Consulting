package ru.FattAkvarium.taskForT1Consulting.util;

public class IncomingStringErrorResponse {

    private String message;

    public IncomingStringErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
