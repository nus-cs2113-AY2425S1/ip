package esme.command;

import esme.ui.Ui;

public class PrintCommand extends Command {
    private String[] words;
    private String input;

    public PrintCommand(Ui ui, String[] words, String input) {
        super(ui);
        this.words = words;
        this.input = input;
    }

    @Override
    public void run() {
        if (words[0].equals("list")) {
            super.ui.printTaskList();
        } else if (words[0].equals("find")) {
            super.ui.printTaskFound(input);
        } else {
            super.ui.printTasksIn(words);
        }
    }
}
