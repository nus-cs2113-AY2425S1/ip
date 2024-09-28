package CassHelpers.commands;

import CassHelpers.exceptions.InvalidDateFormatException;
import CassHelpers.exceptions.InvalidEventFormatException;
import CassHelpers.types.Event;
import CassHelpers.util.Storage;
import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static CassHelpers.util.Parser.parseDateTime;

public class AddEventCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final String input;
    private final int fromIndexOffset = 6;
    private final int toIndexOffset = 4;
    private final int eventIndexOffset = 5;

    public AddEventCommand(TaskList tasks, String input) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.input = input;
    }

    @Override
    public void execute() throws InvalidEventFormatException, InvalidDateFormatException {
        int fromIndex = input.indexOf("/from") + fromIndexOffset;
        int toIndex = input.indexOf("/to");

        if (fromIndex < fromIndexOffset || toIndex < 0) {
            throw new InvalidEventFormatException("Sorry, event entered has the wrong format");
        }

        String from = input.substring(fromIndex, toIndex).trim();
        String to = input.substring(toIndex + toIndexOffset).trim();
        String eventTaskName = input.substring(eventIndexOffset, fromIndex - fromIndexOffset).trim();

        LocalDateTime fromDate = parseDateTime(from);
        LocalDateTime toDate = parseDateTime(to);

        Event newEvent = new Event(eventTaskName, fromDate, toDate);
        taskList.add(newEvent);
        storage.appendTaskToFile(newEvent);
        System.out.println("Got it. I've added this event: \n " + newEvent.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
