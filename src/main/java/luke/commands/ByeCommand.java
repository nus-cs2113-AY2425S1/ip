package luke.commands;

import luke.TaskList;
import luke.Ui;

/**
 * Exits the application.
 */
public class ByeCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    /**
     * Sets the exit flag as true to indicate that the program should be exited.
     * @param taskList Object of class TaskList that contains the user's list of tasks.
     * @param ui UI component for displaying messages.
     * @param inputs A string array of user input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        this.isExit = true;
    }
}
