package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;
import codecatalyst.exception.EmptyTaskDetailException;
import codecatalyst.task.Event;

import java.io.IOException;

public class AddEventCommand extends Command {
    private final String input;

    /**
     * Constructs an {@code AddEventCommand} with the specified user input.
     *
     * @param input The input string containing the task description, start, and end times.
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws EmptyTaskDetailException, IOException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            throw new EmptyTaskDetailException("Event task description, start date or end date is empty.");
        }
        // invariant: there are 3 parts to the input
        if (parts[0].trim().isEmpty()) {
            throw new EmptyTaskDetailException("Event task description is empty.");
        }

        if (parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new EmptyTaskDetailException("start date or end date is empty.");
        }

        Event event = new Event(parts[0], parts[1], parts[2]);
        tasklist.addTask(event);
        ui.printTaskAdded(event, tasklist.getSize());
        storage.saveTasksToFile(tasklist.getTasks());
    }
}
