package quinn.command;

import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

public class AddDeadlineCommand extends AddCommand {
    private final String taskDueDateTime;

    public AddDeadlineCommand(String taskInfo) {
        // Task description
        super(taskInfo.split("/by", 2)[0].trim());

        // Task due datetime
        taskDueDateTime = taskInfo.split("/by", 2)[1].trim();
    }

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
