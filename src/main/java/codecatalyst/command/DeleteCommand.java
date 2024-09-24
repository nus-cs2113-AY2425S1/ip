package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;
import codecatalyst.exception.InvalidTaskNumberException;
import codecatalyst.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;
    public DeleteCommand(String input) throws InvalidTaskNumberException {
        try {
            this.taskIndex = Integer.parseInt(input.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Please enter a valid task number for deletion.");
        }
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception {
        if (taskIndex < 0 || taskIndex >= tasklist.getSize()) {
            throw new InvalidTaskNumberException("         Task number is out of bounds!");
        }

        Task removedTask = tasklist.removeTask(taskIndex);
        ui.printTaskDeleted(removedTask, tasklist.getSize());
        storage.saveTasksToFile(tasklist.getTasks());
    }
}
