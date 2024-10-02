package apsea.command;

import apsea.task.TaskList;
import apsea.ui.Ui;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui) {
        ui.printTasks(taskList);
    }
}
