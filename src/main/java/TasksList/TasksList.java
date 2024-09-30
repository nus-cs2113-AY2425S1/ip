package TasksList;
import Tasks.Task;
import java.util.ArrayList;

public class TasksList {
    private static ArrayList<Task> theTaskList;

    /**
     * Creates an empty list of Tasks.
     */
    public TasksList() {
        theTaskList = new ArrayList<Task>();
    }   

    public ArrayList<Task> getTasksList() {
        return theTaskList;
    }

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
