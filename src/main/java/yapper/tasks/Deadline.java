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
    /**
     * The string representation of the end date input.
     */
    protected String endDateString;
    /**
     * The parsed end date as a LocalDate.
     */
    protected LocalDate endDate;
    /**
     * The parsed end date and time as a LocalDateTime.
     */
    protected LocalDateTime endDateTime;


    /**
     * Creates a new Deadline task with the given description and end date.
     *
     * @param taskDesc        The description of the task.
     * @param endDateString   The string representation of the task's end date.
     */
    public Deadline(String taskDesc, String endDateString) {
        super(taskDesc);
        initializeEndDateTime(endDateString);
    }
    /**
     * Creates a new Deadline task with the given description, completion status,
     * and end date.
     *
     * @param taskDesc        The description of the task.
     * @param isDone          The completion status of the task.
     * @param endDateString   The string representation of the task's end date.
     */
    public Deadline(String taskDesc, boolean isDone, String endDateString) {
        super(taskDesc);
        this.isDone = isDone;
        initializeEndDateTime(endDateString);
    }
    /**
     * Initializes the end date and time of the deadline by parsing the given
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
            try {
                this.endDate = LocalDate.parse(endDateString,
                        DateTimeFormatter.ofPattern(
                        DateAndTimeHandler.DATE_WITHOUT_TIME_INPUT));
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
     * @return a formatted string showing the Deadline task's status, description and end date.
     */
    @Override
    public String taskToDisplay() {
        String endDateAsString = DateAndTimeHandler.getDateTimeFromString(
                endDateString, endDate, endDateTime,
                DateAndTimeHandler.DATE_WITHOUT_TIME_TO_DISPLAY,
                DateAndTimeHandler.DATE_WITH_TIME_TO_DISPLAY);

        return "[" + StringStorage.SYMBOL_DEADLINE + "]"
                + super.taskToDisplay() + ", by " + endDateAsString;
    }
    /**
     * Converts the deadline task to a string format for writing to / reading from a file,
     * including the Deadline symbol and the end date.
     *
     * @return a formatted string representing the deadline task's status, description and end date.
     */
    @Override
    public String taskToString() {
        String endDateAsString = DateAndTimeHandler.getDateTimeFromString(
                endDateString, endDate, endDateTime,
                DateAndTimeHandler.DATE_WITHOUT_TIME_TO_STRING,
                DateAndTimeHandler.DATE_WITH_TIME_TO_STRING);

        return StringStorage.SYMBOL_DEADLINE + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDateAsString;
    }
}
