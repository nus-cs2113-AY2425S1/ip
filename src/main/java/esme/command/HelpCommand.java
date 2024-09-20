package esme.command;

import esme.ui.Ui;

/**
 * Represents the command that handles the user's request for help.
 * When run, it will print a help message to the user.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand object.
     * 
     * @param ui The user interface of the application.
     */
    public HelpCommand(Ui ui) {
        super(ui);
    }

    /**
     * Executes the command by printing a help message to the user.
     */
    @Override
    public void run() {
        super.ui.printHelpMessage();
    }
}
