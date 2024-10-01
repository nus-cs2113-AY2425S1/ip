package niwa.messages;

/**
 * The class {@code NiwaExceptionMessages} contains static final strings used throughout the {@code Niwa} chatbot.
 * These messages provide feedback to users regarding exceptions that may occur during interaction.
 */
public class NiwaExceptionMessages {
    public static final String MESSAGE_INVALID_ARGUMENT = "Wrong arguments! Please check your syntax.";
    public static final String MESSAGE_INVALID_ARGUMENT_CORRECTION = "Wrong arguments! Please check your syntax: %s";
    public static final String MESSAGE_INVALID_SYNTAX = "Your syntax is invalid, " +
            "type 'help' to check for our syntax list!";
    public static final String MESSAGE_INDEX_OUT_OF_BOUND = "Index is out of bound!";
    public static final String MESSAGE_DUPLICATE_TASK = "The given task exists in the current list.";
    public static final String MESSAGE_INDEX_NUMBER_FORMAT = "Task's index must be a number!";

    public static final String MESSAGE_INVALID_DATE_FORMAT = "Your date and/or time is invalid!";
    public static final String MESSAGE_INVALID_DATE_PERIOD = "%s -> %s is an invalid time period!";
}
