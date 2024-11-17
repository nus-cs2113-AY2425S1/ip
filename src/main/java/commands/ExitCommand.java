package commands;

import data.TaskList;
import ui.Ui;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    public ExitCommand(String cmd, String args) {
        super(cmd, args);
    }

    /**
     * Returns that this Command is an exit command
     * @return true
     */
    public boolean isExit(){
        return true;
    }

    /**
     * Empty method - exit method will exit the program implicitly.
     * @param tasks TaskList object that stores all the tasks currently stored
     * @param ui Ui object used for interacting with the user
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui) {

    }


}
