package Tars.Command;

import Tars.TaskList;
import Tars.Storage;
import Tars.UserInterface;
import Tars.Task.Task;
import Tars.TarsException;
import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws TarsException {
        Task task = tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(task, tasks.getTaskCount());

        // 捕获并处理可能的 IOException
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }
}
