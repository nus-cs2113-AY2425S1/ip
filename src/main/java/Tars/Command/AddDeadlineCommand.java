package Tars.Command;

import Tars.TaskList;
import Tars.Task.Deadline;
import Tars.Storage;
import Tars.UserInterface;
import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Deadline newTask = new Deadline(description, by);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getTaskCount());

        // 捕获并处理可能的 IOException
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }
}
