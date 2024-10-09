package customexceptions; // Package for custom exceptions

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Custom exception class for handling errors in Event task construction.
 */
public class EventConstructorException extends Exception {

    /**
     * Constructor that takes an error message as input and generates a custom exception for Event tasks.
     *
     * @param message The error message for the exception.
     */
    public EventConstructorException(String message) {
        super("EVENT CONSTRUCTOR EXCEPTION: " + errorMessage(message)); // Call superclass constructor
    }

    /**
     * Private helper method to interpret and identify specific errors in the event command.
     *
     * @param message The input message that caused the error.
     * @return The interpreted error message.
     */
    private static String errorMessage(String message) {
        if (!(message.contains(" /from ") || message.contains(" /to "))) {
            return "MISSING FROM/TO COMMANDS"; // Error if "/from" or "/to" is missing
        }

        String[] taskStringBreakdown = message.replace("event ", "").split(" /from ");
        if (taskStringBreakdown[0].isEmpty()) {
            return "MISSING TASK STATEMENT"; // Error if task description is missing
        }

        String[] fromToStringBreakdown = taskStringBreakdown[1].split(" /to ");
        if (fromToStringBreakdown.length != 2) {
            return "MISSING FROM/TO DATES"; // Error if either "from" or "to" date is missing
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        try {
            dateTimeFormatter.parse(fromToStringBreakdown[0]);
            dateTimeFormatter.parse(fromToStringBreakdown[1]);
        } catch (DateTimeParseException e) {
            return "INVALID DATETIME EXCEPTION"; // Error if the date/time format is invalid
        }

        return "UNKNOWN ERROR"; // Fallback error message
    }
}
