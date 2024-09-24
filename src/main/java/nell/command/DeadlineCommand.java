package nell.command;

import nell.list.TaskList;
import nell.common.DateFormats;
import nell.common.Messages;
import nell.tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an executable deadline command
 */
public class DeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";

    private final String description;
    private final LocalDateTime by;

    /**
     * Constructs a new DeadlineCommand object with specified task list and command body
     *
     * @param tasks The specified task list
     * @param detail The task description and its by-time
     * @throws IndexOutOfBoundsException If detail cannot be split into 2 words
     */
    public DeadlineCommand(TaskList tasks, String detail) throws IndexOutOfBoundsException, DateTimeParseException {
        super(tasks);
        String[] details = detail.split("/by", 2);

        if (details.length < 2) {
            throw new IndexOutOfBoundsException(Messages.DEADLINE_ERROR_MESSAGE);
        }

        this.description = details[0].trim();
        this.by = LocalDateTime.parse(details[1].trim(), DateFormats.INPUT_DATE_FORMAT);
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
