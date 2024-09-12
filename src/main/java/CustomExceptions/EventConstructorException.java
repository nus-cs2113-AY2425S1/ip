package CustomExceptions; // Package for custom exceptions
import TaskChildren.Task; // Import Task class to manage task deletion

// Custom exception class for handling errors in Event task construction
public class EventConstructorException extends Exception {

    // Constructor that takes an error message as input
    public EventConstructorException(String message) {
        // Call the superclass (Exception) constructor with a detailed error message
        super("EVENT CONSTRUCTOR EXCEPTION: " + errorMessage(message));

        // If an error occurs, the latest task added is deleted to prevent invalid tasks
        Task.deleteLatestTask();
    }

    // Private helper method to interpret the error message
    // It checks for specific missing components in the event command
    private static String errorMessage(String message) {
        // Check if the "/from" and "/to" keywords are missing, which are needed for event tasks
        if (!(message.contains(" /from ") || message.contains(" /to "))) {
            return "MISSING FROM/TO COMMANDS"; // Return specific error if either "/from" or "/to" is missing
        }

        // Split the task input string into task description and the time period starting with "/from"
        String[] taskStringBreakdown = message.replace("event ", "").split(" /from ");

        // Check if the task description before "/from" is empty
        if (taskStringBreakdown[0].isEmpty()) {
            return "MISSING TASK STATEMENT"; // Return specific error if no task description is provided
        }

        // Further split the time period into "from" and "to" parts
        String[] fromToStringBreakdown = taskStringBreakdown[1].split(" /to ");

        // Check if both "from" and "to" components are present
        if (fromToStringBreakdown.length != 2) {
            return "MISSING FROM/TO DATES"; // Return specific error if either "from" or "to" date is missing
        }

        // If none of the specific errors match, return an unknown error
        return "UNKNOWN ERROR";
    }
}
