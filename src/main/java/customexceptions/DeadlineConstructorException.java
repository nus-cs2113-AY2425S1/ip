package customexceptions; // Package for custom exceptions

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Custom exception class for handling errors in Deadline task construction.
 */
public class DeadlineConstructorException extends Exception {

    /**
     * Constructor that takes an error message as input and generates a custom exception for Deadline tasks.
     *
     * @param message The error message for the exception.
     */
    public DeadlineConstructorException(String message) {
        super("DEADLINE CONSTRUCTOR EXCEPTION: " + errorMessage(message)); // Call superclass constructor
    }

    /**
     * Private helper method to interpret and identify specific errors in the deadline command.
     *
     * @param message The input message that caused the error.
     * @return The interpreted error message.
     */
    private static String errorMessage(String message) {
        if (!(message.contains(" /by "))) {
            return "MISSING BY COMMAND"; // Error if "/by" is missing
        }

        String[] taskStringBreakdown = message.replace("deadline ", "").split(" /by ");
        if (taskStringBreakdown[0].isEmpty()) {
            return "MISSING TASK STATEMENT"; // Error if task description is missing
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        try {
            dateTimeFormatter.parse(taskStringBreakdown[1]);
        } catch (DateTimeParseException e) {
            return "INVALID DATETIME EXCEPTION"; // Error if the date/time format is invalid
        }

        return "UNKNOWN ERROR"; // Fallback error message
    }
}
