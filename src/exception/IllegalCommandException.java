package exception;

/**
 * This class represents an exception that is thrown when an illegal or invalid command is issued.
 * It extends the base Exception class and passes an error message to its constructor.
 */
public class IllegalCommandException extends Exception { // inherit from the Exception
    public IllegalCommandException(String message) {
        super(message);
    }
}