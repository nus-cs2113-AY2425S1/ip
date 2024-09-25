package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;
import codecatalyst.exception.EmptyTaskDetailException;
import codecatalyst.task.Deadline;

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
        if (parts.length < 2) {
            throw new EmptyTaskDetailException("Deadline task description or due date cannot be empty.");
        }
        // invariant: there are 2 parts to the input
        if (parts[0].trim().isEmpty()) {
            throw new EmptyTaskDetailException("Deadline task description is empty.");
        }

        if (parts[1].trim().isEmpty()) {
            throw new EmptyTaskDetailException("end date is empty.");
        }

        Deadline deadline = new Deadline(parts[0], parts[1]);
        tasklist.addTask(deadline);
        ui.printTaskAdded(deadline, tasklist.getSize());
        storage.saveTasksToFile(tasklist.getTasks());  // throw IOException
    }
}
