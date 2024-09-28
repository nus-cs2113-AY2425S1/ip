package tasks;

import java.time.LocalDate;

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
