/**
 * Represents a custom exception for the Poirot task management application.
 * This exception is used to handle application-specific errors and provide meaningful error messages.
 */
public class PoirotException extends Exception {
    /**
     * Constructs a new PoirotException with the specified error message.
     *
     * @param message The detailed error message explaining the reason for the exception.
     */
    public PoirotException(String message) {
        super(message);
    }
}
