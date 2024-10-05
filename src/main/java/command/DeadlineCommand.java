package command;

import datahandling.Storage;
import task.Deadline;
import task.TaskList;
import ui.UserInteraction;

public class DeadlineCommand extends Command {
    private final String taskDetails;

    public DeadlineCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception {
        String[] parts = taskDetails.split(" /by ", 2);
        if (parts.length < 2) {
            ui.showMessage("Invalid deadline format. Use: deadline <task> /by <date>");
            return;
        }

        String description = parts[0];
        String by = parts[1];
        tasks.addTask(new Deadline(description, by));
        ui.showMessage("Added: [D][ ] " + description + " (by: " + by + ")");
        storage.save(tasks.getTasks());
    }
}
