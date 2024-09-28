package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;
    DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

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

    public String getStart(){
        return start.format(stringFormatter);
    }

    public String getEnd(){
        return end.format(stringFormatter);
    }

}
