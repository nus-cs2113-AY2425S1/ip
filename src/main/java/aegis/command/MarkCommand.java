package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

/**
 * The MarkCommand class handles marking a task as done or not done in the task list.
 * It updates the task's status and saves the changes to storage.
 */
public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMark;

    /**
     * Constructs a MarkCommand with the specified task index and mark status.
     *
     * @param taskIndex The index of the task to be marked or unmarked.
     * @param isMark    True to mark the task as done; false to unmark it.
     */
    public MarkCommand(int taskIndex, boolean isMark) {
        this.taskIndex = taskIndex;
        this.isMark = isMark;
    }

    /**
     * Executes the MarkCommand by marking or unmarking the specified task in the task list,
     * displaying the result to the user, and saving the updated task list to storage.
     *
     * @param tasks   The TaskList containing the task to be marked or unmarked.
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
        if (isMark) {
            tasks.getTasks().get(taskIndex).markAsDone();
            System.out.printf(" I've marked this task as done:%n   %s%n", tasks.getTasks().get(taskIndex));
        } else {
            tasks.getTasks().get(taskIndex).unmarkAsDone();
            System.out.printf(" I've marked this task as not done yet:%n   %s%n", tasks.getTasks().get(taskIndex));
        }
        System.out.println();
        storage.save(tasks.getTasks());
    }
}
