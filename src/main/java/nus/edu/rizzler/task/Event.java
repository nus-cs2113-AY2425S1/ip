package nus.edu.rizzler.task;

import nus.edu.rizzler.ui.Emoji;

public class Event extends Task{
    Emoji emoji = new Emoji();
    private String from;
    private String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        setFrom(from);
        setTo(to);
    }

    @Override
    public String getTaskTag() {
        return "[E]";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String toString() {
        String taskStatus = this.getIsDone() ? emoji.getTickEmoji() : emoji.getHourglassEmoji();
        return String.format("%s %s (from: %s  to: %s) %s", this.getTaskTag(), this.getTaskName(), this.getFrom(), this.getTo(), taskStatus);
    }
}
