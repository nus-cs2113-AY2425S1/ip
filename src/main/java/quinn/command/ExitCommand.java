package quinn.command;

import quinn.storage.Storage;
import quinn.task.TaskList;
import quinn.ui.Ui;

/**
 * Represents a command to exit the task management system.
 */
public class ExitCommand implements Command {
    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Reset the List of filteredTasks when ExitCommand is executed
        // This will clear the List of filteredTasks
        taskList.resetFilteredTasks();

        ui.displayExit();
    }
}

