package main.java;

import java.util.ArrayList;
import java.util.List;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String formatForStorage();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Check if task description contains a keyword
    public boolean containsKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

        public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getName() { 
        return description; 
    }

    public void markAsDone(){
        this.isDone = true; 
    }

    public void markAsUndone(){
        this.isDone = false; 
    }

    public String toString() { return "["+ getStatusIcon() +"] "+getName(); }

    // ToDo class
    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        public String toString() {
            return "[T]" + super.toString();
        }

        public String formatForStorage() {
            return "T | " + (isDone ? "1" : "0") + " | " + description;
        }
    }

    // Deadline class
    public static class Deadline extends Task {
        public String dueDate;

        public Deadline(String description, String dueDate) {
            super(description);
            this.dueDate = dueDate;
        }

        public String toString() {
            return "[D]" + super.toString() + " (by: " + dueDate + ")";
        }
        public String formatForStorage() {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
        }
    }

    // Event class
    public static class Event extends Task {
        public String startDate;
        public String endDate;

        public Event(String description, String startDate, String endDate) {
            super(description);
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String toString() {
            return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
        }

        public String formatForStorage() {
            return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startDate + " | " + endDate;
        }
    }

}