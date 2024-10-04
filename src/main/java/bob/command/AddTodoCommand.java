package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.task.ToDo;
import bob.ui.Ui;
import bob.storage.Storage;

/**
 * Represents a command to add a "To-Do" task.
 * It parses the input to retrieve the description and adds the to-do task to the task list.
 */
public class AddTodoCommand extends Command {
    private static final String COMMAND_TODO = "todo";
    private final String input;

    /**
     * Constructs an AddTodoCommand object with the specified user input.
     *
     * @param command The user's input string representing the to-do command.
     */
    public AddTodoCommand(String command) {
        this.input = command;
    }

    /**
     * Executes the command to add a "To-Do" task.
     * It extracts the task description from the input string, adds the task to the task list,
     * prints it via the UI, and stores it in the storage.
     *
     * @param tasks   The TaskList where the new task will be added.
     * @param ui      The Ui object to handle the user interface output.
     * @param storage The Storage object to save the task into the persistent storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String description = this.input.substring(COMMAND_TODO.length()).trim();

        if (description.isEmpty()) {
            ui.printEmptyDescription("todo");
        } else {
            Task newTask = new ToDo(description);
            tasks.addTask(newTask);
            ui.printAddedTask(tasks);
            storage.appendTask(newTask);
            storage.save(tasks.getTaskList());
        }
    }

    /**
     * Indicates whether this command should exit the program.
     *
     * @return false, as this command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
