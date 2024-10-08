package pythia.utility;
import pythia.task.Task;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class used for storing and managing a list of {@link Task} objects.
 * This class allows adding, removing, marking tasks as done, and
 * iterating over the task list. It also keeps track of the number of
 * remaining tasks that have not been completed.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private int remainingTasks = 0;      // number of tasks which are not done yet
                                                // might be different from array size
    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {}

    /**
     * Returns the number of tasks that have not been marked as done.
     *
     * @return the number of remaining tasks
     */
    public int getNumberOfRemainingTasks() {
        return remainingTasks;
    }

    /**
     * Returns the total number of tasks in the list, regardless of their completion status.
     *
     * @return the total number of tasks
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }

    /**
     * Adds a new task to the list and increases the count of remaining tasks.
     *
     * @param task the {@link Task} to be added to the list
     */
    public void add(Task task) {
        taskList.add(task);
        remainingTasks++;
    }

    /**
     * Removes the task at the specified index from the list and decreases the count of remaining tasks.
     *
     * @param taskNumber the index of the task to be removed
     * @throws IndexOutOfBoundsException if the index is out of range (less than 0 or greater than or equal to the list size)
     */
    public void remove(int taskNumber) throws IndexOutOfBoundsException {
        if (taskNumber < 0 || taskNumber >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(taskNumber);
        remainingTasks--;
    }

    /**
     * Returns the task at the specified index in the list.
     *
     * @param taskNumber the index of the task to retrieve
     * @return the {@link Task} at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (less than 0 or greater than or equal to the list size)
     */
    public Task get(int taskNumber) {
        return taskList.get(taskNumber);
    }

    /**
     * Marks the task at the specified index as done and decreases the count of remaining tasks.
     *
     * @param taskNumber the index of the task to mark as done
     * @throws IndexOutOfBoundsException if the index is out of range (less than 0 or greater than or equal to the list size)
     */
    public void markAsDone(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskNumber).markAsDone();
        remainingTasks--;
    }

    /**
     * Returns an iterator over the tasks in the list in proper sequence.
     *
     * @return an {@link Iterator} over the tasks in the list
     */
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    /**
     * Returns a string representation of the task list. Each task is prefixed with its index number
     * and listed in sequence. The tasks are separated by new lines.
     *
     * @return a string representing the task list
     */
    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "";
        }

        StringBuilder taskListString = new StringBuilder();
        for (int i = 0; i < taskList.size() - 1; i++) {
            taskListString.append(i + 1).append(". ");
            taskListString.append(taskList.get(i).toString());
            taskListString.append("\n");
        }
        int lastIndex = taskList.size() - 1;
        taskListString.append(lastIndex + 1).append(". ");
        taskListString.append(taskList.get(lastIndex).toString());

        return taskListString.toString();
    }
}
