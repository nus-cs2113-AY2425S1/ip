package chattycharlie.task;

import chattycharlie.commands.CommandType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//Events
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, String start, String end) {
        super(description, CommandType.EVENT);
        String[] startParts = start.split(" ");
        String[] endParts = end.split(" ");
        this.startDate = LocalDate.parse(startParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.startTime = LocalTime.parse(startParts[1], DateTimeFormatter.ofPattern("HH:mm"));
        this.endDate = LocalDate.parse(endParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.endTime = LocalTime.parse(endParts[1], DateTimeFormatter.ofPattern("HH:mm"));
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + startTime + " to: "
                + endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + endTime + ")";
    }

    @Override
    public String toSaveFormat() {
        return "[E]" + super.toSaveFormat() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + startTime + " to: "
                + endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + endTime + ")";
    }
}