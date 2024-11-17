package bob.command;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

/**
 * Represents the command to exit the program, saves the current task list and displays an exit message.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, saves the task list and shows an exit message.
     *
     * @param tasks   The TaskList that the command will operate on.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to handle saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTaskList());
        ui.showExitMessage();
    }

    /**
     * Returns a boolean value to indicate whether this command should exit the program.
     *
     * @return true as the exit command will terminate the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
