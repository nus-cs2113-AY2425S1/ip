package esme.command;

import esme.ui.Ui;

public class ActionCommand extends Command {
    private final String[] words;

    public ActionCommand(Ui ui, String[] words) {
        super(ui);
        this.words = words;
    }

    @Override
    public void run() {
        if (words[0].equals("delete")) {
            ui.deleteTaskFromList(words);
        } else {
            ui.handleTaskStatus(words);
        }
    }
}
