package main.java;

/**
 * Represents the command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Perform the add command by adding the task to the task list,
     * saving the task list and notifying the user.
     *
     * @param tasks The task list.
     * @param ui The UI component which the user will see.
     * @param storage The storage component to save the updated task list.
     * @throws KenChatException If there is an error when saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KenChatException {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.getSize());
    }
}
