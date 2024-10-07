package exceptions;

/**
 * The ExceptionErrorMessage class holds the possible exceptions to
 * print to the user in Ui.
 *
 * @author Tan Ping Hui
 */
public class ExceptionErrorMessage {
    public static final String INVALID_DATE_FORMAT = "Invalid Date Format (Use YYYY-MM-DD)";
    public static final String EMPTY_COMMAND_MESSAGE = "No command given";
    public static final String INVALID_COMMAND_MESSAGE = "Unrecognised or incomplete command";
    public static final String INVALID_TASK_NUMBER_MESSAGE = "This task does not exist";
    public static final String INVALID_TASK_NUMBER_FORMAT_MESSAGE = "The index of the task must be an integer";
}
