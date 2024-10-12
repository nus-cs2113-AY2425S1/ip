package exception;

public class EchoException {
    private EchoException() {}

    /**
     * Returns an error message for missing todo task.
     *
     * @return A string explaining error.
     */
    public static String todoDescriptionMissing() {
        return "You must provide a description for the todo task.";
    }

    /**
     * Returns an error message for missing deadline task.
     *
     * @return A string explaining error.
     */
    public static String deadlineDescriptionMissing() {
        return "You must provide a description for the deadline task.";
    }

    /**
     * Returns an error message for missing event task.
     *
     * @return A string explaining error.
     */
    public static String eventDescriptionMissing() {
        return "You must provide a description for the event task.";
    }

    /**
     * Returns an error message for unknown command.
     *
     * @return A string explaining error.
     */
    public static String unknownCommand() {
        return "I'm sorry, but I don't know what that means.";
    }

    /**
     * Returns an error message for task number not in list.
     *
     * @return A string explaining error.
     */
    public static String taskNumberOutOfRange() {
        return "Task is out of range.";
    }

    /**
     * Returns an error message for non-integer inputs.
     *
     * @return A string explaining error.
     */
    public static String invalidTaskNumberFormat() {
        return "Give an integer only.";
    }

    public static String invalidDeadlineFormat() {
        return "Invalid date format! Please enter the due date in the format yyyy-MM-dd.";
    }

    public static String unmarkIndexEmpty() {
        return "Please specify a task number after 'unmark'.";
    }

    public static String markIndexEmpty() {
        return "Please specify a task number after 'mark'.";
    }

    public static String findInputEmpty() {
        return "Please provide a keyword to find.";
    }

    public static String deleteInputEmpty() {
        return "Please provide a task number to delete.";
    }

    public static String fileNotFound() {
        return "File cannot be found.";
    }
}
