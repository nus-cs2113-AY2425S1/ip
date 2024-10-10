package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;

import Ryan.tasks.Task;
import Ryan.tasks.Deadline;

import Ryan.exceptions.RyanException;
import Ryan.exceptions.InvalidDeadlineFormatException;
import Ryan.exceptions.InvalidDateFormatException;
import Ryan.exceptions.EmptyDescriptionException;

/**
 * Command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String command;
    private static final String DEADLINE_SPLIT_KEYWORD = "/by";

    /**
     * Constructs a DeadlineCommand with the user input command string.
     *
     * @param command The user input specifying the deadline task.
     */
    public DeadlineCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the deadline creation command, adding a new Deadline task.
     *
     * @param tasks The task list to add the deadline task to.
     * @param ui The user interface for displaying the result.
     * @throws RyanException If the command format is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        String[] splitCommand = command.split(DEADLINE_SPLIT_KEYWORD, 2);

        if (splitCommand.length < 2) {
            throw new InvalidDeadlineFormatException();
        }

        String description = splitCommand[0].trim();
        String by = splitCommand[1].trim();

        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }

        try {
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            Ui.showTaskAdded(task, tasks.size());
        } catch (Exception e) {
            throw new InvalidDateFormatException();
        }
    }
}
