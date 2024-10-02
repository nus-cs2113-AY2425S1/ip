package Tars.Command;

import Tars.TaskList;
import Tars.Task.Event;
import Tars.Storage;
import Tars.UserInterface;
import java.io.IOException;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Event newTask = new Event(description, from, to);
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
