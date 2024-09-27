package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;

import static doug.UI.DASHED_LINE;

import java.io.IOException;

/**
 * Represents the actions of the Mark command
 * Marks the selected task as done
 */
public class MarkCommand extends Command {

    private static int taskIndex;

    public MarkCommand(int taskIndex) {
        MarkCommand.taskIndex = taskIndex;
    }

    /**
     * Marks the selected task as done
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.getTask(taskIndex - 1).markAsDone();
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as done");
        System.out.print(tasks.getTask(taskIndex - 1).toString() + "\n" + DASHED_LINE);
    }
}
