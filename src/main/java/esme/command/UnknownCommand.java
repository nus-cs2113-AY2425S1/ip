package esme.command;

import esme.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(Ui ui) {
        super(ui);
    }

    @Override
    public void run() {
        super.ui.handleUnknownCommand();
    }
}
