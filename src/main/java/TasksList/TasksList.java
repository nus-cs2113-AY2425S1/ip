package TasksList;
import Tasks.Task;
import java.util.ArrayList;

/**
 * The TasksList class represents a list of tasks.
 * It provides methods to add, remove, and retrieve tasks from the list.
 * The list is implemented as a static ArrayList of Task objects.
 * 
 * <p>Usage example:
 * <pre>
 *     TasksList tasksList = new TasksList();
 *     tasksList.addTask(new Task("Example Task"));
 *     Task task = tasksList.get(0);
 * </pre>
 * </p>
 */
public class TasksList {
    private static ArrayList<Task> theTaskList;

    /**
     * Creates an empty list of Tasks.
     */
    public TasksList() {
        theTaskList = new ArrayList<Task>();
    }   

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasksList() {
        return theTaskList;
    }

    /**
     * Sets the list of tasks to the specified list.
     *
     * @param TaskList the list of tasks to set
     */
    public void setTasksList(ArrayList<Task> TaskList) {
        TasksList.theTaskList = TaskList;
    }

    /**
     * Adds a person to the address book.
     *
     */
    public void addTask(Task toAdd) {
        theTaskList.add(toAdd);
    }

    /**
     * Removes the task at the specified index in the tasks list.
     *
     * @param index the index of the task to remove
     * @return the task that was removed from the list
     */
    public Task removeTask(int index) {
        return theTaskList.remove(index);
    }
    
    /**
     * Returns the number of tasks in the tasks list.
     *
     * @return the size of the tasks list
     */
    public int size() {
        return theTaskList.size();
    }

    /**
     * Returns the task at the specified position in the tasks list.
     *
     * @param index the index of the task to return
     * @return the task at the specified position in the tasks list
     */
    public Task get(int index) {
        return theTaskList.get(index);
    }
}
