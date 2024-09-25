package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

/**
 * The DeleteCommand class handles the deletion of a task from the task list.
 * It removes the specified task, saves the updated task list to storage, and provides feedback to the user.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DeleteCommand by removing the task at the specified index from the task list,
     * displaying confirmation to the user, and saving the updated task list to storage.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param ui      The Ui used for interacting with the user.
     * @param storage The Storage used to save the updated task list.
     * @throws AegisException If the task index is invalid or if an error occurs while saving the tasks to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException {
        if (taskIndex < 0 || taskIndex >= tasks.getTasks().size()) {
            throw new AegisException("Invalid task number");
        }
        System.out.println();
        System.out.printf(" Noted. I've removed this task:%n   %s%n", tasks.getTasks().get(taskIndex));
        tasks.deleteTask(taskIndex);
        System.out.printf(" Now you have %d tasks in the list.%n", tasks.getTasks().size());
        System.out.println();
        storage.save(tasks.getTasks());
    }
}
