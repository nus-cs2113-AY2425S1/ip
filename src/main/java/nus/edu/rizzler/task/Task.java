package nus.edu.rizzler.task;

import nus.edu.rizzler.ui.Emoji;

public class Task {
    private Emoji emoji = new Emoji();
    private String taskName;
    private Boolean isDone;

    public Task(String taskName, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? emoji.getTickEmoji() : emoji.getHourglassEmoji());
    }

    @Override
    public String toString() {
        return String.format(" %s %s", getStatusIcon(), taskName);
    }

    public String toCSV() {
        return String.format("%s, %s", isDone, taskName);
    }
}
