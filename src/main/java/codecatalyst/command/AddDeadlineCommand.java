package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;
import codecatalyst.exception.EmptyTaskDescriptionException;
import codecatalyst.task.Deadline;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private String input;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified user input.
     *
     * @param input The input string containing the task description and due date.
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception {
        String[] parts = input.split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("Deadline task description or due date cannot be empty.");
        }
        // invariant: there are 2 parts to the input
        if (parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("end date is empty.");
        }

        Deadline deadline = new Deadline(parts[0], parts[1]);
        tasklist.addTask(deadline);
        ui.printTaskAdded(deadline, tasklist.getSize());
        storage.saveTasksToFile(tasklist.getTasks());  // throw IOException
    }
}
