package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs during a specific time period.
 * It extends the Task class and adds start and end times to the task.
 * This class provides methods to retrieve the start and end times, as well as
 * to format the task for file storage.
 */

public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;
    DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    /**
     * Constructs a new Event task with the specified task description, start time, and end time.
     *
     * @param task The description of the event task.
     * @param start The LocalDateTime object representing the start time of the event.
     * @param end The LocalDateTime object representing the end time of the event.
     */

    public Event(String task, LocalDateTime start, LocalDateTime end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getStatusIcon(){
        return "[E]" + (getIsDone() ? "[X]" : "[ ]");
    }

    @Override
    public String fileFormat(){
        return ("E | " + (getIsDone() ? "+" : "-") + " | " + getTask() + " | from " + start.format(fileFormatter) + " to " + end.format(fileFormatter));
    }

    /**
     * Returns the end time of the event in a human-readable format.
     * The start time is formatted as "dd-MM-yyyy hh:mm a".
     *
     * @return A string representing the formatted end time.
     */

    public String getStart(){
        return start.format(stringFormatter);
    }

    /**
     * Returns the end time of the event in a human-readable format.
     * The end time is formatted as "dd-MM-yyyy hh:mm a".
     *
     * @return A string representing the formatted end time.
     */

    public String getEnd(){
        return end.format(stringFormatter);
    }

}
