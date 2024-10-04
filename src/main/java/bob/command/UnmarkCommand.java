package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;
import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            ui.printEmptyListMessage();
            return;
        }
        try {
            Task taskToBeUnmarked = taskList.get(this.index);
            if (taskToBeUnmarked.getStatusIcon().equals(" ")) {
                ui.printTaskAlreadyUnmarkedMessage();
            } else {
                taskToBeUnmarked.unmark();
                ui.showUnmarkTaskMessage(taskToBeUnmarked);
                storage.save(tasks.getTaskList());
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumberMessage(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}