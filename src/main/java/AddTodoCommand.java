/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private String description;
    /**
     * Constructs an AddTodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }
    /**
     * Executes the command to add the todo task to the task list.
     *
     * @param tasks  The task list to which the task will be added.
     * @param ui     The user interface for displaying messages to the user.
     * @param storage The storage for saving tasks to a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.getTaskCount());
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
