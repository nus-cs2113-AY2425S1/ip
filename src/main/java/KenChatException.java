package main.java;

/**
 * Exception class for the KenChat application
 */
public class KenChatException extends Exception {

    public KenChatException(String message) {
        super(message);
    }

    /**
     * Returns an error message because of an empty description for a specific command
     * @param command The command that has the missing description.
     * @return The error message.
     */
    public static String getEmptyDescriptionMessage(String command) {
        return "Description is missing! Enter a description for the command. Use: " + command + " <description>";
    }

    public static String getUnknownCommandMessage() {
        return "Wrong Command! Use <help> for full list of commands. ";
    }

    public static String getInvalidDeadlineFormatMessage() {
        return "Wrong deadline format. Use: deadline <description> /by <date> Note: <date> can be date and/or time.";
    }

    public static String getInvalidEventFormatMessage() {
        return "Wrong event format. Use: event <description> /from <start_time> /to <end_time> Note: <start_time> and <end_time> can be date and/or time.";
    }

    public static String getTaskNotExistMessage() {
        return "No task exists.";
    }

    public static String getInvalidTaskNumberMessage() {
        return "Please enter a valid task number. Use: mark/unmark/delete <number(s)>";
    }

    public static String getTaskNumberDoesNotExistMessage() {
        return "That task number does not exist. Use <list> to see full task list.";
    }

    /**
     * Returns an error message because of a missing task number for Mark/Unmark/Delete command.
     *
     * @param action The command that is supposed to carry out the action of Mark/Unmark/Delete.
     * @return The error message.
     */
    public static String getEmptyTaskNumberMessage(String action) {
        return "Task number is missing!! Please specify the task number to " + action + ".";
    }

    public static String emptyCommand() {
        return "Empty command!! Use <help> for full list of commands.";
    }

    public static String multipleSpaces() {
        return "Too many spaces between words! Ensure each word is separated by only a space.";
    }
    public static String directoryCreationFailure() {
        return "Failed to create directory.";
    }
}
