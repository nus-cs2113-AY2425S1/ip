package niwa.data.task;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;

import java.util.ArrayList;

public class TaskList {
    private static TaskList instance;
    private final ArrayList<Task> tasks;

    // Private constructor to prevent instantiation
    private TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of TaskList.
     *
     * @return the TaskList instance
     */
    public static synchronized TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return the size of the list of tasks
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     * @throws NiwaDuplicateTaskException if the current list
     *         contains the task
     */
    public void addTask(Task task) throws NiwaDuplicateTaskException {
        if (isContainingDuplicate(task)) {
            throw new NiwaDuplicateTaskException();
        }
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param index the index of the task to delete
     * @return the deleted task
     * @throws NiwaTaskIndexOutOfBoundException if the index is
     *         less than 0 or greater than or equal to the size of the task list
     */
    public Task deleteTask(int index) throws NiwaTaskIndexOutOfBoundException {
        Task temp = findTask(index);
        tasks.remove(index);
        return temp;
    }

    /**
     * Clears the content of the task list.
     *
     */
    public void clearTaskList() {
        tasks.clear();
    }

    /**
     * Finds a task in the task list by index.
     *
     * @param index the index of the task to find
     * @return task if found
     * @throws NiwaTaskIndexOutOfBoundException if the index is
     *         less than 0 or greater than or equal to the size of the task list
     */
    public Task findTask(int index) throws NiwaTaskIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new NiwaTaskIndexOutOfBoundException(tasks.size());
        }

        return tasks.get(index);
    }

    /**
     * Checks for duplicate tasks in the task list.
     * The {@link Task#isSameTask} method is used for this comparison, which
     * defines a weaker notion of equality.
     *
     * @param task the task to check
     */
    public boolean isContainingDuplicate(Task task){
        for (Task p : tasks) {
            if (p.isSameTask(task)) {
                return true;
            }
        }
        return false;
    }



}
