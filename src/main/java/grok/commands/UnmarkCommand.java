package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 * This command allows the user to specify the task number of a task to unmark it
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     * @param taskIndex The index of the task to be unmarked as not done (1-based index).
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
    }

    /**
     * Executes the unmark command, marking the specified task as not done.
     * If the task index is invalid, it displays an error message.
     * @param tasks the TaskList containing the tasks.
     * @param ui the ui used to display messages to the user.
     * @param storage the Storage used to handle task persistence.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            ui.showError("Invalid task number.");
            return;
        }

        tasks.getTask(taskIndex).markAsNotDone();
        ui.showLine();
        ui.showMsg("OK, I've marked this task as not done yet:");
        ui.showMsg(tasks.getTask(taskIndex).toString());
        ui.showLine();

        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Error saving tasks to file.");
        }
    }

    /**
     * Indicates that this command does not terminate the program.
     * @return false as the unmark command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
