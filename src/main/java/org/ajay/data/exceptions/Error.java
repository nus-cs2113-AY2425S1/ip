package org.ajay.data.exceptions;

// NOTE: Taken from https://stackoverflow.com/questions/446663/best-way-to-define-error-codes-strings-in-java

import org.ajay.common.Messages;
import org.ajay.data.exceptions.Error;

public enum Error {

    EMPTY_ARG(0, Messages.MESSAGE_EMPTY_ARG), ILLEGAL_COMMAND(1, Messages.MESSAGE_ILLEGAL_COMMAND),
    INVAILD_COMMAND_FORMAT(2, Messages.MESSAGE_INVALID_COMMAND_FORMAT), OUT_OF_BOUNDS(3, Messages.MESSAGE_OUT_OF_BOUNDS);

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
// SUCCESS, WARN, ERROR, INFO,

// }
