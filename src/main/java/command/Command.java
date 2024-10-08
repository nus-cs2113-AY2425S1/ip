package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;

/**
 * Represents abstract Command for all related subclasses of Command to implement execute method.
 */
public abstract class Command {

    /**
     * Execute command with task list, UI, and storage.
     * @param tasks list of task which command takes from
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     * @throws Exception issue handling
     */
    public abstract void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception;

    /**
     * Indicator of command being an exit command.
     * @return true if command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}