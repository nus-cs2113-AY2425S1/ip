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
        if (words[0].equalsIgnoreCase("delete")) {
            super.ui.deleteTaskFromList(words);
        } else {
            super.ui.handleTaskStatus(words);
        }
    }
}
