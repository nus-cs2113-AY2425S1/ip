package CassHelpers.commands;

import CassHelpers.exceptions.InvalidDateFormatException;
import CassHelpers.exceptions.InvalidDateRangeException;
import CassHelpers.exceptions.InvalidEventFormatException;
import CassHelpers.types.Event;
import CassHelpers.util.Storage;
import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static CassHelpers.util.Parser.*;

/**
 * Command class responsible for adding an event task to the task list.
 * It parses the input string for the event name, start time, and end time,
 * and creates a new event task that is added to the task list and saved to storage.
 */
public class AddEventCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final String input;
    private final int FROM_INDEX_OFFSET = 6;
    private final int TO_INDEX_OFFSET = 4;
    private final int EVENT_INDEX_OFFSET = 5;

    /**
     * Constructs a new AddEventCommand.
     *
     * @param tasks The TaskList object where the new event task will be added.
     * @param input The user input containing the event details.
     */
    public AddEventCommand(TaskList tasks, String input) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.input = input;
    }

    /**
     * Executes the command by parsing the input for an event task and adding it to the task list.
     *
     * @throws InvalidEventFormatException If the event format is invalid.
     * @throws InvalidDateFormatException If the date format is invalid.
     */
    @Override
    public void execute() throws InvalidEventFormatException, InvalidDateFormatException, InvalidDateRangeException {
        int fromIndex = input.indexOf("/from") + FROM_INDEX_OFFSET;
        int toIndex = input.indexOf("/to");

        if (fromIndex < FROM_INDEX_OFFSET || toIndex < 0) {
            throw new InvalidEventFormatException("Sorry, event entered has the wrong format");
        }

        String from = input.substring(fromIndex, toIndex).trim();
        String to = input.substring(toIndex + TO_INDEX_OFFSET).trim();
        String eventTaskName = input.substring(EVENT_INDEX_OFFSET, fromIndex - FROM_INDEX_OFFSET).trim();

        LocalDateTime fromDate = parseDateTime(from);
        LocalDateTime toDate = parseDateTime(to);
        DayOfWeek dayOfWeek = parseDayOfWeek(to);

        if (dayOfWeek != null) {
            // Adjust 'toDate' to the next occurrence of the given day of the week after 'toDate'
            toDate = getNextDayOfWeek(toDate, dayOfWeek);
        }

        if (toDate.isBefore(fromDate)) {
            throw new InvalidDateRangeException("The end date cannot be earlier than the start date.");
        }

        Event newEvent = new Event(eventTaskName, fromDate, toDate);
        taskList.add(newEvent);
        storage.appendTaskToFile(newEvent);
        System.out.println("Got it. I've added this event: \n " + newEvent.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
