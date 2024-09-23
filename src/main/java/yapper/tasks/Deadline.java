package yapper.tasks;

import yapper.io.StringStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        return "[" + StringStorage.DEADLINE_SYMBOL + "] "
                + super.taskToDisplay() + ", by " + endDateString;
    }

    // Task Conversion Operations for Saving/Loading Data
    @Override
    public String taskToString() {
        return StringStorage.DEADLINE_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDateString;
    }
}
