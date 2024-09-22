package nova.task;

import nova.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    LocalDate by;

    public Deadline (String description, LocalDate by) {
        super(description);
        this.by = by;
        printAcknowledgementMessage(getTaskInfo());
    }

    public Deadline (String isDone, String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    public String getTaskInfo() {
        return "[D][" + this.getStatusIcon() + "] " + description + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getTaskStorageInfo() {
        return "D" + DIVIDER + this.getStatusIcon() + DIVIDER + description + DIVIDER + by;
    }

    public boolean isDate(LocalDate date) {
        if (by.equals(date)) {
            return true;
        }
        return false;
    }
}
