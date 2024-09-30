package CassHelpers.commands;

import CassHelpers.util.UI;

/**
 * Command class responsible for displaying help instructions to the user.
 * It invokes the UI component to show the available commands and their usage.
 */
public class HelpCommand implements Command {
    private final UI ui;

    /**
     * Constructs a new HelpCommand.
     *
     * @param ui The UI object that will be used to display help instructions.
     */
    public HelpCommand(UI ui) {
        this.ui = ui;
    }

    /**
     * Executes the command by displaying the help instructions.
     */
    @Override
    public void execute() {
        ui.displayHelpInstructions();
    }
}
