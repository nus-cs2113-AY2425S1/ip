package CassHelpers.commands;

import CassHelpers.exceptions.InvalidDeadlineFormatException;
import CassHelpers.types.Deadline;
import CassHelpers.util.Storage;
import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.util.List;

public class AddDeadlineCommand implements Command {
    private final List<Task> taskList;
    private final Storage storage;
    private final String input;
    private final int byIndexOffset = 4;

    public AddDeadlineCommand(TaskList tasks, String input) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.input = input;
    }

    @Override
    public void execute() throws InvalidDeadlineFormatException {
        int byIndex = input.indexOf("/by");

        if (byIndex < 0) {
            throw new InvalidDeadlineFormatException("Sorry, deadline entered has the wrong format");
        }

        String by = input.substring(byIndex + byIndexOffset).trim();
        String deadlineTaskName = input.substring(0, byIndex).trim();

        Deadline newDeadline = new Deadline(deadlineTaskName, by);
        taskList.add(newDeadline);
        storage.appendTaskToFile(newDeadline);
        System.out.println("Got it. I've added this task: \n " + newDeadline.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
