public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task object, with a description as specified in the input string
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of a Task object
     * 
     * @return The description of the Task object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets a status icon for the task, depending on whether it has been marked
     * done or not
     *
     * @return X if isDone is true, space otherwise
     */
    public char getStatusIcon() {
        if (isDone) {
            return 'X';
        }

        return ' ';
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
