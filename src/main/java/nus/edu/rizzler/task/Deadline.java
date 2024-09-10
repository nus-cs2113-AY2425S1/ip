package nus.edu.rizzler.task;

import nus.edu.rizzler.ui.Emoji;

public class Deadline extends Task {
    Emoji emoji = new Emoji();
    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        setBy(by);
    }

    @Override
    public String getTaskTag() {
        return "[D]";
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String toString() {
        String taskStatus = this.getIsDone() ? emoji.getTickEmoji() : emoji.getHourglassEmoji();
        return String.format("%s %s (by: %s) %s", this.getTaskTag(), this.getTaskName(), this.getBy(), taskStatus);
    }
}