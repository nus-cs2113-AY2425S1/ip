package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.TaskList;
import quinn.ui.Ui;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException {
        if (taskList.getNumOfTasks() != 0) {
            taskList.setFilteredTasksByKeyword(keyword);

            if (taskList.getNumOfFilteredTasks() != 0) {
                String response = ui.tasksWithKeywordMessage(taskList, keyword);
                ui.displayResponse(response);
            } else {
                // Reset the List of filteredTasks when FindCommand is executed
                // and there are no matching tasks
                // This will clear the List of filteredTasks
                taskList.resetFilteredTasks();

                throw new QuinnException("There are no matching tasks!");
            }
        } else {
            throw new QuinnException("There are no tasks in your list!");
        }
    }
}
