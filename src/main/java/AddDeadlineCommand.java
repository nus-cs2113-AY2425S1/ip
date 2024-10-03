import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /**
     * Constructs an AddDeadlineCommand with the specified input.
     *
     * @param input The user input containing the task description and deadline.
     */
    public AddDeadlineCommand(String input) {
        String[] parts = input.split("/by");
        this.description = parts[0].trim().substring(9).trim();
        this.by = parts[1].trim();
    }
    /**
     * Executes the command to add the deadline task to the task list.
     *
     * @param tasks  The task list to which the task will be added.
     * @param ui     The user interface for displaying messages to the user.
     * @param storage The storage for saving tasks to a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDateTime.parse(by, INPUT_FORMAT); // Validate the date format
            Task newTask = new Deadline(description, by);
            tasks.add(newTask);
            ui.showAddedTask(newTask, tasks.getTaskCount());
            storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
        } catch (DateTimeParseException e) {
            ui.showError("Invalid date format. Please use yyyy-MM-dd HHmm format.");
        }
    }
}
