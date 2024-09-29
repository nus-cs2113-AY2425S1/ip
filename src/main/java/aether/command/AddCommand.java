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
 * Handles the addition of tasks (e.g., Todo, Deadline, Event).
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(String description, TaskType type) throws DukeException {
        switch (type) {
        case TODO:
            this.task = new Todo(description);
            break;
        case DEADLINE:
            String[] deadlineParts = description.split(" /by ", 2);
            if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new DukeException("Error: Invalid deadline format. Please enter in the format: 'deadline <description> /by <date>'.");
            }
            this.task = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case EVENT:
            String[] eventParts = description.split(" /from ", 2);
            if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
                throw new DukeException("Error: Invalid event format. Please enter in the format: 'event <description> /from <start_time> /to <end_time>'.");
            }
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new DukeException("Error: Invalid event time format. Please enter in the format: 'event <description> /from <start_time> /to <end_time>'.");
            }
            this.task = new Event(eventParts[0], timeParts[0], timeParts[1]);
            break;
        default:
            throw new DukeException("Error: Unknown task type.");
        }
    }

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
