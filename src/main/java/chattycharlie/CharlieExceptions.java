package chattycharlie;

import chattycharlie.commands.CommandType;

/**
 * Represents all the exceptions used in ChattyCharlie task management
 * This class extends {@link Exception} to handle specific error scenarios
 */
public class CharlieExceptions extends Exception{

    /**
     * Constructs a new <Code>CharlieExceptions</Code> with the specified detail message.
     *
     * @param message the message to describe the exception
     */
    public CharlieExceptions(String message){
        super(message);
    }

    /**
     * Creates an exception indicating that a description is missing fro the given command type
     *
     * @param command the type of command that is missing a description
     * @return a <code>CharlieExceptions</code> instance with a specified missing error
     */
    public static CharlieExceptions missingDescription(CommandType command) {
        return new CharlieExceptions("Oop, the task for " + command + " cannot be empty.");
    }

    /**
     * Creates an exception indicating that a deadline is missing for a deadline task type.
     *
     * @return a <code>CharlieExceptions</code> instance with a specific error message.
     */
    public static CharlieExceptions missingDeadline() {
        return new CharlieExceptions("When is this due?");
    }

    /**
     * Creates an exception indicating that the time is missing for a event task type.
     *
     * @return a <code>CharlieExceptions</code> instance with a specific error message.
     */
    public static CharlieExceptions missingTimes() {
        return new CharlieExceptions("Your event is missing or incomplete!");
    }

    /**
     * Creates an exception indicating that the command type cannot be identified.
     *
     * @return a <code>CharlieExceptions</code> instance with a specific error message.
     */
    public static CharlieExceptions cannotIdentifyCommandType() {
        return new CharlieExceptions("Cannot identify command type.");
    }
}
