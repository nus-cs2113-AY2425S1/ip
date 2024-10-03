package tars.command;

import tars.userinterface.UserInterface;
import tars.tasklist.TaskList;
import tars.task.Deadline;
import tars.storage.Storage;

import java.io.IOException;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs an AddDeadlineCommand with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task to the task list, display it in the UI, and save it to storage.
     *
     * @param tasks The task list to which the deadline task will be added.
     * @param ui The user interface to display task information.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Deadline newTask = new Deadline(description, by);
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
