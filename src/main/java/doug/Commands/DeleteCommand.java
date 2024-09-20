package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.tasks.Task;

import java.io.IOException;

import static doug.UI.DASHED_LINE;

public class DeleteCommand extends Command {
    public static void deleteTask(TaskList tasks, int taskIndex) throws IOException {
        Task removedTask = tasks.getTask(taskIndex - 1);
        tasks.removeTask(taskIndex - 1);

        try {
            Storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "I've deleted: " + removedTask + " for you.");
        System.out.println("Your list is now " + tasks.getCount() + " tasks long partner\n" + DASHED_LINE);
    }
}
