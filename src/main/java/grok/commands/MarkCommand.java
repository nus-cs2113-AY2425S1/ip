package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * This command allows the user to specify the task number of a task to mark it completed
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     * @param taskIndex The index of the task to be marked as done (1-based index).
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
    }

    /**
     * Executes the mark command, marking the specified task as done.
     * If an error occurs (e.g., invalid task index), it displays an error message.
     * @param tasks the TaskList containing the tasks.
     * @param ui the ui used to display messages to the user.
     * @param storage the Storage used to handle task persistence.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(taskIndex).markAsDone();
            ui.showLine();
            ui.showMsg("Nice! I've marked this task as done:");
            ui.showMsg(tasks.getTask(taskIndex).toString());
            ui.showLine();
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Error marking task.");
        }
    }

    /**
     * Indicates that this command does not terminate the program.
     * @return false as the mark command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
