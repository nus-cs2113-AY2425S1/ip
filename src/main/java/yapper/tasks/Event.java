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
    /**
     * The string representation of the start date, used if the start date
     * cannot be parsed into a valid {@code LocalDateTime} or {@code LocalDate}.
     */
    protected String startDateString;
    /**
     * The string representation of the end date, used if the end date
     * cannot be parsed into a valid {@code LocalDateTime} or {@code LocalDate}.
     */
    protected String endDateString;
    /**
     * The start date of the event as a {@code LocalDate}, if no time is specified.
     */
    protected LocalDate startDate;
    /**
     * The end date of the event as a {@code LocalDate}, if no time is specified.
     */
    protected LocalDate endDate;
    /**
     * The start date and time of the event as a {@code LocalDateTime}, if both
     * date and time are provided.
     */
    protected LocalDateTime startDateTime;
    /**
     * The end date and time of the event as a {@code LocalDateTime}, if both
     * date and time are provided.
     */
    protected LocalDateTime endDateTime;




    /**
     * Creates an event task with the given description, start date, and end date.
     * The dates can either be in date-only or date-time format.
     *
     * @param taskDesc         the description of the event
     * @param startDateString   the start date of the event as a string
     * @param endDateString     the end date of the event as a string
     */
    public Event(String taskDesc, String startDateString, String endDateString) {
        super(taskDesc);
        initializeStartDateTime(startDateString);
        initializeEndDateTime(endDateString);
    }
    /**
     * Creates an event task with the given description, completion status, start date, and end date.
     * The dates can either be in date-only or date-time format.
     *
     * @param taskDesc         the description of the event
     * @param isDone           whether the event is marked as done
     * @param startDateString   the start date of the event as a string
     * @param endDateString     the end date of the event as a string
     */
    public Event(String taskDesc, boolean isDone, String startDateString, String endDateString) {
        super(taskDesc);
        this.isDone = isDone;
        initializeStartDateTime(startDateString);
        initializeEndDateTime(endDateString);
    }
    /**
     * Initializes the start date and time of the event by parsing the given
     * string. If the string cannot be parsed into a {@code LocalDateTime},
     * it will attempt to parse it as a {@code LocalDate}. If both attempts fail,
     * the string will be stored as-is.
     *
     * @param startDateString the start date string to be parsed
     */
    private void initializeStartDateTime(String startDateString) {
        try {
            this.startDateTime = LocalDateTime.parse(startDateString,
                    DateTimeFormatter.ofPattern(
                    DateAndTimeHandler.DATE_WITH_TIME_INPUT));
            this.startDateString = "";
        } catch (DateTimeParseException e) {
            try {
                this.startDate = LocalDate.parse(startDateString,
                        DateTimeFormatter.ofPattern(
                            DateAndTimeHandler.DATE_WITHOUT_TIME_INPUT));
                this.startDateString = "";
            } catch (DateTimeParseException ex) {
                this.startDateString = startDateString;
            }
        }
    }
    /**
     * Initializes the end date and time of the event by parsing the given
     * string. If the string cannot be parsed into a {@code LocalDateTime},
     * it will attempt to parse it as a {@code LocalDate}. If both attempts fail,
     * the string will be stored as-is.
     *
     * @param endDateString the end date string to be parsed
     */
    private void initializeEndDateTime(String endDateString) {
        try {
            this.endDateTime = LocalDateTime.parse(endDateString,
                    DateTimeFormatter.ofPattern(
                    DateAndTimeHandler.DATE_WITH_TIME_INPUT));
            this.endDateString = "";
        } catch (DateTimeParseException e) {
            this.endDateString = "";
            try {
                this.endDate = LocalDate.parse(endDateString,
                        DateTimeFormatter.ofPattern(
                        DateAndTimeHandler.DATE_WITHOUT_TIME_INPUT));
            } catch (DateTimeParseException ex) {
                this.endDateString = endDateString;
            }
        }
    }


    /**
     * Converts the event task to a string format for display,
     * including the Event symbol, start date and an end date.
     *
     * @return a formatted string showing the Deadline task's status, description, start date and end date.
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

        return "[" + StringStorage.SYMBOL_EVENT + "]"
                + super.taskToDisplay() + ", from " + startDateAsString + " to " + endDateAsString;
    }
    /**
     * Converts the event task to a string format for writing to / reading from a file,
     * including the Event symbol, start date and an end date.
     *
     * @return a formatted string representing the deadline task's status, description, start date and end date.
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

        return StringStorage.SYMBOL_EVENT + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + startDateAsString + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDateAsString;
    }
}
