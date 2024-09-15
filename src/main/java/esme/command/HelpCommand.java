package esme.command;

import esme.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand(Ui ui) {
        super(ui);
    }

    public void run() {
        super.ui.printHelpMessage();
    }
}
