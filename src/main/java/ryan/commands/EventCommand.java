package ryan.commands;

import ryan.utility.TaskList;
import ryan.utility.Ui;

import ryan.tasks.Task;
import ryan.tasks.Event;

import ryan.exceptions.RyanException;
import ryan.exceptions.InvalidEventFormatException;
import ryan.exceptions.InvalidDateFormatException;
import ryan.exceptions.EmptyDescriptionException;

/**
 * Command to add an Event task.
 */
public class EventCommand extends Command {
    private final String command;
    private static final String EVENT_FROM_KEYWORD = "/from";
    private static final String EVENT_TO_KEYWORD = "/to";

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
        String[] splitFrom = command.split(EVENT_FROM_KEYWORD, 2);

        if (splitFrom.length < 2) {
            throw new InvalidEventFormatException();
        }

        String description = splitFrom[0].trim();

        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }

        String[] splitTo = splitFrom[1].split(EVENT_TO_KEYWORD, 2);

        if (splitTo.length < 2) {
            throw new InvalidEventFormatException();
        }

        String from = splitTo[0].trim();
        String to = splitTo[1].trim();
        try {
            Task task = new Event(description, from, to);
            tasks.addTask(task);
            Ui.showTaskAdded(task, tasks.size());
        } catch (Exception e) {
            throw new InvalidDateFormatException();
        }
    }
}
