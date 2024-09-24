package yapper.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yapper.io.DateAndTimeHandler;
import yapper.io.StringStorage;

/**
 * Event is a Task with both a start date and end date.
 */
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
        try {
            this.startDateTime = LocalDateTime.parse(startDateString,
                    DateTimeFormatter.ofPattern(
                    DateAndTimeHandler.DATE_WITH_TIME_INPUT) );
            this.startDateString = "";
        } catch (DateTimeParseException e) {
            try {
                this.startDate = LocalDate.parse(startDateString,
                        DateTimeFormatter.ofPattern(
                            DateAndTimeHandler.DATE_WITHOUT_TIME_INPUT) );
                this.startDateString = "";
            } catch (DateTimeParseException ex) {
                this.startDateString = startDateString;
            }
        }
    }
    private void initializeEndDateTime(String endDateString) {
        try {
            this.endDateTime = LocalDateTime.parse(endDateString,
                    DateTimeFormatter.ofPattern(
                    DateAndTimeHandler.DATE_WITH_TIME_INPUT) );
            this.endDateString = "";
        } catch (DateTimeParseException e) {
            this.endDateString = "";
            try {
                this.endDate = LocalDate.parse(endDateString,
                        DateTimeFormatter.ofPattern(
                        DateAndTimeHandler.DATE_WITHOUT_TIME_INPUT) );
            } catch (DateTimeParseException ex) {
                this.endDateString = endDateString;
            }
        }
    }


   /**
     * Converts the event task to a string format for display,
     * including the Event symbol, start date and an end date.
     *
     * @return a formatted string showing the Deadline task's
     * status, description, start date and end date.
     */
    @Override
    public String taskToDisplay() {
        String startDateAsString = DateAndTimeHandler.getDateTimeFromString(
                endDateString, endDate, endDateTime,
                DateAndTimeHandler.DATE_WITHOUT_TIME_TO_DISPLAY,
                DateAndTimeHandler.DATE_WITH_TIME_TO_DISPLAY);
        String endDateAsString = DateAndTimeHandler.getDateTimeFromString(
                endDateString, endDate, endDateTime,
                DateAndTimeHandler.DATE_WITHOUT_TIME_TO_DISPLAY,
                DateAndTimeHandler.DATE_WITH_TIME_TO_DISPLAY);

        return "[" + StringStorage.EVENT_SYMBOL + "] "
                + super.taskToDisplay() + ", from " + startDateAsString + " to " + endDateAsString;
    }

    // Task Conversion Operations for Saving/Loading Data
    /**
     * Converts the event task to a string format for writing to / reading from a file,
     * including the Event symbol, start date and an end date.
     *
     * @return a formatted string representing the deadline task's
     * status, description, start date and end date.
     */
    @Override
    public String taskToString() {
        String startDateAsString = DateAndTimeHandler.getDateTimeFromString(
                endDateString, endDate, endDateTime,
                DateAndTimeHandler.DATE_WITHOUT_TIME_TO_STRING,
                DateAndTimeHandler.DATE_WITH_TIME_TO_STRING);
        String endDateAsString = DateAndTimeHandler.getDateTimeFromString(
                endDateString, endDate, endDateTime,
                DateAndTimeHandler.DATE_WITHOUT_TIME_TO_STRING,
                DateAndTimeHandler.DATE_WITH_TIME_TO_STRING);

        return StringStorage.EVENT_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + startDateAsString + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDateAsString;
    }

}
