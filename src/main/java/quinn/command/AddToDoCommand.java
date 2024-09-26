package quinn.command;

import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

public class AddToDoCommand extends AddCommand {
    public AddToDoCommand(String taskDescription) {
        super(taskDescription);
    }

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