package tars.command;

import tars.userinterface.UserInterface;
import tars.tasklist.TaskList;
import tars.storage.Storage;
import tars.task.Task;
import tars.tarsexception.TarsException;
import java.io.IOException;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws TarsException {
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        ui.showTaskDone(task);

        // 使用 try-catch 捕获可能的 IOException
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            // 捕获异常并显示错误信息
            ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }
}
