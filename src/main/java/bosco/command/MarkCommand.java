package bosco.command;

import bosco.task.Task;
import bosco.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(int targetNumber) {
        super(targetNumber);
    }

    @Override
    public void execute() {
        Task selectedTask = tasks.getTaskAtIndex(targetNumber - 1);
        selectedTask.markAsDone();
        ui.printMessages(Ui.MESSAGE_MARK_DONE, Ui.INDENT_EXTRA + selectedTask);
    }
}
