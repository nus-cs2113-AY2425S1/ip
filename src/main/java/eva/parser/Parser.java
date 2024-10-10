package eva.parser;

import eva.exception.EvaException;

public class Parser {

    /**
     * Extracts and returns the command from the given input string.
     * Input string is converted to lowercase for case-insensitive matching.
     * If the command is not recognised, a {@code EvaException} is thrown.
     *
     * @param line The input string from the user containing the command and description.
     * @return The extracted command.
     * @throws EvaException if the command is not recognised.
     */
    public String getCommand(String line) throws EvaException {

        String command = line.split(" ")[0].toLowerCase();

        switch (command) {
        case "list":
        case "mark":
        case "unmark":
        case "todo":
        case "deadline":
        case "event":
        case "delete":
        case "find":
        case "bye":
            return command;
        default:
            throw new EvaException("Sorry! I don't understand what '" + line + "' means.");
        }
    }

    /**
     * Parses the arguments from the input line based on the provided command.
     * This method will then split the input line into different argument based on
     * the command type.
     * If the command format is invalid or arguments are missing, a {@code EvaException} is thrown
     *
     * @param line The input string containing the command and description.
     * @param command The specific command extracted from {@link #getCommand(String)}.
     * @return An array of strings containing the parsed arguments.
     * @throws EvaException if the arguments are missing or improperly formatted for the given command.
     */
    public String[] parseArguments(String line, String command) throws EvaException {
        String[] args;

        switch (command) {
        case "mark":
        case "unmark":
        case "delete":
            return parseTaskCommands(line);
        case "todo":
            return parseTodoCommand(line);
        case "deadline":
            return parseDeadlineCommand(line);
        case "event":
            return parseEventCommand(line);
        case "find":
            return parseFindCommand(line);
        default:
            args = new String[]{};
            return args;
        }
    }

    /**
     * Parses the input line for the "find" command and returns the search keyword.
     *
     * @param line The line input from user.
     * @return A String array containing the keyword to search for tasks.
     * @throws EvaException If the search keyword is empty, indicating an invalid "find" command input.
     */
    private static String[] parseFindCommand(String line) throws EvaException {
        String subject = line.replaceFirst("find", "").trim();
        if (subject.isEmpty()) {
            throw new EvaException("Oops! You have to provide a keyword to search for!");
        }
        return new String[]{subject};
    }

    /**
     * Parses the input line for Event task and returns the description for the task.
     *
     * @param line The input from user
     * @return The description of Event task
     * @throws EvaException If there are missing parts in the description of task
     */
    private static String[] parseEventCommand(String line) throws EvaException {
        String[] eventParts = line.replaceFirst("event", "").split("/from|/to");
        if (eventParts.length < 3) {
            throw new EvaException("Oh no! The event command must have a description, a from time, " +
                    "and a to time. \nThe format should be event (name of task) /from (time) /to (time)" +
                    "\nPlease try again!");
        }
        return new String[]{eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()};
    }

    /**
     * Parses the input line for Deadline task and returns the description of task.
     *
     * @param line The line input from user.
     * @return The description of Deadline task.
     * @throws EvaException If there are missing parts in the description of the task.
     */
    private static String[] parseDeadlineCommand(String line) throws EvaException {
        String[] deadlineParts = line.replaceFirst("deadline", "").split("/by");
        if (deadlineParts.length < 2) {
            throw new EvaException("Oh no! The deadline command must have a description and a by time." +
                    "\nIt show be in this format: deadline (name of task) /by (time)." +
                    "\nPlease try again!");
        }
        return new String[]{deadlineParts[0].trim(), deadlineParts[1].trim()};
    }

    /**
     * Parses the input line for Todo Task and returns the description of the task.
     *
     * @param line The line input from user.
     * @return The description of the Todo task.
     * @throws EvaException If the description of task is empty.
     */
    private static String[] parseTodoCommand(String line) throws EvaException {
        String todoDesc = line.replaceFirst("todo", "").trim();
        if (todoDesc.isEmpty()) {
            throw new EvaException("Oops! The description of a todo cannot be empty. Please provide a task description.");
        }
        return new String[]{todoDesc};
    }

    /**
     * Parses the input line for "mark", "unmark" and "delete" commands and returns the task number.
     *
     * @param line The line input from user.
     * @return The task number for the command to proceed.
     * @throws EvaException If task number is not provided.
     */
    private static String[] parseTaskCommands(String line) throws EvaException {
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            throw new EvaException("Oops! You need to provide a task number after the command '" + parts[0] + "'.");
        }
        return new String[]{parts[1]};
    }
}
