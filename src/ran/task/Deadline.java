package ran.task;

/**
 * Represents an user-input Deadline task. An <code>Deadline</code> object corresponds to a deadline task 
 * represented with the attributes of the description of the task to finish before the deadline as a string, 
 * the deadline itself as a string, 
 * and whether it is done as a boolean value.
 * Inherits from the base Task class.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor of a <code>Deadline</code> object.
     * Deadline is initialised as undone.
     * Useful for when user inputs a deadline task.
     *
     * @param description String representing description given to the deadline task
     * @param by String representing the time of the deadline itself
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Alternative constructor of a <code>Deadline</code> object.
     * Attribute of whether the deadline task is done can be controlled.
     * Useful for reading previously created deadline task from a data file.
     *
     * @param isDone Boolean value representing whether the deadline task has been done
     * @param description String representing description given to the deadline task
     * @param by String representing the time of the deadline itself
     */
    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Overload method returning a formatted string of the object.
     *
     * @return String representing the deadline task according to how it should be displayed
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Represent the deadline task in the way it is saved to a data file.
     *
     * @return String representing how the deadline task is saved to a data file
     */
    public String dataFileInput() {
        return "D, " + (isDone ? "1, " : "0, ") + description + ", " + by;
    }
}
