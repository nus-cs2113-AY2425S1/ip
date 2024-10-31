package luke.commands;

import luke.TaskList;
import luke.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public boolean isExit;

    public Command() {

    }

    /**
     * Executes command
     * @param taskList Object of class TaskList that contains the user's list of tasks.
     * @param ui UI component for displaying messages.
     * @param inputs A string array of user input.
     */
    public abstract void execute(TaskList taskList, Ui ui, String[] inputs);

}
