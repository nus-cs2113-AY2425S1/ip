package bosco.command;

import bosco.task.Task;
import bosco.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(int targetNumber) {
        super(targetNumber);
    }

    @Override
    public void execute() {
        Task selectedTask = tasks.getTaskAtIndex(targetNumber - 1);
        selectedTask.markAsNotDone();
        ui.printMessages(Ui.MESSAGE_MARK_UNDONE, Ui.INDENT_EXTRA + selectedTask);
    }
}
