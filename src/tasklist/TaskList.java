package tasklist;

import task.Task;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of Task objects.
 * It provides methods to add, delete, and mark tasks,
 * as well as retrieve the list and its size.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor that initializes TaskList with a given list of tasks.
     *
     * @param list The initial list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    /**
     * Default constructor that initializes an empty task list.
     */
    public TaskList(){
        list = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getListSize() {
        return list.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task){
        list.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The 1-based index of the task to be deleted.
     */
    public void deleteTask(int taskNumber){
        list.remove(taskNumber - 1);
    }

    /**
     * Marks or unmarks a task based on the provided boolean value.
     *
     * @param taskNumber The 1-based index of the task to be marked.
     * @param isMarked If true, marks the task as done; otherwise, unmarks it.
     */
    public void markTask(int taskNumber, boolean isMarked){
        list.get(taskNumber - 1).setMarked(isMarked);
    }
}
