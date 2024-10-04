package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.tasks.Event;
import Ryan.tasks.Task;
import Ryan.exceptions.RyanException;

/**
 * Command to add an Event task.
 */
public class EventCommand extends Command {
    private final String command;

    /**
     * Constructs an EventCommand with the user input command string.
     *
     * @param command The user input specifying the event task.
     */
    public EventCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the event creation command, adding a new Event task.
     *
     * @param tasks The task list to add the event task to.
     * @param ui The user interface for displaying the result.
     * @throws RyanException If the command format is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        String[] splitFrom = command.split("/from", 2);

        if (splitFrom.length < 2) {
            throw new RyanException("Event tasks should be in the format 'description /from start-time /to end-time'.");
        }

        String description = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("/to", 2);

        if (splitTo.length < 2) {
            throw new RyanException("Event tasks should include both start-time and end-time.");
        }

        String from = splitTo[0].trim();
        String to = splitTo[1].trim();
        Task task = new Event(description, from, to);
        tasks.addTask(task);

        ui.showTaskAdded(task, tasks.size());
    }
}
