package doug.Commands;

import doug.Storage;
import doug.TaskList;
import static doug.UI.DASHED_LINE;

import java.io.IOException;

/**
 * Represents the actions of the Unmark command
 * Marks the selected task as not done
 */
public class UnMarkCommand extends Command {

    /**
     * Marks the selected task as not done
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param taskIndex The index of the task in the ArrayList to be marked
     */
    public static void unmarkTask(TaskList tasks, int taskIndex) {
        tasks.getTask(taskIndex - 1).markAsNotDone();
        try {
            Storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as not done");
        System.out.print(tasks.getTask(taskIndex - 1).toString() + "\n" + DASHED_LINE);
    }
}
