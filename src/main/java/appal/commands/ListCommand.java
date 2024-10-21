package appal.commands;

import appal.storage.Storage;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Utils.COMMAND_LIST;

/**
 * ListCommand class lists and shows all current tasks to the user.
 */
public class ListCommand extends Command {
    /**
     * Class constructor.
     */
    public ListCommand() {
        super(COMMAND_LIST);
    }

    /**
     * Executes the command to list and show all current tasks to the user.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
    }
}
