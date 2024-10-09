package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command allows the user to specify a task by its index for removal.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a DeleteCommand with the specified task index.
     * @param taskIndex The index of the task to be deleted (1-based index, adjusted to 0-based internally).
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;  // Convert 1-based index to 0-based index
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
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                ui.showError("Invalid task index. Please provide a valid index.");
                return;
            }

            ui.showLine();
            ui.showMsg("Noted. I've removed this task:");
            ui.showMsg(tasks.deleteTask(taskIndex).toString());
            ui.showMsg("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            ui.showLine();

            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Error deleting task: " + e.getMessage());
        }
    }

    /**
     * Indicates whether this command will terminate the program.
     * @return false as the delete command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
