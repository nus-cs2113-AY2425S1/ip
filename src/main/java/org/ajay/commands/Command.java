package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

/**
 * Represents a command to be executed
 */
public class Command {

    protected boolean isExit = false;

    /**
     * Get the exit boolean.
     *
     * @return boolean representing if the program should exit.
     */
    public boolean getExitBool() {
        return isExit;
    }

    /**
     * Sets the exit boolean to true.
     *
     * @param isExit boolean to set the exit boolean to.
     */
    public void setExitBool(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     *
     * @param tasks   TaskList containing all tasks.
     * @param ui      TextUi object to interact with the user.
     * @param storage Storage object to save the task list.
     */
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
