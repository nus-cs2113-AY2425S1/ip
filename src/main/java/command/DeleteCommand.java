package command;

import data.Storage;
import exceptions.IrisException;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    private final String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    /**
     * Delete a task from the task list
     * @param tasks Task list to delete the targeted task from
     * @param storage File to save and load the task list
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws IrisException {
        Task taskToDelete = Parser.getTaskNum(tasks, description);
        tasks.remove(taskToDelete);
        printDeleteTaskMessage(taskToDelete, tasks.size());
    }

    private static void printDeleteTaskMessage(Task taskToDelete, int size) {
        System.out.println("Noted. I've removed this task:\n"
                + taskToDelete
                + "\nNow you have "
                + size
                + " tasks in the list");
    }
}
