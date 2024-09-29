/**
 * The DukeException class handles custom exceptions specific to the Duke application.
 * It extends the Exception class and provides additional functionality for displaying warning messages.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param message The detail message, which can be retrieved later by the {@code getMessage()} method.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Displays the warning message prefixed with "Warning: ".
     * The message is retrieved using the {@code getMessage()} method from the Exception class.
     */
    public void displayMessage() {
        System.out.println("Warning: " + getMessage());
    }
}
