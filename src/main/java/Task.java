/**
 * The Task class is an abstract class that represents a generic task.
 * It contains common attributes and behaviors for tasks, such as marking tasks
 * as done or undone. Specific types of tasks should extend this class and provide
 * additional functionality.
 */
public abstract class Task {
    protected boolean isDone = false;
    protected String taskName = "";
    protected String formattedTaskInfo = "";

    /**
     * Default constructor for the Task class.
     * Initializes an empty task.
     */
    public Task() {
    }

    /**
     * Constructs a new Task with the specified task name.
     * The task is initialized as not done by default.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false; // Default to not done
    }

    /**
     * Constructs a new Task with the specified completion status and task information.
     *
     * @param isDone           Boolean value indicating whether the task is done.
     * @param formattedTaskInfo A string representing the formatted task information.
     */
    public Task(boolean isDone, String formattedTaskInfo) {
        this.isDone = isDone;
        this.formattedTaskInfo = formattedTaskInfo;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Retrieves the formatted task information, which may include additional details about the task.
     *
     * @return The formatted task information.
     */
    public String getFormattedTaskInfo() {
        return formattedTaskInfo;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }


    /**
     * Marks the task as done by setting the isDone flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the isDone flag to false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Abstract method to return the string representation of the task.
     * Must be implemented by subclasses to provide task-specific details.
     *
     * @return A string representing the task.
     */
    @Override
    public abstract String toString();
}
