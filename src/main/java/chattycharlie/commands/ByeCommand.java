package chattycharlie.commands;

import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.StringDesign;
import chattycharlie.userinteractions.Ui;

/**
 * Represents the command to exit the ChattyCharlie programme.
 * This command will save the tasks to storage and display a farewell to the user
 */
public class ByeCommand implements Command {

    /**
     * Executes the ByeCommand, which saves the tasks to storage and displays a farewell message.
     *
     * @param taskList the list of tasks to be saved.
     * @param ui the user interface for interacting with the user.
     * @param storage the storage system used to save tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        System.out.println(StringDesign.CHARLIE + StringDesign.FAREWELL);
    }

    /**
     * Indicates that this command will cause the application to exit.
     *
     * @return <code>true</code> since the ByeCommand is intended to terminate the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
