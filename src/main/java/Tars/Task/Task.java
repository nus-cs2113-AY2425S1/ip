package Tars.Task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {}

    // Constructor that takes a task description, with the initial status set to not done.
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns the task status icon, "X" if the task is done, otherwise a space.
    public String getStatusIcon() {
        return (isDone ? "X" : " ");  // true -- "X"; false -- " ";
    }

    // Marks the task as done.
    public void markAsDone() {
        this.isDone = true;
    }

    // Marks the task as not done.
    public void markAsNotDone () {
        this.isDone = false;
    }

    // Returns the string representation of the task, including the status icon and description.
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}