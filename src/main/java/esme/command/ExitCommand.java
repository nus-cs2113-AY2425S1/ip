package esme.command;

import esme.Ui;

public class ExitCommand extends Command {
    public ExitCommand(Ui ui) {
        super(ui);
    }

    @Override
    public void run() {
        super.ui.farewell();
    }
}
