package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;

/**
 * Represent command for exiting the program
 */
public class ExitCommand extends Command {

    /**
     * Abstract command used to execute ExitCommand
     * @param tasks list of task which command takes from
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Represent exit command.
     * @return true, for exiting
     */
    @Override
    public boolean isExit() {
        return true;
    }
}