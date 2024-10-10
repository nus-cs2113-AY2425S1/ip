package jeff.helper;

import jeff.task.Task;
import java.util.ArrayList;


/**
 * TaskList class is responsible for managing a list of tasks.
 * It allows adding, removing, and retrieving tasks from its list.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Default constructor that initializes an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor that initializes the task list with an existing list of tasks.
     *
     * @param tasks an ArrayList of Task objects to initialize the task list with.
     */
    public TaskList(ArrayList<Task> tasks){
        this.taskList = tasks;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve.
     * @return the Task object at the given index.
     */
    public Task getTask(int index){
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks in the list.
     */
    public int getCount(){
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the Task object to add to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index the index of the task to remove.
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }
}
