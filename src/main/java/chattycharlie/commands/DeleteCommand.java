package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

/**
 * Represents the command to delete a task from the task list.
 * This command takes an index as input and removes the corresponding task.
 */
public class DeleteCommand implements Command {
    private int toDeleteIndex;

    /**
     * Constructs a <code>DeleteCommand</code> using the provided input line.
     * Parses the input to determine which task index to delete.
     *
     * @param line the input line containing the task index to delete.
     * @throws CharlieExceptions if the index provided is not a valid integer.
     */
    public DeleteCommand(String line) throws CharlieExceptions {
        String toDeleteIndex = line.substring(7).trim();
        this.toDeleteIndex = Integer.parseInt(toDeleteIndex) - 1;
    }

    /**
     * Executes the <code>DeleteCommand</code> by deleting the task at the specified index
     * from the task list.
     *
     * @param taskList the list of tasks from which a task will be deleted.
     * @param ui the user interface for displaying output to the user.
     * @param storage the storage system to save changes made to the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(toDeleteIndex);
    }
}
