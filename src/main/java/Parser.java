/**
 * Provides utility methods for parsing user input commands.
 */
public class Parser {

    /**
     * Parses the command from user input.
     *
     * @param input the user input string
     * @return the command as a lowercase string, or an empty string if input is empty
     */
    public static String parseCommand(String input) {
        input = input.trim();
        if (input.isEmpty()) {
            return "";
        }
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            return input.toLowerCase();
        } else {
            return input.substring(0, spaceIndex).toLowerCase();
        }
    }

    /**
     * Extracts the task number from the input.
     *
     * @param input the user input string
     * @return the task number as an integer (0-indexed)
     */
    static int parseTaskNumber(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Parses the description of a ToDo task from the input.
     *
     * @param input the user input string
     * @return the description of the ToDo task
     */
    static String parseTodo(String input) {
        return input.substring(5).trim();
    }

    /**
     * Parses a Deadline task's description and deadline.
     *
     * @param input the user input string
     * @return an array containing the task description and deadline
     */
    public static String[] parseDeadline(String input) {
        String[] parts = input.replaceFirst("deadline ", "").split(" /by ");
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new String[]{description, by};
    }

    /**
     * Parses an Event task's description, start, and end times.
     *
     * @param input the user input string
     * @return an array containing the task description, start time, and end time
     */
    public static String[] parseEvent(String input) {
        String[] parts = input.replaceFirst("event ", "").split(" /from | /to ");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new String[]{description, from, to};
    }

    /**
     * Extracts the keyword from the input.
     *
     * @param input the user input string
     * @return the search keyword
     */
    public static String parseKeyword(String input) {
        String[] parts = input.split(" ", 2);
        return parts[1];
    }
}
