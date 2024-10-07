package command;

import datahandling.Storage;
import task.Event;
import task.TaskList;
import ui.UserInteraction;

public class EventCommand extends Command {
    private final String taskDetails;

    public EventCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception {
        String[] parts = taskDetails.split(" /from | /to ", 3);
        if (parts.length < 3) {
            ui.showMessage("Invalid event format. Use: event <task> /from <start> /to <end>");
            return;
        }

        String description = parts[0];
        String from = parts[1];
        String to = parts[2];
        tasks.addTask(new Event(description, from, to));
        ui.showMessage("Added: [E][ ] " + description + " (from: " + from + ", to: " + to + ")");
        storage.save(tasks.getTasks());
    }
}
