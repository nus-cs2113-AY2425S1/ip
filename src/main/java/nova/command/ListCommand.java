package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.task.Task;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static void execute(TaskList taskManager) {
        Ui.displaySeparator();
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("     " + (i + 1) + "." + taskManager.getTaskInfo(i));
        }
        Ui.displaySeparator();
    }
}
