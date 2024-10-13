package quinn.command;

import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add an event task to the task list.
 * This class extends the AddCommand abstract class and implements
 * the specific behavior for adding an event task.
 *
 */
public class AddEventCommand extends AddCommand {
    /** The start date and time of the event task. */
    private final String taskStartDateTime;

    /** The end date and time of the event task. */
    private final String taskEndDateTime;

    /**
     * Constructs an AddEventCommand with the specified task information.
     *
     * @param taskInfo a string containing the task description, start date/time,
     *                 and end date/time, separated by "/from" and "/to"
     */
    public AddEventCommand(String taskInfo) {
        // Task description
        super(taskInfo.split("/from|/to", 3)[0].trim());

        // Task start and end datetime
        taskStartDateTime = taskInfo.split("/from|/to", 3)[1].trim();
        taskEndDateTime = taskInfo.split("/from|/to", 3)[2].trim();
    }

    /**
     * Executes the add event command. This method adds a new event task
     * to the task list, updates the UI, and saves the changes to storage.
     *
     * @param taskList the task list to which the event task will be added
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @throws IOException if there's an error saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task newEventTask = taskList.addEventTask(super.getTaskDescription(), taskStartDateTime, taskEndDateTime);

        // Reset the List of filteredTasks when AddCommand is executed
        // This will clear the List of filteredTasks
        taskList.resetFilteredTasks();

        String response = ui.taskAddedMessage(newEventTask)
                + System.lineSeparator() + ui.numOfTasksInListMessage(taskList);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }
}
