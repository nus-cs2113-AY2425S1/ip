package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;

import static doug.UI.DASHED_LINE;

import java.io.IOException;

/**
 * Represents the actions of the Unmark command
 * Marks the selected task as not done
 */
public class UnMarkCommand extends Command {

    private static int taskIndex;

    public UnMarkCommand(int taskIndex) {
        UnMarkCommand.taskIndex = taskIndex;
    }

    /**
     * Marks the selected task as not done
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.getTask(taskIndex - 1).markAsNotDone();
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as not done");
        System.out.print(tasks.getTask(taskIndex - 1).toString() + "\n" + DASHED_LINE);
    }
}
