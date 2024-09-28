package medea.command.update.done;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;
import medea.command.Command;

/**
 * Represents a command to update the completion status of a task.
 * This command allows marking a task as done or undone based on the user's input.
 */
public abstract class UpdateDoneCommand extends Command {

    /**
     * Constructs an UpdateDoneCommand with the specified task index and done status.
     *
     * @param index the index of the task to update
     */
    public UpdateDoneCommand(int index) {
        super(index);
    }

    /**
     * Indicates whether the command is a Mark or Unmark command.
     *
     * @return isDone the new status of the given task's completion
     */
    protected abstract boolean isDone();

    /**
     * Executes the command to update the task's done status in the TaskList.
     *
     * @param tasks the TaskList containing the task to update
     * @param ui the user interface for displaying messages
     * @param storage the storage system for saving task data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String updatedTask = tasks.updateTaskDoneStatus(this.getTaskIndex(), isDone());
        String message = isDone() ? "Got it! I've marked this task as done" : "Ok. I've unmarked this task";
        ui.showMsg(String.format("%s:\n  %s", message, updatedTask));
    }
}
