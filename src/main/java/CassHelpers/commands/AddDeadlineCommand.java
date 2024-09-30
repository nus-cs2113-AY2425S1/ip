package CassHelpers.commands;

import CassHelpers.exceptions.InvalidDateFormatException;
import CassHelpers.exceptions.InvalidDeadlineFormatException;
import CassHelpers.types.Deadline;
import CassHelpers.util.Parser;
import CassHelpers.util.Storage;
import CassHelpers.types.Task;
import CassHelpers.util.TaskList;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Command class responsible for adding a deadline task to the task list.
 * It parses the input string for the task name and deadline and creates a new deadline task.
 * The task is then added to the task list and saved to storage.
 */
public class AddDeadlineCommand implements Command {
    private final List<Task> taskList;
    private final Storage storage;
    private final String input;
    private final int BY_INDEX_OFFSET = 4;
    private final int DEADLINE_INDEX_OFFSET = 8;

    /**
     * Constructs a new AddDeadlineCommand.
     *
     * @param tasks The TaskList object where the new deadline task will be added.
     * @param input The user input containing the deadline details.
     */
    public AddDeadlineCommand(TaskList tasks, String input) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.input = input;
    }

    /**
     * Executes the command by parsing the input for a deadline task and adding it to the task list.
     *
     * @throws InvalidDeadlineFormatException If the deadline format is invalid.
     * @throws InvalidDateFormatException If the date format is invalid.
     */
    @Override
    public void execute() throws InvalidDeadlineFormatException, InvalidDateFormatException {
        int byIndex = input.indexOf("/by");

        if (byIndex < 0) {
            throw new InvalidDeadlineFormatException("Sorry, deadline entered has the wrong format");
        }

        String by = input.substring(byIndex + BY_INDEX_OFFSET).trim();
        String deadlineTaskName = input.substring(DEADLINE_INDEX_OFFSET, byIndex).trim();

        LocalDateTime deadline = Parser.parseDateTime(by);

        Deadline newDeadline = new Deadline(deadlineTaskName, deadline);
        taskList.add(newDeadline);
        storage.appendTaskToFile(newDeadline);
        System.out.println("Got it. I've added this task: \n " + newDeadline.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
