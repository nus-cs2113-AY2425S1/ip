package nateh.commands;

import java.io.IOException;

import nateh.storage.TaskEncoder;
import nateh.tasks.TaskList;
import nateh.ui.Skeleton;
import nateh.ui.Ui;

/**
 * Represents marking a task in the TaskList as incomplete
 */
public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        if (taskList.isEmpty()) {
            throw new NullPointerException();
        }
        taskList.get(index).setDone(false);
        TaskEncoder.unmarkTask(index);
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Aw you didn't get to finish. :(");
        taskList.get(index).print();
        System.out.print(Skeleton.LINE_BREAK);
    }
}
