package yapper.tasks;

import yapper.io.StringStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    // Additional Attributes
    protected String startDateString;
    protected String endDateString;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    // Constructor
    public Event(String taskDesc, String startDateString, String endDateString) {
        super(taskDesc);
        initializeStartDateTime(startDateString);
        initializeEndDateTime(endDateString);
    }

    public Event(String taskDesc, boolean isDone, String startDateString, String endDateString) {
        super(taskDesc);
        this.isDone = isDone;
        initializeStartDateTime(startDateString);
        initializeEndDateTime(endDateString);
    }

    private void initializeStartDateTime(String startDateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.startDateTime = LocalDateTime.parse(startDateString, inputFormatter);
        } catch (DateTimeParseException e) {
            inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                this.startDate = LocalDate.parse(startDateString, inputFormatter);
            } catch (DateTimeParseException ex) {
                this.startDateString = startDateString;
            }
        }
    }
    private void initializeEndDateTime(String endDateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.endDateTime = LocalDateTime.parse(endDateString, inputFormatter);
        } catch (DateTimeParseException e) {
            inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                this.endDate = LocalDate.parse(endDateString, inputFormatter);
            } catch (DateTimeParseException ex) {
                this.endDateString = endDateString;
            }
        }
    }

    // Task Print Operations for Adding/Removing Data
    @Override
    public String taskToDisplay() {
        return "[" + StringStorage.EVENT_SYMBOL + "] "
                + super.taskToDisplay() + ", from " + startDateString + " to " + endDateString;
    }

    // Task Conversion Operations for Saving/Loading Data
    @Override
    public String taskToString() {
        return StringStorage.EVENT_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + startDateString + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDateString;
    }

}
