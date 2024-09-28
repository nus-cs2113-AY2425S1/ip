package king;

/**
 * Represents a task with a description and a status indicating whether it is completed.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task Object with the given description.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as done by setting the completion status to false.
     */
    protected void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done or not.
     * A "[X]" icon indicates that the task is completed, while "[ ]" indicates it is not completed.
     */
    protected String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the description for this task, including its status icon and the description text.
     */
    protected String getTaskDescription() {
        return getStatusIcon() + description;
    }

    /**
     * Returns the description for this task.
     */
    protected String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }


}