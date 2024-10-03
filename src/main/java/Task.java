/**
 * Represents a task with a description and completion status.
 * This class provides functionality to create a task, mark it as done or not done,
 * check if a task description contains a specific substring, and represent the task
 * as a string.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task
     * The status icon is represented as "X" if the task is done,
     * and this (" ") when the task is not done.
     *
     * @return A string representing the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done.
     */
    public void setMarkAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setMarkAsNotDone() {
        isDone = false;
    }

    /**
     * Checks if the task description contains a specified substring.
     *
     * @param subject The substring to be searched in the task description.
     * @return True if the description contains the substring, false otherwise
     */
    public boolean isContain(String subject) {
        return description.toLowerCase().contains(subject.toLowerCase());
    }

    /**
     * Returns a string representation of the task
     * The string representation includes the status icon and task description
     *
     * @return A string representing the task
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
