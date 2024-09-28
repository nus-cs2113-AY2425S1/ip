package bosco.command;

import bosco.task.Task;
import bosco.ui.Ui;

/**
 * Represents the command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * Class constructor.
     *
     * @param targetNumber Number of the task in the list.
     */
    public DeleteCommand(int targetNumber) {
        super(targetNumber);
    }

    /**
     * Overrides the default execute method to delete the specified task.
     * Prints message indicating the task is deleted.
     */
    @Override
    public void execute() {
        Task selectedTask = tasks.getTaskAtIndex(targetNumber - 1);
        tasks.removeTask(selectedTask);
        ui.printMessages(Ui.MESSAGE_DELETED_TASK, Ui.INDENT_EXTRA + selectedTask,
                ui.getTaskCountMessage(tasks.getSize()));
    }
}
