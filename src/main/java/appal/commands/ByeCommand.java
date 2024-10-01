package appal.commands;

import appal.exception.AppalException;
import appal.exception.SaveTasksErrorException;
import appal.storage.Storage;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Messages.BYE_MESSAGE;
import static appal.common.Utils.COMMAND_BYE;

/**
 * ByeCommand class executes the procedures to exit the Appal application.
 */
public class ByeCommand extends Command {
    /**
     * Class constructor.
     */
    public ByeCommand() {
        super(COMMAND_BYE);
    }

    /**
     * Saves tasks from current task list to a text file.
     *
     * @param storage Storage instance for Appal to handle task storage.
     * @param taskList Current task list tracked by Appal.
     * @throws SaveTasksErrorException if error occurs while saving tasks.
     */
    public void saveTasks(Storage storage, TaskList taskList) throws SaveTasksErrorException {
        storage.saveTasksToFile(taskList);
    }

    /**
     * Executes the command to exit the Appal application.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     * @throws AppalException if error occurs while saving tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        saveTasks(storage, taskList);
        super.setExit(true);
        ui.printMessage(BYE_MESSAGE);
    }
}
