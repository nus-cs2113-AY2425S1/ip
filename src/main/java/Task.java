/**
 * Represents a task with a description and its completion status.
 * Tasks can be marked as done or not done, and their status is tracked.
 */
public class Task {
    // Description of the task
    protected String description;

    // Status of whether the task is done
    protected boolean isDone;

    /**
     * Default constructor for Task, initializes the task with an empty description
     * and marks it as not done.
     */
    public Task() {
        description = "";
        isDone = false;
    }

    /**
     * Constructor to create a task with a specific description.
     * The task is initialized as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon for the task.
     * If the task is done, an "X" is returned, otherwise a blank space is returned.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     * Throws an exception if the task is already marked as done.
     *
     * @throws DukeException if the task is already marked as done.
     */
    public void markAsDone() throws DukeException {
        try {
            if (isDone) {
                throw new DukeException("This task is already marked as done"); // throw exception if the task is marked
            }
            isDone = true;
        } catch (DukeException e) {
            e.displayMessage();
        }
    }

    /**
     * Marks the task as not done.
     * Throws an exception if the task is already marked as not done.
     *
     * @throws DukeException if the task is already marked as not done.
     */
    public void markAsNotDone() throws DukeException {
        try {
            if (!isDone) {
                throw new DukeException("This task is already marked as undone"); // throw exception if the task is unmark
            }
            isDone = false;
        } catch (DukeException e) {
            e.displayMessage();
        }
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
