package bob.command;
import bob.task.Deadline;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

/**
 * Represents a command to add a deadline task.
 * It parses the input to retrieve the description and deadline
 * and adds the deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String input;
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String DEADLINE_BY = " /by ";

    /**
     * Constructs an AddDeadlineCommand object with the specified user input.
     *
     * @param command The user's input string representing the deadline command.
     */
    public AddDeadlineCommand(String command) {
        this.input = command;
    }

    /**
     * Executes the command to add a deadline task.
     * It splits the input string into the task description and deadline,
     * adds the deadline task to the task list, prints it via the UI,
     * and stores it in the storage.
     *
     * @param tasks   The TaskList where the new task will be added.
     * @param ui      The Ui object to handle the user interface output.
     * @param storage The Storage object to save the task into the persistent storage.
     * @throws ArrayIndexOutOfBoundsException If the input format is incorrect (for example, missing deadline).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] components = this.input.split(DEADLINE_BY);
        String description = components[0].substring(COMMAND_DEADLINE.length()).trim();
        if (description.isEmpty()) {
            ui.printEmptyDescription("deadline");
        } else {
            try {
                String deadlineBy = components[1];
                Task newTask = new Deadline(description, deadlineBy);
                tasks.addTask(newTask);
                ui.printAddedTask(tasks);
                storage.appendTask(newTask);
                storage.save(tasks.getTaskList());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printInvalidDeadline();
            }
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
