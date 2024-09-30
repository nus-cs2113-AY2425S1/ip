package CassHelpers.commands;

import CassHelpers.util.TaskList;

/**
 * Command class responsible for handling the exit operation.
 * It stops the task list execution and prints a goodbye message.
 */
public class ExitCommand implements Command {
    private final TaskList taskList;

    /**
     * Constructs a new ExitCommand.
     *
     * @param tasks The TaskList object used to control the running state.
     */
    public ExitCommand(TaskList tasks) {
        this.taskList = tasks;
    }

    /**
     * Executes the command by stopping the running state of the task list.
     */
    @Override
    public void execute() {
        taskList.setRunningState(false);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
