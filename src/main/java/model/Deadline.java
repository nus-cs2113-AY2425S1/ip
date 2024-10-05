package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    // Constructor that takes LocalDateTime for 'by'
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D][" + (isDone() ? "X" : " ") + "] " + getDescription() + " (by: " + by.format(formatter) + ")";
    }

    public String saveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by.format(formatter);
    }
}
