package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;
import doug.tasks.Task;
import static doug.UI.DASHED_LINE;

import java.io.IOException;

/**
 * Represents the actions of the Delete command
 * Removes the indicated task from the ArrayList of tasks
 */
public class DeleteCommand extends Command {

    private static int taskIndex;

    public DeleteCommand(int taskIndex) {
        DeleteCommand.taskIndex = taskIndex;
    }

    /**
     * Removes the selected task from the existing TaskList object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task removedTask = tasks.getTask(taskIndex - 1);
        tasks.removeTask(taskIndex - 1);

        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "I've deleted: " + removedTask + " for you.");
        System.out.println("Your list is now " + tasks.getCount() + " tasks long partner\n" + DASHED_LINE);
    }
}
