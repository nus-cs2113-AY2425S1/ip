package appal.commands;

import appal.exception.AppalException;
import appal.exception.SaveTasksErrorException;
import appal.storage.Storage;
import appal.task.TaskList;
import appal.ui.Ui;

/**
 * Command class represents a generic command, with the abstract method execute()
 * that more specific command subclasses will implement.
 */
public abstract class Command {
    protected boolean isExit = false;
    private String commandType;

    /**
     * Class constructor.
     *
     * @param commandType String that indicates the type of command that is run.
     */
    public Command(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return commandType;
    }

    /**
     * Returns the exit status of Appal.
     *
     * @return Exit status of Appal.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets the exit status of Appal.
     */
    public void setExit(boolean isExit) {
        this.isExit = isExit;
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
     * Executes the command. Implementation will be done in subclasses.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     * @throws AppalException if error occurs while command is being executed.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException;
}
