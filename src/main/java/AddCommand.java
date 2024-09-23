/**
 * The AddCommand class represents a command to add a todo task to a task list.
 * It processes user input to extract the task name and executes the command
 * to add the task.
 */
public class AddCommand extends Command {
    private String taskName;

    /**
     * Constructs an AddCommand with the specified user input.
     * The task name is extracted from the user input string.
     *
     * @param userInput The input string from the user, expected to start with "todo ".
     */

    public AddCommand(String userInput) {
        taskName = userInput.substring("todo ".length());
    }

    /**
     * Executes the command to add a todo task to the task list.
     * It creates a new Todo object, adds it to the task list, displays a message to the user,
     * and saves the updated task list to storage.
     *
     * @param tasks The TaskList object to which the task will be added.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save the task list.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task task = new Todo(taskName);
        tasks.addTask(task);
        ui.showTodoAdded(tasks);
        storage.save(tasks);
    }
}
