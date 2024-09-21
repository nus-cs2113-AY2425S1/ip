package nell.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a new Task object, with a description as specified in the input string
     *
     * @param description The description of the task
     * @param type The type of task
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Constructs a new Task object, with a specified doneness for the task
     *
     * @param description The description of the task
     * @param isDone Whether the task is done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.type = " ";
    }

    /**
     * Gets a status icon for the task, depending on whether it has been marked
     * done or not
     *
     * @return X if isDone is true, space otherwise
     */
    public char getStatusIcon() {
        return (isDone) ? 'X' : ' ';
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
        return String.format("[%S][%s] %s", this.type, this.getStatusIcon(), this.description);
    }

    /**
     * Formats a string for writing into a .txt file
     *
     * @return the formatted string for writing to the file
     */
    public String getFileLine() {
        return String.format("%s|%s|%s", this.type, this.getStatusIcon(), this.description);
    }

    /**
     * Returns true if the task description contains a specified keyword.
     * Returns false otherwise
     *
     * @param keyword The keyword
     * @return whether the keyword is present in the task description
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
