package yapper.tasks;

import yapper.io.StringStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    // Additional Attributes
    protected String startDate;
    protected String endDate;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    // Constructor
    public Event(String taskDesc, String startDate, String endDate) {
        super(taskDesc);
        initializeStartDateTime(startDate);
        initializeEndDateTime(endDate);
    }

    public Event(String taskDesc, boolean isDone, String startDate, String endDate) {
        super(taskDesc);
        this.isDone = isDone;
        initializeStartDateTime(startDate);
        initializeEndDateTime(endDate);
    }

    private void initializeStartDateTime(String startDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm");
        try {
            this.startDateTime = LocalDateTime.parse(startDateTime, inputFormatter);
        } catch (DateTimeParseException e) {
            this.startDate = startDateTime;
        }
    }

    private void initializeEndDateTime(String endDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm");
        try {
            this.endDateTime = LocalDateTime.parse(endDateTime, inputFormatter);
        } catch (DateTimeParseException e) {
            this.endDate = endDateTime;
        }
    }

    // Task Print Operations for Adding/Removing Data
    @Override
    public String taskToDisplay() {
        return "[" + StringStorage.EVENT_SYMBOL + "] "
                + super.taskToDisplay() + ", from " + startDate + " to " + endDate;
    }

    // Task Conversion Operations for Saving/Loading Data
    @Override
    public String taskToString() {
        return StringStorage.EVENT_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + startDate + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDate;
    }

}
