package nell.command;

import nell.TaskList;
import nell.common.DateFormats;
import nell.common.Messages;
import nell.tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an executable event command
 */
public class EventCommand extends Command{
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a new EventCommand object with specified task list and command body
     *
     * @param tasks The specified task list
     * @param detail The task description and its start and end details
     * @throws IndexOutOfBoundsException If detail cannot be split into 2 words
     */
    public EventCommand(TaskList tasks, String detail) throws IndexOutOfBoundsException, DateTimeParseException {
        super("event", tasks);
        String[] details = detail.split("/from|/to", 3);

        if (details.length < 3) {
            throw new IndexOutOfBoundsException(Messages.EVENT_ERROR_MESSAGE);
        }

        this.description = details[0].trim();
        this.from = LocalDateTime.parse(details[1].trim(), DateFormats.INPUT_DATE_FORMAT);
        this.to = LocalDateTime.parse(details[2].trim(), DateFormats.INPUT_DATE_FORMAT);
    }

    /**
     * Executes command - adds new Event task to the task list
     */
    @Override
    public void execute() {
        Event eventToAdd = new Event(this.description, this.from, this.to);
        this.tasks.addTask(eventToAdd);
    }
}
