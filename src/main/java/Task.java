public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description. The task is initially not done.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone(){
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the task with its status icon.
     */
    @Override
    public String toString(){
        return ("[" + getStatusIcon() + "] " + description);
    }
}
