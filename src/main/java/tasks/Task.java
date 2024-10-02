package tasks;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to retrieve the task details, mark the task as done or not done,
 * and convert the task into a format suitable for saving to a file.
 */

public class Task {
    private String task;
    private boolean isDone;

    public Task() {
        task = "";
    }

    public boolean getIsDone() {
        return isDone;
    }

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

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

    public String fileFormat(){
        return ("T | " + (getIsDone() ? "+" : "-") + " | " + getTask());
    }
}
