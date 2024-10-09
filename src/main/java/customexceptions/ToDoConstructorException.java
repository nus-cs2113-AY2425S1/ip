package customexceptions; // Package for custom exceptions

/**
 * Custom exception class for handling errors in ToDo task construction.
 */
public class ToDoConstructorException extends Exception {

    /**
     * Constructor that takes an error message as input and generates a custom exception for ToDo tasks.
     *
     * @param message The error message for the exception.
     */
    public ToDoConstructorException(String message) {
        super("TODO CONSTRUCTOR EXCEPTION: " + errorMessage(message)); // Call superclass constructor
    }

    /**
     * Private helper method to interpret and identify specific errors in the ToDo command.
     *
     * @param message The input message that caused the error.
     * @return The interpreted error message.
     */
    private static String errorMessage(String message) {
        String taskString = message.replace("todo ", "");
        if (taskString.isEmpty()) {
            return "MISSING TASK STATEMENT"; // Error if task description is missing
        }

        return "UNKNOWN ERROR"; // Fallback error message
    }
}
