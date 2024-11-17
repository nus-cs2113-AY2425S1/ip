package commands;

import data.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    /**
     * Creates a new instance of the FindCommand class with the specified cmd and args.
     * @param cmd The name of the command
     * @param args The arguments of the command
     */
    public FindCommand(String cmd, String args) {
        super(cmd, args);
    }

    /**
     * Executes the list command on the given TaskList and Ui.
     * @param tasks TaskList object that stores all the tasks currently stored
     * @param ui Ui object used for interacting with the user
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui) {
        ui.printList(tasks.findTasks(args));
    }
}
