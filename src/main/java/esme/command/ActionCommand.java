package esme.command;

import esme.Ui;

public class ActionCommand extends Command {
    private final String[] words;

    public ActionCommand(Ui ui, String[] words) {
        super(ui);
        this.words = words;
    }

    @Override
    public void run() {
        ui.handleTaskStatus(words);
    }
}
