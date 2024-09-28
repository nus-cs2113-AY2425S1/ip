package medea.command.create.task;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;
import medea.command.Command;

/**
 * An abstract class representing a command to create a task.
 * Subclasses must implement the {@link #addTask(TaskList)} method
 * to define how specific types of tasks are added.
 */
public abstract class TaskCommand extends Command {

    /**
     * Adds a task to the specified TaskList.
     *
     * @param tasks the TaskList to which the task will be added
     * @return a string representation of the created task
     */
    protected abstract String addTask(TaskList tasks);

    /**
     * Executes the task creation command, adding the task to the TaskList,
     * and displaying a confirmation message to the user.
     *
     * @param tasks the TaskList to which the task will be added
     * @param ui the user interface for displaying messages
     * @param storage the storage system for saving task data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String createdTask = addTask(tasks);
        int taskSize = tasks.getSize();

        String message = String.format("Noted. I've added this task:%n  %s%n" +
                                       "Now you have %d tasks in the list.", createdTask, taskSize);
        ui.showMsg(message);
    }
}
