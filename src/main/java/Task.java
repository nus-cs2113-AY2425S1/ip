/**
 * Class for the task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Builder method for a task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }


    /**
     * Mark a class as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Get the description of the task inputted by the user.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the current status of the task.
     */
    public String currentStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Return the to be saved format for the current task.
     */
    public String toSave() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Return the string format for the task.
     */
    @Override
    public String toString() {
        return currentStatus() + " " + description;
    }



}
