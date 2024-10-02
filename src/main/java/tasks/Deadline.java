package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task that has a specific deadline.
 * It extends the Task class, adding a deadline date and time.
 * This class includes methods for retrieving the deadline and formatting
 * the task for file storage and display.
 */

public class Deadline extends Task{
    private LocalDateTime deadline;
    DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public Deadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getStatusIcon(){
        return "[D]" + (getIsDone() ? "[X]" : "[ ]");
    }

    @Override
    public String fileFormat(){
        return ("D | " + (getIsDone() ? "+" : "-") + " | " + getTask() + " | by " + deadline.format(fileFormatter));
    }

    public String getDeadline(){
        return this.deadline.format(stringFormatter);
    }
}
