package ran.task;

/**
 * Represents a generic user-input task. A <code>Task</code> object corresponds to a generic task represented
 * with the attributes of its description as a string and whether it is done as a boolean value.
 * To be used as a base class for classes of specific types of task to inherit.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of a <code>Task</code> object.
     * Task is initialised as undone.
     *
     * @param description String representing description attribute given to the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for the description attribute of the task.
     *
     * @return String representing description attribute of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for marking the task as done.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Setter for marking the task as not done.
     */
    public void setAsUndone() {
        isDone = false;
    }

    /**
     * Overload method returning a formatted string of the object.
     * To be overloaded by child classes.
     *
     * @return String representing the task according to how it should be displayed
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Represent the task in the way it is saved to a data file.
     * To be overloaded by child classes.
     *
     * @return Empty string
     */
    public String dataFileInput() {
        return "";
    }

}
