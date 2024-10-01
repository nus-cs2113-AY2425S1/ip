// Task.java
package Yukee.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskType() {
        return " "; // Default task type, should be overridden in subclasses
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
