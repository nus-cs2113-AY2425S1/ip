/**
 * Parses user input and validates task-related arguments for the KBot application.
 * This class contains methods for splitting input commands,
 * validating task numbers, and checking the correctness of
 * deadline and event arguments.
 */
public class Parser {

    /**
     * Splits the user input into command and argument parts.
     *
     * @param input The user input string to be parsed.
     * @return An array of strings where the first element is the command
     *         and the second element is the argument (if any).
     */
    public String[] parseInput(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parses a task number from a string argument and adjusts it to zero-based index.
     *
     * @param argument The string representation of the task number.
     * @return The task number adjusted to a zero-based index.
     * @throws KBotException If the argument cannot be parsed as an integer.
     */
    public int parseTaskNumber(String argument) throws KBotException {
        try {
            return Integer.parseInt(argument) - 1; // Adjust for zero-based index
        } catch (NumberFormatException e) {
            throw new KBotException("Invalid task number.");
        }
    }

    /**
     * Validates the deadline argument to ensure it contains a valid description and date.
     *
     * @param argument The argument string representing the deadline task.
     * @throws KBotException If the argument does not contain the required "/by " keyword.
     */
    public void validateDeadlineArgument(String argument) throws KBotException {
        if (!argument.contains("/by ")) {
            throw new KBotException("The deadline description or date is missing.");
        }
    }

    /**
     * Validates the event argument to ensure it contains valid start and end times.
     *
     * @param argument The argument string representing the event task.
     * @throws KBotException If the argument does not contain the required "/from "
     *                       and "/to " keywords.
     */
    public void validateEventArgument(String argument) throws KBotException {
        if (!argument.contains("/from ") || !argument.contains("/to ")) {
            throw new KBotException("The event description or timing is missing.");
        }
    }
}
