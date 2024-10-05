package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;
import exception.InvalidTaskNumberException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(String taskIndex) throws InvalidTaskNumberException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex) - 1; 
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception {
        if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
            tasks.getTask(taskIndex).unmarkTask();
            ui.showMessage("Marked this task as not done:\n" + tasks.getTask(taskIndex));
            storage.saveTasksToFile(tasks.getTasks());  // Save after unmarking task
        } else {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }
}
