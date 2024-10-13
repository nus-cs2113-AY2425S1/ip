package main.java;

public class KenChatException extends Throwable {
    public KenChatException(String message) {
        super(message);
    }
    public static String getEmptyDescriptionMessage(String command) {
        return "ERROR!!! Enter a description for the command. Use: " + command + " <descption>";
    }

    public static String getUnknownCommandMessage() {
        return "Wrong command/Please do not have space(s) before a command! Use <help> for full list of commands. ";
    }

    public static String getInvalidDeadlineFormatMessage() {
        return "Wrong deadline format. Use: deadline <description> /by <date/time>";
    }

    public static String getInvalidEventFormatMessage() {
        return "Wrong event format. Use: event <description> /from <start_time> /to <end_time>";
    }

    public static String getTaskNotExistMessage() {
        return "No task exist.";
    }

    public static String getInvalidTaskNumberMessage() {
        return "Please enter a valid task number. Use: mark/unmark <number(s)>";
    }

    public static String getTaskNumberDoesNotExistMessage() {
        return "That task number does not exist. Use <list> to see full task list.";
    }

    public static String getEmptyTaskNumberMessage(String action) {
        return "Task number is missing!! Please specify the task number to " + action + ".";
    }

    public static String emptyCommand() {
        return "Empty command! Use <help> for full list of commands.";
    }
}
