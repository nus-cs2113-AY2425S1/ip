package esme.command;

import esme.ui.Ui;

public class DeleteCommand extends Command {
    private final String[] words;

    public DeleteCommand(Ui ui, String[] words) {
        super(ui);
        this.words = words;
    }

    @Override
    public void run() {
        ui.deleteTaskFromList(words);
    }
}
