public class DBotException extends Exception {
    private final String errorType;

    /**
     * Represents a custom exception for DBot with an associated error type.
     * Extends the standard Exception class to include both an error message and an error type.
     *
     * @param message   the detail message associated with the exception
     * @param errorType the type of the error that caused the exception
     */
    public DBotException(String message, String errorType) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * Returns the type of the error that caused the exception.
     *
     * @return the error type as a string
     */
    public String getErrorType() {
        return this.errorType;
    }
}
