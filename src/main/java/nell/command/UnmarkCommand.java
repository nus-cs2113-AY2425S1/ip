package nell.command;

import nell.TaskList;
import nell.common.Messages;

/**
 * Represents an executable unmark command
 */
public class UnmarkCommand extends Command{
    /**
     * Constructs a UnmarkCommand object with a specified task list and task index
     *
     * @param tasks The specified task list
     * @param taskIndex The specified task index
     */
    public UnmarkCommand(TaskList tasks, int taskIndex){
        super("unmark", tasks, taskIndex);
    }

    /**
     * Executes task - marks a specified task as not done.
     * If the task is not in the task list, informs user that the task is invalid.
     */
    public void execute() {
        try {
            this.tasks.getTaskAtIndex(taskIndex - 1).setDone(false);
            System.out.println(Messages.UNMARK_MESSAGE);
            System.out.println(this.tasks.getTaskStringAtIndex(taskIndex - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
        }
    }
}
