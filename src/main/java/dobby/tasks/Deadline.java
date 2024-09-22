package dobby.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalDateTime dateAndTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        try {
            dateAndTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                date = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e2) {
                this.by = by;
            }
        }
    }

    public String getBy() {
        if (dateAndTime != null) {
            return dateAndTime.format(DateTimeFormatter.ofPattern("yyyy MM dd HH.mm"));
        } else if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("yyyy MM dd"));
        } else {
            return by;
        }
    }

    public void setBy(String by) {
        this.by = by;

        try {
            dateAndTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy MM dd HH.mm"));
            date = null;
        } catch (DateTimeParseException e) {
            try {
                date = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy MM dd"));
                dateAndTime = null;
            } catch (DateTimeParseException e2) {
                date = null;
                dateAndTime = null;
            }
        }
    }

    @Override
    public String toString() {
        String formattedTime;

        if (dateAndTime != null) {
            formattedTime = dateAndTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH.mm"));
        } else if (date != null) {
            formattedTime = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            formattedTime = by;
        }

        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
