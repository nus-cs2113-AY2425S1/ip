/**
 * The Parser class is responsible for interpreting user input.
 * It extracts commands, task descriptions, and task indexes from the input string.
 */
public class Parser {

    /**
     * Parses the command from the user input.
     *
     * @param input The full user input string.
     * @return The command, which is the first word of the input string.
     */
    public String parseCommand(String input) {
        return input.split(" ")[0];  // Get the first word as the command
    }

    /**
     * Parses the task description from the user input based on the command.
     * Throws an AndyException if the description is empty.
     *
     * @param input The full user input string.
     * @param command The command for which the task description is being parsed.
     * @return The task description, trimmed of excess spaces.
     * @throws AndyException if the task description is empty.
     */
    public String parseTaskDescription(String input, String command) throws AndyException {
        String description = input.substring(command.length()).trim();
        if (description.isEmpty()) {
            throw new AndyException("Task description cannot be empty.");
        }
        return description;
    }

    /**
     * Parses the task index from the user input.
     * Throws an AndyException if the index is invalid or cannot be parsed.
     *
     * @param input The full user input string containing the task index.
     * @return The task index as an integer (0-based).
     * @throws AndyException if the task index is invalid or cannot be parsed.
     */
    public int parseTaskIndex(String input) throws AndyException {
        try {
            String[] parts = input.split(" ");
            return Integer.parseInt(parts[1]) - 1;  // Task index is usually the second word
        } catch (Exception e) {
            throw new AndyException("Invalid task index.");
        }
    }
}