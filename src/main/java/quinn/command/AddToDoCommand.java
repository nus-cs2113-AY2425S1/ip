package quinn.command;

import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a to-do task to the task list.
 * This class extends the AddCommand abstract class and implements
 * the specific behavior for adding a to-do task.
 *
 */
public class AddToDoCommand extends AddCommand {
    /**
     * Constructs an AddToDoCommand with the specified task description.
     *
     * @param taskDescription the description of the to-do task
     */
    public AddToDoCommand(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Executes the add to-do command. This method adds a new to-do task
     * to the task list, updates the UI, and saves the changes to storage.
     *
     * @param taskList the task list to which the to-do task will be added
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @throws IOException if there's an error saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task newToDoTask = taskList.addToDoTask(super.getTaskDescription());

        // Reset the List of filteredTasks when AddCommand is executed
        // This will clear the List of filteredTasks
        taskList.resetFilteredTasks();

        String response = ui.taskAddedMessage(newToDoTask)
                + System.lineSeparator() + ui.numOfTasksInListMessage(taskList);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }
}