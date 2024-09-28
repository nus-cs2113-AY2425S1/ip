package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

/**
 * Represents a command to list all tasks in the task management system.
 * This command retrieves and displays the current list of tasks to the user.
 */
public class ListCommand extends Command {

    /** The command word for the list command. */
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the list command, retrieving and displaying all tasks from the TaskList.
     *
     * @param tasks the TaskList containing the tasks to display
     * @param ui the user interface for displaying messages
     * @param storage the storage system (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = String.format("Here are the tasks in your list:%n%s", tasks);
        ui.showMsg(message);
    }
}
