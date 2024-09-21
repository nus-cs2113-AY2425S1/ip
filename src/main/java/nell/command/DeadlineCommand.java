package nell.command;

import nell.TaskList;
import nell.common.Messages;
import nell.tasks.Deadline;

/**
 * Represents an executable deadline command
 */
public class DeadlineCommand extends Command{
    private final String description;
    private final String by;

    /**
     * Constructs a new DeadlineCommand object with specified task list and command body
     *
     * @param tasks The specified task list
     * @param detail The task description and its by-time
     * @throws IndexOutOfBoundsException If detail cannot be split into 2 words
     */
    public DeadlineCommand(TaskList tasks, String detail) throws IndexOutOfBoundsException {
        super("deadline", tasks);
        String[] details = detail.split("/by", 2);

        if (details.length < 2) {
            throw new IndexOutOfBoundsException(Messages.DEADLINE_ERROR_MESSAGE);
        }

        this.description = details[0].trim();
        this.by = details[1].trim();
    }

    /**
     * Executes command - adds new Deadline task to the task list
     */
    @Override
    public void execute() {
        Deadline deadlineToAdd = new Deadline(this.description, this.by);
        this.tasks.addTask(deadlineToAdd);
    }
}
