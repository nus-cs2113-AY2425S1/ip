package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

/**
 * Represents the command to list all tasks.
 * This command displays the entire task list to the user.
 */
public class ListCommand implements Command {

    /**
     * Executes the <code>ListCommand</code> by displaying the entire list of tasks
     * to the user through the user interface.
     *
     * @param taskList the list of tasks to be displayed.
     * @param ui the user interface for displaying the task list.
     * @param storage the storage system (not used in this command).
     * @throws CharlieExceptions if an error occurs during the command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieExceptions {
        ui.displayList(taskList);
    }
}
