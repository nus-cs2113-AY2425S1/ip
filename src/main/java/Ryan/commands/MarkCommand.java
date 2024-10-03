package Ryan.commands;

import Ryan.tasks.*;
import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.exceptions.RyanException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index - 1 ;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        if (!isValidIndex(index, tasks.size())) {
            throw new Ryan.exceptions.RyanException("Invalid task number.");
        }

        Task task = tasks.getTask(index); // Get the task before marking
        task.mark();
        ui.showTaskMarked(task);
    }

    private boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}

