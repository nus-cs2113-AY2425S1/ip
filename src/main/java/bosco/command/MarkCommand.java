package bosco.command;

import bosco.task.Task;
import bosco.ui.Ui;

/**
 * Represents the command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * Class constructor.
     *
     * @param targetNumber Number of the task in the list.
     */
    public MarkCommand(int targetNumber) {
        super(targetNumber);
    }

    /**
     * Overrides the default execute method to mark the specified task as done.
     * Prints message indicating task is marked.
     */
    @Override
    public void execute() {
        Task selectedTask = tasks.getTaskAtIndex(targetNumber - 1);
        selectedTask.markAsDone();
        ui.printMessages(Ui.MESSAGE_MARK_DONE, Ui.INDENT_EXTRA + selectedTask);
    }
}
