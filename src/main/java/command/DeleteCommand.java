package command;

import data.Storage;
import exceptions.IrisException;
import task.Task;
import task.TaskList;

/**
 * The DeleteCommand class handles the deletion of tasks from the task list.
 * It identifies the task to be removed and updates the task list accordingly.
 *
 * @author Tan Ping Hui
 */
public class DeleteCommand extends Command {
    private final String description;

    /**
     * Constructs a DeleteCommand object with the specified task description.
     *
     * @param description The description of the task to be deleted.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to delete a task from the task list.
     * It identifies the task based on the description and removes it.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param storage The storage (not used in this command but included for method signature).
     * @throws IrisException If the task cannot be found or if an error occurs during deletion.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws IrisException {
        Task taskToDelete = Parser.getTaskNum(tasks, description);
        tasks.remove(taskToDelete);
        printDeleteTaskMessage(taskToDelete, tasks.size());
    }

    /**
     * Prints a message indicating that a task has been deleted from the task list.
     *
     * @param taskToDelete The task that was removed.
     * @param size The current size of the task list after deletion.
     */
    private static void printDeleteTaskMessage(Task taskToDelete, int size) {
        System.out.println("Noted. I've removed this task:\n"
                + taskToDelete
                + "\nNow you have "
                + size
                + " tasks in the list");
    }
}
