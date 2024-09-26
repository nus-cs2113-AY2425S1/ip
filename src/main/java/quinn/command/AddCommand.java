package quinn.command;

import quinn.storage.Storage;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

/**
 * Represents an abstract base class for all add commands in the task management system.
 * This class implements the Command interface and provides common functionality
 * for adding tasks to the task list.
 *
 * Subclasses must implement the {@link #execute(TaskList, Ui, Storage)} method
 * to define the specific behavior for adding different types of tasks.
 *
 */
public abstract class AddCommand implements Command {
    /** The description of the task to be added. */
    private final String taskDescription;

    /**
     * Constructs an AddCommand with the specified task description.
     *
     * @param taskDescription the description of the task to be added
     */
    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Returns the description of the task to be added.
     *
     * @return the task description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return false, as add commands do not cause the program to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the add command. This method must be implemented by subclasses
     * to define the specific behavior for adding different types of tasks.
     *
     * @param taskList the task list to which the task will be added
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @throws IOException if there's an error saving the updated task list
     */
    @Override
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
