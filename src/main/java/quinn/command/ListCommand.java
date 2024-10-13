package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.TaskList;
import quinn.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {
    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException {
        if (taskList.getNumOfTasks() != 0) {
            // Reset the List of filteredTasks when ListCommand is executed
            // This will clear the List of filteredTasks
            taskList.resetFilteredTasks();

            String response = ui.tasksInListMessage(taskList);
            ui.displayResponse(response);
        } else {
            throw new QuinnException("There are no tasks in your list!");
        }
    }
}
