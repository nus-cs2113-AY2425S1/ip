package UranusExceptions;

/**
 * Represents an exception that is thrown when the input provided by the user is empty.
 * It extends the UranusExceptions class and provides a specific error message.
 */
public class EmptyInputExceptions extends UranusExceptions {
    /**
     * Constructs a new EmptyInputExceptions.
     * This exception is triggered when the user input is empty.
     */
    public EmptyInputExceptions() {
        super();
    }

    /**
     * Overrides the getMessage method to provide a specific error message for empty input.
     *
     * @return A string message indicating that the input cannot be empty.
     */
    @Override
    public String getMessage() {
        return "Input cannot be empty!";
    }
}
