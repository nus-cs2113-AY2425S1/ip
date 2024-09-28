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

public class AddDeadlineCommand implements Command {
    private final List<Task> taskList;
    private final Storage storage;
    private final String input;
    private final int byIndexOffset = 4;
    private final int deadlineIndexOffset=8;

    public AddDeadlineCommand(TaskList tasks, String input) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.input = input;
    }

    @Override
    public void execute() throws InvalidDeadlineFormatException, InvalidDateFormatException {
        int byIndex = input.indexOf("/by");

        if (byIndex < 0) {
            throw new InvalidDeadlineFormatException("Sorry, deadline entered has the wrong format");
        }

        String by = input.substring(byIndex + byIndexOffset).trim();
        String deadlineTaskName = input.substring(deadlineIndexOffset, byIndex).trim();

        LocalDateTime deadline = Parser.parseDateTime(by);

        Deadline newDeadline = new Deadline(deadlineTaskName,deadline);
        taskList.add(newDeadline);
        storage.appendTaskToFile(newDeadline);
        System.out.println("Got it. I've added this task: \n " + newDeadline.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
