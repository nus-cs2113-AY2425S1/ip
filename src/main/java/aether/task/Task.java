package aether.task;

/**
 * Abstract base class for all types of tasks.
 * <p>
 * The {@code Task} class provides the foundational structure for task objects,
 * including common attributes such as description and completion status.
 * It defines abstract methods for serializing and deserializing tasks,
 * ensuring that all subclasses implement these essential functionalities.
 * </p>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone {@code true} if the task is completed, {@code false} otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a checkmark if the task is done, otherwise returns an empty space.
     *
     * @return "✓" if the task is done, otherwise " ".
     */
    public String getStatus() {
        return (isDone ? "✓" : " ");
    }

    /**
     * Returns "1" if the task is done, otherwise "0" for storage purposes.
     *
     * @return "1" if done, "0" otherwise.
     */
    public String getStatusForStorage() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    /**
     * Converts the task to a savable string format.
     *
     * @return A string representation of the task suitable for storage.
     */
    public abstract String toDataString();

    /**
     * Creates a {@code Task} object from its stored string representation.
     *
     * @param data The stored string representation of the task.
     * @return The {@code Task} object created from the stored data.
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
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }
}
