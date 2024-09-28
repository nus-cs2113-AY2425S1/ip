package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command allows the user to specify a task by its index for removal.
 */
public class DeleteCommand extends Command {

    /** The command word for the delete command. */
    public static final String COMMAND_WORD = "delete";

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Executes the delete command, removing the specified task from the TaskList.
     *
     * @param tasks the TaskList from which the task will be deleted
     * @param ui the user interface for displaying messages
     * @param storage the storage system for saving task data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String deletedTask = tasks.deleteTask(this.getTaskIndex());
        int taskSize = tasks.getSize();

        String message = String.format("Noted. I've removed this task:%n  %s%nNow you have %d tasks in the list.", deletedTask, taskSize);
        ui.showMsg(message);
    }
}
