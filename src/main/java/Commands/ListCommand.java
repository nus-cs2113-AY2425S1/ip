package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the list command by displaying all tasks in the task list through the UI.
     *
     * @param taskList The task list to display.
     * @param ui The user interface for displaying the task list.
     * @param storage Unused parameter.
     * @throws AlyException If any error occurs during execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        ui.showList(taskList);
    }
}