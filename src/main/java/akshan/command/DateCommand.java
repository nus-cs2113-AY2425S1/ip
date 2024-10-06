package akshan.command;

import akshan.handler.DateTime;
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

        if (taskList.size() == 0) {
            throw new IndexOutOfBoundsException("Oops the list is empty!");
        }

        String dateTimeString = DateTime.convertToString(taskString);
        TaskList toBePrinted = new TaskList();

        IntStream.range(0, taskList.size())
                .filter(index -> taskList
                        .getTask(index)
                        .toString()
                        .contains(dateTimeString))
                .forEach(index -> toBePrinted.addItem(taskList.getTask(index)));

        if (toBePrinted.size() == 0) {
            System.out.println("Got it. Sadly, there are no tasks that match the date " + dateTimeString + ".");
        } else {
            System.out.println("Got it. Here are the tasks with the matching date " + dateTimeString + ":");
            IntStream.range(0, toBePrinted.size())
                    .forEach(index -> System.out.println("  " + (index + 1) + "."
                            + taskList
                            .getTask(index)
                            .toString()));
        }
    }
}
