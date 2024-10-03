package Ryan.commands;

import Ryan.utility.*;
import Ryan.tasks.*;
import Ryan.exceptions.RyanException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        if (!isValidIndex(index, tasks.size())) {
            throw new RyanException("Invalid task number.");
        }

        Task task = tasks.getTask(index); // Get the task before removal
        tasks.deleteTask(index); // Now delete the task
        ui.showTaskDeleted(task, tasks.size());
    }

    private boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}
