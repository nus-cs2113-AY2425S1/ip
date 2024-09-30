package appal.task;

/**
 * Task class is a generic class for tasks,
 * with a task attribute that contains task details,
 * and isDone attribute to indicate completion,
 * and contains methods to handle tasks.
 */
public class Task {
    protected String task;
    protected boolean isDone;
    private static int totalTasks = 0;

    /**
     * Class constructor.
     *
     * @param task Description of the task.
     */
    public Task(String task) {
        setTask(task);
        setDone(false);
        totalTasks += 1;
    }

    /**
     * Returns a String character indicating whether a task has been completed or not.
     * A completed task will return "X" while an uncompleted task will return " ".
     *
     * @return String indicator.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a String '1' or '0', when a task is completed or not respectively.
     *
     * @return Number as a String to indicate task completion.
     */
    public String getStatusValue() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns the String representation of a Task when printed in the task list,
     * with indication of completion and task description.
     *
     * @return String representation of Task for printing.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTask();
    }

    /**
     * Returns the description of the Task as a String.
     *
     * @return String containing task description.
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets task description.
     *
     * @param task Description of task.
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Sets a task as done or undone.
     *
     * @param isDone If task is done, true, else false.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the total number of tasks stored by Appal.
     *
     * @return Number of tasks stored by Appal.
     */
    public static int getTotalTasks() {
        return totalTasks;
    }

    /**
     * Updates the total number of tasks stored by Appal,
     * whenever a task is added or removed.
     *
     * @param totalTasks Updated number of tasks.
     */
    public static void setTotalTasks(int totalTasks) {
        Task.totalTasks = totalTasks;
    }

    /**
     * Returns String representation of a Task when stored and saved in a text file,
     * including task description and additional information based of type of task.
     *
     * @return String representation of a Task for saving.
     */
    public String getTaskInfo() {
        return task;
    }
}
