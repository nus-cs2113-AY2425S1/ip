package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;
import codecatalyst.exception.InvalidTaskNumberException;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String input) throws InvalidTaskNumberException {
        try {
            this.taskIndex = Integer.parseInt(input.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("         " + input + " is not a number.");
        }
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception {
        if (taskIndex < 0 || taskIndex >= tasklist.getSize()) {
            throw new InvalidTaskNumberException("         Task number is out of bounds!");
        }

        tasklist.markTaskAsNotDone(taskIndex);
        ui.printTaskUnmarked(tasklist.getTask(taskIndex));
        storage.saveTasksToFile(tasklist.getTasks());
    }
}
