package aether;

/**
 * Custom exception class for handling application-specific errors in Aether.
 * <p>
 * The {@code DukeException} class extends {@link Exception} to provide a mechanism for
 * throwing and catching exceptions that are specific to the Aether task manager application.
 * It encapsulates error messages that describe issues encountered during the application's
 * execution, such as invalid commands, task parsing errors, or file I/O problems.
 * </p>
 */
public class DukeException extends Exception {

    /**
     * Constructs a new {@code DukeException} with the specified detail message.
     * <p>
     * The detail message is saved for later retrieval by the {@link #getMessage()} method.
     * </p>
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
