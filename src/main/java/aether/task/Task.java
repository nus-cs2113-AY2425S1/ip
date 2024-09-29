package aether.task;

/**
 * Abstract base class for all types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns "✓" if the task is done, otherwise returns " ".
     */
    public String getStatus() {
        return (isDone ? "✓" : " ");
    }

    /**
     * Returns "1" if the task is done, otherwise "0" for storage purposes.
     */
    public String getStatusForStorage() {
        return (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    /**
     * Converts the task to a savable string format.
     */
    public abstract String toDataString();

    /**
     * Converts a stored string back to a Task object.
     * @param data The stored string representation of the task.
     * @return The Task object.
     */
    public static Task fromStorage(String data) {
        // Logic to parse the task from a stored string representation
        String[] parts = data.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        }
        if (task != null) {
            task.setDone(isDone);
        }
        return task;
    }

    /**
     * Get the description of the task.
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }
}
