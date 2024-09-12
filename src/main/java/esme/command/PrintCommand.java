package esme.command;

import esme.ui.Ui;

public class PrintCommand extends Command {
    public PrintCommand(Ui ui) {
        super(ui);
    }

    @Override
    public void run() {
        super.ui.printTaskList();
    }
}
