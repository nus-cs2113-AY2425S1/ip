package esme.command;

import esme.ui.Ui;

/**
 * Represents the command that handles the user's request that is not recognized.
 * When run, it will print an error message to the user asking them to try again.
 */
public class UnknownCommand extends Command {
    public UnknownCommand(Ui ui) {
        super(ui);
    }

    /**
     * Executes the command by printing an error message to the user asking them to try again.
     */
    @Override
    public void run() {
        super.ui.handleUnknownCommand();
    }
}
