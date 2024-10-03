package tars.command;

import tars.tasklist.TaskList;
import tars.storage.Storage;
import tars.task.Todo;
import tars.userinterface.UserInterface;
import tars.tarsexception.tarsException;

import java.io.IOException;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand with the specified task description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a todo task to the task list, display it in the UI, and save it to storage.
     *
     * @param tasks   The task list to which the todo task will be added.
     * @param ui      The user interface to display task information.
     * @param storage The storage to save the updated task list.
     * @throws tarsException If the description is empty.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws tarsException {
        if (description.isEmpty()) {
            throw new tarsException("The description of a todo cannot be empty.");
        }

        // Create a new Todo task and add it to the task list
        Todo newTask = new Todo(description);
        tasks.addTask(newTask);

        // Display task added confirmation
        ui.showTaskAdded(newTask, tasks.getTaskCount());

        // Save the updated task list, catching possible IOException
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }
}
