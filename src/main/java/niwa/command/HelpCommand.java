package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code HelpCommand} class provides a guide to available commands.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_GUIDE = "help: Print this guide.";
    public static final String[] COMMAND_KEYWORDS = {};

    protected List<Command> commands; // List of available commands

    /**
     * Sets the list of commands for the help guide.
     *
     * @param commands List of available commands.
     */
    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length; // Validate argument count
    }

    /**
     * Prints a user guide with available commands.
     *
     * @return A {@code CommandResult} containing the user guide messages.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        for (Command command : commands) {
            try {
                // Get the command guide message using reflection
                String guide = (String) command.getClass().getField("COMMAND_GUIDE").get(null);
                messages.add(guide); // Add the guide message to the list
            } catch (IllegalAccessException | NoSuchFieldException e) {
                messages.add("Invalid command: " + e.getMessage());
            }
        }

        return new CommandResult(messages);
    }
}
