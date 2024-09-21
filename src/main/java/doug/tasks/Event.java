package doug.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected String from;
    protected String to;
    protected LocalDateTime fromDateAndTime;
    protected LocalDate fromDate;
    protected LocalDateTime toDateAndTime;
    protected LocalDate toDate;

    public Event(String description, String from, String to){
        super(description);

        try {
            fromDateAndTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.from = "";
        }  catch (DateTimeParseException e1) {
            try {
                fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.from = "";
            } catch (DateTimeParseException e2) {
                this.from = from;
            }
        }

        try {
            toDateAndTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = "";
        }  catch (DateTimeParseException e1) {
            try {
                toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.to = "";
            } catch (DateTimeParseException e2) {
                this.to = to;
            }
        }
    }

    @Override
    public String toString(){
        String fromInString;
        if (from.isEmpty()) {
            if (fromDateAndTime == null) {
                fromInString = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } else {
                fromInString = fromDateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh.mma"));
            }
        } else {
            fromInString = from;
        }

        String toInString;
        if (to.isEmpty()) {
            if (toDateAndTime == null) {
                toInString = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } else {
                toInString = toDateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh.mma"));
            }
        } else {
            toInString = to;
        }

        return "[E]" + super.toString() + " (from: " + fromInString + " to: " + toInString + ")";
    }

    @Override
    public String saveString() {
        String saveFromInString;
        if (from.isEmpty()) {
            if (fromDateAndTime == null) {
                saveFromInString = fromDate.toString();
            } else {
                saveFromInString = fromDateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            }
        } else {
            saveFromInString = from;
        }

        String saveToInString;
        if (to.isEmpty()) {
            if (toDateAndTime == null) {
                saveToInString = toDate.toString();
            } else {
                saveToInString = toDateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            }
        } else {
            saveToInString = to;
        }

        return "E | " + super.saveString() + " | " + description + " | " + saveFromInString + " | " + saveToInString;
    }
}
