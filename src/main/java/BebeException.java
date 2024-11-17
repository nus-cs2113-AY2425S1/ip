/**
 * Represents a custom exception specific to the Bebe application.
 * Used to handle application-specific errors.
 */
public class BebeException extends Exception{
    /**
     * Constructs a BebeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public BebeException(String message){
        super(message);
    }
}
