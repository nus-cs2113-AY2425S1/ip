package ran.command;

/**
 * Documents the different types of commands.
 */
public enum CommandType {
    /**
     * Terminating command
     */
    TERMINATE,

    /**
     * Show list command
     */
    LIST,

    /**
     * Add todo task command
     */
    TODO,

    /**
     * Add deadline task command
     */
    DEADLINE,

    /**
     * Add event task command
     */
    EVENT,

    /**
     * Set task as done command
     */
    MARK,

    /**
     * Set tasks as not done command
     */
    UNMARK,

    /**
     * Delete task command
     */
    DELETE,

    /**
     * Find tasks with keyword command
     */
    FIND,

    /**
     * Show help message to user
     */
    HELP,

    /**
     * Catch-all for all invalid commands
     */
    UNDEFINED
}
