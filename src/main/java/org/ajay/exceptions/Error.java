package org.ajay.exceptions;


// NOTE: Taken from https://stackoverflow.com/questions/446663/best-way-to-define-error-codes-strings-in-java
public enum Error {

    EMPTY_ARG(0, "Empty argument found."), ILLEGAL_COMMAND(1, "Invalid command found."), INVAILD_COMMAND_FORMAT(2, "Invalid command format found.");

    private final int code;
    private final String description;

    private Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return description + " (Error Code: " + code + ")";
    }
}

// public enum ErrorLevel {
//     SUCCESS, WARN, ERROR, INFO,

// }
