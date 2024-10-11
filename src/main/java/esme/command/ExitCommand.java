package esme.command;

import esme.ui.Ui;

/**
 * Represents the command that handles the user's request to exit the application.
 * When run, it will print a farewell message to the user and exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an instance of the command.
     *
     * @param ui The user interface to be used for output.
     */
    public ExitCommand(Ui ui) {
        super(ui);
    }

    /**
     * Executes the command by printing a farewell message to the user.
     */
    @Override
    public void run() {
        super.ui.farewell();
    }
}
