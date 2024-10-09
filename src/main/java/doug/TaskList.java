package doug;

import doug.Main.DougException;
import doug.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the ArrayList containing all the added Tasks
 * Contains methods to access and modify tasks in the ArrayList
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private static int counter;
    private static UI ui;

    public TaskList() {
        counter = 0;
        tasks = new ArrayList<>();
        ui = new UI();
    }

    /**
     * Adds a new task to the ArrayList
     *
     * @param task New task object to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        counter++;
    }

    /**
     * Returns the entire ArrayList as it is
     *
     * @return The current ArrayList
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a specific task based on it's index in the ArrayList
     *
     * @param id Index of the task to be returned
     * @return The task object specified by the input index
     */
    public  Task getTask(int id) {
        return tasks.get(id);
    }

    /**
     * Removes a specific task based on it's index in the ArrayList
     *
     * @param id Index of the task to be removed
     */
    public void removeTask(int id) {
        tasks.remove(id);
        counter--;
    }

    /**
     * Returns the number of tasks currently in the ArrayList
     *
     * @return Size of the ArrayList
     */
    public int getCount() {
        return counter;
    }

    /**
     * Checks if a certain input index is within the current size of the ArrayList
     *
     * @param listIndex Index to check
     * @throws DougException If the index lies outside the size of the ArrayList
     */
    public void checkOutOfBounds(int listIndex) throws DougException {
        if (listIndex > counter || listIndex <= 0) {
            throw new DougException(ui.getDashedLine()
                    + "No can do bud, your input is invalid!\n"
                    + ui.getDashedLine());
        }
    }
}
