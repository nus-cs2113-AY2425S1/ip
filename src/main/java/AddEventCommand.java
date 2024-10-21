/**
 * Represents a command to add a new event to the task list.
 * This command creates a new Event task with a description, start time, and end time.
 */
public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new AddEventCommand with the specified event details.
     *
     * @param description The description of the event.
     * @param from        The start time/date of the event.
     * @param to          The end time/date of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add a new event to the task list.
     * This method creates a new Event, adds it to the task list, updates the UI,
     * and saves the updated task list to storage.
     *
     * @param tasks   The TaskList to which the new event will be added.
     * @param ui      The Ui object used to display output to the user.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.getTaskCount());
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
