package bob.command;
import bob.task.Event;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

/**
 * Represents a command to add an event task.
 * It parses the input to retrieve the description, start time, and end time,
 * then adds the event task to the task list.
 */
public class AddEventCommand extends Command {
    private final String input;
    private static final String COMMAND_EVENT = "event";
    private static final String EVENT_START = " /from ";
    private static final String EVENT_END = " /to ";

    /**
     * Constructs an AddEventCommand object with the specified user input.
     *
     * @param command The user's input string representing the event command.
     */
    public AddEventCommand(String command) {
        this.input = command;
    }

    /**
     * Executes the command to add an event task.
     * It extracts the description, start time and end time from the input string,
     * adds the event task to the task list, prints it via the UI,
     * and stores it in the storage.
     *
     * @param tasks   The TaskList where the new task will be added.
     * @param ui      The Ui object to handle the user interface output.
     * @param storage The Storage object to save the task into the persistent storage.
     * @throws StringIndexOutOfBoundsException If the input format of the event is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int fromIndex = this.input.indexOf(EVENT_START);
        int toIndex = this.input.indexOf(EVENT_END);
        String userTypedDescription = this.input.substring(COMMAND_EVENT.length()).trim();
        if (userTypedDescription.isEmpty()) {
            ui.printEmptyDescription("event");
        } else {
            try {
                String description = input.substring(COMMAND_EVENT.length() +1, fromIndex);
                String startTime = input.substring(fromIndex + EVENT_START.length(), toIndex);
                String endTime = input.substring(toIndex + EVENT_END.length());
                Task newTask = new Event(description, startTime, endTime);
                tasks.addTask(newTask);
                ui.printAddedTask(tasks);
                storage.appendTask(newTask);
                storage.save(tasks.getTaskList());
            } catch (StringIndexOutOfBoundsException e) {
                ui.printInvalidEvent();
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