package nus.edu.rizzler.exception;

/**
 * Represents a custom exception for errors specific to the Rizzler application.
 */
public class RizzlerException extends RuntimeException {

    /**
     * Constructs a {@code RizzlerException} with the specified error message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public RizzlerException(String message) {
        super(message);
    }
}

