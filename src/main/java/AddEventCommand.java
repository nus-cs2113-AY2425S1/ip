import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * The AddEventCommand class represents a command to add an event task
 * to a task list. It processes user input to extract the task name and
 * the event date range, and executes the command to add the task.
 */
public class AddEventCommand extends Command {
    private String taskName;
    private String from;
    private String to;

    /**
     * Constructs an AddEventCommand with the specified user input.
     * The task name, start date, and end date are extracted from the user input string.
     *
     * @param userInput The input string from the user, expected to contain "event ".
     */
    public AddEventCommand(String userInput) {
        taskName = userInput.substring("event ".length(), userInput.indexOf("/from "));
        from = userInput.substring(userInput.indexOf("/from ") + "/from ".length(), userInput.indexOf(" /to "));
        to = userInput.substring(userInput.indexOf(" /to ") + " /to ".length());
    }

    /**
     * Executes the command to add an event task to the task list.
     * It creates a new Event object using the specified date range, adds it to the task list,
     * displays a message to the user, and saves the updated task list to storage.
     * If the dates are invalid, it creates the task with the original string representations.
     *
     * @param tasks The TaskList object to which the task will be added.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate dateFrom = LocalDate.parse(from, formatter);
            LocalDate dateTo = LocalDate.parse(to, formatter);
            Task task = new Event(taskName, dateFrom, dateTo);
            tasks.addTask(task);
        } catch (DateTimeParseException e) {
            Task task = new Event(taskName, from, to);
            tasks.addTask(task);
        }
        ui.showEventAdded(tasks);
        storage.save(tasks);
    }
}
