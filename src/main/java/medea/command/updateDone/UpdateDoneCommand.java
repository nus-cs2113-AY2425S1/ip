package medea.command.updateDone;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;
import medea.command.Command;

/**
 * Represents a command to update the completion status of a task.
 * This command allows marking a task as done or undone based on the user's input.
 */
public class UpdateDoneCommand extends Command {

    /** The status indicating whether the task is done or not. */
    private boolean isDone;

    /**
     * Constructs an UpdateDoneCommand with the specified task index and done status.
     *
     * @param index the index of the task to update
     * @param isDone true to mark the task as done, false to mark it as undone
     */
    public UpdateDoneCommand(int index, boolean isDone) {
        super(index);
        this.isDone = isDone;
    }

    /**
     * Executes the command to update the task's done status in the TaskList.
     *
     * @param tasks the TaskList containing the task to update
     * @param ui the user interface for displaying messages
     * @param storage the storage system for saving task data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String updatedTask = tasks.updateTaskDoneStatus(this.getTaskIndex(), isDone);

        String message = isDone ? "Got it! I've marked this task as done" : "Ok. I've unmarked this task";
        ui.showMsg(String.format("%s:\n  %s", message, updatedTask));
    }
}
