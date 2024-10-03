package cristiano.commands;

import cristiano.exceptions.RonaldoException;

/**
 * The Parser class is the main class responsible for interpreting user input commands
 * and returning the corresponding Command object to be executed by the Ronaldo chatbot.
 * It handles predefined commands and manages special hidden commands related to certain input keywords.
 */
public class Parser {

    /**
     * A boolean flag that determines whether hidden commands should be processed.
     * When set to true, hidden commands like those containing "messi" or "siu" can be triggered.
     */
    public static boolean isHidden = true;

    /**
     * Parses the user input and returns the corresponding Command object based on the input.
     * This method also checks for hidden commands based on certain keywords.
     * Resets, isHidden so hidden commands can be triggered again.
     *
     * @param commands The user input string that contains the command to be parsed.
     * @return The Command object that matches the user input.
     * @throws RonaldoException If the input is empty or the command format is invalid.
     */
    public static Command parse(String commands) throws RonaldoException {
        if (commands.trim().isEmpty()) {
            throw new RonaldoException("Empty");
        } else if (commands.contains("messi") && isHidden) {
            return new HiddenCommand(commands);
        } else if (commands.contains("siu") && isHidden) {
            return new HiddenCommand(commands);
        }
        isHidden = true;
        String[] input = commands.split(" ", 2);
        String command = input[0].trim();
        switch (command) {
        case "help":
            return new HelpCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
        case "unmark":
            return new MarkCommand(input);
        case "list":
            return new ListCommand();
        case "event":
        case "todo":
        case "deadline":
            return new AddCommand(commands);
        case "delete":
            return new DeleteCommand(commands);
        case "find":
            return new FindCommand(commands);
        default:
            throw new RonaldoException("Format");
        }
    }
}
