package CustomExceptions; // Package for custom exceptions
import TaskChildren.Task; // Import Task class to manage task deletion

// Custom exception class for handling errors in ToDo task construction
public class ToDoConstructorException extends Exception {

    // Constructor that takes an error message as input
    public ToDoConstructorException(String message) {
        // Call the superclass (Exception) constructor with a detailed error message
        super("TODO CONSTRUCTOR EXCEPTION: " + errorMessage(message));

        // If an error occurs, the latest task added is deleted to prevent invalid tasks
        Task.deleteLatestTask();
    }

    // Private helper method to interpret the error message
    // It checks for specific missing components in the todo command
    private static String errorMessage(String message) {

        // Remove the "todo" keyword from the message to isolate the task statement
        String taskString = message.replace("todo ", "");

        // Check if the task statement is empty
        if (taskString.isEmpty()) {
            return "MISSING TASK STATEMENT"; // Return specific error if the task description is missing
        }

        // If none of the specific errors match, return an unknown error
        return "UNKNOWN ERROR";
    }
}
