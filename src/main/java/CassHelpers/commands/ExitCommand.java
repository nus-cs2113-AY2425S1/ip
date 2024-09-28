package CassHelpers.commands;

import CassHelpers.util.TaskList;

public class ExitCommand implements Command {
    private final TaskList taskList;

    public ExitCommand(TaskList tasks) {
        this.taskList = tasks;
    }

    @Override
    public void execute() {
        taskList.setRunningState(false);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
