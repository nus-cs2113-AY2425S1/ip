package esme.command;

import esme.ui.Ui;

public class EntryCommand extends Command {
    private final String command;
    private final String input;

    public EntryCommand(Ui ui, String command, String input) {
        super(ui);
        this.command = command;
        this.input = input;
    }

    @Override
    public void run() {
        super.ui.addTaskToList(command, input);
    }
}
