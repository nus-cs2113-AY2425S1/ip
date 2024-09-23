package yapper.tasks;

import yapper.io.StringStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    // Additional Attributes
    protected String endDate;
    protected LocalDateTime endDateTime;

    // Constructors
    public Deadline(String taskDesc, String endDate) {
        super(taskDesc);
        initializeEndDateTime(endDate);
    }

    public Deadline(String taskDesc, boolean isDone, String endDate) {
        super(taskDesc);
        this.isDone = isDone;
        initializeEndDateTime(endDate);
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
        return "[" + StringStorage.DEADLINE_SYMBOL + "] "
                + super.taskToDisplay() + ", by " + endDate;
    }

    // Task Conversion Operations for Saving/Loading Data
    @Override
    public String taskToString() {
        return StringStorage.DEADLINE_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDate;
    }
}
