package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The ByeCommand class handles the termination of the program when the user inputs the "bye" command.
 */
public class ByeCommand extends Command{
    /**
     * Executes the ByeCommand by calling the `sayGoodbye` method of the Ui class.
     * This is used to display the goodbye message to the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.sayGoodbye();
    }

    /**
     * Overrides the isExit method to indicate that this command should terminate the program.
     *
     * @return true, indicating that the program should exit after this command.
     */
    @Override
    public boolean isExit(){
        return true;
    }
}
