package nateh.commands;

import nateh.tasks.TaskList;
import nateh.ui.Skeleton;
import nateh.ui.Ui;

/**
 * Represents the command to list out all tasks in the TaskList
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.print(Skeleton.LINE_BREAK);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            taskList.get(i).print();
        }
        System.out.print(Skeleton.LINE_BREAK);
    }
}
