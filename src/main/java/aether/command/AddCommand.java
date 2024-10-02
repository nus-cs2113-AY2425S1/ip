package aether.command;

import aether.storage.Storage;
import aether.task.Deadline;
import aether.task.Event;
import aether.task.Task;
import aether.task.Todo;
import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.DukeException;
import java.io.IOException;

/**
 * Handles the addition of tasks (e.g., Todo, Deadline, Event) to the task list.
 * This command parses the task description and type, creates the appropriate task,
 * and adds it to the task list while ensuring data persistence.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task description and type.
     *
     * @param description The description of the task to be added.
     * @param type        The type of the task (TODO, DEADLINE, EVENT).
     * @throws DukeException If the task type is unknown or the description format is invalid.
     */
    public AddCommand(String description, TaskType type) throws DukeException {
        switch (type) {
        case TODO:
            if(description == null || description.isEmpty()) {
                throw new DukeException("Task cannot be empty.");
            }
            this.task = new Todo(description);
            break;
        case DEADLINE:
            String[] deadlineParts = description.split(" /by ", 2);
            if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new DukeException("Error: Invalid deadline format. Please enter in the format: 'deadline " +
                        "<description> /by <date>'.");
            }
            this.task = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case EVENT:
            String[] eventParts = description.split(" /from ", 2);
            if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
                throw new DukeException("Error: Invalid event format. Please enter in the format: 'event " +
                        "<description> /from <start_time> /to <end_time>'.");
            }
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new DukeException("Error: Invalid event time format. Please enter in the format: 'event " +
                        "<description> /from <start_time> /to <end_time>'.");
            }
            this.task = new Event(eventParts[0], timeParts[0], timeParts[1]);
            break;
        default:
            throw new DukeException("Error: Unknown task type.");
        }
    }

    /**
     * Executes the add command by adding the task to the task list and saving the updated list.
     *
     * @param taskList The TaskList object containing all current tasks.
     * @param ui       The Ui object responsible for user interactions.
     * @param storage  The Storage object responsible for saving and loading tasks.
     * @throws DukeException If an error occurs during task addition or saving.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(task);
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            Ui.response("Error saving tasks: " + e.getMessage());
        }
    }
}
