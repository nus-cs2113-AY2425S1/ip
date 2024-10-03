package tars.command;

import tars.tasklist.TaskList;
import tars.storage.Storage;
import tars.task.Todo;
import tars.userinterface.UserInterface;
import tars.tarsexception.TarsException;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws TarsException {
        if (description.isEmpty()) {
            throw new TarsException("The description of a todo cannot be empty.");
        }

        // 创建新的 Todo 任务并添加到任务列表
        Todo newTask = new Todo(description);
        tasks.addTask(newTask);

        // 显示任务添加成功信息
        ui.showTaskAdded(newTask, tasks.getTaskCount());

        // 使用 try-catch 捕获可能的 IOException
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            // 捕获异常并显示错误信息
            ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }
}
