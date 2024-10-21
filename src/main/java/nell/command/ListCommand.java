package nell.command;

import nell.list.TaskList;
import nell.common.Messages;

/**
 * Represents an executable "list" command
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Constructs a new ListCommand object given a task list
     *
     * @param tasks The given task list
     */
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * Executes the list command, by printing out all tasks in the task list
     */
    @Override
    public void execute() {
        System.out.println(Messages.LIST_TASKS_MESSAGE);
        int taskCount = tasks.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            // Prints all tasks in list
            System.out.println(tasks.getTaskStringAtIndex(i));
        }
    }
}
