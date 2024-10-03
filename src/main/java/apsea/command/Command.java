package apsea.command;

import apsea.exception.ApseaException;
import apsea.storage.Storage;
import apsea.task.TaskList;
import apsea.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public Command() {

    }

    public boolean getExitStatus() {
        return isExit;
    }

    /**
     * Executes command based on user's input.
     *
     * @param taskList List of tasks.
     * @param ui Ui for displaying messages.
     * @throws ApseaException if arguments and input are invalid.
     */
    public abstract void runCommand(TaskList taskList, Ui ui, Storage storage) throws ApseaException;
}
