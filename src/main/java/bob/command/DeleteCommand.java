package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            ui.printEmptyListMessage();
            return;
        }
        try {
            Task removedTask = tasks.deleteTask(taskIndex);
            ui.showDeleteTaskMessage(removedTask, tasks.getSize());
            storage.save(tasks.getTaskList());
        } catch (NullPointerException | NumberFormatException e) {
            ui.printInvalidTaskNumberMessage(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

