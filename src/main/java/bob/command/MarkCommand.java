package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;
import java.util.ArrayList;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
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
            Task taskToBeMarked = taskList.get(this.index);
            if (taskToBeMarked.getStatusIcon().equals("X")) {
                ui.printTaskAlreadyDoneMessage();
            } else {
                taskToBeMarked.markAsDone();
                ui.showMarkTaskMessage(taskToBeMarked);
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