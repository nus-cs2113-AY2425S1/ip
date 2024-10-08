package command;

import datahandling.Storage;
import task.Deadline;
import task.TaskList;
import ui.UserInteraction;
import java.io.IOException;

/**
 * Command to add deadline task to task list.
 */
public class DeadlineCommand extends Command {
    private final String taskDetails;

    /**
     * Construct DeadlineCommand with defined task details.
     * @param taskDetails description of a deadline task
     */
    public DeadlineCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Abstract method used to create Deadline task, to be added to task list.
     * @param tasks list of task which command takes from
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     * @throws IOException in the event of an error saving task list to storage
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws IOException {
        try {
            String[] parts = taskDetails.split(" /by ", 2);
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid deadline format. Use: deadline <task> /by <date>");
            }

            String description = parts[0];
            String by = parts[1];
            tasks.addTask(new Deadline(description, by));
            ui.showMessage("Added: " + tasks.getTask(tasks.getSize() - 1));
            storage.saveTasksToFile(tasks.getTasks());
        } catch (IllegalArgumentException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
