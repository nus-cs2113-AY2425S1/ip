package ran.exception;

import ran.command.CommandType;

/**
 * Represents a exception for the Ran chatbot when trying to run a command with invalid arguments.
 * Inherits from base <code>RanException</code> class.
 */
public class MissingArgumentException extends RanException {
    private CommandType type;

    /**
     * Constructor for a <code>MissingArgumentException</code> object.
     *
     * @param type CommandType (enum type) representing the type of command throwing this exception
     */
    public MissingArgumentException(CommandType type) {
        this.type = type;
    }

    /**
     * Getter for the <code>CommandType</code> of the exception.
     *
     * @return CommandType of the exception
     */
    public CommandType getType() {
        return type;
    }
}
