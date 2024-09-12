package CustomExceptions; // Package for custom exceptions
import TaskChildren.Task; // Import Task class to manage task deletion

// Custom exception class for handling errors in Deadline task construction
public class DeadlineConstructorException extends Exception {

    // Constructor that takes an error message as input
    public DeadlineConstructorException(String message) {
        // Call the superclass (Exception) constructor with a detailed error message
        super("DEADLINE CONSTRUCTOR EXCEPTION: " + errorMessage(message));

        // If an error occurs, the latest task added is deleted to prevent invalid tasks
        Task.deleteLatestTask();
    }

    // Private helper method to interpret the error message
    // It checks for specific missing components in the deadline command
    private static String errorMessage(String message) {
        // Check if the "/by" keyword is missing, which is needed for deadline tasks
        if (!(message.contains(" /by "))) {
            return "MISSING BY COMMAND"; // Return specific error if "/by" is not found
        }

        // Split the task input string into task description and deadline parts
        String[] taskStringBreakdown = message.replace("deadline ", "").split(" /by ");

        // Check if the task description before "/by" is empty
        if (taskStringBreakdown[0].isEmpty()) {
            return "MISSING TASK STATEMENT"; // Return specific error if no task description
        }

        // If none of the specific errors match, return an unknown error
        return "UNKNOWN ERROR";
    }
}
