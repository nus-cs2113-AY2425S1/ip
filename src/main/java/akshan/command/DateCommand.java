package akshan.command;

import akshan.handler.DateTime;
import akshan.task.Task;
import akshan.task.TaskList;

import java.util.stream.IntStream;

public final class DateCommand extends Command {
    /**
     * Constructor for DateCommand.
     *
     * @param commandType The command from the user.
     * @param taskString The string appended to the command to be executed.
     * @param taskList The list of tasks.
     */
    public DateCommand(CommandType commandType, String taskString, TaskList taskList) {
        super(commandType, taskString, taskList);
    }

    /**
     * Lists all tasks that occur on specified date.
     *
     * @throws IllegalArgumentException If the date format is invalid.
     */
    @Override
    public void execute() throws IllegalArgumentException {
        if (!DateTime.isDateTime(taskString)) {
            throw new IllegalArgumentException(taskString + " is not a date/time!");
        }

        String dateTimeString = DateTime.convertToString(taskString);

        System.out.println("Got it. Here are the tasks with the matching date:");
        IntStream.range(0, taskList.size())
                .mapToObj(index -> taskList.getTask(index).toString())
                .filter(string -> DateTime.convertToString(string).contains(dateTimeString))
                .forEach(string -> System.out.println("  " + string));
    }
}
