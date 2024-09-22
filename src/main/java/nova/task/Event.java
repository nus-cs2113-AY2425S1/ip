package nova.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    LocalDate from;
    LocalDate to;

    public Event (String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        printAcknowledgementMessage(getTaskInfo());
    }

    public Event (String isDone, String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    public String getTaskInfo() {
        return "[E][" + this.getStatusIcon() + "] " + description
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getTaskStorageInfo() {
        return "E" + DIVIDER + this.getStatusIcon() + DIVIDER + description + DIVIDER + from + DIVIDER + to;
    }

    public boolean isDate(LocalDate date) {
        if (date.isBefore(from) || date.isAfter(to)) {
            return false;
        }
        return true;
    }

}
