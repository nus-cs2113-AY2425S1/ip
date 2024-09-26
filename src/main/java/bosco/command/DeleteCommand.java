package bosco.command;

import bosco.task.Task;
import bosco.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(int targetNumber) {
        super(targetNumber);
    }

    @Override
    public void execute() {
        Task selectedTask = tasks.getTaskAtIndex(targetNumber - 1);
        tasks.removeTask(selectedTask);
        ui.printMessages(Ui.MESSAGE_DELETED_TASK, Ui.INDENT_EXTRA + selectedTask,
                ui.getTaskCountMessage(tasks.getSize()));
    }
}
