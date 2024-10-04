package Ryan.commands;

import Ryan.tasks.*;
import Ryan.exceptions.RyanException;
import Ryan.utility.TaskList;
import Ryan.utility.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        if (!isValidIndex(index, tasks.size())) {
            throw new RyanException("Invalid task number.");
        }

        Task task = tasks.getTask(index);
        task.unmark();
        ui.showTaskUnmarked(task);
    }

    private boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}
