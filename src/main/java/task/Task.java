package task;

/**
 * Represent general Task with description and completion status
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Construct Task with defined description and initialize it as not done.
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon to represent task being done or not done.
     * @return "X" if the task is done, else it will be blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    /**
     * Mark task as done.
     */
    public void markTaskDone() {
        isDone = true;
    }

    /**
     * Unmark task as not done.
     */
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * Returns description of task.
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
