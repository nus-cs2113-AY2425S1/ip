package commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return ("[D]" + super.getStatusIcon());
    }

    @Override
    public String createTaskList() {
        return (getStatusIcon()+  " " +  description + "(by:" + by + ")");
    }

    @Override
    public String createTaskTxt() {
        return ("D | " + super.getStatus() + " | " + description + " | " + by);
    }
}
