package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.tasks.Task;

import java.io.IOException;

import static doug.UI.DASHED_LINE;

public class Command {
    public static void addNewTask(TaskList tasks, Task newTask) {
        tasks.addTask(newTask);

        try {
            Storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "I've added: " + newTask + " for you.");
        System.out.println("Your list is now " + tasks.getCount() + " tasks long partner\n" + DASHED_LINE);
    }

}
