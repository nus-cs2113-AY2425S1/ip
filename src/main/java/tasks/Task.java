package tasks;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to retrieve the task details, mark the task as done or not done,
 * and convert the task into a format suitable for saving to a file.
 */

public class Task {
    private final String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task() {
        task = "";
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the task description.
     *
     * @return The description of the task.
     */

    public String getTask() {
        return task;
    }

    /**
     * Returns the status icon for the task, which is "[X]" if the task is done
     * and "[ ]" if it is not done.
     *
     * @return A string representing the status icon of the task.
     */

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setStatus() {
        isDone = true;
    }

    public void unsetStatus() {
        isDone = false;
    }

    public String getContents() {
        return task;
    }

    @Override
    public String toString() {
        return this.task;
    }

    /**
     * Returns the task in a file-saving format, including its type and status.
     *
     * @return A string in the format suitable for saving to a file.
     */

    public String fileFormat(){
        return ("T | " + (getIsDone() ? "+" : "-") + " | " + getTask());
    }
}
