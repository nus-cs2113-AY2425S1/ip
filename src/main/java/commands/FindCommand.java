package commands;

import data.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public FindCommand(String cmd, String args) {
        super(cmd, args);
    }

    /**
     * @param tasks TaskList object that stores all the tasks currently stored
     * @param ui Ui object used for interacting with the user
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui) {
        ui.printList(tasks.findTasks(args));
    }
}
