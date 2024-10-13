package quinn.command;

import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a deadline task to the task list.
 * This class extends the AddCommand abstract class and implements
 * the specific behavior for adding a deadline task.
 *
 */
public class AddDeadlineCommand extends AddCommand {
    /** The due date and time of the deadline task. */
    private final String taskDueDateTime;

    /**
     * Constructs an AddDeadlineCommand with the specified task information.
     *
     * @param taskInfo a string containing the task description and due date/time,
     *                 separated by "/by"
     */
    public AddDeadlineCommand(String taskInfo) {
        // Task description
        super(taskInfo.split("/by", 2)[0].trim());

        // Task due datetime
        taskDueDateTime = taskInfo.split("/by", 2)[1].trim();
    }

    /**
     * Executes the add deadline command. This method adds a new deadline task
     * to the task list, updates the UI, and saves the changes to storage.
     *
     * @param taskList the task list to which the deadline task will be added
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @throws IOException if there's an error saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task newDeadlineTask = taskList.addDeadlineTask(super.getTaskDescription(), taskDueDateTime);

        // Reset the List of filteredTasks when AddCommand is executed
        // This will clear the List of filteredTasks
        taskList.resetFilteredTasks();

        String response = ui.taskAddedMessage(newDeadlineTask)
                + System.lineSeparator() + ui.numOfTasksInListMessage(taskList);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }
}
