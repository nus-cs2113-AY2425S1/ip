package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.tasks.Task;
import static doug.UI.DASHED_LINE;

import java.io.IOException;

/**
 * Base class for all other command classes to inherit from
 */
public class Command {

    /**
     * Adds the newly created task to the existing TaskList object holding the ArrayList of tasks
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param newTask The newly created task (Todo, Deadline or Event)
     */
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
