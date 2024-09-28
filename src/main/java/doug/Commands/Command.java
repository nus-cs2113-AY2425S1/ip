package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;
import doug.tasks.Task;

import java.io.IOException;

/**
 * Base class for all other command classes to inherit from
 */
public class Command {
    protected static boolean isBye;

    public Command(){
        isBye = false;
    }

    public void setBye() {
        Command.isBye = true;
    }

    public boolean saidBye() {
        return isBye;
    }

    /**
     * Overloaded method that executes the specific action of each inherited Command class
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {

    }

    /**
     * Adds the newly created task to the existing TaskList object holding the ArrayList of tasks
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param newTask The newly created task object (Todo, Deadline or Event)
     */
    public static void addNewTask(TaskList tasks, Task newTask, UI ui, Storage storage) {
        tasks.addTask(newTask);

        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(ui.getDashedLine() + "I've added: " + newTask + " for you.");
        System.out.println("Your list is now " + tasks.getCount() + " tasks long partner\n" + ui.getDashedLine());
    }

}
