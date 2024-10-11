package org.ajay.data.exceptions;

// NOTE: Taken from https://stackoverflow.com/questions/446663/best-way-to-define-error-codes-strings-in-java

import org.ajay.common.Messages;
import org.ajay.data.exceptions.Error;

/**
 * Represents an error in Jarvis.
 */
public enum Error {

    EMPTY_ARG(0, Messages.MESSAGE_EMPTY_ARG), ILLEGAL_COMMAND(1, Messages.MESSAGE_ILLEGAL_COMMAND),
    INVAILD_COMMAND_FORMAT(2, Messages.MESSAGE_INVALID_COMMAND_FORMAT),
    OUT_OF_BOUNDS(3, Messages.MESSAGE_OUT_OF_BOUNDS);

    private final int code;
    private final String description;

    /**
     * Constructor for the Error class.
     *
     * @param code        Error code.
     * @param description Description of the error.
     */
    private Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Gets the description of the error.
     *
     * @return Description of the error.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the error code.
     *
     * @return Error code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the string representation of the error.
     *
     * @return String representation of the error.
     */
    @Override
    public String toString() {
        return description + " (Error Code: " + code + ")";
    }
}
