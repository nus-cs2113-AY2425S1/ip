package commands;

import data.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(String cmd, String args) {
        super(cmd, args);
    }

    /**
     * Deletes the task at the given index in the TaskList.
     * @param tasks TaskList object that stores all the tasks currently stored
     * @param ui Ui object used for interacting with the user
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui) {
        tasks.deleteTask(Integer.parseInt(args));
    }

}
