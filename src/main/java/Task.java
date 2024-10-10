public class Task {
    protected boolean isDone;
    protected String task;

    /**
     * Represents a task with a description and a completion status.
     * The task can be marked as done or not done.
     *
     * @param task the description of the task
     */
    public Task(String task) {
        this(task, false);
    }

    /**
     * Represents a task with a description and a specified completion status.
     * The task can be marked as done or not done.
     *
     * @param task   the description of the task
     * @param isDone the initial completion status of the task
     */
    public Task(String task, boolean isDone) {
        this.task = task.trim();
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description as a string
     */
    public String getTask() {
        return task;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status
     * (marked with "X" for done and " " for not done) and its description.
     *
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        return "[" +
                (isDone ? "X" : " ") +
                "] " +
                task;
    }

}
