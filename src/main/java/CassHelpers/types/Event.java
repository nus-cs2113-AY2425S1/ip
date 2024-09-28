package CassHelpers.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    public Event(String taskName, LocalDateTime fromTime,LocalDateTime toTime) {
        super(taskName);
        setFromTime(fromTime);
        setToTime(toTime);
        setFrom(this.fromTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm")));
        setTo(this.toTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm")));
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
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

    public String getFromString(){
        return this.fromTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    public String getToString(){
        return this.toTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")";
    }

    public String toWritableString(){
        return "E"+super.toWritableString()+","+from+","+to;
    }
}
