package bosco.command;

import bosco.task.Task;
import bosco.ui.Ui;

/**
 * Represents the command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    /**
     * Class constructor.
     *
     * @param targetNumber Number of the task in the list.
     */
    public UnmarkCommand(int targetNumber) {
        super(targetNumber);
    }

    /**
     * Overrides the default execute method to mark the specified task as not done.
     * Prints message indicating task is marked as not done.
     */
    @Override
    public void execute() {
        Task selectedTask = tasks.getTaskAtIndex(targetNumber - 1);
        selectedTask.markAsNotDone();
        ui.printMessages(Ui.MESSAGE_MARK_UNDONE, Ui.INDENT_EXTRA + selectedTask);
    }
}
