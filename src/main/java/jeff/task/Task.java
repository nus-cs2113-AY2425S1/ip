package jeff.task;

/**
 * Represents a task with a description and a completion status.
 * The <code>Task</code> class provides methods to get the task's status and manipulate its state.
 */
public class Task {

    protected String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, represented by "X" if completed or a space if not.
     *
     * @return A string representing the completion status icon.
     */
    public String getStatusIcon() { //Returns X or " " depending on completion status
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status number of the task.
     * Returns 1 if the task is marked as done, otherwise returns 0.
     *
     * @return An integer representing the completion status (1 for done, 0 for not done).
     */
    public int getStatusNumber(){ //Returns 1 if task is marked as finished
        return (isDone ? 1 : 0);
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone A boolean indicating the new completion status of the task.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task's content for file storage.
     * This includes the status number and the description of the task.
     *
     * @return A string formatted for file storage.
     */
    public String fileContent(){
        return " | " + getStatusNumber() + " | " + description;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string that represents the task.
     */
    @Override
    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }
}
