package doug.Commands;

import doug.Storage;
import doug.TaskList;

import java.io.IOException;

import static doug.UI.DASHED_LINE;

public class UnMarkCommand extends Command {
    public static void unmarkTask(TaskList tasks, int taskIndex) throws IOException {
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
