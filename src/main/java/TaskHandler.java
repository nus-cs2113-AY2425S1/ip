import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The TaskHandler class manages the list of tasks, including adding, removing,
 * marking as done, and unmarking tasks. It interacts with the Fenix class
 * to validate and retrieve task indices based on user input.
 */
public class TaskHandler {
    private Fenix fenix;
    private ArrayList<Task> taskArrayList;

    /**
     * Constructs a TaskHandler associated with the given Fenix instance.
     * Initializes an empty list of tasks.
     *
     * @param fenix The Fenix instance that this TaskHandler interacts with.
     */
    public TaskHandler(Fenix fenix) {
        this.fenix = fenix;
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Returns an unmodifiable view of the task list to prevent external modification.
     *
     * @return an unmodifiable list of tasks.
     */
    public List<Task> getTaskArrayList() {
        return Collections.unmodifiableList(taskArrayList);
    }

    /**
     * Marks the task specified by the task number as done.
     * Retrieves the task index from Fenix and marks the corresponding task.
     *
     * @param taskNumber The task number provided by the user.
     * @return The task that was marked as done, or null if the task number is invalid.
     */
    public Task markAsDone(String taskNumber) {
        int taskIndex = fenix.getTaskIndexFromInput(taskNumber);
        if (taskIndex == -1) {
            return null;
        }
        return markTaskAsDone(taskIndex);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param taskNumber The index of the task to mark as done.
     * @return The task that was marked as done.
     */
    private Task markTaskAsDone(int taskNumber) {
        Task task = taskArrayList.get(taskNumber);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks the task specified by the task number, setting it as not done.
     * Retrieves the task index from Fenix and unmarks the corresponding task.
     *
     * @param taskNumber The task number provided by the user.
     * @return The task that was unmarked, or null if the task number is invalid.
     */
    public Task unmarkAsDone(String taskNumber) {
        int taskIndex = fenix.getTaskIndexFromInput(taskNumber);
        if (taskIndex == -1) {
            return null;
        }
        return unmarkTaskAsDone(taskIndex);
    }

    /**
     * Unmarks the task at the specified index, setting it as not done.
     *
     * @param taskNumber The index of the task to unmark.
     * @return The task that was unmarked.
     */
    private Task unmarkTaskAsDone(int taskNumber) {
        Task task = taskArrayList.get(taskNumber);
        task.unmarkAsDone();
        return task;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void storeTask(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Deletes the task specified by the task number from the task list.
     * Retrieves the task index from Fenix and removes the corresponding task.
     *
     * @param taskNumber The task number provided by the user.
     * @return The task that was deleted, or null if the task number is invalid.
     */
    public Task deleteTask(String taskNumber) {
        int taskIndex = fenix.getTaskIndexFromInput(taskNumber);
        if (taskIndex == -1) {
            return null;
        }
        Task task = taskArrayList.get(taskIndex);
        taskArrayList.remove(taskIndex);
        return task;
    }

    public ArrayList<Task> findTasks(String commandInfo) {
        ArrayList<Task> foundArrayList = new ArrayList<>();
        for (Task task : taskArrayList) {
            String taskName = task.getTaskName();
            String formattedTaskInfo = task.getFormattedTaskInfo();
            String formattedTaskName = formattedTaskInfo.split("\\(", 2)[0];
            if (taskName.contains(commandInfo) || formattedTaskName.contains(commandInfo)) {
                foundArrayList.add(task);
            }
        }
        return foundArrayList;
    }

    public int getNumberOfUnfinishedTasks() {
        int numberOfUnfinishedTasks = 0;
        for (Task task : taskArrayList) {
            boolean isDone = task.getIsDone();
            if (!isDone) {
                numberOfUnfinishedTasks++;
            }
        }
        return numberOfUnfinishedTasks;
    }
}
