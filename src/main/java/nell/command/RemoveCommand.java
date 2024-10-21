package nell.command;

import nell.list.TaskList;
import nell.common.Messages;

/**
 * Represents an executable remove command
 */
public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    /**
     * Constructs a new RemoveCommand object with a specified task list and index
     *
     * @param tasks The specified task list
     * @param taskIndex The specified task index
     */
    public RemoveCommand(TaskList tasks, int taskIndex) {
        super(tasks, taskIndex);
    }

    /**
     * Executes command - removes the specified task from the task list.
     * If the task is not in the task list, informs user that the task is invalid
     */
    @Override
    public void execute() {
        try {
            this.tasks.removeTask(this.taskIndex - 1);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
        }
    }
}
