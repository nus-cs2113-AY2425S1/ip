package nell.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a new Task object, with a description as specified in the input string
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
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

    /**
     * Sets the isDone attribute in the class to the specified boolean value
     *
     * @param done The boolean value isDone is to be set to
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return String.format("[ ][%s] %s", this.getStatusIcon(), this.description);
    }
}
