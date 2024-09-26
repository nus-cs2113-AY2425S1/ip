package quinn.task;

/**
 * Represents an abstract base class for all types of tasks in the Quinn task management application.
 * This class defines common properties and behaviors for all tasks.
 *
 */
public abstract class Task {
    private final TaskType type;
    private final String description;

    /** The completion status of the task. */
    private boolean isDone;

    /**
     * Constructs a new Task with the specified type and description.
     *
     * @param type the type of the task
     * @param description the description of the task
     */
    public Task(TaskType type, String description) {
        // By default, the task is not done
        this(type, description, false);
    }

    /**
     * Constructs a new Task with the specified type, description, and completion status.
     *
     * @param type the type of the task
     * @param description the description of the task
     * @param isDone the initial completion status of the task
     */
    public Task(TaskType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "[✔]" if the task is done, "[ ]" otherwise
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[✔]"; // mark done task with ✔
        } else {
            return "[ ]";
        }
    }

    /** Marks the task as done. */
    public void setDone() {
        this.isDone = true;
    }

    /**Marks the task as not done.*/
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains the given keyword (case-insensitive).
     *
     * @param keyword the keyword to search for in the task description
     * @return true if the task description contains the keyword, false otherwise
     */
    public boolean hasKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task, including its type, status, and description
     */
    @Override
    public String toString() {
        return "[" + type.getAbbreviation() + "] " + getStatusIcon() + " " + description;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return a string representation of the task for file storage
     */
    public String saveFormat() {
        return type.getAbbreviation() + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
