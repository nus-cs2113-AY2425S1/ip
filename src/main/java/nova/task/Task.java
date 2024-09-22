package nova.task;

import nova.Ui;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public static final String DIVIDER = " | ";

    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static void removeTask() {
        numberOfTasks--;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }
    public void printAcknowledgementMessage(String message) {
        Ui.displayAcknowledgementMessage(message, numberOfTasks);
    }

    public String getTaskInfo() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String getTaskStorageInfo() {
        return "";
    }

    public boolean isDate(LocalDate date) {
        return false;
    }

}
