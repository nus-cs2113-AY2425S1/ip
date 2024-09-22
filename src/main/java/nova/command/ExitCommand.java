package nova.command;

import nova.Ui;

/**
 * Represents a command to exit the application.
 * This command allows the user to terminate the session gracefully.
 */
public class ExitCommand extends Command {

    /**
     * The command word for the Exit command.
     */
    public static final String COMMAND_WORD = "bye";

    /**
     * The message displayed to the user upon exiting.
     */
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Executes the Exit command by displaying a farewell message
     * and returning true to indicate the application should close.
     *
     * @return true to signal that the application should terminate.
     */
    public static boolean execute() {
        Ui.displayMessage(BYE_MESSAGE);
        return true;
    }
}
