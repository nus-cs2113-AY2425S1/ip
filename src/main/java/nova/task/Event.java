package nova.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public static final String TYPE = "E";
    LocalDate from;
    LocalDate to;

    /**
     * Constructs an Event task with the description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    public Event (String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        printAcknowledgementMessage(getTaskInfo());
    }

    /**
     * Constructs an Event task with the specified status, description, start time, and end time.
     * The task is marked as done if the status string is "X".
     * This function is used to load Event task from storage
     *
     * @param isDone     The status of the task ("X" for done, otherwise not done).
     * @param description The description of the Event task.
     * @param from       The start time of the Event task.
     * @param to         The end time of the Event task.
     */
    public Event(String isDone, String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Event task, including its status icon, description, start time, and end time.
     *
     * @return The Event task information as a string.
     */
    @Override
    public String getTaskInfo() {
        return "[E][" + this.getStatusIcon() + "] " + description
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the Event task for storage purposes.
     *
     * @return A string representing the Event task for storage.
     */
    @Override
    public String getTaskStorageInfo() {
        return "E" + DIVIDER + this.getStatusIcon() + DIVIDER + description + DIVIDER + from + DIVIDER + to;
    }

    /**
     * Checks if the event occurs on a given date.
     * This method compares the task's event range (from-to) to the input date.
     *
     * @param date The date to check against.
     * @return true if the input date falls within the event's start and end date, false otherwise.
     */
    @Override
    public boolean isDate(LocalDate date) {
        if (date.isBefore(from) || date.isAfter(to)) {
            return false;
        }
        return true;
    }

}
