package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by; // The deadline date

    // Updated constructor to handle LocalDate
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    // Format the date as "MMM dd yyyy" (e.g., Dec 02 2019)
    @Override
    public String toString() {
        return "[D][" + (isDone() ? "X" : " ") + "] " + getDescription() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    // Store in save format "D | 0 | description | yyyy-MM-dd"
    public String saveFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}
