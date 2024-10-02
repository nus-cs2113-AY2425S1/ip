import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the command to add a new task to the task list.
 * Supports adding ToDo, Deadline, and Event types.
 */
public class AddCommand extends Command {
    private final String taskType;
    private final String description;

    /**
     * Constructs an AddCommand with the specified task type and description.
     *
     * @param taskType   The type of task to add (e.g., todo, deadline, event).
     * @param description The description of the task.
     */
    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * Parses a string into a LocalDateTime.
     *
     * @param dateTimeString The string to parse, in the format yyyy-MM-dd HHmm.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws BebeException if the format is incorrect.
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws BebeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new BebeException("Invalid date/time format. Use yyyy-MM-dd HHmm.");
        }
    }


    /**
     * Executes the add command by adding a new task to the task list.
     * Depending on the task type, it will create a ToDo, Deadline, or Event.
     *
     * @param tasks   The list of tasks where the new task will be added.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage object to save tasks.
     * @throws BebeException if an invalid task type or format is provided.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        Task task;
        try {
            switch (taskType) {
                case "todo":
                    task = new Todo(description);
                    break;
                case "deadline":
                    String[] parts = description.split(" /by ");
                    if (parts.length < 2) throw new BebeException("The deadline command requires a /by argument.");
                    LocalDateTime by = parseDateTime(parts[1]);
                    task = new Deadline(parts[0], by);
                    break;
                case "event":
                    String[] eventParts = description.split(" /from | /to ");
                    if (eventParts.length < 3) throw new BebeException("The event command requires /from and /to arguments.");
                    LocalDateTime from = parseDateTime(eventParts[1]);
                    LocalDateTime to = parseDateTime(eventParts[2]);
                    task = new Event(eventParts[0], from, to);
                    break;
                default:
                    throw new BebeException("Unknown task type.");
            }
            tasks.addTask(task);
            storage.save(tasks.getTasks());

            // Show confirmation of added task
            ui.show("Got it. I've added this task:");
            ui.show("  " + task);
            ui.show("Now you have " + tasks.getTaskCount() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BebeException("Invalid input format. Please check your command.");
        }
    }
}
