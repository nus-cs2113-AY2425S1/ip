package bob.command;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTaskList());
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
