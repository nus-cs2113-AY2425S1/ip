package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;
import codecatalyst.exception.InvalidTaskNumberException;
import codecatalyst.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param input The input string containing the task index.
     * @throws InvalidTaskNumberException If the input is not a valid number.
     */
    public DeleteCommand(String input) throws InvalidTaskNumberException {
        try {
            this.taskIndex = Integer.parseInt(input.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Input is not a valid task number.", input);
        }
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception {
        if (taskIndex < 0 || taskIndex >= tasklist.getSize()) {
            throw new InvalidTaskNumberException("Task number is out of bounds!", taskIndex);
        }

        Task removedTask = tasklist.removeTask(taskIndex);
        ui.printTaskDeleted(removedTask, tasklist.getSize());
        storage.saveTasksToFile(tasklist.getTasks());
    }
}
