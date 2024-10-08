package command;

import datahandling.Storage;
import task.Event;
import task.TaskList;
import ui.UserInteraction;
import java.io.IOException;

/**
 * Represent command to add event task to task list
 */
public class EventCommand extends Command {
    private final String taskDetails;

    /**
     * Constructs EventCommand with defined task details.
     * @param taskDetails description of event
     */
    public EventCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     *
     * @param tasks list of task types which command takes from
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     * @throws IOException if there is issues pertaining to saving task list to storage
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws IOException {
        try {
            String[] parts = taskDetails.split(" /from | /to ", 3);
            if (parts.length < 3) {
                throw new IllegalArgumentException("Invalid event format. Use: event <task> /from <start> /to <end>");
            }

            String description = parts[0];
            String from = parts[1];
            String to = parts[2];
            tasks.addTask(new Event(description, from, to));
            ui.showMessage("Added: " + tasks.getTask(tasks.getSize() - 1));
            storage.saveTasksToFile(tasks.getTasks());
        } catch (IllegalArgumentException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
