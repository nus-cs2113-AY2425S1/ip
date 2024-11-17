package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;
import aegis.task.Task;

/**
 * The AddCommand class handles the addition of a new task to the task list.
 * It saves the updated task list to storage and provides feedback to the user.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the task to the task list, displaying confirmation to the user,
     * and saving the updated task list to storage.
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui used for interacting with the user.
     * @param storage The Storage used to save the updated task list.
     * @throws AegisException If an error occurs while saving the tasks to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException {
        tasks.addTask(task);
        System.out.println();
        System.out.printf(" New task added: %n   %s%n", task);
        System.out.printf(" %d tasks needed to be done. Let me assist you!%n", tasks.getTasks().size());
        System.out.println();
        storage.save(tasks.getTasks());
    }
}
