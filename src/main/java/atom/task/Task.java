package atom.task;

/**
 * Represents an abstract class containing methods and attributes specific
 * to a task.
 */
public abstract class Task {
    /** Description/name of task */
    protected String item;
    protected boolean isDone;

    public Task(String item) {
        this.item = item;
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return Task name.
     */
    public String getItem() {
        return item;
    }

    /**
     * Returns a string denoting the done status of a task.
     *
     * @return <code>"X"</code> if task is done, <code>" "</code> otherwise.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     * <p>
     * Sets done status, <code>isDone</code> to <code>true</code>.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     * <p>
     * Sets done status, <code>isDone</code> to <code>false</code>.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a symbol string that represents a specific task type.
     * <p>
     * For <code>todo</code> tasks, symbol is <code>T</code>.
     * <p>
     * For <code>deadline</code> tasks, symbol is <code>D</code>.
     * <p>
     * For <code>event</code> tasks, symbol is <code>E</code>.
     * <p>
     * This method is overridden in all sub classes of <code>Task</code>.
     *
     * @return Task Symbol.
     */
    public String setTaskType() {
        return "";
    }

    /**
     * Returns the due date of the <code>deadline</code> task.
     * <p>
     * This method is overridden in the <code>Deadline</code> class.
     *
     * @return Due date of <code>deadline</code> task.
     */
    public String getDueDate() {
        return "";
    }

    /**
     * Returns the start date of the <code>event</code> task.
     * <p>
     * This method is overridden in the <code>Event</code> class.
     *
     * @return Start date of <code>event</code> task.
     */
    public String getStartDate() {
        return "";
    }

    /**
     * Returns the end date of the <code>event</code> task.
     * <p>
     * This method is overridden in the <code>Event</code> class.
     *
     * @return End date of <code>event</code> task.
     */
    public String getEndDate() {
        return "";
    }
}
