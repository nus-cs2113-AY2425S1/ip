package bot.command;

import bot.Storage;
import bot.TobyBotException;
import bot.Ui;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskList;

import java.io.IOException;

/**
 * Command to add a new task (either a Todo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private final String type;
    private final String details;

    /**
     * Constructor for AddCommand.
     *
     * @param type    The type of task to add (todo, deadline, or event).
     * @param details The description and other details of the task.
     */
    public AddCommand(String type, String details) {
        this.type = type;
        this.details = details;
    }

    /**
     * Executes the add command by creating the appropriate task and adding it to the task list.
     *
     * @param tasks   The list of tasks to add the new task to.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated tasks.
     * @throws TobyBotException If the task type or details are invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TobyBotException {
        Task task;
        switch (type) {
            case "todo":
                if (details.isEmpty()) throw new TobyBotException("The description of a todo cannot be empty.");
                task = new Todo(details);
                break;
            case "deadline":
                String[] deadlineParts = details.split(" by ", 2);
                if (deadlineParts.length < 2) throw new TobyBotException("Please specify a deadline with by.");
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = details.split(" from | to ");
                if (eventParts.length < 3) throw new TobyBotException("Please specify an event with from and to.");
                task = new Event(eventParts[0], eventParts[1], eventParts[2]);
                break;
            default:
                throw new TobyBotException("Unknown task type.");
        }
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n  " + task);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Unable to save tasks.");
        }
    }
}