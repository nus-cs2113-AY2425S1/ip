package nateh.commands;

import java.io.IOException;

import nateh.storage.TaskEncoder;
import nateh.tasks.Deadline;
import nateh.tasks.TaskList;
import nateh.ui.Ui;

/**
 * Class represents the command that adds a deadline task to the {@code TaskList}
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes adding a deadline to the TaskList parameter
     * @param taskList the TaskList object being executed on
     * @param ui the Ui object used for user interactions
     * @throws IOException when a file error occurs
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        taskList.addTask(deadline);
        TaskEncoder.addTask(deadline.toFileFormat());
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
}
