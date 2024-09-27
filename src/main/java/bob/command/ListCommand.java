package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            ui.printEmptyListMessage();
            return;
        }
        System.out.println("Here " + (tasks.getSize() == 1 ? "is" : "are") + " the " + (tasks.getSize() == 1 ? "task" : "tasks") + " in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
