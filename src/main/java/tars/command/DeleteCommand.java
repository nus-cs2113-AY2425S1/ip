package tars.command;

import tars.userinterface.UserInterface;
import tars.storage.Storage;
import tars.tasklist.TaskList;
import tars.task.Task;
import tars.tarsexception.TarsException;

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
