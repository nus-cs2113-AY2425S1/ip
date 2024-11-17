package tars.command;

import tars.tasklist.TaskList;
import tars.task.Event;
import tars.storage.Storage;
import tars.userinterface.UserInterface;
import java.io.IOException;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an AddEventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task to the task list, display it in the UI, and save it to storage.
     *
     * @param tasks The task list to which the event task will be added.
     * @param ui The user interface to display task information.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Event newTask = new Event(description, from, to);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getTaskCount());

        // Handle possible IOException during task saving
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }
}
