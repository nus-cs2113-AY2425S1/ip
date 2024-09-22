package erika.task;

import erika.settings.Settings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected String start = null;
    protected LocalDate startDate = null;
    protected LocalDateTime startDateTime = null;
    protected String end = null;
    protected LocalDate endDate = null;
    protected LocalDateTime endDateTime = null;

    public Event(String description, String start, String end){
        super(description);
        getStart(start);
        getEnd(end);
    }

    private void getStart(String start) {
        try {
            this.startDateTime = LocalDateTime.parse(start, Settings.DATE_TIME_IN_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.startDate = LocalDate.parse(start, Settings.DATE_IN_FORMATTER);
            } catch (DateTimeParseException e1) {
                this.start = start;
            }
        }
    }

    private void getEnd(String end) {
        try {
            this.endDateTime = LocalDateTime.parse(end, Settings.DATE_TIME_IN_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.endDate = LocalDate.parse(end, Settings.DATE_IN_FORMATTER);
            } catch (DateTimeParseException e1) {
                this.end = end;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", isDone ? "X":" ", description,
                (startDateTime != null ? startDateTime.format(Settings.DATE_TIME_OUT_FORMATTER) :
                        startDate != null ? startDate.format(Settings.DATE_OUT_FORMATTER) : start),
                            endDateTime != null ? endDateTime.format(Settings.DATE_TIME_OUT_FORMATTER) :
                                endDate != null ? endDate.format(Settings.DATE_OUT_FORMATTER) : end);
    }

    @Override
    public String generateFileLine() {
        return String.format("E,%s,%s,%s,%s\n", isDone ? "1" : "0", description,
                (startDateTime != null ? startDateTime.format(Settings.DATE_TIME_IN_FORMATTER) :
                        startDate != null ? startDate.format(Settings.DATE_IN_FORMATTER) : start),
                            endDateTime != null ? endDateTime.format(Settings.DATE_TIME_IN_FORMATTER) :
                                endDate != null ? endDate.format(Settings.DATE_IN_FORMATTER) : end);
    }
}
