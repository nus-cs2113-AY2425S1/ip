package akshan.command;

import akshan.task.TaskList;

import java.util.stream.IntStream;

public final class FindCommand extends Command {
    /**
     * Constructor for FindCommand.
     *
     * @param commandType The command from the user.
     * @param taskString The string appended to the command to be executed.
     * @param taskList The list of tasks.
     */
    public FindCommand(CommandType commandType, String taskString, TaskList taskList) {
        super(commandType, taskString, taskList);
    }

    /**
     * Lists all tasks with the keyword
     *
     * @throws IndexOutOfBoundsException If list is empty.
     */
    @Override
    public void execute() throws IndexOutOfBoundsException {
        if (taskList.size() == 0) {
            throw new IndexOutOfBoundsException("Oops the list is empty!");
        }

        TaskList toBePrinted = new TaskList();

        IntStream.range(0, taskList.size())
                .filter(index -> taskList
                        .getTask(index)
                        .toString()
                        .contains(taskString))
                .forEach(index -> toBePrinted.addItem(taskList.getTask(index)));

        if (toBePrinted.size() == 0) {
            System.out.println("Got it. Sadly, there are no tasks that match the keyword '" + taskString + "'.");
        }
        else {
            System.out.println("Got it. Here are the tasks with the matching keyword '" + taskString + "':");
            IntStream.range(0, toBePrinted.size())
                    .forEach(index -> System.out.println("  " + (index + 1) + "."
                            + taskList
                            .getTask(index)
                            .toString()));
        }
    }
}
