package esme.command;

import esme.ui.Ui;

/**
 * Represents the command that handles the user's request to print the task list.
 * When run, it will print the task list to the user.
 */
public class PrintCommand extends Command {
    private String[] words;
    private String input;

    public PrintCommand(Ui ui, String[] words, String input) {
        super(ui);
        this.words = words;
        this.input = input;
    }

    /**
     * Executes the command by printing the task list to the user.
     */
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

