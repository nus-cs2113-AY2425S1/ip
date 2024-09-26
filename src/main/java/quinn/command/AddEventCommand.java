package quinn.command;

import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

public class AddEventCommand extends AddCommand {
    private final String taskStartDateTime;
    private final String taskEndDateTime;

    public AddEventCommand(String taskInfo) {
        // Task description
        super(taskInfo.split("/from|/to", 3)[0].trim());

        // Task start and end datetime
        taskStartDateTime = taskInfo.split("/from|/to", 3)[1].trim();
        taskEndDateTime = taskInfo.split("/from|/to", 3)[2].trim();
    }

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
