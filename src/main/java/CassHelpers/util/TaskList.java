package CassHelpers.util;

import CassHelpers.types.Task;
import java.util.ArrayList;

/**
 * The TaskList class manages a collection of Task objects and interacts with the Storage class for persistence.
 * It supports functionality to manage the state of the task list and handle tasks during runtime.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private boolean isRunning = true;

    /**
     * Constructor for TaskList that initializes the task list and assigns a Storage object for task persistence.
     *
     * @param storage The Storage object responsible for file operations related to task saving and loading.
     */
    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Constructor for TaskList that initializes the task list with an existing list of tasks and assigns a Storage object.
     *
     * @param taskList The ArrayList of tasks to initialize the task list.
     * @param storage  The Storage object responsible for file operations related to task saving and loading.
     */
    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Returns the running state of the TaskList.
     * This is used to determine whether the application should continue running or exit.
     *
     * @return The boolean value indicating if the TaskList is running.
     */
    public boolean getRunningState() {
        return this.isRunning;
    }

    /**
     * Sets the running state of the TaskList.
     * Can be used to signal the application to stop or continue execution.
     *
     * @param isRunning The new running state to be set for the TaskList.
     */
    public void setRunningState(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Retrieves the current list of tasks stored in the TaskList.
     *
     * @return The ArrayList of Task objects representing the tasks in the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Retrieves the Storage object associated with the TaskList.
     * This can be used for interacting with file storage for saving and loading tasks.
     *
     * @return The Storage object responsible for handling task persistence.
     */
    public Storage getStorage() {
        return this.storage;
    }
}
