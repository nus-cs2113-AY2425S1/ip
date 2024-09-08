package November.Tasks;

/**
 * Defines a task with a description and completion status.
 */
public class Task {

    /** Description of the task. */
    protected String description;

    /** Indicates task completion status. */
    protected boolean isComplete;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description Describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as complete.
     */
    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void setIncomplete() {
        this.isComplete = false;
    }

    /**
     * Returns an icon representing the task's completion status.
     *
     * @return "X" if the task is completed, or " " if it is not.
     */
    public String getStatusIcon() {
        return (isComplete ? "X" : " ");
    }

    /**
     * Returns the task icon. Default is an empty space.
     *
     * @return A string representing the task icon.
     */
    public String getTaskIcon() {
        return " ";
    }

    /**
     * Prints the task.
     * Default implementation does nothing.
     */
    public void printTask() {
        System.out.println();
    }
}
