package yapper.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import yapper.io.DateAndTimeHandler;

import yapper.io.StringStorage;

/**
 * Deadline is a Task with an end date.
 */
public class Deadline extends Task {
    // Additional Attributes
    protected String endDateString;
    protected LocalDate endDate;
    protected LocalDateTime endDateTime;

    // Constructors
    public Deadline(String taskDesc, String endDateString) {
        super(taskDesc);
        initializeEndDateTime(endDateString);
    }
    public Deadline(String taskDesc, boolean isDone, String endDateString) {
        super(taskDesc);
        this.isDone = isDone;
        initializeEndDateTime(endDateString);
    }

    private void initializeEndDateTime(String endDateString) {
        try {
            this.endDateTime = LocalDateTime.parse(endDateString,
                    DateTimeFormatter.ofPattern(
                    DateAndTimeHandler.DATE_WITH_TIME_INPUT) );
            this.endDateString = "";
        } catch (DateTimeParseException e) {
            try {
                this.endDate = LocalDate.parse(endDateString,
                        DateTimeFormatter.ofPattern(
                        DateAndTimeHandler.DATE_WITHOUT_TIME_INPUT) );
                this.endDateString = "";
            } catch (DateTimeParseException ex) {
                this.endDateString = endDateString;
            }
        }
    }



   /**
     * Converts the deadline task to a string format for display,
     * including the Deadline symbol and the end date.
     *
     * @return a formatted string showing the Deadline task's
    * status, description and end date.
     */
    @Override
    public String taskToDisplay() {
        String endDateAsString = DateAndTimeHandler.getDateTimeFromString(
                endDateString, endDate, endDateTime,
                DateAndTimeHandler.DATE_WITHOUT_TIME_TO_DISPLAY,
                DateAndTimeHandler.DATE_WITH_TIME_TO_DISPLAY);

        return "[" + StringStorage.DEADLINE_SYMBOL + "] "
                + super.taskToDisplay() + ", by " + endDateAsString;
    }
    /**
     * Converts the deadline task to a string format for writing to / reading from a file,
     * including the Deadline symbol and the end date.
     *
     * @return a formatted string representing the deadline task's
     * status, description and end date.
     */
    @Override
    public String taskToString() {
        String endDateAsString = DateAndTimeHandler.getDateTimeFromString(
                endDateString, endDate, endDateTime,
                DateAndTimeHandler.DATE_WITHOUT_TIME_TO_STRING,
                DateAndTimeHandler.DATE_WITH_TIME_TO_STRING);

        return StringStorage.DEADLINE_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDateAsString;
    }
}
