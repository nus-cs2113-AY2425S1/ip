package main.java;

/**
 * Represents a task with a description/name and its completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String formatForStorage();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task's completion status icon.
     *
     * @return "X" if task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description/name of the task.
     *
     * @return The task description.
     */
    public String getName() { 
        return description; 
    }

    public void markAsDone(){
        this.isDone = true; 
    }

    public void markAsUndone(){
        this.isDone = false; 
    }

    /**
     * Returns a formatted string of the task.
     *
     * @return The string of the task.
     */
    public String toString() {return "["+ getStatusIcon() +"] "+getName();}

    // ToDo class
    public static class ToDo extends Task {
        /**
         * Create a ToDo task with the specific description.
         *
         * @param description The description of the ToDo task.
         */
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

        /**
         * Creates a Deadline task with the specific description and due date.
         *
         * @param description The description of the Deadline task.
         * @param dueDate The due date of the Deadline task.
         */
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

        /**
         * Creates an Event task with the specific description, start date, and end date.
         * @param description The description of the Event task.
         * @param startDate The start date of the Event task.
         * @param endDate The end date of the Event task.
         */
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