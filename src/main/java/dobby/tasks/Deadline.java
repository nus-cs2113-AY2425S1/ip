package dobby.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;
    protected LocalDateTime byDateAndTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        try {
            byDateAndTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e2) {
                this.by = by;
            }
        }
    }

    public String getBy() {
        if (byDateAndTime != null) {
            return byDateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else if (byDate != null) {
            return byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return by;
        }
    }

    @Override
    public String toString() {
        String formattedTime;

        if (byDateAndTime != null) {
            formattedTime = byDateAndTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH.mm"));
        } else if (byDate != null) {
            formattedTime = byDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            formattedTime = by;
        }

        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
