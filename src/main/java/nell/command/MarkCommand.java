package nell.command;

import nell.list.TaskList;
import nell.common.Messages;

/**
 * Represents an executable mark command
 */
public class MarkCommand extends Command{
    /**
     * Constructs a MarkCommand object with a specified task list and task index
     *
     * @param tasks The specified task list
     * @param taskIndex The specified task index
     */
    public MarkCommand(TaskList tasks, int taskIndex){
        super("mark", tasks, taskIndex);
    }

    /**
     * Executes task - marks a specified task as done.
     * If task is not in task list, informs user that the task is invalid.
     */
    public void execute() {
        try {
            this.tasks.getTaskAtIndex(taskIndex - 1).setDone(true);
            System.out.println(Messages.MARK_MESSAGE);
            System.out.println(this.tasks.getTaskStringAtIndex(taskIndex - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
        }
    }
}
