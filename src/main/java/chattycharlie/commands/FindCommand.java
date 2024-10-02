package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.TaskList;
import chattycharlie.task.Task;
import chattycharlie.userinteractions.Storage;
import chattycharlie.userinteractions.Ui;

/**
 * Represents the command to find a task based on a search keyword.
 * This command searches through the task list and displays any matching tasks.
 */
public class FindCommand implements Command {
    private String itemToBeFound;

    /**
     * Constructs a <code>FindCommand</code> using the provided input line.
     * Extracts the keyword to be searched in the task list.
     *
     * @param line the input line containing the command and keyword to search for.
     */
    public FindCommand(String line) {
        this.itemToBeFound = line.substring(5);
    }

    /**
     * Executes the <code>FindCommand</code> by searching the task list for tasks that contain
     * the specified keyword and displaying the matching tasks to the user.
     *
     * @param taskList the list of tasks to search.
     * @param ui the user interface for displaying search results.
     * @param storage the storage system (not used in this command).
     * @throws CharlieExceptions if an error occurs during the command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieExceptions {
        int count = 1;
        ui.displaySearchList();
        for(int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if(task.getDescription().contains(itemToBeFound)) {
                ui.displayTaskInList(task, count);
                count++;
            }
        }
        if(count == 1) {
            ui.displayError("No task found that contains: " + itemToBeFound );
        }
    }

}
