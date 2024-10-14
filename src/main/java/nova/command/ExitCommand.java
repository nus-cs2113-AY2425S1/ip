package nova.command;

import nova.Ui;

/**
 * Represents a command to exit the application.
 * This command allows the user to terminate the session gracefully.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Executes the Exit command by displaying a farewell message
     */
    public static void execute() {
        Ui.displayMessage(BYE_MESSAGE);
    }
}
