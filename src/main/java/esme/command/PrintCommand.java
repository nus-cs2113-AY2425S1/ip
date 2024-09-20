package esme.command;

import esme.ui.Ui;

/**
 * Represents the command that handles the user's request to print the task list.
 * When run, it will print the task list to the user.
 */
public class PrintCommand extends Command {
    public PrintCommand(Ui ui) {
        super(ui);
    }

    /**
     * Executes the command by printing the task list to the user.
     */
    @Override
    public void run() {
        super.ui.printTaskList();
    }
}

