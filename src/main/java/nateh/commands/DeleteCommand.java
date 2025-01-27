package nateh.commands;

import java.io.IOException;

import nateh.storage.TaskEncoder;
import nateh.tasks.Task;
import nateh.tasks.TaskList;
import nateh.ui.Skeleton;
import nateh.ui.Ui;

/**
 * Class represents the command where a task is deleted from the TaskList
 */
public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command deleting the {@code Task} at the index
     * @param taskList the TaskList object being executed on
     * @param ui the Ui object used for user interactions
     * @throws IOException when an input/output error occurs
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        Task temp = taskList.get(index);
        taskList.remove(index);
        TaskEncoder.deleteTask(index);
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Okay. I've deleted the task:");
        temp.print();
        System.out.printf("Now you have %d tasks\n", taskList.size());
        System.out.print(Skeleton.LINE_BREAK);
    }
}
