package others;

public interface TaskOperations {
    /**
     * Marks task as done.
     */
    void setAsDone();
    /**
     * Marks task as undone.
     */
    void setAsUndone();
    /**
     * Returns a string version of the task.
     */
    String toString();
}
