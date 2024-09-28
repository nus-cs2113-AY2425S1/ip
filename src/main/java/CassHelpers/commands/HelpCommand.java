package CassHelpers.commands;

import CassHelpers.util.UI;

public class HelpCommand implements Command {
    private final UI ui;

    public HelpCommand(UI ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.displayHelpInstructions();
    }
}
