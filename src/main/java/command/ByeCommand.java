package command;

import data.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The ByeCommand class handles the termination of the session. It saves the
 * current task list to storage and displays a farewell message to the user.
 *
 * @author Tan Ping Hui
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand, saving the task list to storage and displaying
     * a goodbye message to the user.
     *
     * @param tasks The task list that will be saved to storage for future sessions.
     * @param storage The storage system where the task list will be saved.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        storage.save(tasks);
        Ui.showEndChatMessage();
    }
}
